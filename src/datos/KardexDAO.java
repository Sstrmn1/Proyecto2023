package datos;

import entidades.Kardex;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basedatos.Conexion;

public class KardexDAO {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;

    public KardexDAO() {
        CON = Conexion.getInstancia();
    }

    public List<Kardex> listar(int idLote) {
        List<Kardex> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT\n"
                    + "    t.fecha,\n"
                    + "    t.id_transaccion AS id_trans,\n"
                    + "    c.nombre AS cliente,\n"
                    + "    r.nombre AS rol,\n"
                    + "    -t.cantidad AS cantidad,\n"
                    + "    (total_cantidad_stock - COALESCE(SUM(t.cantidad) OVER (ORDER BY t.fecha, t.id_transaccion), 0)) AS saldo_stock\n"
                    + "FROM\n"
                    + "    transaccion t\n"
                    + "INNER JOIN lote l ON t.id_lote = l.id_lote\n"
                    + "INNER JOIN orden_de_venta o ON t.id_orden = o.id_orden \n"
                    + "INNER JOIN sucursal s ON o.id_sucursal = s.id_sucursal\n"
                    + "INNER JOIN cliente c ON s.id_cliente = c.id_cliente\n"
                    + "INNER JOIN usuario u ON o.id_usuario = u.id_usuario\n"
                    + "INNER JOIN rol r ON u.id_rol = r.id_rol\n"
                    + "CROSS JOIN (SELECT SUM(t.cantidad) + l.stock AS total_cantidad_stock\n"
                    + "            FROM transaccion t\n"
                    + "            INNER JOIN lote l ON t.id_lote = l.id_lote\n"
                    + "            WHERE l.id_lote = ?\n"
                    + "            GROUP BY l.id_lote) AS total_stock\n"
                    + "WHERE\n"
                    + "    l.id_lote = ?\n"
                    + "ORDER BY\n"
                    + "    t.fecha;");
            ps.setInt(1, idLote);
            ps.setInt(2, idLote);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Kardex(
                        rs.getTimestamp(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)
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

    public int selectSaldoInicial(int idLote) {
        int saldoInicial = -1;
        try {
            ps = CON.conectar().prepareStatement("SELECT\n"
                    + "SUM(t.cantidad) + l.stock AS saldoInicial\n"
                    + "FROM\n"
                    + "transaccion t\n"
                    + "INNER JOIN lote l ON t.id_lote = l.id_lote\n"
                    + "WHERE\n"
                    + "l.id_lote = ?");
            ps.setInt(1, idLote);

            rs = ps.executeQuery();
            while (rs.next()) {
                saldoInicial = rs.getInt(1);
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
        return saldoInicial;

    }

}
