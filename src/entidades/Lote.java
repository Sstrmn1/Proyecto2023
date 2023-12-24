package entidades;

import java.sql.Date;

public class Lote {

    private int idLote;
    private int idArticulo;
    private int idLaboratorio;
    private String articuloCodigo;
    private String articuloDescripcion;
    private String loteCodigo;
    private Date fechaFabricacion;
    private Date fechaExpiracion;
    private int stock;
    private float precioUnitario;
    private String laboratorioNombre;
    private boolean activo;

    public Lote() {
    }

    public Lote(int idLote, int idArticulo, int idLaboratorio, String articuloCodigo, String articuloDescripcion, String loteCodigo, Date fechaFabricacion, Date fechaExpiracion, int stock, float precioUnitario, String laboratorioNombre, boolean activo) {
        this.idLote = idLote;
        this.idArticulo = idArticulo;
        this.idLaboratorio = idLaboratorio;
        this.articuloCodigo = articuloCodigo;
        this.articuloDescripcion = articuloDescripcion;
        this.loteCodigo = loteCodigo;
        this.fechaFabricacion = fechaFabricacion;
        this.fechaExpiracion = fechaExpiracion;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.laboratorioNombre = laboratorioNombre;
        this.activo = activo;
    }

    public Lote(int idLote, int idArticulo, String loteCodigo, float precioUnitario) {
        this.idLote = idLote;
        this.idArticulo = idArticulo;
        this.loteCodigo = loteCodigo;
        this.precioUnitario = precioUnitario;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getArticuloCodigo() {
        return articuloCodigo;
    }

    public void setArticuloCodigo(String articuloCodigo) {
        this.articuloCodigo = articuloCodigo;
    }

    public String getArticuloDescripcion() {
        return articuloDescripcion;
    }

    public void setArticuloDescripcion(String articuloDescripcion) {
        this.articuloDescripcion = articuloDescripcion;
    }

    public String getLoteCodigo() {
        return loteCodigo;
    }

    public void setLoteCodigo(String loteCodigo) {
        this.loteCodigo = loteCodigo;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getLaboratorioNombre() {
        return laboratorioNombre;
    }

    public void setLaboratorioNombre(String laboratorioNombre) {
        this.laboratorioNombre = laboratorioNombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return loteCodigo;
    }

}
