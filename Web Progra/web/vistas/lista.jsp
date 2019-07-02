<%-- 
    Document   : lista
    Created on : 01/07/2019, 08:21:08 PM
    Author     : Nacho
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="modelo.User"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista</title>
    </head>
    <body>
        <h1>Lista de Productos</h1>
        <a href="Controlador?accion=añadir">Agregar un Nuevo Producto</a>
        <br>
        <br>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <%
               UserDAO dao = new UserDAO();
               List <User> list = dao.listar();
               Iterator<User> i = list.iterator();
               User a = null;
               int p=-1;
               while (i.hasNext()){
                   ++p;
                   a = i.next();
            %>
            <tbody>
                <tr>
                    <td><%=a.getListaProductos().get(p).getDescripcionCorta() %></td>
                    <td><%=a.getListaProductos().get(p).getPrecio() %></td>
    
                    <td>
                        <a href="Controlador?accion=trueque&id=<%= a.getId()%>">Trueque</a>
                      
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
        
        
        
        
        
        
    </body>
</html>
