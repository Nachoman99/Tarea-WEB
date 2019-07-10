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
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
                String descripcionCorta="";
                if (id != null) {
                    user = dao.search(id);
                }
                for (int i = 0; i < user.getListaProductos().size(); i++) {
                     if(user.getListaProductos().get(i).isAceptadoPrimeraVez()){
                         descripcionCorta=user.getListaProductos().get(i).getDescripcionCorta();
                     }
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

        <%
            String productoAceptado = (String) request.getAttribute("aceptado");
            boolean aceptacion = false;
            if (productoAceptado.equalsIgnoreCase("true")) {
                aceptacion = true;
            } else {
                aceptacion = false;
            }
            if (aceptacion) {

        %>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#aceptacion").modal('show');
            });

        </script>
        <!-- Modal -->
        <div id="aceptacion" class="modal fade" role="dialog" >
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" href="Controlador?accion=aceptadoPrimera&id= <%=id%>">&times;</button>
                        <h4 class="modal-title">BUENAS NOTICIAS</h4>
                    </div>
                    <div class="modal-body">
                        <p>SU PRODUCTO DE NOMBRE: <%=descripcionCorta%> SOLICITADO HA SIDO ACEPTADO</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" href="Controlador?accion=aceptadoPrimera&id= <%=id%>">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <%            
        }
        %>


    </table>
    <br>
    <a href="Controlador?accion=menu">Ir al menu</a>
</body>
</html>