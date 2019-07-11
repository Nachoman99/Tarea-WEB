package DAO;

import java.util.List;
import modelo.Producto;
import modelo.User;

/**
 * User Interface
 *
 * @author Kevin Trejos
 */
public interface UserInterface {

    /**
     * List to the User
     *
     * @return A List
     */
    List<User> listar();

    /**
     * Method to register
     *
     * @param user User to register
     * @return true if registered well, false otherwise
     */
    boolean registrarse(User user);

    /**
     * Insert a product
     *
     * @param producto Product to insert
     * @param userID id of the user
     */
    void insertarProducto(Producto producto, String userID);

    /**
     * Barter application
     *
     * @param productoUsuario Product to change
     * @param solicitado Product requested
     * @param userID User Id
     */
    void insertarSolicitud(Producto productoUsuario, Producto solicitado, String userID);

    /**
     * Enter
     *
     * @param email email of the User
     * @param password password of the User
     * @return User
     */
    User signIn(String email, String password);

    /**
     * Reject barter
     *
     * @param productoBorrar product to erase
     * @param userID id of the user
     */
    void rechazar(Producto productoBorrar, String userID);

    /**
     * accept barter
     *
     * @param productoAceptar product to accept
     * @param userID id of the User
     */
    void accept(Producto productoAceptar, String userID);
}
