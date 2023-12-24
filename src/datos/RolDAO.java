package datos;

import basedatos.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Rol;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//Se debe sobreescribir metodos abstractos de la implementacion
public class RolDAO implements CrudSimpleInterface<Rol> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public RolDAO() {
        CON = Conexion.getInstancia();

    }

    @Override
    public List<Rol> listar(String texto) {
        /*Este metodo/clase usa patron singleton
        Investigar patron singleton*/
        List<Rol> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select * from rol where nombre like ?");
            ps.setString(1, texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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

    public List<Rol> seleccionarRoles() {
        List<Rol> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select id_rol, nombre from rol order by nombre asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1), rs.getString(2)));
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
    public boolean insertar(Rol obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("insert into rol(nombre, activo) values(?,1)");
            ps.setString(1, obj.getNombre());
            if (ps.executeUpdate() > 0) {
                /*Para INSERT o UPDATE no se puede utilizar execute query
                se debe utilizar executeUpdate.
                
                Execute query devuelve resultSet. executeUpdate devuelve un entero.
                Si se ha insertado un registro devuelve 1, si no, devuelve 0
                 */
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
    public boolean actualizar(Rol obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE rol\n"
                    + "SET nombre = ?\n"
                    + "WHERE id_rol = ?");
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.getIdRol());
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
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE rol\n"
                    + "SET activo = 0\n"
                    + "WHERE id_rol = ?");
            ps.setInt(1, id);
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
    public boolean activar(int id) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE rol\n"
                    + "SET activo = 1\n"
                    + "WHERE id_rol = ?");
            ps.setInt(1, id);
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
    public int total() {
        int totalRegistros = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_rol) from rol");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_rol)");
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
            ps = CON.conectar().prepareStatement("select nombre from rol where nombre=?");
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
