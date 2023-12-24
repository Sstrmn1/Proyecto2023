package negocio;

import datos.ClienteDAO;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteControl {

    private final ClienteDAO DATOS;
    private Cliente obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ClienteControl() {
        this.DATOS = new ClienteDAO();
        this.obj = new Cliente();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Cliente> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        String[] titulos = {"ID", "Nombre", "Documento", "Num Documento",
            "Telefono", "Email", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[7];
        this.registrosMostrados = 0;

        for (Cliente item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdCliente());
            registro[1] = item.getNombre();
            registro[2] = item.getTipoDocumento();
            registro[3] = item.getNumeroDocumento();
            registro[4] = item.getTelefono();
            registro[5] = item.getEmail();
            registro[6] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public String insertar(String nombre, String tipoDocumento, String numeroDocumento,
            String telefono, String email, boolean estado) {
        if (DATOS.existe(nombre)) {
            return "El registro ya existe";
        } else {
            obj.setNombre(nombre);
            obj.setTipoDocumento(tipoDocumento);
            obj.setNumeroDocumento(numeroDocumento);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            obj.setActivo(estado);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en la insercion.";
            }
        }

    }

    public String actualizar(int id, String nombre, String tipoDocumento,
            String numeroDocumento, String telfono, String email, boolean estado) {
        obj.setIdCliente(id);
        obj.setNombre(nombre);
        obj.setTipoDocumento(tipoDocumento);
        obj.setNumeroDocumento(numeroDocumento);
        obj.setTelefono(telfono);
        obj.setEmail(email);
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
