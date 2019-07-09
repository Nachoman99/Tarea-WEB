<%-- 
    Document   : index
    Created on : 28-jun-2019, 20:07:12
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
        <title>Bienvenidos al Mercado JOOSKE</title>
    </head>
    <body>

        <br>
        <p>Bienvenidos al Mercado JOOSKE<p>
            <br>
            <br>
        <div class ="container">
            <%
                String ruta = (String) request.getAttribute("img");
            %>


            <!-- Trigger the modal with a button -->
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

            <!-- Modal -->
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">
                            <p>Some text in the modal.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>



            <a class="btn btn-success btn-lg" href="Controlador?accion=registrarse">REGISTRARSE</a>
            <br>
            <br>
            <a class="btn btn-success btn-lg" href="Controlador?accion=ingresar">INGRESAR</a>
            <br>
            <br>
            <a class="btn btn-success btn-lg" href="Controlador?accion=listar">Listar(quitarlo despues)</a>
        </div>
        <image src="images/mercado.jpg"width="400" height="400">  
    </body>
</html>
