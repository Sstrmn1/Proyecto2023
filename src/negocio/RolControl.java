package negocio;

import datos.RolDAO;
import entidades.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RolControl {

    private final RolDAO DATOS;
    private Rol obj;
    private DefaultTableModel modeloTabla;

    public int registrosMostrados;

    public RolControl() {
        this.DATOS = new RolDAO();
        //Se usa el constructor vacio para ir agregando datos despues 
        //si se requiere
        this.obj = new Rol();
        this.registrosMostrados = 0;

    }

    public DefaultTableModel listar(String texto) {
        List<Rol> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        String[] titulos = {"ID", "Nombre", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[3];
        this.registrosMostrados = 0;

        for (Rol item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getIdRol());
            registro[1] = item.getNombre();
            registro[2] = estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public String insertar(String nombre) {
        //Analizar procedimiento almacenado para determinar parametros de metodos similares a este
        //Por ejemplo en insertar, el procedimiento es insert into rol( nombre, activo) values(?,1)
        //Y como el campo activo es definido por defecto no es necesario pasar un valor de activo como parametro
        if (DATOS.existe(nombre)) {
            return "El registro ya existe";

        } else {

            //Se setea el atributo nombre en el objeto creado por
            //el constructor vacio
            obj.setNombre(nombre);
            
            //Luego se intenta insertar el objeto y dependiendo el resultado
            //del intento se devuelve un mensaje
            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en la insercion.";
            }

        }

    }

    public String actualizar(int idRol, String nombre, String nombreAnterior) {

        if (nombre.equals(nombreAnterior)) {
            obj.setIdRol(idRol);
            obj.setNombre(nombre);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualizacion";
            }

        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe";
            } else {
                obj.setIdRol(idRol);
                obj.setNombre(nombre);
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
            return "No se puede activar el registro.";
        }
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el registro.";
        }
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
