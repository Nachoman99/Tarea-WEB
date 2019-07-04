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
import java.util.ArrayList;
import java.util.Iterator;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class ManejoXML {

    XMLEncoder encoder = null;
    XMLDecoder decoder = null;

    private void createEncoder(String ruta) throws FileNotFoundException {
        encoder = new XMLEncoder(new FileOutputStream(ruta, true));
    }

    private void createDecoder(String ruta) throws FileNotFoundException {
        decoder = new XMLDecoder(new FileInputStream(ruta));
    }

    public void closeEncoder() {
        encoder.close();
    }

    public void write(String ruta, User user) throws FileNotFoundException {
        if (encoder == null) {
            createEncoder(ruta);
            encoder.writeObject(user);
        } else if (encoder != null) {
            encoder.writeObject(user);
        }
    }

    public User readFirst(String ruta) throws FileNotFoundException {
        if (decoder == null) {
            createDecoder(ruta);
            User user = (User) decoder.readObject();
            decoder.close();
            return user;
        } else if (decoder != null) {
            User user = (User) decoder.readObject();
            decoder.close();
            return user;
        }
        return null;
    }

    public ArrayList<User> readAll(String ruta) throws FileNotFoundException {
        ArrayList<User> listUser = new ArrayList<>();
        User user;
        if (decoder == null) {
            createDecoder(ruta);
            while ((user = (User) decoder.readObject()) != null) {
                listUser.add(user);
            }
            decoder.close();
            return listUser;
        } else {
            while ((user = (User) decoder.readObject()) != null) {
                listUser.add(user);
            }
            decoder.close();
            return listUser;
        }
    }
}
