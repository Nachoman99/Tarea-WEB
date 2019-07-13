<%-- 
    Document   : ListaProductos
    Created on : 08-jul-2019, 19:16:37
    Author     : Kevin Trejos
--%>

<%@page import="java.util.Iterator"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.User"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>MI LISTA DE PRODUCTOS</h1>
        <br>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>PRODUCTO</th>
                    <th>PRECIO</th>
                </tr>
            </thead>
            <%
                UserDAO dao = new UserDAO();
                String idUser = (String) request.getAttribute("id");
                User user = dao.searchUser(idUser);
                ArrayList<Producto> list = user.getListaProductos();
                Iterator<Producto> iterador = list.iterator();
                while (iterador.hasNext()) {
                    Producto next = iterador.next();
            %>
            <tbody>
                <tr>
                    <td><a href="Controlador?accion=caracteristicas&consecutivo=<%=next.getNumeroConsecutivo()%>"><%=next.getDescripcionCorta()%></a></td>
                    <td><%=next.getPrecio()%></td>
                </tr>
            </tbody>
            <%
            }
            %>
        </table>
        <a href="Controlador?accion=menu">Regresar al menu</a>
    </body>
</html>
