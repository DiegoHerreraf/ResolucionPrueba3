<%-- 
    Document   : anadirAlCarrito
    Created on : 25-11-2018, 23:20:37
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="shop.dao.*" %>
<!DOCTYPE html>
<%
    Articulo a = ArticuloDB.obtenerArticulo(Integer.parseInt(request.getParameter("id")));
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="0" width="1000" align="center">
            <tr bgcolor="skyblue">
                <th><a href="/ResolucionPrueba3/Pages/index.jsp">Catalogo</a></th>
                <th><a href="/ResolucionPrueba3/Pages/registrarArticulo.jsp">Registrar Articulo</a></th>
                <th><a href="/ResolucionPrueba3/Pages/registrarVenta.jsp">Registrar Venta</a></th>
                <th><a href="/ResolucionPrueba3/Pages/verVentas.jsp">Ver Ventas</a></th>
                <th><a href="ServletLogueo?accion=cerrar">Cerrar Sesion</a></th>
                <th width="200"></th>
            </tr>    
        </table>
        <h2 align="center">Añadir Articulo a carrito de compras</h2>
        <table border="0" width="500" align="center">
            <form method="post" action="${pageContext.request.contextPath}/ServletController">
                <tr>
                    <th rowspan="5"><img src="../Img/<%= a.getImagen()%>" width="140" height="140"></th>
                    <th>Codigo</th>
                    <th><input type="text" name="txtCodigo" value="<%= a.getCodigoArticulo()%>" readonly></th>
                </tr><tr>
                    <th>Nombre</th>
                    <th><input type="text" name="txtNombre" value="<%= a.getNombre()%>" readonly></th>
                </tr><tr>
                    <th>Precio</th>
                    <th><input type="text" name="txtPrecio" value="<%= a.getPrecio()%>" readonly /></th>
                </tr><tr>
                    <th>Cantidad</th>
                    <th><input type="number" value="0" min="0" name="txtCantidad" value="0"/></th>
                </tr><tr>
                    <th colspan="3"><input type="submit" value="Añadir" name="btnanadir"/></th>
                </tr>
                <input type="hidden" name="accion" value="AnadirCarrito"/>
            </form> 
        </table>
    </body>
</html>
