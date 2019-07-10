<%-- 
    Document   : Ingresar
    Created on : 29-jun-2019, 15:08:35
    Author     : Kevin Trejos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <title>Ingresar</title>
    </head>
    <body>
        <%
            String ingreso = (String) request.getAttribute("ingreso");
            boolean ingresa = true;
            if (ingreso.equalsIgnoreCase("true")) {
                ingresa = true;
            }else{
                ingresa = false;
            }
            if (!ingresa) {
                
        %>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#ingresoMalo").modal('show');
            });

        </script>
        <!-- Modal -->
        <div id="ingresoMalo" class="modal fade" role="dialog" >
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">ERROR AL INGRESAR</h4>
                    </div>
                    <div class="modal-body">
                        <p>Por favor verifique sus datos e inténtelo de nuevo</p>
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
        <h1>Ingresar</h1>
        <form action="Controlador">
            Correo:<br>
            <input type="text" name="txtCorreo"><br>
            Contraseña:<br>
            <input type="password" name="txtPassword"><br>
            <br>
            <input type="submit" name="accion" value="INGRESO">
            <a href="Controlador?accion=inicio">Regresar a la página de inicio</a>
        </form>
    </body>
</html>
