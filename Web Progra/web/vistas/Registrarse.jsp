<%-- 
    Document   : Registrarse
    Created on : 28-jun-2019, 20:20:09
    Author     : Kevin Trejos
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="controlador.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <title>Registrarse</title>
    </head>
    <body>
        <%
            String email1 = (String) request.getAttribute("email");
            String vacio1 = (String) request.getAttribute("vacio");
            System.out.println("email " + email1);
            System.out.println("vacio " + vacio1);

            boolean email = false;
            boolean vacio = false;
            if (email1.equalsIgnoreCase("false") && vacio1.equalsIgnoreCase("false")) {
                email = false;
                vacio = false;
            } else if (email1.equalsIgnoreCase("true") && vacio1.equalsIgnoreCase("true")) {
                email = true;
                vacio = true;
            } else if (email1.equalsIgnoreCase("true")) {
                email = true;
            } else if (vacio1.equalsIgnoreCase("true")) {
                vacio = true;
            }
            
        %>

        <%
            if (!email && !vacio) {

        %>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#correoMalo").modal('show');
            });

        </script>
        <!-- Modal -->
        <div id="correoMalo" class="modal fade" role="dialog" >
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Error de Registro</h4>
                    </div>
                    <div class="modal-body">
                        <p>El correo es inválido</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <%        } else if (vacio) {
        %>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#vaciosMalo").modal('show');
            });

        </script>
        <!-- Modal -->
        
        <div id="vaciosMalo" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Error de Registro</h4>
                    </div>
                    <div class="modal-body">
                        <p>Por favor complete todos los espacios</p>
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
        <h1>Registrarse</h1>
        <form action="Controlador">
            Número de cédula:<br>
            <input type="text" name="txtId"><br>
            Nombre:<br>
            <input type="text" name="txtName"><br>
            Apellidos:<br>
            <input type="text" name="txtSecondName"><br>
            Correo electrónico:<br>
            <input type="text" name="txtEmail"><br>
            Provincia:<br>
            <input type="text" name="txtProvincia"><br>
            Cantón:<br>
            <input type="text" name="txtCanton"><br>
            Distrito:<br>
            <input type="text" name="txtDistrito"><br>
            Contraseña:<br>
            <input type="password" name="txtPassword"><br>
            <br>
            <br>
            <input type="submit" name="accion" value="REGISTRAR">
            <a href="Controlador?accion=inicio">Regresar a la página de inicio</a>
        </form>
    </body>

</html>
