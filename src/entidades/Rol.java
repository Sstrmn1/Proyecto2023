package entidades;

import java.util.Objects;

public class Rol {

    private int idRol;
    private String nombre;
    private boolean activo;

    public Rol() {
    }

    public Rol(int idRol, String nombre, boolean activo) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Rol(int idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }   
    

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
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
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.idRol;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + (this.activo ? 1 : 0);
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
        final Rol other = (Rol) obj;
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

}
