/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class ManejoXML {
    
    XMLEncoder encoder = null;
    XMLDecoder decoder = null;
    
    public void write(String ruta, User user) throws FileNotFoundException{
        encoder = new XMLEncoder(new FileOutputStream(ruta, true));
        encoder.writeObject(user);
        encoder.close();
    }
    
    public User read(String ruta) throws FileNotFoundException{
        decoder = new XMLDecoder(new FileInputStream(ruta));
        User user = (User) decoder.readObject();
        decoder.close();
        return user;
    }
    
    
    /*
    XMLEncoder encoder = null;
        XMLDecoder decoder = null;

        //Aplicable también para un objeto que tenga una lista como atributo, para Serializar o Deserializar conjuntos de datos
        try {
            //Serialization: Java object to XML 
            encoder = new XMLEncoder(new FileOutputStream("files/xmlFile.xml"));
            encoder.writeObject(new User("Andrea Araya", "andrea.araya@email.com", 26, true));
            encoder.close();

            //Deserialization: XML to Java object
            decoder = new XMLDecoder(new FileInputStream("files/xmlFile.xml"));
            System.out.println((User) decoder.readObject());
            decoder.close();
        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
            ex.printStackTrace();
        }
    */
}
