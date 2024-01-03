package datos;

import basedatos.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Rol;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//Se debe sobreescribir metodos abstractos de la implementacion
/*
 * La clase RolDAO implementa la interfaz CrudSimpleInterface
 * y se debe sobreescribir los metodos abstractos de la interfaz
 * 
 * La clase RolDAO es la clase que interactua con la base de datos
 * 
 * Los Data Access Object (DAO) son clases que interactuan con la base de datos
 * y se encargan de realizar las operaciones basicas de la base de datos
 * 
 * Investigar sobre el patron de diseño DAO, patron de diseño MVC y singleton
 */
public class RolDAO implements CrudSimpleInterface<Rol> {

    /*
     * Se crea una instancia de la clase Conexion
     * para poder interactuar con la base de datos
     * Se declara como final para que no se pueda modificar
     * y se inicializa en el constructor
     * 
     * La clase Conexion es una clase singleton
     * que permite crear una sola instancia de la clase
     * y asi no tener que crear multiples instancias
     * de la clase Conexion
     * 
     * Se declara una variable de tipo PreparedStatement
     * para poder preparar las consultas a la base de datos
     * 
     * Se declara una variable de tipo ResultSet
     * para poder almacenar los resultados de las consultas
     * a la base de datos
     * 
     * Se declara una variable de tipo boolean
     * para poder almacenar el resultado de las operaciones
     * de la base de datos
     */
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta;

    /*
     * Se crea un constructor para inicializar la instancia
     * de la clase Conexion
     * 
     * En este caso se usa el metodo getInstancia de la clase Conexion
     * para obtener la instancia de la clase Conexion
     * 
     */
    public RolDAO() {
        CON = Conexion.getInstancia();

    }

    /*
     * El metodo listar recibe un parametro de tipo String
     * que se utiliza para realizar la busqueda de los registros
     * en la base de datos
     * 
     * Retorna una lista de objetos de tipo Rol
     */
    @Override
    public List<Rol> listar(String texto) {
        //Se crea una lista de objetos de tipo Rol
        //List es una interfaz que permite crear listas de objetos
        //ArrayList es una clase que implementa la interfaz List
        List<Rol> registros = new ArrayList();
        //Se prepara un bloque try-catch para capturar posibles errores
        try {
            //Se prepara la consulta a la base de datos
            ps = CON.conectar().prepareStatement("select * from rol where nombre like ?");
            //Se asigna el parametro recibido al parametro de la consulta
            ps.setString(1, texto + "%");
            //Se ejecuta la consulta y se almacena el resultado en la variable rs
            rs = ps.executeQuery();
            //Se recorre el resultado de la consulta
            while (rs.next()) {
                //Se agregan los registros a la lista de objetos de tipo Rol
                //Se crea un objeto de tipo Rol y se asignan los valores
                registros.add(new Rol(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
            }
            //Se cierran las conexiones a la base de datos
            ps.close();
            //Se cierra el resultado de la consulta
            rs.close();
        } 
        //Se captura un posible error
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
        //Se ejecuta el bloque finally para cerrar las conexiones a la base de datos        
        finally {
            //Se asigna null a la variable ps
            ps = null;
            //Se asigna null a la variable rs
            rs = null;
            //Se ejecuta el metodo desconectar de la clase Conexion
            CON.desconectar();
        }
        //Se retorna la lista de objetos de tipo Rol
        return registros;
    }

    /*
     * El metodo seleccionarRoles no recibe parametros
     * 
     * Retorna una lista de objetos de tipo Rol
     */
    public List<Rol> seleccionarRoles() {
        List<Rol> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select id_rol, nombre from rol order by nombre asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1), rs.getString(2)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }

    /*
     * El metodo insertar recibe un parametro de tipo Rol
     * Retorna un valor booleano
     */
    @Override
    public boolean insertar(Rol obj) {
        //Se asigna false a la variable respuesta
        respuesta = false;
        try {
            //Se prepara la consulta de insercion a la base de datos
            ps = CON.conectar().prepareStatement("insert into rol(nombre, activo) values(?,1)");
            //Se asignan los parametros recibidos a la consulta
            ps.setString(1, obj.getNombre());
            //Se ejecuta la consulta y se almacena el resultado en la variable respuesta
            //Si se ha insertado un registro devuelve 1, si no, devuelve 0
            if (ps.executeUpdate() > 0) {
                /*Para INSERT o UPDATE no se puede utilizar execute query
                se debe utilizar executeUpdate.
                
                Execute query devuelve resultSet. executeUpdate devuelve un entero.
                Si se ha insertado un registro devuelve 1, si no, devuelve 0
                 */
                respuesta = true;
            }
            //Se cierra la conexion a la base de datos
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        //Se retorna el valor de la variable respuesta
        return respuesta;
    }

    /*
     * El metodo actualizar recibe un parametro de tipo Rol
     * Retorna un valor booleano
     */
    @Override
    public boolean actualizar(Rol obj) {
        respuesta = false;
        try {
            /*
             * Se prepara la consulta de actualizacion a la base de datos
             * La consulta recibe dos parametros
             * El primer parametro es el nombre del rol
             * El segundo parametro es el id del rol
             */
            ps = CON.conectar().prepareStatement("UPDATE rol\n"
                    + "SET nombre = ?\n"
                    + "WHERE id_rol = ?");
            //Se asignan los parametros recibidos a la consulta
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.getIdRol());
            //Se ejecuta la consulta y se almacena el resultado en la variable respuesta
            //Si se ha actualizado un registro devuelve 1, si no, devuelve 0
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return respuesta;
    }

    /*
     * El metodo desactivar recibe un parametro de tipo int
     * que es el id del rol
     * Retorna un valor booleano
     */
    @Override
    public boolean desactivar(int id) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE rol\n"
                    + "SET activo = 0\n"
                    + "WHERE id_rol = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return respuesta;
    }


    /*
     * El metodo activar recibe un parametro de tipo int
     * que es el id del rol
     * Retorna un valor booleano
     */
    @Override
    public boolean activar(int id) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE rol\n"
                    + "SET activo = 1\n"
                    + "WHERE id_rol = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return respuesta;
    }

    /*
     * El metodo total no recibe parametros
     * Retorna un valor entero
     * 
     * El metodo total devuelve el total de registros de la tabla rol
     * en la base de datos
     */
    @Override
    public int total() {
        //Se declara una variable de tipo int
        //para almacenar el total de registros
        int totalRegistros = 0;
        try {
            //Se prepara la consulta a la base de datos
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_rol) from rol");
            //Se ejecuta la consulta y se almacena el resultado en la variable rs
            rs = ps.executeQuery();
            //Se recorre el resultado de la consulta
            while (rs.next()) {
                //Se asigna el resultado a la variable totalRegistros
                totalRegistros = rs.getInt("COUNT(id_rol)");
            }
            //Se cierran las conexiones a la base de datos
            ps.close();
            //Se cierra el resultado de la consulta
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            //Se asigna null a la variable ps y rs
            ps = null;
            rs = null;
            //Se ejecuta el metodo desconectar de la clase Conexion
            CON.desconectar();
        }
        return totalRegistros;
    }

    /*
     * El metodo existe recibe un parametro de tipo String
     * que es el nombre del rol
     * Retorna un valor booleano
     * 
     * El metodo existe devuelve true si el rol existe en la base de datos
     * y false si no existe
     */
    @Override
    public boolean existe(String texto) {
        respuesta = false;
        try {
            ps = CON.conectar().prepareStatement("select nombre from rol where nombre=?");
            ps.setString(1, texto);
            rs = ps.executeQuery();
            //El metodo last() mueve el cursor de la ultima fila
            //del resultado de la consulta
            rs.last();
            //El metodo getRow devuelve el numero de la fila
            //en la que se encuentra el cursor
            //Si el numero de la fila es mayor a 0
            //significa que existe un registro con ese nombre
            //en la base de datos
            if (rs.getRow() > 0) {
                respuesta = true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return respuesta;
    }

}
