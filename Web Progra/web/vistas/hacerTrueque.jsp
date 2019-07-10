<%-- 
    Document   : hacerTrueque
    Created on : 06-jul-2019, 13:16:53
    Author     : Kevin Trejos
--%>

<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelo.User"%>
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
        <h1>Aceptando trueque</h1>

        <%
            UserDAO dao = new UserDAO();
            List<User> list = dao.listar();
            Iterator<User> iterador = list.iterator();
            User user = null;
            int id1 = Integer.parseInt((String) request.getAttribute("productoPrimero"));
            int id2 = Integer.parseInt((String) request.getAttribute("productoSegundo"));
            String precio = (String) request.getAttribute("precioIncorrecto");
            int producto1 = 0;
            int producto2 = 0;
            while (iterador.hasNext()) {
                User next = iterador.next();
                user = next;
                for (int i = 0; i < user.getListaProductos().size(); i++) {
                    if (user.getListaProductos().get(i).getNumeroConsecutivo() == id1) {
                        producto1 = user.getListaProductos().get(i).getNumeroConsecutivo();
                    }
                    if (user.getListaProductos().get(i).getNumeroConsecutivo() == id2) {
                        producto2 = user.getListaProductos().get(i).getNumeroConsecutivo();
                    }

        %>



        <%                }
            }

           
            boolean ingresa = true;
            if (precio.equalsIgnoreCase("true")) {
                ingresa = true;
            } else {
                ingresa = false;
            }
            if (ingresa) {

        %>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#precioMalo").modal('show');
            });

        </script>
        <!-- Modal -->
        <div id="precioMalo" class="modal fade" role="dialog" >
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">ERROR DE PRECIOS</h4>
                    </div>
                    <div class="modal-body">
                        <p>La diferencia de precios es muy grande...</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <%
            }
        %>


        <p>Descripcion Corta Del Primer Producto: <%=dao.searchProduct(producto1).getDescripcionCorta()%><p>
        <p>Precio Del Primer Producto <%=dao.searchProduct(producto1).getPrecio()%><p> 
        <p>Descripcion Corta Del Segundo Producto: <%=dao.searchProduct(producto2).getDescripcionCorta()%><p>
        <p>Precio Del Segundo Producto <%=dao.searchProduct(producto2).getPrecio()%><p>     

            <br>
        <p> Recuerde que la diferencia de precios no debe de ser mas o menos 1000<p>

            <a href="Controlador?accion=aceptacion&producto1=<%=id1%>&producto2=<%=id2%>">Aceptar</a>  
            <br>
            <a href="Controlador?accion=menu">Regresar al menu y cancelar trueque</a>
    </body>
</html>
