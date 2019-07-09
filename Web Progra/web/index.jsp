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
        <title>Bienvenidos al Mercado JOOSKE</title>
    </head>
    <body>
        
        <br>
        <p>Bienvenidos al Mercado JOOSKE<p>
        <br>
        <br>
        <div class ="container">
            <%
                String ruta = (String)request.getAttribute("img");
            %>
            <a class="btn btn-success btn-lg" href="Controlador?accion=registrarse">REGISTRARSE</a>
            <br>
            <br>
            <a class="btn btn-success btn-lg" href="Controlador?accion=ingresar">INGRESAR</a>
            <br>
            <br>
            <a class="btn btn-success btn-lg" href="Controlador?accion=listar">Listar(quitarlo despues)</a>
        </div>
        <image src="images/mercado.jpg">  
    </body>
</html>
