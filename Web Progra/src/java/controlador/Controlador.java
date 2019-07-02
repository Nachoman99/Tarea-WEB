/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Controlador extends HttpServlet{
    
    public String registrarse = "vistas/Registrarse.jsp";
    
    public User user = new User();
    public UserDAO dao = new UserDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso = "";
        String accion = request.getParameter("accion");
        System.out.println("La accion es: " + accion);
        if (accion.equalsIgnoreCase("registrarse")) {
            String nombre = request.getParameter("txtName");
            String id = request.getParameter("txtId");
            String apellidos = request.getParameter("txtSecondName");
            String correo = request.getParameter("txtEmail");
            String ubicación = request.getParameter("txtLocation");
            user.setId(id);
            user.setEmail(correo);
            user.setName(nombre);
            user.setSecondName(apellidos);
            user.setUbicacion(ubicación);
            dao.registrarse(user);
            acceso = registrarse;
        }else if(accion.equalsIgnoreCase("ingresar")){
            System.out.println("Holaa");
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
