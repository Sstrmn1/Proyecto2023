package basedatos;

/*
 * Importar librerias de java.sql que se utilizaran en la clase y distintos metodos de
 * otras clases en el proyecto.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Savepoint;
import java.sql.DatabaseMetaData;

public class Conexion {
    // atributos

    /*
     * Se declaran las variables que se utilizaran para la conexion a la base de
     * datos
     * En el caso de mysql se utiliza el driver de mysql y la url de la base de
     * datos
     * Al ser mariaSql no se requiere contraseña
     */
    private final String DRIVER = "org.gjt.mm.mysql.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String BD = "bd_genolab";
    private final String USUARIO = "root";
    private final String PASSWORD = "";

    /*
     * Se declara la variable cadena de tipo Connection que se utilizara para la
     * conexion
     */
    public Connection cadena;

    /*
     * Se declara la variable instancia de tipo Conexion que se utilizara para la
     * conexion
     * y se inicializa en null
     */
    public static Conexion instancia;
    private Savepoint savepoint;

    /*
     * Se declara la variable dbmd de tipo DatabaseMetaData que puede ser utilizada
     * para
     * obtener informacion sobre la base de datos a la que esta conectada la
     * aplicacion
     */
    private DatabaseMetaData dbmd;

    // metodos
    /*
     * Constructor de la clase Conexion
     */
    public Conexion() {
        this.cadena = null;
    }

    /*
     * Metodo conectar que se utiliza para realizar la conexion a la base de datos
     * Se utiliza un try-catch para intentar realizar la conexion a la base de datos
     * En el caso de un metodo que maneja conexiones a base de datos es obligatorio
     * utilizar un try-catch para manejar las excepciones sql
     */
    public Connection conectar() {
        try {
            /*
             * El metodo forName de la clase Class se utiliza para cargar el driver
             * de la base de datos que se utilizara para la conexion
             */
            Class.forName(DRIVER);
            /*
             * En la variable cadena se almacena la conexion a la base de datos
             * Se utiliza el metodo getConnection de la clase DriverManager
             * Se envia como parametros la url de la base de datos, el usuario y la
             * contraseña
             */
            this.cadena = DriverManager.getConnection(URL + BD, USUARIO, PASSWORD);
        }
        /*
         * En el caso de que no se pueda realizar la conexion a la base de datos
         * se muestra un mensaje con el error y se cierra la aplicacion
         * ClassNotFoundException es la excepcion que se lanza cuando no se encuentra
         * la clase que se esta intentando cargar
         * SqlException es la excepcion que se lanza cuando ocurre un error en la
         * conexion
         * a la base de datos
         */
        catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        /*
         * Se retorna la variable cadena que contiene la conexion a la base de datos
         */
        return this.cadena;

    }

    /*
     * Metodo desconectar que se utiliza para cerrar la conexion a la base de datos
     */
    public void desconectar() {
        try {
            /*
             * El metodo close de la clase Connection se utiliza para cerrar la conexion
             */
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void setSavepoint() {
        try {
            savepoint = this.cadena.setSavepoint();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Savepoint getSavepoint() {
        return savepoint;
    }

    public DatabaseMetaData getMetadata(Connection connection) {
        try {
            this.dbmd = connection.getMetaData();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this.dbmd;
    }

    public void setAutoCommit(boolean estado) {
        try {
            this.cadena.setAutoCommit(estado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void releaseSavepoint() {
        try {
            cadena.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void rollbackToSavepoint(Savepoint savepoint) {
        try {
            if (savepoint != null) {
                cadena.rollback(savepoint);
            } else {
                System.out.println("Savepoint is null. No rollback performed.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void commit() {
        try {
            if (savepoint != null) {
                cadena.commit();
            } else {
                System.out.println("Savepoint is null. No commit performed.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*
     * Metodo getInstancia que se utiliza para obtener la instancia de la clase
     * Conexion
     * Se utiliza synchronized para que solo se pueda acceder a la instancia de la
     * clase desde un solo hilo
     * 
     * This method is an example of the Singleton Design Pattern. This pattern
     * restricts the instantiation of a class and ensures that only one instance of
     * the class exists in the Java Virtual Machine (JVM). The pattern also provides
     * a global point of access to the object.
     * 
     * The getInstancia() method is static, meaning it belongs to the Conexion class
     * and not to any instance of the class. It returns the single instance of the
     * Conexion class.
     * 
     * The synchronized keyword in the method declaration ensures that only one
     * thread at a time can execute this method. This is important in multi-threaded
     * environments to prevent two threads from creating two different instances of
     * the Conexion class.
     * 
     * Inside the method, it checks if instancia (the static variable of type
     * Conexion) is null. If it is null, it creates a new Conexion object and
     * assigns it to instancia. If instancia is not null, it means the single
     * instance of Conexion has already been created, so it just returns this
     * existing instance.
     * 
     * This way, no matter how many times you call Conexion.getInstancia(), you
     * always get the same instance of the Conexion class. This is useful for things
     * like database connections, where you typically want to share a single
     * connection throughout your application.
     */
    public synchronized static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

}
