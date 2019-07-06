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
                user = dao.search(id);
            }
            
        %>
        <h1>Mi perfil</h1>
        <p>Nombre: <%=user.getName() + "\n"%>
           Apellidos: <%=user.getSecondName() + "\n"%>
           Cédula: <%=user.getId() + "\n"%>
           Correo: <%=user.getEmail() + "\n"%>
           Provincia: <%=user.getProvincia() + "\n"%>
           Cantón: <%=user.getCanton() + "\n"%>
           Distrito: <%=user.getDistrito() + "\n"%>
           Contraseña: <%=user.getPassword()%>
        <p>

</body>
</html>
