<%-- 
    Document   : index
    Created on : 24-11-2018, 22:19:35
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="shop.dao.*" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Partials/Head.jsp" %>
    </head>
    <body>
        <nav>
            <%@include file="../Partials/Nav.jsp" %>
        </nav>
        <h2 align="center">Articulos</h2>
        <table border="0" align="center" width="1000">
            
             <%
                ArrayList<Articulo> lista = ArticuloDB.obtenerArticulo();
                int salto = 0;
                for (Articulo a : lista) {
             %>
             <th><img src="../Img/<%=a.getImagen()%>" width="140" height="140"><p>
                 <%= a.getNombre()%><br>
                 <%= a.getPrecio()%><p>
                     <a href="/ResolucionPrueba3/Pages/modificar.jsp?id=<%= a.getCodigoArticulo()%>">Modificar</a>
                     <a href="/ResolucionPrueba3/Pages/anadirAlCarrito.jsp?id=<%= a.getCodigoArticulo()%>">Añadir</a>   
             </th>
             <%
                 salto++;
                 if(salto==4){
             %>
                <tr>
             <%
                 salto=0;
                 }
             }
             %>
        </table>
    </body>
</html>
