package negocio;

import datos.ClienteDAO;
import datos.SucursalDAO;
import datos.DistritoDAO;
import entidades.Cliente;
import entidades.Sucursal;
import entidades.Distrito;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class SucursalControl {

    private final SucursalDAO DATOSSUCURSAL;
    private final ClienteDAO DATOSCLIENTE;
    private final DistritoDAO DATOSDISTRITO;
    private Sucursal obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public SucursalControl() {
        this.DATOSSUCURSAL = new SucursalDAO();
        this.DATOSCLIENTE = new ClienteDAO();
        this.DATOSDISTRITO = new DistritoDAO();
        this.obj = new Sucursal();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Sucursal> lista = new ArrayList();
        lista.addAll(DATOSSUCURSAL.listar(texto));
        String[] titulos = {"ID", "IDDistrito", "IDCliente", "Nom Cliente", "Nom Distrito", "Direccion",
            "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[7];
        this.registrosMostrados = 0;

        for (Sucursal item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdSucursal());
            registro[1] = Integer.toString(item.getIdDistrito());
            registro[2] = Integer.toString(item.getIdCliente());
            registro[3] = item.getNombreCliente();
            registro[4] = item.getNombreDistrito();
            registro[5] = item.getDireccion();
            registro[6] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listar(String texto, String campo) {
        List<Sucursal> lista = new ArrayList();
        lista.addAll(DATOSSUCURSAL.listar(texto, campo));
        String[] titulos = {"ID", "IDDistrito", "IDCliente", "Nom Cliente", "Nom Distrito", "Direccion",
            "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[7];
        this.registrosMostrados = 0;

        for (Sucursal item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdSucursal());
            registro[1] = Integer.toString(item.getIdDistrito());
            registro[2] = Integer.toString(item.getIdCliente());
            registro[3] = item.getNombreCliente();
            registro[4] = item.getNombreDistrito();
            registro[5] = item.getDireccion();
            registro[6] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public DefaultComboBoxModel listarDistritos() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Distrito> lista = new ArrayList();
        lista = DATOSDISTRITO.listarNombres();
        for (Distrito item : lista) {
            items.addElement(new Distrito(item.getIdDistrito(), item.getNombre()));
        }
        return items;
    }

    public DefaultComboBoxModel listarClientes() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Cliente> lista = new ArrayList();
        lista = DATOSCLIENTE.listarNombres();
        for (Cliente item : lista) {
            items.addElement(new Cliente(item.getIdCliente(), item.getNombre()));
        }
        return items;
    }

    public int enviarIdCliente(Cliente cliente) {
        return cliente.getIdCliente();
    }

    public String insertar(int idDistrito, int idCliente, String direccion, boolean estado) {

        obj.setIdDistrito(idDistrito);
        obj.setIdCliente(idCliente);
        obj.setDireccion(direccion);

        obj.setActivo(estado);

        if (DATOSSUCURSAL.insertar(obj)) {
            return "OK";
        } else {
            return "Error en la insercion.";
        }

    }

    public String actualizar(int idSucursal, int idDistrito, int idCliente, String direccion, boolean estado) {
        obj.setIdSucursal(idSucursal);
        obj.setIdDistrito(idDistrito);
        obj.setIdCliente(idCliente);
        obj.setDireccion(direccion);
        obj.setActivo(estado);
        if (DATOSSUCURSAL.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la insercion";
        }

    }

    public int total() {
        return DATOSSUCURSAL.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
