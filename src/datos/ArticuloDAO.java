package datos;

import datos.interfaces.CrudSimpleInterface;
import entidades.Articulo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class ArticuloDAO implements CrudSimpleInterface<Articulo> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public ArticuloDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Articulo> listar(String texto) {
        List<Articulo> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT a.id_articulo, a.id_ffarmaceutica, a.codigo, a.descripcion,\n"
                    + "f.descripcion AS forma_farmaceutica, a.activo FROM articulo a INNER JOIN forma_farmaceutica f\n"
                    + "ON a.id_ffarmaceutica = f.id_ffarmaceutica WHERE a.descripcion LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Articulo(rs.getInt("id_articulo"),
                        rs.getInt("id_ffarmaceutica"), rs.getString("descripcion"),
                        rs.getString("forma_farmaceutica"), rs.getString("codigo"),
                        rs.getBoolean("activo")));
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

    public List<Articulo> listar(String texto, String campo) {
        List<Articulo> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT a.id_articulo, a.id_ffarmaceutica, a.codigo, a.descripcion,\n"
                    + "f.descripcion AS forma_farmaceutica, a.activo FROM articulo a INNER JOIN forma_farmaceutica f\n"
                    + "ON a.id_ffarmaceutica = f.id_ffarmaceutica WHERE "+campo+" LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Articulo(rs.getInt("id_articulo"),
                        rs.getInt("id_ffarmaceutica"), rs.getString("descripcion"),
                        rs.getString("forma_farmaceutica"), rs.getString("codigo"),
                        rs.getBoolean("activo")));
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

    public List<Articulo> seleccionarArticulo() {
        List<Articulo> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select id_articulo, descripcion, codigo from articulo order by descripcion asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Articulo(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
    public boolean insertar(Articulo obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO articulo (id_ffarmaceutica, descripcion, codigo, activo) VALUES \n"
                    + "(?,?,?,?);");
            ps.setInt(1, obj.getIdFfarmaceutica());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getCodigo());
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
    public boolean actualizar(Articulo obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE articulo SET\n"
                    + "id_ffarmaceutica = ?,\n"
                    + "descripcion = ?,\n"
                    + "codigo = ?,\n"
                    + "activo = ?\n"
                    + "WHERE id_articulo = ?;");
            ps.setInt(1, obj.getIdFfarmaceutica());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getCodigo());
            ps.setInt(4, obj.isActivo() ? 1 : 0);

            ps.setInt(5, obj.getIdArticulo());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_articulo) from articulo");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_articulo)");
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
