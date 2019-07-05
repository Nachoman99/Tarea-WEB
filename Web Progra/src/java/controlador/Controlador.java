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
            ArrayList<String> imagenes= new ArrayList<>();
            imagenes.add(imagen);
            producto=new Producto(imagenes, corta, detallada, categoria, precio, 0);
            dao.insertarProducto(producto, user.getId());
            acceso = lista;
     
        } else if (accion.equalsIgnoreCase("listar")) {
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

            System.out.println("id " + id);
            System.out.println("nombre " + nombre);
            System.out.println("apellidos " + apellidos);
            System.out.println("correo " + correo);
            System.out.println("provincia " + provincia);
            System.out.println("canton " + canton);
            System.out.println("distrito " + distrito);
            System.out.println("password  " + password);

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
                    acceso = lista;
                } else {
                    System.out.println("puto");
                    acceso = registrarse;
                }
            } else {
                System.out.println("Menos");
                acceso = registrarse;
            }
        }else if(accion.equalsIgnoreCase("INGRESO")){
            System.out.println("Puuuu");
            String email = request.getParameter("txtCorreo");
            String password = request.getParameter("txtPassword");
            if(dao.signIn(email, password) != null){
                acceso = lista;
            }else{
                acceso = ingresar;
            }
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
        /*
        String acceso ="";
       String accion = request.getParameter("accion");
       if (accion.equalsIgnoreCase("listar")){
            acceso = listar;
       }else if(accion.equalsIgnoreCase("agregar")){
            acceso=agregar;
        }else if(accion.equalsIgnoreCase("insertar")){
            String nombre = request.getParameter("txtNombre");
            String apellidos = request.getParameter("txtApellidos");
            a.setNombre(nombre);
            a.setApellidos(apellidos);
            dao.insertar(a);
            acceso=listar;
        }else if(accion.equalsIgnoreCase("editar")){
            request.setAttribute("id_alumno", request.getParameter("id"));
            acceso=editar;
        }else if(accion.equalsIgnoreCase("actualizar")){
            int id = Integer.parseInt(request.getParameter("txtId"));
            String nombre = request.getParameter("txtNombre");
            String apellidos = request.getParameter("txtApellido");
            a.setId(id);
            a.setNombre(nombre);
            a.setApellidos(apellidos);
            dao.actualizar(a);
           acceso=listar; 
        }else if(accion.equalsIgnoreCase("eliminar")){
            int id = Integer.parseInt(request.getParameter("id"));
            a.setId(id);
            dao.eliminar(a);
            acceso = listar;
        }
       
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
         */
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
