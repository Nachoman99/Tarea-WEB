/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
 
import java.util.ArrayList;
import java.util.List;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class UserDAO implements UserInterface{

    private List<User> users;

    public UserDAO() {
        users= DatosArray.getInstance().alumnos;
    }
    
    
    
    @Override
    public void registrarse(User user) {
        users.add(user);
    }

    @Override
    public List<User> listar() {
        return users;
    }

    @Override
    public boolean actualizar(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarProducto(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
