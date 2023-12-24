package negocio;

import datos.LaboratorioDAO;
import entidades.Laboratorio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class LaboratorioControl {

    private final LaboratorioDAO DATOS;
    private Laboratorio obj;
    private DefaultTableModel modeloTabla;

    public int registrosMostrados;

    public LaboratorioControl() {
        this.DATOS = new LaboratorioDAO();
        this.obj = new Laboratorio();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Laboratorio> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        String[] titulos = {"ID", "Nombre", "Procedencia", "Activo"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[4];
        this.registrosMostrados = 0;

        for (Laboratorio item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdLaboratorio());
            registro[1] = item.getNombre();
            registro[2] = item.getProcedencia();
            registro[3] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public String insertar(String nombre, String procedencia, boolean estado) {
        if (DATOS.existe(nombre)) {
            return "El laboratorio que intenta insertar ya existe";
        } else {
            obj.setNombre(nombre);
            obj.setProcedencia(procedencia);
            obj.setActivo(estado);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en la insercion";
            }
        }
    }

    public String actualizar(int idLaboratorio, String nombre, String procedencia, String nombreAnterior) {

        if (nombre.equals(nombreAnterior)) {
            obj.setIdLaboratorio(idLaboratorio);
            obj.setNombre(nombre);
            obj.setProcedencia(procedencia);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualizacion";
            }
        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe";
            } else {
                obj.setIdLaboratorio(idLaboratorio);
                obj.setNombre(nombre);
                obj.setProcedencia(procedencia);
                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualizacion";
                }
            }
        }

    }

    public String actualizar(int idLaboratorio, String nombre, String procedencia, boolean estado, String nombreAnterior) {

        if (nombre.equals(nombreAnterior)) {
            obj.setIdLaboratorio(idLaboratorio);
            obj.setNombre(nombre);
            obj.setProcedencia(procedencia);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualizacion";
            }
        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe";
            } else {
                obj.setIdLaboratorio(idLaboratorio);
                obj.setNombre(nombre);
                obj.setProcedencia(procedencia);
                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualizacion";
                }
            }
        }

    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el laboratorio seleccionado";
        }
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el laboratorio seleccionado";
        }
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
