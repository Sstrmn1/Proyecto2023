package datos;

import basedatos.Conexion;
import entidades.Laboratorio;
import datos.interfaces.CrudSimpleInterface;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LaboratorioDAO implements CrudSimpleInterface<Laboratorio> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public LaboratorioDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Laboratorio> listar(String texto) {
        /*Este metodo/clase usa patron singleton
        Investigar patron singleton*/
        List<Laboratorio> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select * from laboratorio where nombre like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Laboratorio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
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

    public List<Laboratorio> seleccionarLaboratorio() {
        List<Laboratorio> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select id_laboratorio, nombre from laboratorio order by nombre asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Laboratorio(rs.getInt(1), rs.getString(2)));
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
    public boolean insertar(Laboratorio obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("insert into laboratorio(nombre, procedencia, activo)\n"
                    + "values(?,?,?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getProcedencia());
            ps.setBoolean(3, obj.isActivo());
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
    public boolean actualizar(Laboratorio obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE laboratorio\n"
                    + "SET nombre = ?, procedencia = ?\n"
                    + "WHERE id_laboratorio = ?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getProcedencia());
            ps.setInt(3, obj.getIdLaboratorio());
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
            ps = CON.conectar().prepareStatement("UPDATE laboratorio\n"
                    + "SET activo = 0\n"
                    + "WHERE id_laboratorio = ?");
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
            ps = CON.conectar().prepareStatement("UPDATE laboratorio\n"
                    + "SET activo = 1\n"
                    + "WHERE id_laboratorio = ?");
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_laboratorio) from laboratorio");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_laboratorio)");
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
            ps = CON.conectar().prepareStatement("select nombre from laboratorio where nombre=?");
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
