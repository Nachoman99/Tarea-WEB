<%-- 
    Document   : Registrarse
    Created on : 28-jun-2019, 20:20:09
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
        <h1>Registrarse</h1>
        <form action="Controlador">
            Número de cédula:<br>
            <input type="text" id="txtId"><br>
            Nombre:<br>
            <input type="text" name="txtName"><br>
            Apellidos:<br>
            <input type="text" secondName="txtSecondName"><br>
            Correo electrónico:<br>
            <input type="text" email="txtEmail"><br>
            Provincia:<br>
            <input type="text" provincia="txtProvincia"><br>
            Cantón:<br>
            <input type="text" canton="txtCanton"><br>
            Distrito:<br>
            <input type="text" distrito="txtDistrito"><br>
            <br>
            <input type="submit" location="accion" value="REGISTRARSE">
            <a href="Controlador?accion=registrarse">Regresar a la página de inicio</a>
        </form>
    </body>
</html>
