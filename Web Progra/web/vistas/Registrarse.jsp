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
        <title>Registrarse</title>
    </head>
    <body>
        <
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
