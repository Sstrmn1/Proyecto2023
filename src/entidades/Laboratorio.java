package entidades;

import java.util.Objects;

public class Laboratorio {

    private int idLaboratorio;
    private String nombre;
    private String procedencia;
    private boolean activo;

    public Laboratorio() {
    }

    public Laboratorio(int idLaboratorio, String nombre) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
    }

    public Laboratorio(int idLaboratorio, String nombre, String procedencia, boolean activo) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.procedencia = procedencia;
        this.activo = activo;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
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
        hash = 89 * hash + this.idLaboratorio;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + Objects.hashCode(this.procedencia);
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
        final Laboratorio other = (Laboratorio) obj;
        if (this.idLaboratorio != other.idLaboratorio) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.procedencia, other.procedencia);
    }

}
