package entidades;
import java.sql.Date;

public class Usuario {
    private int idUsuario;
    private int idRol;
    private String rolNombre;
    private String password;
    private String nombre;
    private String apellido;
    private String ci;
    private String email;
    private Date fechaNac;
    private String foto;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(int idUsuario, int idRol, String rolNombre, String nombre, String apellido, String ci, String email, boolean activo) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.rolNombre = rolNombre;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.email = email;
        this.activo = activo;
    }   

    public Usuario(int idUsuario, int idRol, String rolNombre, String password, String nombre, String apellido, String ci, String email, Date fechaNac, String foto, boolean activo) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.rolNombre = rolNombre;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.email = email;
        this.fechaNac = fechaNac;
        this.foto = foto;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", idRol=" + idRol + ", rolNombre=" + rolNombre + ", password=" + password + ", nombre=" + nombre + ", apellido=" + apellido + ", ci=" + ci + ", email=" + email + ", fechaNac=" + fechaNac + ", foto=" + foto + ", activo=" + activo + '}';
    }


    
    
}
