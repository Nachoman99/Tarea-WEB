/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Archivos.ManejoArchivos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class Main {
 
    public static void main(String[] args) throws IOException {
        
        ManejoArchivos archivos = new ManejoArchivos();
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
        archivos.escribirArchivo("Prueba.txt", user);
       // System.out.println(archivos.leerArchivo("Prueba.txt"));
        ////////////////////////
        ArrayList<Producto> listaProd = new ArrayList<>();
        Producto pr = new Producto(null, "Celular", "Huawei", "Entretenimiento", 2000, 3);
        listaProd.add(pr);
        User user2 = new User("098", "Kevin", "trejos", "kevin.trejos", "Alajuela", "San ramón", "San Ramón", "qwer", listaProd);
        archivos.escribirArchivo("Prueba.txt", user2);
    }
}
