package datos;

import datos.interfaces.CrudSimpleInterface;
import entidades.Lote;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class LoteDAO implements CrudSimpleInterface<Lote> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public LoteDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Lote> listar(String texto) {
        List<Lote> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT l.id_lote,\n"
                    + "l.id_articulo,\n"
                    + "l.id_laboratorio,\n"
                    + "a.codigo AS codigo,\n"
                    + "a.descripcion AS articulo,\n"
                    + "l.codigo AS lote,\n"
                    + "l.fecha_fabricacion,\n"
                    + "l.fecha_expiracion,\n"
                    + "l.stock,\n"
                    + "l.precio_unitario,\n"
                    + "lab.nombre as laboratorio,\n"
                    + "l.activo\n"
                    + "FROM lote l \n"
                    + "INNER JOIN articulo a\n"
                    + "ON l.id_articulo = a.id_articulo\n"
                    + "INNER JOIN laboratorio lab\n"
                    + "ON l.id_laboratorio = lab.id_laboratorio\n"
                    + "WHERE a.codigo LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Lote(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getInt(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getBoolean(12)
                ));
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

    public List<Lote> listar(String texto, String campo) {
        List<Lote> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT l.id_lote,\n"
                    + "l.id_articulo,\n"
                    + "l.id_laboratorio,\n"
                    + "a.codigo AS codigo,\n"
                    + "a.descripcion AS articulo,\n"
                    + "l.codigo AS lote,\n"
                    + "l.fecha_fabricacion,\n"
                    + "l.fecha_expiracion,\n"
                    + "l.stock,\n"
                    + "l.precio_unitario,\n"
                    + "lab.nombre as laboratorio,\n"
                    + "l.activo\n"
                    + "FROM lote l \n"
                    + "INNER JOIN articulo a\n"
                    + "ON l.id_articulo = a.id_articulo\n"
                    + "INNER JOIN laboratorio lab\n"
                    + "ON l.id_laboratorio = lab.id_laboratorio\n"
                    + "WHERE " + campo + " LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Lote(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getInt(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getBoolean(12)
                ));
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
    public boolean insertar(Lote obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO lote \n"
                    + "(id_articulo,\n"
                    + "id_laboratorio,\n"
                    + "codigo,\n"
                    + "fecha_fabricacion,\n"
                    + "fecha_expiracion,\n"
                    + "stock,\n"
                    + "precio_unitario,\n"
                    + "activo) \n"
                    + "VALUES \n"
                    + "(?,?,?,?,?,?,?,?)");
            ps.setInt(1, obj.getIdArticulo());
            ps.setInt(2, obj.getIdLaboratorio());
            ps.setString(3, obj.getLoteCodigo());
            ps.setDate(4, obj.getFechaFabricacion());
            ps.setDate(5, obj.getFechaExpiracion());
            ps.setInt(6, obj.getStock());
            ps.setFloat(7, obj.getPrecioUnitario());
            ps.setInt(8, obj.isActivo() ? 1 : 0);

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

    @Override
    public boolean actualizar(Lote obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE lote set \n"
                    + "id_articulo=?,\n"
                    + "id_laboratorio=?,\n"
                    + "codigo=?,\n"
                    + "fecha_fabricacion=?,\n"
                    + "fecha_expiracion=?,\n"
                    + "stock=?,\n"
                    + "precio_unitario=?,\n"
                    + "activo=? \n"
                    + "WHERE id_lote=?");
            ps.setInt(1, obj.getIdArticulo());
            ps.setInt(2, obj.getIdLaboratorio());
            ps.setString(3, obj.getLoteCodigo());
            ps.setDate(4, obj.getFechaFabricacion());
            ps.setDate(5, obj.getFechaExpiracion());
            ps.setInt(6, obj.getStock());
            ps.setFloat(7, obj.getPrecioUnitario());
            ps.setInt(8, obj.isActivo() ? 1 : 0);

            ps.setInt(9, obj.getIdLote());
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

    public List<Lote> seleccionarLote(int idArticulo) {
        List<Lote> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT l.id_lote,\n"
                    + "l.id_articulo,\n"
                    + "l.codigo AS lote,\n"
                    + "l.precio_unitario\n"
                    + "FROM lote l \n"
                    + "-- INNER JOIN articulo a\n"
                    + "-- ON l.id_articulo = a.id_articulo\n"
                    + "WHERE l.id_articulo = ?");
            ps.setInt(1, idArticulo);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Lote(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4))
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
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_lote) from lote");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_lote)");
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
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        respuesta = false;
        return respuesta;
    }

}
