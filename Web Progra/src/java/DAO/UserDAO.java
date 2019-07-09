/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Archivos.ManejoJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
public class UserDAO implements UserInterface {

    private List<User> users;
    private ManejoJson json = new ManejoJson();

    public UserDAO() throws IOException {
        users = DatosArray.getInstance().users;
    }

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

    public User search(String id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if (next.getId().equals(id)) {
                System.out.println("User " + next);
                return next;
            }
        }
//        System.out.println("Perra");
        return null;
    }

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

    @Override
    public List<User> listar() {
        return users;
    }

    @Override
    public boolean actualizar(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    @Override
    public void insertarProducto(Producto producto, String userID) {
//         Alumno a = alumnos.get(alumnos.size()-1);
//        Alumno alumnoCopy = new Alumno(a.getId()+1, alumno.getNombre(), alumno.getApellidos());
//        return alumnos.add(alumnoCopy);
//        ArrayList<Producto> producto1 = users.get(users.size()-1).getListaProductos();
//        if(producto1.isEmpty()&&users.size()==1){
//             producto.setNumeroConsecutivo(1);
//        }else{      
//             Producto productoNumero = producto1.get(producto1.size()-1);
//             producto.setNumeroConsecutivo(productoNumero.getNumeroConsecutivo()+1);
//        }
//      
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
//
//    0=normal
//1=pendienteTrueque
//2=aceptado
//3=rechazado

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
                            next.getListaProductos().get(o).setEstadoTrueque(0);
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

    @Override
    public void aceptarTrueque(Producto solicitado, Producto enviado, String userRecibe) {
        solicitado.setEstadoTrueque(2);
        enviado.setEstadoTrueque(2);

        List<User> listaAux = new ArrayList<>();
        Iterator<User> iterador2 = users.iterator();
        while (iterador2.hasNext()) {
            User next = iterador2.next();
            listaAux.add(next);
        }
        String userEnvia = "";
        int consecutive = 0;
        Iterator<User> iterador10 = listaAux.iterator();
        while (iterador10.hasNext()) {
            User next = iterador10.next();
            if (userRecibe.equals(next.getId())) {
                for (int i = 0; i < next.getProductoIntercambiar().size(); i++) {
                    if (next.getProductoIntercambiar().get(i).getEstadoTrueque() == 1) {
                        consecutive = next.getProductoIntercambiar().get(i).getNumeroConsecutivo();
                    }
                }
            }
            for (int i = 0; i < next.getListaProductos().size(); i++) {
                if (next.getListaProductos().get(i).getNumeroConsecutivo() == consecutive) {
                    userEnvia = next.getId();
                }
            }
        }

        Iterator<User> iterador3 = listaAux.iterator();
        while (iterador3.hasNext()) {
            User next = iterador3.next();
            if (userEnvia.equals(next.getId())) {
                for (int i = 0; i < next.getProductoIntercambiar().size(); i++) {
                    if (solicitado.getNumeroConsecutivo() == next.getProductoIntercambiar().get(i).getNumeroConsecutivo()) {
                        next.getProductoIntercambiar().remove(solicitado);
                    }
                    if (enviado.getNumeroConsecutivo() == next.getProductoIntercambiar().get(i).getNumeroConsecutivo()) {
                        next.getProductoIntercambiar().remove(enviado);
                    }
                    if (next.getProductosSolicitados().get(i).getNumeroConsecutivo() == solicitado.getNumeroConsecutivo()) {
                        next.getProductosSolicitados().remove(solicitado);
                    }
                    if (enviado.getNumeroConsecutivo() == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                        next.getProductosSolicitados().remove(enviado);
                    }
                }
            }
            if (userRecibe.equals(next.getId())) {
                for (int i = 0; i < next.getProductoIntercambiar().size(); i++) {
                    if (solicitado.getNumeroConsecutivo() == next.getProductoIntercambiar().get(i).getNumeroConsecutivo()) {
                        next.getProductoIntercambiar().remove(solicitado);
                    }
                    if (enviado.getNumeroConsecutivo() == next.getProductoIntercambiar().get(i).getNumeroConsecutivo()) {
                        next.getProductoIntercambiar().remove(enviado);
                    }
                    if (next.getProductosSolicitados().get(i).getNumeroConsecutivo() == solicitado.getNumeroConsecutivo()) {
                        next.getProductosSolicitados().remove(solicitado);
                    }
                    if (enviado.getNumeroConsecutivo() == next.getProductosSolicitados().get(i).getNumeroConsecutivo()) {
                        next.getProductosSolicitados().remove(enviado);
                    }
                }
            }
        }

        //Producto productoEnviadoENV = null;
        Producto productoSolicitadoENV = null;
        //Producto productoEnviadoREC;
        Producto productoSolicitadoREC = null;
        Iterator<User> iterador5 = listaAux.iterator();
        while (iterador5.hasNext()) {
            User next = iterador5.next();
            if (userEnvia.equals(next.getId())) {
                for (int i = 0; i < next.getListaProductos().size(); i++) {
                    System.out.println("Hola");
                    if (enviado.getNumeroConsecutivo() == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                        next.getListaProductos().remove(i);
                    }
                    if (solicitado.getNumeroConsecutivo() == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                        productoSolicitadoENV = next.getListaProductos().remove(i);
                    }
                    System.out.println("PUto");
                }
            }
            if (userRecibe.equals(next.getId())) {
                for (int i = 0; i < next.getListaProductos().size(); i++) {
                    if (enviado.getNumeroConsecutivo() == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                        next.getListaProductos().remove(i);
                    }
                    if (solicitado.getNumeroConsecutivo() == next.getListaProductos().get(i).getNumeroConsecutivo()) {
                        productoSolicitadoREC = next.getListaProductos().remove(i);
                    }
                }
            }
        }

        Iterator<User> iterador6 = listaAux.iterator();
        while (iterador6.hasNext()) {
            User next = iterador6.next();
            if (userEnvia.equals(next.getId())) {
                next.getListaProductos().add(productoSolicitadoREC);
            }
            if (userRecibe.equals(next.getId())) {
                next.getListaProductos().add(productoSolicitadoENV);
            }
        }

        try {
            json.deleteFile("jsonFile.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //Escribe todo actualizado
        Iterator<User> iterador4 = listaAux.iterator();
        while (iterador4.hasNext()) {
            User next = iterador4.next();
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
