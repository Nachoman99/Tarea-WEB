<%-- 
    Document   : perfil
    Created on : 01/07/2019, 08:21:17 PM
    Author     : Nacho
<% 
            AlumnoDAO dao = new AlumnoDAO();
            int id = Integer.parseInt((String) request.getAttribute("id_alumno"));
            Alumno a = (Alumno) dao.buscar(id);
        %>
--%>

<%@page import="modelo.User"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PERFIL</title>
    </head>
    <body>
        <%
            UserDAO dao = new UserDAO();
            String id = (String) request.getAttribute("id");
            User user = null;
            if (id != null) {
                user = dao.searchUser(id);
            }
        %>
        <h1>Mi perfil</h1>
        <p>Nombre: <%=user.getName()%><p>

        <p>Apellidos: <%=user.getSecondName()%><p>

        <p>Cédula: <%=user.getId()%><p>

        <p>Correo: <%=user.getEmail()%><p>

        <p>Provincia: <%=user.getProvincia()%><p>

        <p>Cantón: <%=user.getCanton()%><p>

        <p>Distrito: <%=user.getDistrito()%><p>

        <p>Contraseña: <%=user.getPassword()%><p>
        
        </p>
        <a href="Controlador?accion=menu">Regresar al menu</a>
    </body>
</html>
