/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Archivos.ManejoArchivos;
import Archivos.FileManagement;
import Archivos.ManejoXML;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class Main {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        ManejoXML xml = null;
        xml = new ManejoXML();
        // ManejoArchivos archivos = new ManejoArchivos();
        ArrayList<String> listaImages = new ArrayList<>();
        ArrayList<String> listaImages2 = new ArrayList<>();
        String img = "JPG";
        String img2 = "JPG2";
        listaImages2.add(img);
        listaImages2.add(img2);
        String image1 = "Imagen1";
        String image2 = "Imagen2";
        listaImages.add(image1);
        listaImages.add(image2);
        Producto pro2 = new Producto(listaImages2, "PC", "HP", "Estudio", 1000000, 2);
        Producto pro1 = new Producto(listaImages, "TELE", "SAMSUMG", "Hogar", 1000, 1);
        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(pro1);
        lista.add(pro2);
        User user = new User("123", "Juan", "Trejos", "qwer", "Puntarenas", "Montes", "Union", "123", lista);
        //archivos.escribirArchivo("Prueba.txt", user);
//        System.out.println(archivos.leerArchivo("Prueba.txt"));
        ////////////////////////
        ArrayList<Producto> listaProd = new ArrayList<>();
        Producto pr = new Producto(null, "Celular", "Huawei", "Entretenimiento", 2000, 3);
        listaProd.add(pr);
        User user2 = new User("098", "Kevin", "trejos", "kevin.trejos", "Alajuela", "San ramón", "San Ramón", "qwer", listaProd);
        User user3 = new User("4321", "Fernanda", "Herrera", "Fer12", "Alajuela", "san Ramon", "SAn ramon", "Fer1233", listaProd);
        FileManagement json = new FileManagement();
        
        try {
            json.write("files/jsonFile.json", user);
            json.write("files/jsonFile.json", user2);
            json.write("files/jsonFile.json", user3);
            ArrayList<User> listaUser = json.readAll("files/jsonFile.json");
            System.out.println(listaUser);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
