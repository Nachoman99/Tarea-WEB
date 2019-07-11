package DAO;

import Archivos.FileManagement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Producto;
import modelo.User;

/**
 * Class that handles all User methods to modify within the file
 *
 * @author Kevin Trejos
 */
public class UserDAO implements UserInterface {

    private List<User> users;
    private FileManagement json = new FileManagement();

    /**
     * Constructor that matches a User list by the DataArray instance
     *
     * @throws IOException
     */
    public UserDAO() throws IOException {
        users = DatosArray.getInstance().users;
    }

    /**
     * Search for a product by its id
     *
     * @param consecutive the consecutive number of the product to search
     * @return he product found, null if you can not find it
     */
    public Producto searchProduct(int consecutive) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            ArrayList<Producto> list = next.getListaProductos();
            Iterator<Producto> it = list.iterator();
            while (it.hasNext()) {
                Producto next1 = it.next();
                if (consecutive == next1.getNumeroConsecutivo()) {
                    return next1;
                }
            }
        }
        return null;
    }

    /**
     * Search for a User
     *
     * @param id id of the user to search
     * @return The user found, null if you can not find it
     */
    public User searchUser(String id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if (next.getId().equals(id)) {
                System.out.println("User " + next);
                return next;
            }
        }
        return null;
    }

    /**
     * Method for when a user registers and write it in the file
     *
     * @param user user to register
     * @return true if you registered it, false otherwise
     */
    @Override
    public boolean registrarse(User user) {
        boolean repeat = false;
        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getId().equals(user.getId())) {
                repeat = true;
            }
        }
        if (!repeat) {
            try {
                User userCopy = new User(user.getId(), user.getName(), user.getSecondName(), user.getEmail(), user.getProvincia(), user.getCanton(), user.getDistrito(), user.getPassword(), new ArrayList<>());
                json.write("jsonFile.json", userCopy);
                return users.add(userCopy);
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * method that returns all the users that are in the file
     *
     * @return all the users that are in the file
     */
    @Override
    public List<User> listar() {
        return users;
    }

    /**
     * Method for when a user enters and verify if the data is correct
     *
     * @param email email of the User
     * @param password password of the User
     * @return the User who entered
     */
    @Override
    public User signIn(String email, String password) {
        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getEmail().equals(email) && next.getPassword().equals(password)) {
                return next;
            }
        }
        return null;
    }

    /**
     * Method to insert a product in the list of products of a specific User
     *
     * @param producto product to insert
     * @param userID id of the user to whom the product will be inserted
     */
    @Override
    public void insertarProducto(Producto producto, String userID) {
        int cantidad = 0;
        Iterator<User> iteradorN = users.iterator();
        while (iteradorN.hasNext()) {
            User next = iteradorN.next();
            cantidad += next.getListaProductos().size();

        }
        if (cantidad == 0) {
            producto.setNumeroConsecutivo(1);
        } else {
            producto.setNumeroConsecutivo(cantidad + 1);
        }
        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getId().equals(userID)) {
                next.agregarProducto(producto);
            }
        }
        List<User> listaAux = new ArrayList<>();
        Iterator<User> iterador2 = users.iterator();
        while (iterador2.hasNext()) {
            User next = iterador2.next();
            listaAux.add(next);
        }
        try {
            json.deleteFile("jsonFile.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Iterator<User> iterador3 = listaAux.iterator();
        while (iterador3.hasNext()) {
            User next = iterador3.next();
            try {
                json.write("jsonFile.json", next);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            users = DatosArray.getInstance().users;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ask the User for a product for barter
     *
     * @param productoUsuario Product to be exchanged
     * @param solicitado Product requested
     * @param userID id of the User to request barter
     */
    @Override
    public void insertarSolicitud(Producto productoUsuario, Producto solicitado, String userID) {
        productoUsuario.setEstadoTrueque(2);
        solicitado.setEstadoTrueque(1);

        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getId().equals(userID)) {
                next.getProductosSolicitados().add(solicitado);
                next.getProductoIntercambiar().add(productoUsuario);
            }

        }

        String userIDSolicitado = "";
        Iterator<User> itera = users.iterator();
        while (itera.hasNext()) {
            User next = itera.next();
            for (int i = 0; i < next.getListaProductos().size(); i++) {
                if (solicitado.getNumeroConsecutivo() == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                    userIDSolicitado = next.getId();
                }
            }
        }

        Iterator<User> itera2 = users.iterator();
        while (itera2.hasNext()) {
            User next = itera2.next();
            if (next.getId().equals(userIDSolicitado)) {
                next.getProductosSolicitados().add(productoUsuario);
                next.getProductoIntercambiar().add(solicitado);
            }

        }

        List<User> listaAux = new ArrayList<>();
        Iterator<User> iterador2 = users.iterator();
        while (iterador2.hasNext()) {
            User next = iterador2.next();
            listaAux.add(next);
        }
        try {
            json.deleteFile("jsonFile.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Iterator<User> iterador3 = listaAux.iterator();
        while (iterador3.hasNext()) {
            User next = iterador3.next();
            try {
                json.write("jsonFile.json", next);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            users = DatosArray.getInstance().users;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to use if the User accepts the barter
     *
     * @param productoAceptar Product to receive
     * @param userID id of the user
     */
    public void accept(Producto productoAceptar, String userID) {
        int consecProduct = 0;
        int consecProductUser = 0;

        Producto productoCambiar1 = null;
        Producto productoCambiar2 = null;
        Iterator<User> iterador4 = users.iterator();
        while (iterador4.hasNext()) {
            User next = iterador4.next();
            for (int i = 0; i < next.getProductoIntercambiar().size(); i++) {
                if (productoAceptar.getNumeroConsecutivo() == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                    productoCambiar1 = next.getListaProductos().remove(i); //ProductoCambiar1 == celular
                    System.out.println("Producto1 " + productoCambiar1);
                }
            }
            for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                if (productoAceptar.getNumeroConsecutivo() == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                    productoCambiar2 = next.getListaProductos().remove(i);  //Producto cambiar2 == tele 
                    System.out.println("Producto2 " + productoCambiar2);
                }
            }
        }

        Iterator<User> iterador5 = users.iterator();
        while (iterador5.hasNext()) {
            User next = iterador5.next();
            for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                if (productoAceptar.getNumeroConsecutivo() == next.getProductoIntercambiar().get(i).getNumeroConsecutivo()) {
                    productoCambiar2.setEstadoTrueque(2);
                    productoCambiar2.setAceptadoPrimeraVez(true);
                    next.getListaProductos().add(productoCambiar2);
                }
                if (productoAceptar.getNumeroConsecutivo() == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                    productoCambiar1.setEstadoTrueque(2);
                    next.getListaProductos().add(productoCambiar1);
                }
            }
        }

        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getId().equals(userID)) {
                for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                    if (productoAceptar.getNumeroConsecutivo() == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                        consecProduct = next.getProductosSolicitados().get(i).getNumeroConsecutivo();
                        consecProductUser = next.getProductoIntercambiar().get(i).getNumeroConsecutivo();
                        next.getProductoIntercambiar().remove(i);
                        next.getProductosSolicitados().remove(i);
                    }
                }
                for (int i = 0; i < next.getListaProductos().size(); i++) {
                    if (consecProductUser == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                        next.getListaProductos().get(i).setEstadoTrueque(2);
                    }
                }
            }
        }

        Iterator<User> iterador2 = users.iterator();
        while (iterador2.hasNext()) {
            User next = iterador2.next();
            for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                if (consecProduct == next.getProductoIntercambiar().get(i).getNumeroConsecutivo()) {
                    next.getProductoIntercambiar().remove(i);
                    next.getProductosSolicitados().remove(i);
                }
                for (int j = 0; j < next.getListaProductos().size(); j++) {
                    if (next.getListaProductos().get(j) != null) {
                        if (consecProduct == next.getListaProductos().get(j).getNumeroConsecutivo()) {
                            next.getListaProductos().get(i).setEstadoTrueque(2);
                        }
                    }
                }
            }
        }

        Iterator<User> iterador3 = users.iterator();
        while (iterador3.hasNext()) {
            User next = iterador3.next();
            for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                if (consecProduct == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                    next.getProductoIntercambiar().remove(i);
                    next.getProductosSolicitados().remove(i);
                }
            }
        }

        List<User> listaAux = new ArrayList<>();
        Iterator<User> iteradorUser = users.iterator();
        while (iteradorUser.hasNext()) {
            User next = iteradorUser.next();
            listaAux.add(next);
        }
        try {
            json.deleteFile("jsonFile.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Iterator<User> iteradorList = listaAux.iterator();
        while (iteradorList.hasNext()) {
            User next = iteradorList.next();
            try {
                json.write("jsonFile.json", next);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            users = DatosArray.getInstance().users;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to use if the user rejects the barter
     *
     * @param productoBorrar product to refuse
     * @param userID id of the user
     */
    @Override
    public void rechazar(Producto productoBorrar, String userID) {
        int posicion = 0;
        int idOtroProducto = 0;
        int idProductoUsuario = 0;
        Iterator<User> iterador = users.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getId().equals(userID)) {
                for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                    if (productoBorrar.getNumeroConsecutivo() == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                        idOtroProducto = next.getProductosSolicitados().get(i).getNumeroConsecutivo();
                        idProductoUsuario = next.getProductoIntercambiar().get(i).getNumeroConsecutivo();
                        posicion = i;

                    }
                }

                next.getProductoIntercambiar().remove(posicion);
                next.getProductosSolicitados().remove(posicion);

                for (int i = 0; i < next.getListaProductos().size(); i++) {
                    if (idProductoUsuario == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                        next.getListaProductos().get(i).setEstadoTrueque(0);
                    }
                }
            }
        }

        Iterator<User> itera = users.iterator();
        while (itera.hasNext()) {
            User next = itera.next();
            for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                if (idOtroProducto == next.getProductoIntercambiar().get(i).getNumeroConsecutivo()) {
                    next.getProductoIntercambiar().remove(posicion);
                    next.getProductosSolicitados().remove(posicion);

                    for (int o = 0; o < next.getListaProductos().size(); o++) {
                        if (idOtroProducto == next.getListaProductos().get(o).getNumeroConsecutivo()) {
                            next.getListaProductos().get(o).setEstadoTrueque(3);
                            next.getListaProductos().get(o).setRechazado(true);
                        }
                    }

                }
            }
        }

        Iterator<User> itera2 = users.iterator();
        while (itera2.hasNext()) {
            User next = itera2.next();
            for (int i = 0; i < next.getProductosSolicitados().size(); i++) {
                if (idOtroProducto == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                    next.getProductoIntercambiar().remove(posicion);
                    next.getProductosSolicitados().remove(posicion);
                }
            }
        }

        List<User> listaAux = new ArrayList<>();
        Iterator<User> iterador2 = users.iterator();
        while (iterador2.hasNext()) {
            User next = iterador2.next();
            listaAux.add(next);
        }
        try {
            json.deleteFile("jsonFile.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Iterator<User> iterador3 = listaAux.iterator();
        while (iterador3.hasNext()) {
            User next = iterador3.next();
            try {
                json.write("jsonFile.json", next);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            users = DatosArray.getInstance().users;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change status from accepted to false
     *
     * @param userID id of the user
     */
    public void cambiarAceptadoFalso(String userID) {

        Iterator<User> iterador1 = users.iterator();
        while (iterador1.hasNext()) {
            User next = iterador1.next();
            for (int i = 0; i < next.getListaProductos().size(); i++) {
                if (next.getListaProductos().get(i).isAceptadoPrimeraVez()) {
                    next.getListaProductos().get(i).setAceptadoPrimeraVez(false);
                }
            }
        }

        List<User> listaAux = new ArrayList<>();
        Iterator<User> iterador2 = users.iterator();
        while (iterador2.hasNext()) {
            User next = iterador2.next();
            listaAux.add(next);
        }
        try {
            json.deleteFile("jsonFile.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Iterator<User> iterador3 = listaAux.iterator();
        while (iterador3.hasNext()) {
            User next = iterador3.next();
            try {
                json.write("jsonFile.json", next);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            users = DatosArray.getInstance().users;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the status of rejected to false
     *
     * @param userID id of the user
     */
    public void cambiarRechazadoFalso(String userID) {

        Iterator<User> iterador1 = users.iterator();
        while (iterador1.hasNext()) {
            User next = iterador1.next();
            for (int i = 0; i < next.getListaProductos().size(); i++) {
                if (next.getListaProductos().get(i).isRechazado()) {
                    next.getListaProductos().get(i).setRechazado(false);
                    next.getListaProductos().get(i).setEstadoTrueque(0);
                }
            }
        }

        List<User> listaAux = new ArrayList<>();
        Iterator<User> iterador2 = users.iterator();
        while (iterador2.hasNext()) {
            User next = iterador2.next();
            listaAux.add(next);
        }
        try {
            json.deleteFile("jsonFile.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Iterator<User> iterador3 = listaAux.iterator();
        while (iterador3.hasNext()) {
            User next = iterador3.next();
            try {
                json.write("jsonFile.json", next);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            users = DatosArray.getInstance().users;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
