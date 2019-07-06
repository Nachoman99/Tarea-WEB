/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;


/**
 *
 * @author Kevin Trejos
 */
public class User {
    
    private String id;
    private String name;
    private String secondName;
    private String email;
    private String provincia;
    private String canton;
    private String distrito;
    private String password; 
    private ArrayList<Producto> listaProductos;
    private ArrayList<Producto> productosSolicitados;
    private ArrayList<Producto> productoIntercambiar;
    
    public User() {
    }

    public User(String id, String name, String secondName, String email, String provincia, String canton, String distrito, String password, ArrayList<Producto> listaProductos) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.password = password;
        this.listaProductos = listaProductos;
        this.productosSolicitados = new ArrayList<>();
        this.productoIntercambiar = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Producto> getProductosSolicitados() {
        return productosSolicitados;
    }

    public void setProductosSolicitados(ArrayList<Producto> productosSolicitados) {
        this.productosSolicitados = productosSolicitados;
    }

    public ArrayList<Producto> getProductoIntercambiar() {
        return productoIntercambiar;
    }

    public void setProductoIntercambiar(ArrayList<Producto> productoIntercambiar) {
        this.productoIntercambiar = productoIntercambiar;
    }
    
    public void agregarProducto(Producto producto){
        listaProductos.add(producto);
    }
    
    public boolean eliminarProducto(Producto producto){
        return listaProductos.remove(producto);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", secondName=" + secondName + ", email=" + email + ", provincia=" + provincia + ", canton=" + canton + ", distrito=" + distrito + ", password=" + password + ", listaProductos=" + listaProductos + ", productosSolicitados=" + productosSolicitados + ", pendientesTrueque=" + productoIntercambiar + '}';
    }
}
