/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.User;

/**
 *
 * @author Kevin Trejos
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    public String registrarse = "vistas/Registrarse.jsp";
    public String ingresar = "vistas/Ingresar.jsp";
    public String añadir = "vistas/añadir.jsp";
    public String lista = "vistas/lista.jsp";
    public String perfil = "vistas/perfil.jsp";
    public String trueque = "vistas/trueque.jsp";
    public String menu = "vistas/Menu.jsp";
    public String inicio = "index.jsp";
    public String caracteristicas = "vistas/productoCaracteristicas.jsp";
    public String validarTrueque = "vistas/hacerTrueque.jsp";
    public String notificaciones = "vistas/Notifications.jsp";
    private static boolean emailValid = false;

    public User user = new User();
    public UserDAO dao;

    public Controlador() throws IOException {
        this.dao = new UserDAO();
    }

    public static boolean isEmailValid() {
        return emailValid;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private boolean verifyEmail(String email) {
        if (email.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso = "";
        String accion = request.getParameter("accion");
        System.out.println("La accion es: " + accion);
        if (accion.equalsIgnoreCase("registrarse")) {
            String msg = "Hola";
            request.setAttribute("mensaje", msg);
            acceso = registrarse;

        } else if (accion.equalsIgnoreCase("ingresar")) {
            acceso = ingresar;
        } else if (accion.equalsIgnoreCase("anadir")) {
            acceso = añadir;
        } else if (accion.equalsIgnoreCase("introducir")) {
            //metodo de meter la vara saica
            Producto producto;
            String corta = request.getParameter("txtDescripcionCorta");
            String detallada = request.getParameter("txtDescripcionDetallada");
            int precio = Integer.parseInt(request.getParameter("txtPrecio"));
            String imagen = request.getParameter("txtImagen");
            String categoria = request.getParameter("categoria");
            ArrayList<String> imagenes = new ArrayList<>();
            imagenes.add(imagen);
            producto = new Producto(imagenes, corta, detallada, categoria, precio, 0);
            dao.insertarProducto(producto, user.getId());
            acceso = menu;

        } else if (accion.equalsIgnoreCase("listar")) {
            request.setAttribute("id", user.getId());
            acceso = lista;
        } else if (accion.equalsIgnoreCase("registrar")) {

            String id = request.getParameter("txtId");
            String nombre = request.getParameter("txtName");
            String apellidos = request.getParameter("txtSecondName");
            String correo = request.getParameter("txtEmail");
            String provincia = request.getParameter("txtProvincia");
            String canton = request.getParameter("txtCanton");
            String distrito = request.getParameter("txtDistrito");
            String password = request.getParameter("txtPassword");
            if (!nombre.equals("") && !id.equals("") && !apellidos.equals("") && !correo.equals("") && !provincia.equals("") && !canton.equals("") && !distrito.equals("") && !password.equals("")) {
                if (verifyEmail(correo)) {
                    System.out.println("Hola");
                    emailValid = true;
                    user.setCanton(canton);
                    user.setDistrito(distrito);
                    user.setEmail(correo);
                    user.setId(id);
                    user.setName(nombre);
                    user.setPassword(password);
                    user.setProvincia(provincia);
                    user.setSecondName(apellidos);
                    dao.registrarse(user);
                    acceso = menu;
                } else {
                    acceso = registrarse;
                }
            } else {
                acceso = registrarse;
            }
        } else if (accion.equalsIgnoreCase("INGRESO")) {
            String email = request.getParameter("txtCorreo");
            String password = request.getParameter("txtPassword");
            User userAux;
            if ((userAux = dao.signIn(email, password)) != null) {
                user.setId(userAux.getId());
                ArrayList<Producto> list = userAux.getProductoIntercambiar();
                if (list != null) {
                    Iterator<Producto> it = list.iterator();
                    boolean notify = false;
                    while (it.hasNext()) {
                        Producto next = it.next();
                        if (next.getEstadoTrueque() == 1) {
                            notify = true;
                        }
                    }
                    if (userAux.getProductoIntercambiar().size() > 0 && notify) {
                        request.setAttribute("id", userAux.getId());
                        acceso = notificaciones;
                    } else {
                        acceso = menu;
                    }
                }else{
                    acceso = menu;
                }
            } else {
                acceso = ingresar;
            }
        } else if (accion.equalsIgnoreCase("perfil")) {
            request.setAttribute("id", user.getId());
            System.out.println("USER " + user.getId());
            acceso = perfil;
        } else if (accion.equalsIgnoreCase("inicio")) {
            acceso = inicio;
        } else if (accion.equalsIgnoreCase("trueque")) {
            //datos
            request.setAttribute("productoPrimero", request.getParameter("consecutivoPrimero"));
            request.setAttribute("id", user.getId());
            acceso = trueque;
        } else if (accion.equalsIgnoreCase("caracteristicas")) {
            //datos
            request.setAttribute("productoID", request.getParameter("consecutivo"));
            acceso = caracteristicas;
        } else if (accion.equalsIgnoreCase("validarTrueque")) {
            request.setAttribute("productoPrimero", request.getParameter("consecutivoPrimero"));
            request.setAttribute("productoSegundo", request.getParameter("consecutivoSegundo"));
            acceso = validarTrueque;
        } else if (accion.equalsIgnoreCase("notification")) {
            System.out.println("Holaaa");
            request.setAttribute("id", user.getId());
            acceso = notificaciones;
        } else if (accion.equalsIgnoreCase("menu")) {
            acceso = menu;

        } else if (accion.equalsIgnoreCase("aceptacion")) {
            int intProduct1 = Integer.parseInt(request.getParameter("producto1"));
            System.out.println("PRODUCTO1 " + intProduct1);
            int intProduct2 = Integer.parseInt(request.getParameter("producto2"));
            System.out.println("PRODUCT2 " + intProduct2);
            Producto producto1 = dao.searchProduct(intProduct1);
            Producto producto2 = dao.searchProduct(intProduct2);
            System.out.println("Producto " + producto1);
            System.out.println("Producto2 " + producto2);
            if (producto1.getPrecio() >= producto2.getPrecio()) {
                if ((producto1.getPrecio() - producto2.getPrecio()) > 1000) {
                    //no se puede hacer
                } else {
                    //si se puede hacer
                    dao.insertarSolicitud(producto2, producto1, user.getId());
                    acceso = menu;
                }
            } else if (producto1.getPrecio() < producto2.getPrecio()) {
                if ((producto2.getPrecio() - producto1.getPrecio()) > 1000) {
                    //no se puede hacer
                } else {
                    //si se puede hacer
                    dao.insertarSolicitud(producto2, producto1, user.getId());
                    acceso = menu;
                }
            }
        }else if(accion.equalsIgnoreCase("aceptarTrueque")){
            
        }else if(accion.equalsIgnoreCase("rechazarTrueque")){
            int intProduct1 = Integer.parseInt(request.getParameter("consecutivoBorrar"));
            Producto producto1 = dao.searchProduct(intProduct1);
            dao.rechazar(producto1, user.getId());
            acceso = menu;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
