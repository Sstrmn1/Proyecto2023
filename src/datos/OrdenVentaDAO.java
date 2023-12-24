package datos;

import datos.interfaces.CrudSimpleInterface;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import basedatos.Conexion;
import entidades.OrdenVenta;
import entidades.Transaccion;
import java.sql.Statement;

public class OrdenVentaDAO implements CrudSimpleInterface<OrdenVenta> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public OrdenVentaDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<OrdenVenta> listar(String texto) {
        List<OrdenVenta> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select o.id_orden, \n"
                    + "c.nombre as cliente, \n"
                    + "d.nombre as distrito, \n"
                    + "o.fecha_hora, \n"
                    + "r.nombre as rol, \n"
                    + "u.id_usuario as usr, \n"
                    + "o.importe_total as importe\n"
                    + "from orden_de_venta o \n"
                    + "inner join sucursal s on o.id_sucursal = s.id_sucursal\n"
                    + "inner join distrito d on s.id_distrito = d.id_distrito\n"
                    + "inner join cliente c on s.id_cliente = c.id_cliente\n"
                    + "inner join usuario u on o.id_usuario = u.id_usuario\n"
                    + "inner join rol r on u.id_rol = r.id_rol\n"
                    + "where c.nombre like ?\n"
                    + "order by o.id_orden asc;");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int idOrden = rs.getInt(1);
                String clienteNombre = rs.getString(2);
                String distritoNombre= rs.getString(3);
                Timestamp fechaHora = rs.getTimestamp(4);
                String rolNombre = rs.getString(5);
                int idUsuario = rs.getInt(6);
                float importe = rs.getFloat(7);
                registros.add(new OrdenVenta(idOrden, idUsuario, fechaHora, importe, clienteNombre, distritoNombre, rolNombre));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(OrdenVenta obj) {
        respuesta = false;
        boolean ordenOk = false;
        boolean transaccionesOk = false;
        boolean loteOk = false;
        int idGenerado = -1; // Variable para almacenar la ID autogenerada

        try {
            if (CON.cadena.isClosed()) {
                CON.conectar();
            }
//            System.out.println(CON.cadena.isClosed());
            if (CON.getMetadata(CON.cadena).supportsSavepoints()) {
                System.out.println("Savepoint supported by the driver and database");
            }
            //Apagar autocommit
//            CON.cadena.setAutoCommit(false);

            // Establecer el savepoint antes de comenzar las operaciones
            CON.cadena.setSavepoint();

            // 1. Insertar en la tabla orden_de_venta
            String consultaOrdenVenta = "INSERT INTO orden_de_venta "
                    + "(numero_orden, id_sucursal, id_usuario, fecha_hora, importe_total) "
                    + "VALUES (?, ?, ?, NOW(), 0)";
            ps = CON.cadena.prepareStatement(consultaOrdenVenta, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getNumeroOrden());
            ps.setInt(2, obj.getIdSucursal());
            ps.setInt(3, obj.getIdUsuario());

            if (ps.executeUpdate() > 0) {
                ordenOk = true;
                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1);

                    // 2. Insertar transacciones en la tabla transaccion
                    String consultaTransaccion = "INSERT INTO transaccion "
                            + "(id_lote, id_orden, cantidad, fecha) "
                            + "VALUES (?, ?, ?, NOW())";
                    ps = CON.cadena.prepareStatement(consultaTransaccion);

                    for (Transaccion transaccion : obj.getTransacciones()) {
                        ps.setInt(1, transaccion.getIdLote());
                        ps.setInt(2, idGenerado);
                        ps.setInt(3, transaccion.getCantidad());
                        ps.addBatch();
                    }
                    int[] result = ps.executeBatch();

                    // Verificar que todas las transacciones se hayan insertado correctamente
                    for (int res : result) {
                        if (res <= 0) {
                            transaccionesOk = false;
                            break;
                        } else {
                            transaccionesOk = true;
                        }
                    }

                    // 3. Actualizar el importe total en la tabla orden_de_venta
                    String consultaUpdateImporte = "UPDATE orden_de_venta AS o "
                            + "SET o.importe_total = (SELECT SUM(t.cantidad * l.precio_unitario) "
                            + "FROM transaccion AS t "
                            + "INNER JOIN lote AS l ON t.id_lote = l.id_lote "
                            + "WHERE t.id_orden = o.id_orden) "
                            + "WHERE o.id_orden = ?";
                    ps = CON.cadena.prepareStatement(consultaUpdateImporte);
                    ps.setInt(1, idGenerado);
                    ps.executeUpdate();

                    // 4. Restar las cantidades de transacciones a los stocks en la tabla lote
                    String consultaUpdateStockBase = "UPDATE lote AS l "
                            + "JOIN transaccion AS t ON l.id_lote = t.id_lote "
                            + "SET l.stock = l.stock - t.cantidad "
                            + "WHERE t.id_orden = ? AND l.id_lote = ?";
                    ps = CON.cadena.prepareStatement(consultaUpdateStockBase);

                    for (Transaccion transaccion : obj.getTransacciones()) {
                        // Obtener el id de lote de la transacción
                        int idLote = transaccion.getIdLote();
                        int cantidad = transaccion.getCantidad();

                        // Obtener el stock actual del lote
                        int stockActual = obtenerStockLote(idLote);

                        // Verificar si la cantidad de la transacción es mayor al stock actual
                        if (cantidad > stockActual) {
                            // La cantidad es mayor al stock, realizar un rollback y establecer respuesta como falsa
//                            CON.rollbackToSavepoint();
                            loteOk = false;
                            break;  // Salir del bucle
                        } else {
                            loteOk = true;
                        }

                        // Configurar los parámetros
                        ps.setInt(1, idGenerado);
                        ps.setInt(2, idLote);

                        // Agregar la operación al lote
                        ps.addBatch();
                    }

                    // Ejecutar todas las operaciones en lote
                    int[] resultLote = ps.executeBatch();

                    // Verificar que todas las operaciones en lote de la tabla lote se hayan ejecutado correctamente
                    for (int res : resultLote) {
                        if (res <= 0) {
                            loteOk = false;
                            break;
                        } else {
                            loteOk = true;
                        }
                    }
//                    ps.close();
                }
                generatedKeys.close();
            }

            // Verificar que todas las operaciones se ejecutaron correctamente
            if (ordenOk && transaccionesOk && loteOk) {
                respuesta = true;
            }
            // Verificar condiciones y establecer respuesta
            if (!respuesta) {
                // Algo salió mal, deshacer hasta el savepoint
                CON.rollbackToSavepoint(CON.getSavepoint());

            }
            //Commit
            CON.commit();
            // Liberar el savepoint
            CON.releaseSavepoint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {

            try {
                CON.cadena.setAutoCommit(true);
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CON.desconectar();
        }
        return respuesta;
    }

    // Obtener el stock actual de un lote específico
    private int obtenerStockLote(int idLote) throws SQLException {
        int stockActual = 0;
        String consultaStock = "SELECT stock FROM lote WHERE id_lote = ?";
        try (PreparedStatement psStock = CON.conectar().prepareStatement(consultaStock)) {
            psStock.setInt(1, idLote);
            try (ResultSet rsStock = psStock.executeQuery()) {
                if (rsStock.next()) {
                    stockActual = rsStock.getInt("stock");
                }
            }
        }
        return stockActual;
    }

    @Override
    public boolean actualizar(OrdenVenta obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE orden_de_venta SET\n"
                    + "fecha_hora = NOW(),\n"
                    + "importe_total = ?\n"
                    + "WHERE id_orden = ?;");
            ps.setFloat(1, obj.getImporteTotal());

            ps.setInt(2, obj.getIdOrden());
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return respuesta;
    }

    public List<Transaccion> listarTransacciones(int idOrden) {
        List<Transaccion> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT \n"
                    + "    a.codigo AS 'Codigo Articulo',\n"
                    + "    a.descripcion AS 'Descripcion Articulo',\n"
                    + "    l.codigo AS 'Codigo Lote',\n"
                    + "    t.cantidad AS 'Cantidad',\n"
                    + "    t.id_orden AS 'ID Orden',\n"
                    + "    (t.cantidad * l.precio_unitario) AS 'Importe'\n"
                    + "FROM\n"
                    + "    bd_genolab.transaccion t\n"
                    + "    JOIN bd_genolab.lote l ON t.id_lote = l.id_lote\n"
                    + "    JOIN bd_genolab.articulo a ON l.id_articulo = a.id_articulo\n"
                    + "    JOIN bd_genolab.orden_de_venta o ON t.id_orden = o.id_orden\n"
                    + "    where t.id_orden= ? ");
            ps.setInt(1, idOrden);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Transaccion(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(5),
                        rs.getInt(4),
                        rs.getFloat("Importe"))
                );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean desactivar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean activar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        respuesta = false;
        return respuesta;
    }

}
