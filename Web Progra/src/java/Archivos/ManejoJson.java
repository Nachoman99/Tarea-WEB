/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class ManejoJson {
    
    BufferedWriter output = null;
    BufferedReader input = null;
    Gson gson = new Gson();
    
    private void createDocument(String ruta) throws IOException{
        output = new BufferedWriter(new FileWriter(ruta, true));
    }
    
    private void closeWriter() throws IOException{
        output.close();
    }
    
    public User readFirst(String ruta) throws IOException{
        input = new BufferedReader(new FileReader(ruta));
        User user = gson.fromJson(input.readLine(), User.class);
        input.close();
        return user;
    }
    
    public void write(String ruta, User user) throws IOException{
        createDocument(ruta);
        output.write(gson.toJson(user));
        output.write("\n");
        closeWriter();
    }
    
    public ArrayList<User> readAll(String ruta) throws IOException{
        ArrayList<User> list = new ArrayList<>();
        User user;
        try {
           input = new BufferedReader(new FileReader(ruta)); 
        } catch (FileNotFoundException e) {
            createDocument(ruta);
            return list;
        }
        
        
        while ((user = gson.fromJson(input.readLine(), User.class)) != null) {            
            list.add(user);
        }
        input.close();
        return list;
    }
}
