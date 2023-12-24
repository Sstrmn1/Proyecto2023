package datos;

import datos.interfaces.CrudSimpleInterface;
import entidades.Sucursal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class SucursalDAO implements CrudSimpleInterface<Sucursal> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public SucursalDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Sucursal> listar(String texto) {
        List<Sucursal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT \n"
                    + "    s.id_sucursal AS idSucursal,\n"
                    + "    s.id_distrito AS idDistrito,\n"
                    + "    s.id_cliente AS idCliente,\n"
                    + "    d.nombre AS nombreDistrito,\n"
                    + "    c.nombre AS nombreCliente,\n"
                    + "    s.direccion,\n"
                    + "    s.activo\n"
                    + "FROM sucursal s\n"
                    + "INNER JOIN distrito d ON s.id_distrito = d.id_distrito\n"
                    + "INNER JOIN cliente c ON s.id_cliente = c.id_cliente where c.nombre like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Sucursal(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7)));
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

    public List<Sucursal> listar(String texto, String campo) {
        List<Sucursal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT \n"
                    + "    s.id_sucursal AS idSucursal,\n"
                    + "    s.id_distrito AS idDistrito,\n"
                    + "    s.id_cliente AS idCliente,\n"
                    + "    d.nombre AS nombreDistrito,\n"
                    + "    c.nombre AS nombreCliente,\n"
                    + "    s.direccion,\n"
                    + "    s.activo\n"
                    + "FROM sucursal s\n"
                    + "INNER JOIN distrito d ON s.id_distrito = d.id_distrito\n"
                    + "INNER JOIN cliente c ON s.id_cliente = c.id_cliente where " + campo + " like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Sucursal(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7)));
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

    public List<Sucursal> listarDireccion(int idCliente) {
        List<Sucursal> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT\n"
                    + "s.id_sucursal,\n"
                    + "d.nombre AS distrito,\n"
                    + "s.direccion\n"
                    + "FROM sucursal s\n"
                    + "INNER JOIN distrito d\n"
                    + "ON s.id_distrito = d.id_distrito\n"
                    + "INNER JOIN cliente\n"
                    + "ON s.id_cliente = cliente.id_cliente\n"
                    + "WHERE s.id_cliente = ?");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Sucursal(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
    public boolean insertar(Sucursal obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("insert into sucursal(id_distrito,id_cliente,direccion, "
                    + "activo)\n"
                    + "values(?,?,?,?)");
            ps.setInt(1, obj.getIdDistrito());
            ps.setInt(2, obj.getIdCliente());
            ps.setString(3, obj.getDireccion());
            ps.setInt(4, obj.isActivo() ? 1 : 0);

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
    public boolean actualizar(Sucursal obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE sucursal SET id_distrito = ?, id_cliente = ?, "
                    + "direccion = ?, activo = ?"
                    + " WHERE id_sucursal = ?");
            ps.setInt(1, obj.getIdDistrito());
            ps.setInt(2, obj.getIdCliente());
            ps.setString(3, obj.getDireccion());
            ps.setInt(4, obj.isActivo() ? 1 : 0);

            ps.setInt(5, obj.getIdSucursal());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_sucursal) from sucursal");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_sucursal)");
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
//        try {
//            ps = CON.conectar().prepareStatement("select nombre from cliente where nombre=?");
//            ps.setString(1, texto);
//            rs = ps.executeQuery();
//            rs.last();
//            if (rs.getRow() > 0) {
//                respuesta = true;
//            }
//            ps.close();
//            rs.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        } finally {
//            ps = null;
//            rs = null;
//            CON.desconectar();
//        }
        return respuesta;
    }

}
