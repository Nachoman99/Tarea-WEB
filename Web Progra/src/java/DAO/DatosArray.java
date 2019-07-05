/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Archivos.ManejoArchivos;
import Archivos.ManejoJson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Nacho
 */
public class DatosArray {

    private static DatosArray instance;
    public List<User> alumnos;
    public ManejoJson json = new ManejoJson();
//    public ManejoArchivos archivos = new ManejoArchivos();

    private DatosArray() throws IOException  {
        
//        try {
//            ArrayList<String> listaImages = new ArrayList<>();
//            ArrayList<String> listaImages2 = new ArrayList<>();
//            String img = "JPG";
//            String img2 = "JPG2";
//            listaImages2.add(img);
//            listaImages2.add(img2);
//            String image1 = "Imagen1";
//            String image2 = "Imagen2";
//            listaImages.add(image1);
//            listaImages.add(image2);
//            Producto pro2 = new Producto(listaImages2, "PC", "HP", "Estudio", 1000000, 2);
//            Producto pro1 = new Producto(listaImages, "TELE", "SAMSUMG", "Hogar", 1000, 1);
//            ArrayList<Producto> lista = new ArrayList<>();
//            lista.add(pro1);
//            lista.add(pro2);
//            User user = new User("123", "Juan", "Trejos", "qwer", "Puntarenas", "Montes", "Union", "123", lista);
//            json.write("jsonFile.json", user);
            alumnos = json.readAll("jsonFile.json");
            
////            archivos.escribirArchivo("src/Prueba.txt", user);
//            alumnos = archivos.leerArchivo("Prueba.txt");
//        }catch(Exception e){
//            
//        }
    }
    
    public static DatosArray getInstance() throws IOException{
        if (instance == null) {
            instance = new DatosArray();
        }
        return instance;
    }

}

