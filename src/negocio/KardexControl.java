package negocio;

import datos.KardexDAO;
import datos.LoteDAO;
import entidades.Kardex;
import entidades.Lote;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class KardexControl {

    private final KardexDAO DATOSKARDEX;
    private final LoteDAO DATOSLOTE;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public KardexControl() {
        this.DATOSKARDEX = new KardexDAO();
        this.DATOSLOTE = new LoteDAO();
        
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(int idLote) {
        List<Kardex> lista = new ArrayList();
        lista.addAll(DATOSKARDEX.listar(idLote));
        String[] titulos = {"Fecha hora",
            "Operacion",
            "Cliente",
            "Usuario",
            "Cantidad",
            "Saldo stock"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];
        this.registrosMostrados = 0;

        for (Kardex item : lista) {
            registro[0] = String.valueOf(item.getFechaHora());
            registro[1] = Integer.toString(item.getIdTransaccion());
            registro[2] = item.getCliente();
            registro[3] = item.getRol();
            registro[4] = Integer.toString(item.getCantidad());
            registro[5] = Integer.toString(item.getSaldoStock());

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public DefaultComboBoxModel cargarLote(int idArticulo) {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Lote> lista = new ArrayList();
        lista = DATOSLOTE.seleccionarLote(idArticulo);
        for (Lote item : lista) {
            items.addElement(new Lote(item.getIdLote(), item.getIdArticulo(), item.getLoteCodigo(), item.getPrecioUnitario()));
        }
        return items;
    }
    
    public int selectSaldoInicial(int idLote){
        int saldoInicial= DATOSKARDEX.selectSaldoInicial(idLote);
        return saldoInicial;
    }

}
