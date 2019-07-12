<%-- 
    Document   : añadir
    Created on : 01/07/2019, 08:21:01 PM
    Author     : Nacho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <title>Añadir</title>
    </head>
    <body>
        <h1>Añadir producto</h1>
        <%
            String producto = (String) request.getAttribute("productoCorrecto");
        %>
        <form action="Controlador">
            DescripcionCorta<br>
            <input type="text" name="txtDescripcionCorta"><br>  
            DescripcionDetallada<br>
            <input type="text" name="txtDescripcionDetallada"><br>   
            Precio<br>
            <input type="text" name="txtPrecio"><br>  
            Imagenes<br>
            <input type="file" name="txtImagen" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="Insertar imagen"><br>
            <p>
                Pick a car:<br>
                <select name="categoria">

                    <option>Tecnologia</option>

                    <option selected>Cocina</option>

                    <option>Ropa</option>

                </select>
                
            </p>
            <input type="submit" name="accion" value="introducir">
            <a href="Controlador?accion=menu">Regresar a la menu</a>
            
        </form>
        <%
            boolean errorProducto = false;
            
            if (producto.equalsIgnoreCase("true")) {
                errorProducto=true;
            } else{
                errorProducto=false;
            } 
         %>
        
         <%
            if (errorProducto) {

        %>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#productoMalo").modal('show');
            });

        </script>
        <!-- Modal -->
        <div id="productoMalo" class="modal fade" role="dialog" >
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Error de Ingreso De Producto</h4>
                    </div>
                    <div class="modal-body">
                        <p>Por favor completar todos los espacios...</p>
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
        
    </body>
</html>
