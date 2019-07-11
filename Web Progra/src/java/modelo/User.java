package modelo;

import java.util.ArrayList;


/**
 * Class User
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
    
    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param id
     * @param name
     * @param secondName
     * @param email
     * @param provincia
     * @param canton
     * @param distrito
     * @param password
     * @param listaProductos
     */
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

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     *
     * @param secondName
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     *
     * @param provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     *
     * @return
     */
    public String getCanton() {
        return canton;
    }

    /**
     *
     * @param canton
     */
    public void setCanton(String canton) {
        this.canton = canton;
    }

    /**
     *
     * @return
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     *
     * @param distrito
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    /**
     *
     * @param listaProductos
     */
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     *
     * @return
     */
    public ArrayList<Producto> getProductosSolicitados() {
        return productosSolicitados;
    }

    /**
     *
     * @param productosSolicitados
     */
    public void setProductosSolicitados(ArrayList<Producto> productosSolicitados) {
        this.productosSolicitados = productosSolicitados;
    }

    /**
     *
     * @return
     */
    public ArrayList<Producto> getProductoIntercambiar() {
        return productoIntercambiar;
    }

    /**
     *
     * @param productoIntercambiar
     */
    public void setProductoIntercambiar(ArrayList<Producto> productoIntercambiar) {
        this.productoIntercambiar = productoIntercambiar;
    }
    
    /**
     *
     * @param producto
     */
    public void agregarProducto(Producto producto){
        listaProductos.add(producto);
    }
    
    /**
     *
     * @param producto
     * @return
     */
    public boolean eliminarProducto(Producto producto){
        return listaProductos.remove(producto);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", secondName=" + secondName + ", email=" + email + ", provincia=" + provincia + ", canton=" + canton + ", distrito=" + distrito + ", password=" + password + ", listaProductos=" + listaProductos + ", productosSolicitados=" + productosSolicitados + ", pendientesTrueque=" + productoIntercambiar + '}';
    }
}
