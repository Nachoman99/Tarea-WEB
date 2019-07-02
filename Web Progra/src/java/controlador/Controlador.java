/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String lista = "vistas/añadir.jsp";
    public String perfil = "vistas/perfil.jsp";
    public String trueque = "vistas/trueque.jsp";

    public User user = new User();
    public UserDAO dao = new UserDAO();

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

    private boolean verifyEmail(String email) throws ExceptionsControler {
        if (email.contains("@")) {
            return true;
        } else {
            throw new ExceptionsControler("Formato de correo inválido");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso = "";
        String accion = request.getParameter("accion");
        System.out.println("La accion es: " + accion);
        if (accion.equalsIgnoreCase("registrarse")) {
            acceso = registrarse;
            String nombre = request.getParameter("txtName");
            String id = request.getParameter("txtId");
            String apellidos = request.getParameter("txtSecondName");
            String correo = request.getParameter("txtEmail");
            String provincia = request.getParameter("txtProvincia");
            String canton = request.getParameter("txtCanton");
            String distrito = request.getParameter("txtDistrito");
            String password = request.getParameter("txtPassword");
            System.out.println("Hola");
            if (nombre != null && id != null && apellidos != null && correo != null && provincia != null && canton != null && distrito != null && password != null) {
                try {
                    if (verifyEmail(correo)) {
                        System.out.println("putt");
                        user = new User(id, nombre, apellidos, correo, provincia, canton, distrito, password, null);
                        dao.registrarse(user);
                        acceso = lista;
                    }
                    System.out.println("kkkk");
                } catch (ExceptionsControler ex) {
                    ex.printStackTrace();
                }
            }
//            user.setId(id);
//            user.setEmail(correo);
//            user.setName(nombre);
//            user.setSecondName(apellidos);
//            user.setCanton(canton);
//            user.setDistrito(distrito);
//            user.setProvincia(provincia);
//            user.setPassword(password);
//            dao.registrarse(user);
//            acceso = lista;
        } else if (accion.equalsIgnoreCase("ingresar")) {
            String correo = request.getParameter("txtCorreo");
            String password = request.getParameter("txtPassword");
            //Verificar si el usuario existe
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
