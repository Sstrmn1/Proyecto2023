package datos;

import datos.interfaces.CrudSimpleInterface;
import entidades.FormaFarmaceutica;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class FormaFarmaceuticaDAO implements CrudSimpleInterface<FormaFarmaceutica> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public FormaFarmaceuticaDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<FormaFarmaceutica> listar(String texto) {
        List<FormaFarmaceutica> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select * from forma_farmaceutica where descripcion like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new FormaFarmaceutica(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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

    public List<FormaFarmaceutica> seleccionarFormaFarmaceutica() {
        List<FormaFarmaceutica> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select id_ffarmaceutica, descripcion from forma_farmaceutica order by descripcion asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new FormaFarmaceutica(rs.getInt(1), rs.getString(2)));
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
    public boolean insertar(FormaFarmaceutica obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("insert into forma_farmaceutica(descripcion, activo)\n"
                    + "values(?,?)");
            ps.setString(1, obj.getDescripcion());
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
    public boolean actualizar(FormaFarmaceutica obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE forma_farmaceutica SET descripcion = ?, activo = ? WHERE id_ffarmaceutica = ?");
            ps.setString(1, obj.getDescripcion());
            ps.setInt(2, obj.isActivo() ? 1 : 0);
            ps.setInt(3, obj.getIdFFarmaceutica());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_ffarmaceutica) from forma_farmaceutica");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_ffarmaceutica)");
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
            ps = CON.conectar().prepareStatement("select descripcion from forma_farmaceutica where descripcion=?");
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
