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
    private boolean isPrimeraVez = true;

    private void createEncoder(String ruta) throws FileNotFoundException {
        if (isPrimeraVez) {
            encoder = new XMLEncoder(new FileOutputStream(ruta, true));
            isPrimeraVez = false;
        }
        ArrayList<User> list = readAll(ruta);
        System.out.println("Perro");
        if (list.size() > 0 && !isPrimeraVez) {
            //encoder = new XMLEncoder(new FileOutputStream(ruta, true));
            System.out.println("Gola");
        }
    }

    private void createDecoder(String ruta) throws FileNotFoundException {
        decoder = new XMLDecoder(new FileInputStream(ruta));
    }

    /**
     *
     */
    public void closeEncoder() {
        encoder.close();
    }

    /**
     *
     * @param ruta
     * @param user
     * @throws FileNotFoundException
     */
    public void write(String ruta, User user) throws FileNotFoundException {
        if (encoder == null) {
            createEncoder(ruta);
            encoder.writeObject(user);
        } else if (encoder != null) {
            encoder.writeObject(user);
        }
    }

    /**
     *
     * @param ruta
     * @return
     * @throws FileNotFoundException
     */
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

    /**
     *
     * @param ruta
     * @return
     */
    public ArrayList<User> readAll(String ruta) {
        ArrayList<User> listUser = new ArrayList<>();
        User user = null;
        boolean continuar = true;
        try {
            if (decoder == null) {
                createDecoder(ruta);
                while (continuar) {
                    user = (User) decoder.readObject();
                    listUser.add(user);
                }
                decoder.close();
                return listUser;
            } else {
                while (continuar) {
                    user = (User) decoder.readObject();
                    listUser.add(user);
                }
                decoder.close();
                return listUser;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            continuar = false;
        }catch( FileNotFoundException o){
            System.out.println("No se ha creado el archivo con los datos");            
        }
        return listUser;
    }
}
