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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ingresar</h1>
        <form action="Controlador"
            Correo:<br>
            <input type="text" correo="txtCorreo"><br>
            Contraseña:<br>
            <input type="text" password="txtPassword"><br>
            <br>
            <input type="submit" location="accion" value="INGRESAR">
            <a href="Controlador?accion=inicio">Regresar a la página de inicio</a>
        </form>
    </body>
</html>
