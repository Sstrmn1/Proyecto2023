package entidades;

import java.sql.Timestamp;

public class Kardex {

    private Timestamp fechaHora;
    private int idTransaccion;
    private String cliente;
    private String rol;
    private int cantidad;
    private int saldoStock;

    public Kardex() {
    }

    public Kardex(Timestamp fechaHora, int idTransaccion, String cliente, String rol, int cantidad, int saldoStock) {
        this.fechaHora = fechaHora;
        this.idTransaccion = idTransaccion;
        this.cliente = cliente;
        this.rol = rol;
        this.cantidad = cantidad;
        this.saldoStock = saldoStock;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSaldoStock() {
        return saldoStock;
    }

    public void setSaldoStock(int saldoStock) {
        this.saldoStock = saldoStock;
    }

}
