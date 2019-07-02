/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class ManejoArchivos {

    public ArrayList<User> leerArchivo(String ruta) throws FileNotFoundException, IOException {
        ArrayList<User> list = new ArrayList<>();
        File file = new File(ruta);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = "";
        String[] parts;
        String[] prosucts;
        String id = "";
        String nombre = "";
        String secondName = "";
        String email = "";
        String provincia = "";
        String canton = "";
        String distrito = "";
        String password = "";
        String productos = "";
        String images = "";
        String descCorta = "";
        String descLarga = "";
        String categoria = "";
        int precio = 0;
        int consecutivo = 0;
        String[] listImages = null;
        String image1 = "";
        String image2 = "";
        String image3 = "";
        String image4 = "";
        String image5 = "";
        String producto1;
        String[] partsProducto;
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ArrayList<String> listaImages = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            parts = str.split(",");
            id = parts[0];
            nombre = parts[1];
            secondName = parts[2];
            email = parts[3];
            provincia = parts[4];
            canton = parts[5];
            distrito = parts[6];
            password = parts[7];
            productos = parts[8];
            prosucts = productos.split("¿");
            for (int i = 0; i < prosucts.length; i++) {
                if (prosucts[i] != null) {
                    producto1 = prosucts[i];
                    partsProducto = producto1.split("-");
                    descCorta = partsProducto[0];
                    descLarga = partsProducto[1];
                    precio = Integer.parseInt(partsProducto[3]);
                    consecutivo = Integer.parseInt(partsProducto[4]);
                    images = partsProducto[5];
                    listImages = images.split("#");

                    for (int j = 0; j < listImages.length; j++) {
                        if (listImages[j] != null && j == 0) {
                            image1 = listImages[0];
                        }
                        if (listImages[j] != null && j == 1) {
                            image2 = listImages[1];
                        }
                        if (listImages[j] != null && j == 2) {
                            image3 = listImages[2];
                        }
                        if (listImages[j] != null && j == 3) {
                            image4 = listImages[3];
                        }
                        if (listImages[j] != null && j == 4) {
                            image5 = listImages[4];
                        }
                    }
                    listaImages = new ArrayList<>();
                    if (image1 != null && !listaImages.contains(image1)) {
                        listaImages.add(image1);
                    }
                    if (image2 != null && !listaImages.contains(image2)) {
                        listaImages.add(image2);
                    }
                    if (image3 != null && !listaImages.contains(image3)) {
                        listaImages.add(image3);
                    }
                    if (image4 != null && !listaImages.contains(image4)) {
                        listaImages.add(image4);
                    }
                    if (image5 != null && !listaImages.contains(image5)) {
                        listaImages.add(image5);
                    }
                    Producto producto = new Producto(listaImages, descCorta, descLarga, categoria, precio, consecutivo);
                    listaProductos.add(producto);
                }
            }
            User user = new User(id, nombre, secondName, email, provincia, canton, distrito, password, listaProductos);
            list.add(user);
        }
        return list;
    }

    public String imprimirValores( ArrayList<User> list){
        String usersList="";
         ArrayList<User> lista = list;
         Iterator<User> iterator = lista.iterator();
         while (iterator.hasNext()) {
            User next = iterator.next();
            usersList+=next.toString()+"\n";
        }
         return usersList;
    }
    
    public String conversor(User user) {
        String strImages = "";
        String strProducts = "";
        String[] parts;
        for (int i = 0; i < user.getListaProductos().size(); i++) {
            if (user.getListaProductos().get(i).getListaImagenes() != null) {
                strImages += user.getListaProductos().get(i).getListaImagenes().toString().replace("[", "").replace("]", "!").replace(",", "#").replace("!", "#");
                strImages += "!";
            }else{
                strImages = "null";
            }
        }
        for (int i = 0; i < user.getListaProductos().size(); i++) {
            parts = strImages.split("!");
            strProducts += user.getListaProductos().get(i).getDescripcionCorta() + "-" + user.getListaProductos().get(i).getDescripcionDetallada() + "-"
                    + user.getListaProductos().get(i).getCategoria() + "-" + user.getListaProductos().get(i).getPrecio() + "-" + user.getListaProductos().get(i).getNumeroConsecutivo() + "-" + parts[i] + "¿";
        }
        return strProducts;
    }

    public void escribirArchivo(String ruta, User user) throws FileNotFoundException, IOException {
        File file = new File(ruta);
        FileWriter wr = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter writer = new BufferedWriter(wr);
        writer.write(user.getId() + "," + user.getName() + "," + user.getSecondName() + "," + user.getEmail() + "," + user.getProvincia() + "," + user.getCanton() + ","
                + user.getDistrito() + "," + user.getPassword() + "," + conversor(user));
        writer.write("\n");
        writer.close();
    }
}
