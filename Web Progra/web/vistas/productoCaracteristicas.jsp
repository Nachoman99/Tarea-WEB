<%-- 
    Document   : productoCaracteristicas
    Created on : 05/07/2019, 09:12:52 PM
    Author     : Nacho
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelo.User"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Caracteristicas del producto</h1>
        <%
               UserDAO dao = new UserDAO();
               List <User> list = dao.listar();
               Iterator<User> i = list.iterator();
               User a = null;
               Producto producto=new Producto();
               int id = Integer.parseInt((String) request.getAttribute("productoID"));
            
               String imagen="";
               
               while (i.hasNext()){
                  a = i.next();
                 
                  for (int j = 0; j < a.getListaProductos().size(); j++) {
                       if(a.getListaProductos().get(j).getNumeroConsecutivo()==id){
                           producto=a.getListaProductos().get(j);
                           imagen=a.getListaProductos().get(j).getListaImagenes().get(0);
                           //System.out.println("Imagennnnnnnnnnnnnnnn= "+imagen);
                       }
                      
                  
                           
                   
        %>
         <%}%>
         <%}%>
         <p>Caracteristicas del producto<p>
             
         <p>Descripcion Corta: <%=producto.getDescripcionCorta() %><p>
            
         <p>Descripcion Detallada: <%=producto.getDescripcionDetallada()%><p> 
           
         <p>Categoria: <%=producto.getCategoria()%><p> 
            
         <p>Precio: <%=producto.getPrecio()%><p> 
            
         <p> Imagen: <p>
             
         <img src=<%=imagen%>>
  
             
          <a href="Controlador?accion=menu">Regresar al menu</a>
    </body>
</html>
