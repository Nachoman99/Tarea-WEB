<%-- 
    Document   : Notifications
    Created on : 06-jul-2019, 17:08:15
    Author     : Kevin Trejos
--%>

<%@page import="modelo.User"%>
<%@page import="java.util.List"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notificaciones</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>PRODUCTO</th>
                    <th>PRECIO</th>
                </tr>
            </thead>
            <%
                UserDAO dao = new UserDAO();
                String id = (String) request.getAttribute("id");
                User user = null;
                if (id != null) {
                    user = dao.search(id);
                }
                for (int i = 0; i < user.getPendientesTrueque().size(); i++) {

            %>
            <tbody>
            <td><a href="Controlador?accion=caracteristicas&consecutivoSegundo=<%=user.getPendientesTrueque().get(i).getNumeroConsecutivo()%>"><%=user.getPendientesTrueque().get(i).getDescripcionCorta()%></a></td>
            <td><%=user.getPendientesTrueque().get(i).getPrecio()%></td>
        </tr>
        <%}
        %>
    </table>
    <br>
    <a href="Controlador?accion=menu">Ir al menu</a>
</body>
</html>
