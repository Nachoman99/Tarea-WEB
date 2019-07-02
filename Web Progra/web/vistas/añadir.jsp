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
        <title>Añadir</title>
    </head>
    <body>
        <h1>Añadir producto</h1>
        <form action="Controlador">
            DescripcionCorta<br>
            <input type="text" name="txtDescripcionCorta"><br>  
            DescripcionDetallada<br>
            <input type="text" name="txtDescripcionDetallada"><br>   
            Precio<br>
            <input type="text" name="txtPrecio"><br>  
            Imagenes<br>
            <input type="file" imagen="txtImagen" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="Insertar imagen"><br>
            <p>
                Pick a car:<br>
                <select name="categoria">

                    <option>Tecnologia</option>

                    <option selected>Cocina</option>

                    <option>Ropa</option>

                </select>
            </p>
            <input type="submit" name="accion" value="introducir">
            <a href="Controlador?accion=lista">Regresar a la lista de productos</a>
            
        </form>
    </body>
</html>
