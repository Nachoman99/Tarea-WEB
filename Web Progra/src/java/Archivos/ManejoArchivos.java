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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        String[] listImages;
        String image1 = "";
        String image2 = "";
        String image3 = "";
        String image4 = "";
        String image5 = "";
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
            prosucts = productos.split("-");
            images = prosucts[0];
            descCorta = prosucts[1];
            descLarga = prosucts[2];
            categoria = prosucts[3];
            precio = Integer.parseInt(prosucts[4]);
            consecutivo = Integer.parseInt(prosucts[5]);
            listImages = images.split("#");
            for (int i = 0; i < listImages.length; i++) {
                if (listImages[i] != null && i == 0) {
                    image1 = listImages[0];                        
                }
                if (listImages[i] != null && i == 1) {
                    image2 = listImages[1];
                }
                if (listImages[i] != null && i == 2) {
                    image3 = listImages[2];
                }
                if (listImages[i] != null && i == 3) {
                    image4 = listImages[3];
                }
                if (listImages[i] != null && i == 4) {
                    image5 = listImages[4];
                }
            }
            if (image1 != null) {
                listaImages.add(image1);
            }if(image2 != null){
                listaImages.add(image2);
            }if (image3 != null) {
                listaImages.add(image3);
            }if (image4 != null) {
                listaImages.add(image4);
            }if (image5 != null) {
                listaImages.add(image5);
            }
            Producto producto = new Producto(listaImages, descCorta, descLarga, categoria, precio, consecutivo);
            listaProductos.add(producto);
            User user = new User(id, nombre, secondName, email, provincia, canton, distrito, password, listaProductos);
            list.add(user);
        }
        return list;
    }

    public String conversor(User user) {
        String strImages = "";
        String strProducts = "";
        ArrayList<String> imagesList;
        for (int i = 0; i < user.getListaProductos().size(); i++) {
            imagesList = user.getListaProductos().get(i).getListaImagenes();
            for (int j = 0; j < imagesList.size(); j++) {
//                if (imagesList.get(j) != null) {
                strImages += "#" + imagesList.remove(j);
//                }
            }
            strProducts += strImages + "-" + user.getListaProductos().get(i).getDescripcionCorta() + "-" + user.getListaProductos().get(i).getDescripcionDetallada() + "-"
                    + user.getListaProductos().get(i).getCategoria() + "-" + user.getListaProductos().get(i).getPrecio() + "-" + user.getListaProductos().get(i).getNumeroConsecutivo() + "-";
        }
        return strProducts;
    }

    public void escribirArchivo(String ruta, User user) throws FileNotFoundException, IOException {
        File file = new File(ruta);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(user.getId() + "," + user.getName() + "," + user.getSecondName() + "," + user.getEmail() + "," + user.getProvincia() + "," + user.getCanton() + ","
                + user.getDistrito() + "," + user.getPassword() + "," + conversor(user));
        writer.close();

        /*
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.password = password;
        this.listaProductos = listaProductos;
         */
//        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
//        writer.writeObject(user);
//        writer.close();
//        try (FileWriter fw = new FileWriter(ruta, true);
//            ObjectOutputStream writer = new ObjectOutputStream(out)
//            BufferedWriter bw = new BufferedWriter(fw);
//            PrintWriter out = new PrintWriter(bw)) {
//           // bw.write(ruta);
//            //more code
//            //out.println("more text");
//            out.close();
//            out.print(user);
//            bw.close();
//        } catch (IOException e) {
//            //exception handling left as an exercise for the reader
//        }
    }
}
