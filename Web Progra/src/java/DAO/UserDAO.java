/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
 
import Archivos.ManejoJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class UserDAO implements UserInterface{

    private List<User> users;
    private ManejoJson json = new ManejoJson();
    
    public UserDAO() throws IOException {
        users = DatosArray.getInstance().users;
    }

    @Override
    public boolean registrarse(User user) {
        try {
            User userCopy = new User(user.getId(), user.getName(), user.getSecondName(), user.getEmail(), user.getProvincia(), user.getCanton(), user.getDistrito(), user.getPassword(), new ArrayList<>());
            json.write("jsonFile.json", userCopy);
            return users.add(userCopy);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
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
    public User signIn(String email, String password) {
        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getEmail().equals(email) && next.getPassword().equals(password)) {
                return next;
            }
        }
        return null;
    }

    @Override
    public void insertarProducto(Producto producto, String userID) {
        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getId().equals(userID)) {
                next.agregarProducto(producto);
                try {
                    users = DatosArray.getInstance().users;
                } catch (IOException e) {
                }
                
            }
        }
        
    }
    
}
