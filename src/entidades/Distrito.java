package entidades;

import java.util.Objects;

public class Distrito {

    private int idDistrito;
    private String nombre;
    private boolean activo;

    public Distrito() {
    }

    public Distrito(int idDistrito, String nombre) {
        this.idDistrito = idDistrito;
        this.nombre = nombre;
    }

    public Distrito(int idDistrito, String nombre, boolean activo) {
        this.idDistrito = idDistrito;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
//    public String toString() {
//        return "Distrito{" + "idDistrito=" + idDistrito + ", nombre=" + nombre + ", activo=" + activo + '}';
//    }
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idDistrito;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + (this.activo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Distrito other = (Distrito) obj;
        if (this.idDistrito != other.idDistrito) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

}
