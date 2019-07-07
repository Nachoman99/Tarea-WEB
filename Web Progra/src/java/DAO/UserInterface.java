/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public interface UserInterface {
    
    List<User> listar();
    boolean registrarse(User user);
    boolean actualizar(User user);
    void insertarProducto(Producto producto, String userID);
    void insertarSolicitud(Producto productoUsuario, Producto solicitado,String userID);
    User signIn(String email, String password);
    void rechazar(Producto productoBorrar,String userID);
}
