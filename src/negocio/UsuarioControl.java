package negocio;

import clases.Variables;
import datos.UsuarioDAO;
import datos.RolDAO;
import entidades.Usuario;
import entidades.Rol;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class UsuarioControl {

    private final UsuarioDAO DATOSUSUARIO;
    private final RolDAO DATOSROL;
    private Usuario obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public UsuarioControl() {
        this.DATOSUSUARIO = new UsuarioDAO();
        this.DATOSROL = new RolDAO();
        this.obj = new Usuario();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOSUSUARIO.listar(texto));
        String[] titulos = {"ID", "IDRol", "Rol", "Nombre", "Apellido", "Password", "CI", "Email", "Fecha Nac.", "Foto", "Activo"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[11];
        this.registrosMostrados = 0;

        for (Usuario item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdUsuario());
            registro[1] = Integer.toString(item.getIdRol());
            registro[2] = item.getRolNombre();
            registro[3] = item.getNombre();
            registro[4] = item.getApellido();
            registro[5] = item.getPassword();
            registro[6] = item.getCi();
            registro[7] = item.getEmail();
            registro[8] = String.valueOf(item.getFechaNac());
            registro[9] = item.getFoto();
            registro[10] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listar(String texto, String campo) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOSUSUARIO.listar(texto, campo));
        String[] titulos = {"ID", "IDRol", "Rol", "Nombre", "Apellido", "Password", "CI", "Email", "Fecha Nac.", "Foto", "Activo"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[11];
        this.registrosMostrados = 0;

        for (Usuario item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdUsuario());
            registro[1] = Integer.toString(item.getIdRol());
            registro[2] = item.getRolNombre();
            registro[3] = item.getNombre();
            registro[4] = item.getApellido();
            registro[5] = item.getPassword();
            registro[6] = item.getCi();
            registro[7] = item.getEmail();
            registro[8] = String.valueOf(item.getFechaNac());
            registro[9] = item.getFoto();
            registro[10] = estado;

            this.modeloTabla.addRow(registro);
            registrosMostrados++;
        }
        return this.modeloTabla;
    }

    public String login(String email, String password) {
        String resp = "0";
        Usuario usu = this.DATOSUSUARIO.login(email, this.encriptar(password));
        if (usu != null) {
            if (usu.isActivo()) {
                Variables.usuarioId = usu.getIdUsuario();
                Variables.rolId = usu.getIdRol();
                Variables.rolNombre = usu.getRolNombre();
                Variables.usuarioNombre = usu.getNombre();
                Variables.usuarioApellido = usu.getApellido();
                Variables.usuarioCi = usu.getCi();
                Variables.usuarioEmail = usu.getEmail();
                resp = "1";
            } else {
                resp = "2";
            }
        }
        return resp;
    }

    public DefaultComboBoxModel cargarRoles() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Rol> lista = new ArrayList();
        lista = DATOSROL.seleccionarRoles();
        for (Rol item : lista) {
            items.addElement(new Rol(item.getIdRol(), item.getNombre()));
        }
        return items;
    }

    public String insertar(int idRol, String password, String nombre, String apellido, String ci,
            String email, Date fechaNac, String foto, boolean estado) {
        if (DATOSUSUARIO.existe(nombre)) {
            return "El registro ya existe";
        } else {
            obj.setIdRol(idRol);
            obj.setNombre(nombre);
            obj.setApellido(apellido);
            obj.setPassword(this.encriptar(password));
            obj.setCi(ci);
            obj.setFechaNac(fechaNac);
            obj.setEmail(email);
            obj.setActivo(estado);
            obj.setFoto(foto);

            if (DATOSUSUARIO.insertar(obj)) {
                return "OK";
            } else {
                return "Error en la insercion.";
            }
        }

    }

    public String actualizar(int id, int idRol, String password, String nombre, String apellido, String ci,
            String email, Date fechaNac, String foto, boolean estado) {
        obj.setIdUsuario(id);
        obj.setIdRol(idRol);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        String encriptado;
        if (password.length() == 64) {
            encriptado = password;
        } else {
            encriptado = this.encriptar(password);
        }
        obj.setPassword(encriptado);
        obj.setCi(ci);
        obj.setFechaNac(fechaNac);
        obj.setEmail(email);
        obj.setActivo(estado);

        if (DATOSUSUARIO.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualizacion.";
        }

    }

    public static String encriptar(String valor) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hash = md.digest(valor.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public int total() {
        return DATOSUSUARIO.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

    public String generaNombreImagen(String nombre, String numeroDocumento) {
        String nombreImagen = nombre.replaceAll("\\s", "").trim() + numeroDocumento.trim();
        return nombreImagen;
    }
}
