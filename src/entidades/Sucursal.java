package entidades;

public class Sucursal {

    private int idSucursal;
    private int idDistrito;
    private int idCliente;
    private String nombreDistrito;
    private String nombreCliente;
    private String direccion;
    private String calle;
    private String numeroCalle;
    private String oficina;
    private boolean activo;

    public Sucursal() {
    }

    public Sucursal(int idSucursal, int idDistrito, int idCliente, String nombreDistrito,
            String nombreCliente, String calle, String numeroCalle, String oficina, boolean activo) {
        this.idSucursal = idSucursal;
        this.idDistrito = idDistrito;
        this.idCliente = idCliente;
        this.nombreDistrito = nombreDistrito;
        this.nombreCliente = nombreCliente;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.oficina = oficina;
        this.activo = activo;
    }

    public Sucursal(int idSucursal, int idDistrito, int idCliente, String nombreDistrito, String nombreCliente, String direccion, boolean activo) {
        this.idSucursal = idSucursal;
        this.idDistrito = idDistrito;
        this.idCliente = idCliente;
        this.nombreDistrito = nombreDistrito;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.activo = activo;
    }

    public Sucursal(int idSucursal, String nombreDistrito, String direccion) {
        this.idSucursal = idSucursal;
        this.nombreDistrito = nombreDistrito;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return nombreDistrito + ", " + direccion;
    }

}
