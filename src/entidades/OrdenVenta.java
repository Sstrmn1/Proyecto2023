package entidades;

import java.sql.Timestamp;
import java.util.List;

public class OrdenVenta {

    private int idOrden;
    private int numeroOrden;
    private int idSucursal;
    private int idUsuario;
    private Timestamp fechaHora;
    private float importeTotal;
    private List<Transaccion> transacciones;

    private String clienteNombre;
    private String distritoNombre;
    private String rolNombre;
    
    public OrdenVenta() {
    }

    public OrdenVenta(int idOrden, int numeroOrden, int idSucursal, int idUsuario, Timestamp fechaHora, float importeTotal, List<Transaccion> transacciones) {
        this.idOrden = idOrden;
        this.numeroOrden = numeroOrden;
        this.idSucursal = idSucursal;
        this.idUsuario = idUsuario;
        this.fechaHora = fechaHora;
        this.importeTotal = importeTotal;
        this.transacciones = transacciones;
    }

    public OrdenVenta(int idOrden, int idUsuario, Timestamp fechaHora, float importeTotal, String clienteNombre, String distritoNombre, String rolNombre) {
        this.idOrden = idOrden;
        this.idUsuario = idUsuario;
        this.fechaHora = fechaHora;
        this.importeTotal = importeTotal;
        this.clienteNombre = clienteNombre;
        this.distritoNombre = distritoNombre;
        this.rolNombre = rolNombre;
    }    
    

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getDistritoNombre() {
        return distritoNombre;
    }

    public void setDistritoNombre(String distritoNombre) {
        this.distritoNombre = distritoNombre;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }



    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    @Override
    public String toString() {
        return "OrdenVenta{" + "idOrden=" + idOrden + ", numeroOrden=" + numeroOrden + ", idSucursal=" + idSucursal + ", idUsuario=" + idUsuario + ", fechaHora=" + fechaHora + ", importeTotal=" + importeTotal + '}';
    }

}
