package datos;

import datos.interfaces.CrudSimpleInterface;
import entidades.Distrito;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class DistritoDAO implements CrudSimpleInterface<Distrito> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public DistritoDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Distrito> listar(String texto) {
        List<Distrito> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select * from distrito where nombre like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Distrito(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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

    public List<Distrito> listarNombres() {
        List<Distrito> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select id_distrito, nombre from distrito order by nombre asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Distrito(rs.getInt(1), rs.getString(2)));
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
    public boolean insertar(Distrito obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("insert into distrito(nombre, activo)\n"
                    + "values(?,?)");
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.isActivo() ? 1 : 0);

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
    public boolean actualizar(Distrito obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE distrito SET nombre = ?, activo = ? WHERE id_distrito = ?");
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.isActivo() ? 1 : 0);
            ps.setInt(3, obj.getIdDistrito());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_distrito) from distrito");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_distrito)");
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
            ps = CON.conectar().prepareStatement("select nombre from distrito where nombre=?");
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
