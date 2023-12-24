package negocio;

import datos.LoteDAO;
import datos.ArticuloDAO;
import datos.LaboratorioDAO;
import entidades.Lote;
import entidades.Articulo;
import entidades.Laboratorio;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class LoteControl {

    private final LoteDAO DATOSLOTE;
    private final ArticuloDAO DATOSARTICULO;
    private final LaboratorioDAO DATOSLABORATORIO;
    private Lote obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public LoteControl() {
        this.DATOSLOTE = new LoteDAO();
        this.DATOSARTICULO = new ArticuloDAO();
        this.DATOSLABORATORIO = new LaboratorioDAO();
        this.obj = new Lote();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Lote> lista = new ArrayList();
        lista.addAll(DATOSLOTE.listar(texto));
        String[] titulos = {"ID",
            "IDArt",
            "IDLab",
            "Articulo",
            "Descripcion",
            "Lote",
            "Fecha fab.",
            "Fecha exp.",
            "Stock",
            "Precio uni.",
            "Laboratorio",
            "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[12];
        this.registrosMostrados = 0;

        for (Lote item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdLote());
            registro[1] = Integer.toString(item.getIdArticulo());
            registro[2] = Integer.toString(item.getIdLaboratorio());
            registro[3] = item.getArticuloCodigo();
            registro[4] = item.getArticuloDescripcion();
            registro[5] = item.getLoteCodigo();
            registro[6] = String.valueOf(item.getFechaFabricacion());
            registro[7] = String.valueOf(item.getFechaExpiracion());
            registro[8] = Integer.toString(item.getStock());
            registro[9] = Float.toString(item.getPrecioUnitario());
            registro[10] = item.getLaboratorioNombre();

            registro[11] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listar(String texto, String campo) {
        List<Lote> lista = new ArrayList();
        lista.addAll(DATOSLOTE.listar(texto, campo));
        String[] titulos = {"ID",
            "IDArt",
            "IDLab",
            "Articulo",
            "Descripcion",
            "Lote",
            "Fecha fab.",
            "Fecha exp.",
            "Stock",
            "Precio uni.",
            "Laboratorio",
            "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[12];
        this.registrosMostrados = 0;

        for (Lote item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdLote());
            registro[1] = Integer.toString(item.getIdArticulo());
            registro[2] = Integer.toString(item.getIdLaboratorio());
            registro[3] = item.getArticuloCodigo();
            registro[4] = item.getArticuloDescripcion();
            registro[5] = item.getLoteCodigo();
            registro[6] = String.valueOf(item.getFechaFabricacion());
            registro[7] = String.valueOf(item.getFechaExpiracion());
            registro[8] = Integer.toString(item.getStock());
            registro[9] = Float.toString(item.getPrecioUnitario());
            registro[10] = item.getLaboratorioNombre();

            registro[11] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public DefaultComboBoxModel cargarArticulo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Articulo> lista = new ArrayList();
        lista = DATOSARTICULO.seleccionarArticulo();
        for (Articulo item : lista) {
            items.addElement(new Articulo(item.getIdArticulo(), item.getDescripcion(), item.getCodigo()));
        }
        return items;
    }

    public DefaultComboBoxModel cargarLaboratorio() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Laboratorio> lista = new ArrayList();
        lista = DATOSLABORATORIO.seleccionarLaboratorio();
        for (Laboratorio item : lista) {
            items.addElement(new Laboratorio(item.getIdLaboratorio(), item.getNombre()));
        }
        return items;
    }

    public String insertar(int idArticulo,
            int idLaboratorio,
            String articuloCodigo,
            String articuloDescripcion,
            String loteCodigo,
            Date fechaFabricacion,
            Date fechaExpiracion,
            int stock,
            float precioUnitario,
            String laboratorioNombre,
            boolean activo) {
        if (DATOSLOTE.existe(loteCodigo)) {
            return "El registro ya existe";
        } else {
            obj.setIdArticulo(idArticulo);
            obj.setIdLaboratorio(idLaboratorio);
            obj.setArticuloCodigo(articuloCodigo);
            obj.setArticuloDescripcion(articuloDescripcion);
            obj.setLoteCodigo(loteCodigo);
            obj.setFechaFabricacion(fechaFabricacion);
            obj.setFechaExpiracion(fechaExpiracion);
            obj.setStock(stock);
            obj.setPrecioUnitario(precioUnitario);
            obj.setActivo(activo);

            if (DATOSLOTE.insertar(obj)) {
                return "OK";
            } else {
                return "Error en la insercion.";
            }
        }

    }

    public String actualizar(int id,
            int idArticulo,
            int idLaboratorio,
            String articuloCodigo,
            String articuloDescripcion,
            String loteCodigo,
            Date fechaFabricacion,
            Date fechaExpiracion,
            int stock,
            float precioUnitario,
            String laboratorioNombre,
            boolean activo) {
        obj.setIdLote(id);
        obj.setIdArticulo(idArticulo);
        obj.setIdLaboratorio(idLaboratorio);
        obj.setArticuloCodigo(articuloCodigo);
        obj.setArticuloDescripcion(articuloDescripcion);
        obj.setLoteCodigo(loteCodigo);
        obj.setFechaFabricacion(fechaFabricacion);
        obj.setFechaExpiracion(fechaExpiracion);
        obj.setStock(stock);
        obj.setPrecioUnitario(precioUnitario);
        obj.setActivo(activo);

        if (DATOSLOTE.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualizacion.";
        }

    }

    public int total() {
        return DATOSLOTE.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
