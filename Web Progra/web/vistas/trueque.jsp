<%-- 
    Document   : trueque
    Created on : 01/07/2019, 08:21:35 PM
    Author     : Nacho
--%>

<%@page import="java.util.Iterator"%>
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
        <h1>TRUEQUE</h1>
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
                List<User> list = dao.listar();
                Iterator<User> iterador = list.iterator();
                User user = null;
                while (iterador.hasNext()) {
                    User next = iterador.next();
                    user = next;
                    for (int i = 0; i < user.getListaProductos().size(); i++) {


            %>
            <tbody>
                <tr>
                    <td><a href="Controlador?accion=caracteristicas&consecutivo=<%=user.getListaProductos().get(i).getNumeroConsecutivo()%>"><%=user.getListaProductos().get(i).getDescripcionCorta()%></a></td>
                    <td><%=user.getListaProductos().get(i).getPrecio()%></td>

                    <td>
                        <a href="Controlador?accion=validarTrueque">Intercambiar este artículo</a>
                    </td>
                </tr>
                <%    }
                    }
                %>
                </body>
                </html>
