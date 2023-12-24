package entidades;

import java.util.Objects;


public class FormaFarmaceutica {
    
    private int idFFarmaceutica;
    private String descripcion;
    private boolean activo;

    public FormaFarmaceutica() {
    }

    public FormaFarmaceutica(int idFFarmaceutica, String descripcion, boolean activo) {
        this.idFFarmaceutica = idFFarmaceutica;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public FormaFarmaceutica(int idFFarmaceutica, String descripcion) {
        this.idFFarmaceutica = idFFarmaceutica;
        this.descripcion = descripcion;
    }
    
    

    public int getIdFFarmaceutica() {
        return idFFarmaceutica;
    }

    public void setIdFFarmaceutica(int idFFarmaceutica) {
        this.idFFarmaceutica = idFFarmaceutica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idFFarmaceutica;
        hash = 79 * hash + Objects.hashCode(this.descripcion);
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
        final FormaFarmaceutica other = (FormaFarmaceutica) obj;
        if (this.idFFarmaceutica != other.idFFarmaceutica) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        return Objects.equals(this.descripcion, other.descripcion);
    }
    
    
    
    
}
