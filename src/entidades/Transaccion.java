package entidades;

import java.sql.Timestamp;

public class Transaccion {

    private int idTransaccion;
    private int idLote;
    private String articulo;
    private String articuloDescripcion;
    private String loteCodigo;
    private int idOrden;
    private int cantidad;
    private float importe;
    private Timestamp fechaHora;

    public Transaccion() {
    }

    public Transaccion(int idTransaccion, int idLote, String Articulo, String ArticuloDescripcion, String LoteCodigo, int idOrden, int cantidad, Timestamp fechaHora) {
        this.idTransaccion = idTransaccion;
        this.idLote = idLote;
        this.articulo = Articulo;
        this.articuloDescripcion = ArticuloDescripcion;
        this.loteCodigo = LoteCodigo;
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    public Transaccion(int idTransaccion, int idLote, int idOrden, int cantidad, Timestamp fechaHora) {
        this.idTransaccion = idTransaccion;
        this.idLote = idLote;
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

//    public Transaccion(String Articulo, String ArticuloDescripcion, String LoteCodigo, int idOrden, int cantidad, float importe) {
//        this.Articulo = Articulo;
//        this.ArticuloDescripcion = ArticuloDescripcion;
//        this.LoteCodigo = LoteCodigo;
//        this.idOrden = idOrden;
//        this.cantidad = cantidad;
//        this.importe = importe;
//    }   

    public Transaccion(String Articulo, String ArticuloDescripcion, String LoteCodigo, int idLote, int cantidad, float importe) {
        this.articulo = Articulo;
        this.articuloDescripcion = ArticuloDescripcion;
        this.loteCodigo = LoteCodigo;
        this.idLote = idLote;
        this.cantidad = cantidad;
        this.importe = importe;
    }
    
    
    

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
    
    
    

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
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

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "idTransaccion=" + idTransaccion + ", idLote=" + idLote + ", Articulo=" + articulo + ", ArticuloDescripcion=" + articuloDescripcion + ", LoteCodigo=" + loteCodigo + ", idOrden=" + idOrden + ", cantidad=" + cantidad + ", fechaHora=" + fechaHora + '}';
    }
    
    
}
