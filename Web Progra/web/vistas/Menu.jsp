<%-- 
    Document   : Menu
    Created on : 05-jul-2019, 20:27:13
    Author     : Kevin Trejos
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
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>MENÚ</h1>
        <a href="Controlador?accion=perfil">VER PERFIL</a>
        <br>
        <a href="Controlador?accion=listar">IR A LA LISTA DE PRODUCTOS</a>
        <br>
        <a href="Controlador?accion=anadir">AÑADIR UN NUEVO PRODUCTO</a>
    </body>
</html>
