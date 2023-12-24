package negocio;

import datos.DistritoDAO;
import entidades.Distrito;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class DistritoControl {

    private final DistritoDAO DATOS;
    private Distrito obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public DistritoControl() {
        this.DATOS = new DistritoDAO();
        this.obj = new Distrito();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Distrito> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        String[] titulos = {"ID", "Descripcion", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[4];
        this.registrosMostrados = 0;

        for (Distrito item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdDistrito());
            registro[1] = item.getNombre();
            registro[2] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public String insertar(String nombre, boolean estado) {
        if (DATOS.existe(nombre)) {
            return "El registro ya existe";
        } else {
            obj.setNombre(nombre);
            obj.setActivo(estado);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en la insercion.";
            }
        }

    }

    public String actualizar(int id, String nombre, boolean estado) {
        obj.setIdDistrito(id);
        obj.setNombre(nombre);
        obj.setActivo(estado);
        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la insercion";
        }

    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
