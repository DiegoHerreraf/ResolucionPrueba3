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
    <table border="0" width="1000" align="center">
        <tr bgcolor="skyblue">
            <th><a href="index.jsp">Articulos</a></th>
            <th><a href="registrarArticulo.jsp">Registrar Articulo</a></th>
            <th><a href="./Pages/RegistrarVenta.jsp">Registrar Venta</a></th>
            <th><a href="verVentas.jsp">Ver Ventas</a></th>
            <th><a href="ServletLogueo?accion=cerrar">Cerrar Sesion</a></th>
            <th width="200"></th>
        </tr>
    </table>
        <h2 align="center">Articulos</h2>
        <table border="0" align="center" width="1000">
            
             <%
                ArrayList<Articulo> lista = ArticuloDB.obtenerProducto();
                int salto = 0;
                for (Articulo a : lista) {
             %>
             <th><img src="../Img/<%=a.getImagen()%>" width="140" height="140"><p>
                 <%= a.getNombre()%><br>
                 <%= a.getPrecio()%><p>
                     <a href="./Pages/modificar.jsp?id=<%= a.getCodigoArticulo()%>">Modificar</a>
                     <a href="./Pages/anadirAlCarrito.jsp?id=<%= a.getCodigoArticulo()%>">Añadir</a>
                     
             </th>
             <%
                 salto++;
                 if(salto==3){
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
