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
        <h1>Lista de Productos para trueque</h1>
        
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
                List<User> list = dao.listar();
                Iterator<User> i = list.iterator();
                User a = null;
                String id = (String) request.getAttribute("id");
                while (i.hasNext()) {

                    a = i.next();
                    if (!a.getId().equals(id)) {

                        for (int j = 0; j < a.getListaProductos().size(); j++) {
                            if (a.getListaProductos().get(j).getEstadoTrueque() == 0) {

            %>
            <tbody>
                <tr>
                    <td><%=a.getListaProductos().get(j).getDescripcionCorta()%></td>
                    <td><%=a.getListaProductos().get(j).getPrecio()%></td>

                    <td>
                        <a href="Controlador?accion=trueque&consecutivoPrimero=<%=a.getListaProductos().get(j).getNumeroConsecutivo()%>">Trueque</a>
                        <a href="Controlador?accion=caracteristicas&consecutivo=<%=a.getListaProductos().get(j).getNumeroConsecutivo()%>">Caracteristicas</a>
                    </td>
                </tr>
                <%}
                            }
                        }
                    }%>
            </tbody>
        </table>


        <a href="Controlador?accion=menu">Ir al menu</a>



    </body>
</html>
