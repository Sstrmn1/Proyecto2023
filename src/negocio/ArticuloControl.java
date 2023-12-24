package negocio;

import datos.ArticuloDAO;
import datos.FormaFarmaceuticaDAO;
import entidades.Articulo;
import entidades.FormaFarmaceutica;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ArticuloControl {

    private final ArticuloDAO DATOSARTICULO;
    private final FormaFarmaceuticaDAO DATOSFFARMACEUTICA;
    private Articulo obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ArticuloControl() {
        this.DATOSARTICULO = new ArticuloDAO();
        this.DATOSFFARMACEUTICA = new FormaFarmaceuticaDAO();
        this.obj = new Articulo();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Articulo> lista = new ArrayList();
        lista.addAll(DATOSARTICULO.listar(texto));
        String[] titulos = {"ID", "IDFormaFarmaceutica", "Codigo", "Descripcion", "FFarmaceutica", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[6];
        this.registrosMostrados = 0;

        for (Articulo item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdArticulo());
            registro[1] = Integer.toString(item.getIdFfarmaceutica());
            registro[2] = item.getCodigo();
            registro[3] = item.getDescripcion();
            registro[4] = item.getFfDescripcion();
            registro[5] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
        public DefaultTableModel listar(String texto, String campo) {
        List<Articulo> lista = new ArrayList();
        lista.addAll(DATOSARTICULO.listar(texto, campo));
        String[] titulos = {"ID", "IDFormaFarmaceutica", "Codigo", "Descripcion", "FFarmaceutica", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[6];
        this.registrosMostrados = 0;

        for (Articulo item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdArticulo());
            registro[1] = Integer.toString(item.getIdFfarmaceutica());
            registro[2] = item.getCodigo();
            registro[3] = item.getDescripcion();
            registro[4] = item.getFfDescripcion();
            registro[5] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public DefaultComboBoxModel cargarFormaFarmaceutica() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<FormaFarmaceutica> lista = new ArrayList();
        lista = DATOSFFARMACEUTICA.seleccionarFormaFarmaceutica();
        for (FormaFarmaceutica item : lista) {
            items.addElement(new FormaFarmaceutica(item.getIdFFarmaceutica(), item.getDescripcion()));
        }
        return items;
    }

    public String insertar(int idFFarmaceutica, String descripcion, String ffDescripcion, String codigo,
            boolean estado) {
        if (DATOSARTICULO.existe(descripcion)) {
            return "El registro ya existe";
        } else {
            obj.setIdFfarmaceutica(idFFarmaceutica);
            obj.setDescripcion(descripcion);
            obj.setFfDescripcion(ffDescripcion);
            obj.setCodigo(codigo);
            obj.setActivo(estado);

            if (DATOSARTICULO.insertar(obj)) {
                return "OK";
            } else {
                return "Error en la insercion.";
            }
        }

    }

    public String actualizar(int id, int idFFarmaceutica, String descripcion, String ffDescripcion,
             String codigo, boolean estado) {
        obj.setIdArticulo(id);
        obj.setIdFfarmaceutica(idFFarmaceutica);
        obj.setDescripcion(descripcion);
        obj.setFfDescripcion(ffDescripcion);
        obj.setCodigo(codigo);
        obj.setActivo(estado);

        if (DATOSARTICULO.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualizacion.";
        }

    }

    public int total() {
        return DATOSARTICULO.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
