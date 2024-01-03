package datos.interfaces;

import java.util.List;

/*
 * Las interfaces son como clases abstractas pero con metodos abstractos
 * y constantes, no se pueden crear objetos de una interfaz
 * En una interfaz todos los metodos son publicos y abstractos
 * y las variables son publicas, estaticas y finales
 * 
 * En este caso se crea una interfaz generica, que puede ser usada por
 * cualquier clase, pero se debe especificar el tipo de dato que se va a usar
 * en la clase que implemente esta interfaz
 * 
 * En este caso se usa la interfaz para crear un CRUD generico de operaciones
 * basicas de una tabla de la base de datos
 * 
 * CRUD: Create, Read, Update, Delete
 * 
 * Esta interfaz sera utilizada por las clases de Data Access Object (DAO)
 * para crear las clases que interactuan con la base de datos
 */
public interface CrudSimpleInterface<T> {
    public List<T> listar(String texto);
    public boolean insertar(T obj);
    public boolean actualizar(T obj);
    public boolean desactivar(int id);
    public boolean activar(int id);
    public int total();
    public boolean existe(String texto);
}