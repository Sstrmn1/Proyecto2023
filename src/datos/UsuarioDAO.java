package datos;

import datos.interfaces.CrudSimpleInterface;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class UsuarioDAO implements CrudSimpleInterface<Usuario> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public UsuarioDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Usuario> listar(String texto) {
        List<Usuario> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT u.id_usuario, u.id_rol, r.nombre AS rol_nombre, u.password, u.nombre, u.apellido, \n"
                    + "u.ci, u.email,  u.fecha_nacimiento, u.foto, u .activo\n"
                    + "FROM usuario u INNER JOIN rol r ON u.id_rol=r.id_rol WHERE u.nombre LIKE ? ORDER BY u.id_usuario DESC");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getDate(9), rs.getString(10), rs.getBoolean(11)));
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
    
    public List<Usuario> listar(String texto, String campo) {
        List<Usuario> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT u.id_usuario, u.id_rol, r.nombre AS rol_nombre, u.password, u.nombre, u.apellido, \n"
                    + "u.ci, u.email,  u.fecha_nacimiento, u.foto, u .activo\n"
                    + "FROM usuario u INNER JOIN rol r ON u.id_rol=r.id_rol WHERE "+campo+" LIKE ? ORDER BY u.id_usuario DESC");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getDate(9), rs.getString(10), rs.getBoolean(11)));
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

//    public List<Usuario> listarNombres() {
//        List<Usuario> registros = new ArrayList();
//        try {
//            ps = CON.conectar().prepareStatement("select id_usuario, nombre from usuario order by nombre asc");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                registros.add(new Usuario(rs.getInt(1), rs.getString(2)));
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
//        return registros;
//    }
    @Override
    public boolean insertar(Usuario obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("insert into usuario(id_rol, password, nombre, "
                    + "apellido, ci, email, fecha_nacimiento, foto, activo)\n"
                    + "values(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, obj.getIdRol());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getNombre());
            ps.setString(4, obj.getApellido());
            ps.setString(5, obj.getCi());
            ps.setString(6, obj.getEmail());
            ps.setDate(7, obj.getFechaNac());
            ps.setString(8, obj.getFoto());
            ps.setInt(9, obj.isActivo() ? 1 : 0);

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
    public boolean actualizar(Usuario obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE usuario SET id_rol = ?, password = ?, nombre = ?,"
                    + "apellido = ?, ci = ?, email = ?, fecha_nacimiento = ?, foto = ?, activo = ?"
                    + " WHERE id_usuario = ?");
            ps.setInt(1, obj.getIdRol());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getNombre());
            ps.setString(4, obj.getApellido());
            ps.setString(5, obj.getCi());
            ps.setString(6, obj.getEmail());
            ps.setDate(7, obj.getFechaNac());
            ps.setString(8, obj.getFoto());
            ps.setInt(9, obj.isActivo() ? 1 : 0);

            ps.setInt(10, obj.getIdUsuario());
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

    public Usuario login(String email, String password) {
        Usuario usu = null;
        try {
            ps = CON.conectar().prepareStatement("SELECT "
                    + "u.id_usuario, "
                    + "u.id_rol, "
                    + "r.nombre AS rol_nombre, "
                    + "u.nombre, "
                    + "u.apellido, "
                    + "u.ci, "
                    + "u.email, "
                    + "u.activo "
                    + "FROM usuario u "
                    + "INNER JOIN rol r ON u.id_rol=r.id_rol "
                    + "WHERE u.email=? AND u.password=? "
            );
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.first()) {
                usu = new Usuario(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8));
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
        return usu;
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_usuario) from usuario");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_usuario)");
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
        try {
            ps = CON.conectar().prepareStatement("select nombre from usuario where nombre=?");
            ps.setString(1, texto);
            rs = ps.executeQuery();
            rs.last();
            if (rs.getRow() > 0) {
                respuesta = true;
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
        return respuesta;
    }

}
