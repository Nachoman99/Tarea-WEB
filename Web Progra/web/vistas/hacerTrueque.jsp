<%-- 
    Document   : hacerTrueque
    Created on : 06-jul-2019, 13:16:53
    Author     : Kevin Trejos
--%>

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
        <h1>Aceptando trueque</h1>

        <%
            UserDAO dao = new UserDAO();
            List<User> list = dao.listar();
            Iterator<User> iterador = list.iterator();
            User user = null;
            int id1 = Integer.parseInt((String) request.getAttribute("productoPrimero"));
            int id2 = Integer.parseInt((String) request.getAttribute("productoSegundo"));
            Producto producto1=new Producto();
            Producto producto2=new Producto();
            while (iterador.hasNext()) {
                User next = iterador.next();
                user = next;
                for (int i = 0; i < user.getListaProductos().size(); i++) {
                    if (user.getListaProductos().get(i).getNumeroConsecutivo()==id1) {
                        producto1= user.getListaProductos().get(i);
                    
                    }
                    if(user.getListaProductos().get(i).getNumeroConsecutivo()==id2){
                         producto2= user.getListaProductos().get(i);
                    }
                            
        %>



        <%    
                }
            }
        %>

         <p>Descripcion Corta Del Primer Producto: <%=producto1.getDescripcionCorta() %><p>
         <p>Precio Del Primer Precio: <%=producto1.getPrecio()%><p> 
         <p>Descripcion Corta Del Segundo Producto: <%=producto2.getDescripcionCorta() %><p>
         <p>Precio Del Segundo Precio: <%=producto2.getPrecio()%><p>     
          
        <a href="Controlador?accion=aceptacion&producto1=<%=producto1%>&producto2<%=producto2%>">Aceptar</a>  
        <br>
        <a href="Controlador?accion=inicio">Regresar al menu y cancelar trueque</a>
    </body>
</html>
