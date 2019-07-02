/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
 
import java.util.ArrayList;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class UserDAO implements UserInterface{

    private ArrayList<User> users = new ArrayList<>();
    
    @Override
    public void registrarse(User user) {
        users.add(user);
    }
    
}
