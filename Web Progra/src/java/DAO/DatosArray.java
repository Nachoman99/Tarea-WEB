/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Archivos.ManejoArchivos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.User;

/**
 *
 * @author Nacho
 */
public class DatosArray {
    private static DatosArray instance ;
    public List <User> alumnos;
    public ManejoArchivos archivos = new ManejoArchivos();
    
    
    private DatosArray(){
        try {
           alumnos  =archivos.leerArchivo("Prueba.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException o){
            o.printStackTrace();
        }
        
    }
    
    public static DatosArray getInstance() {
        if (instance == null) {
            instance = new DatosArray();
        }
        return instance;
    }
    
    
}
