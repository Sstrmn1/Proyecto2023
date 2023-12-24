package datos;

import datos.interfaces.CrudSimpleInterface;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class ClienteDAO implements CrudSimpleInterface<Cliente> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    public ClienteDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Cliente> listar(String texto) {
        List<Cliente> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select * from cliente where nombre like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7)));
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

    public List<Cliente> listarNombres() {
        List<Cliente> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select id_cliente, nombre from cliente order by nombre asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Cliente(rs.getInt(1), rs.getString(2)));
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

    public List<Cliente> listarNombres(String texto) {
        List<Cliente> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select "
                    + "id_cliente, "
                    + "nombre "
                    + "from cliente "
                    + "where nombre like ? order by nombre asc");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Cliente(rs.getInt(1), rs.getString(2)));
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
    public boolean insertar(Cliente obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("insert into cliente(nombre, tipo_documento, numero_documento, "
                    + "telefono, email, activo)\n"
                    + "values(?,?,?,?,?,?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getTipoDocumento());
            ps.setString(3, obj.getNumeroDocumento());
            ps.setString(4, obj.getTelefono());
            ps.setString(5, obj.getEmail());
            ps.setInt(6, obj.isActivo() ? 1 : 0);

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
    public boolean actualizar(Cliente obj) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cliente SET nombre = ?, tipo_documento = ?, "
                    + "numero_documento = ?, telefono = ?, email = ?, activo = ?"
                    + " WHERE id_cliente = ?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getTipoDocumento());
            ps.setString(3, obj.getNumeroDocumento());
            ps.setString(4, obj.getTelefono());
            ps.setString(5, obj.getEmail());
            ps.setInt(6, obj.isActivo() ? 1 : 0);

            ps.setInt(7, obj.getIdCliente());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_cliente) from cliente");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_cliente)");
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
            ps = CON.conectar().prepareStatement("select nombre from cliente where nombre=?");
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
