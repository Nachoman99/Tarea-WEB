<%-- 
    Document   : Notifications
    Created on : 06-jul-2019, 17:08:15
    Author     : Kevin Trejos
--%>

<%@page import="modelo.Producto"%>
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
                    <th>PRODUCTO A INTERCAMBIAR</th>
                    <th>PRECIO</th>
                    <th>MIS PRODUCTOS</th>
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
                for (int i = 0; i < user.getProductosSolicitados().size(); i++) {
                    if (user.getProductoIntercambiar().get(i).getEstadoTrueque() == 1) {

            %>
            <tbody>  
            <td><a href="Controlador?accion=caracteristicas&consecutivo=<%=user.getProductosSolicitados().get(i).getNumeroConsecutivo()%>"><%=user.getProductosSolicitados().get(i).getDescripcionCorta()%></a></td>
            <td><%=user.getProductosSolicitados().get(i).getPrecio()%></td>
            <td><a href="Controlador?accion=caracteristicas&consecutivo=<%=user.getProductoIntercambiar().get(i).getNumeroConsecutivo()%>"><%=user.getProductoIntercambiar().get(i).getDescripcionCorta()%></a></td>
            <td><%=user.getProductoIntercambiar().get(i).getPrecio()%></td>
            <td><a href="Controlador?accion=aceptarTrueque&prodIntercambiar=<%=user.getProductosSolicitados().get(i).getNumeroConsecutivo()%>">ACEPTAR TRUEQUE</a></td>
            <td><a href="Controlador?accion=rechazarTrueque&consecutivoBorrar=<%=user.getProductosSolicitados().get(i).getNumeroConsecutivo()%>">RECHAZAR TRUEQUE</a></td>
        </tr>
        <% }
            }
        %>
    </table>
    <br>
    <a href="Controlador?accion=menu">Ir al menu</a>
</body>
</html>