<%-- 
    Document   : modificar
    Created on : 26-11-2018, 1:53:48
    Author     : Diego
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="shop.dao.*" %>
<%@page import="java.util.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Obtenemos el id o el codigo del articulo que deseamos modificar o actualizar --%>
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
        <br>
        <h2 align="center">Modificar articulo</h2>
        <%-- En el action del formulario le decimos que llama al Controlador --%>
        <form method="post" action="${pageContext.request.contextPath}/ServletController">
                <%-- Indica al controlador que vamos hacer una modificacion --%>
                    <tr>
                    <th rowspan="5"><img src="../Img/<%= a.getImagen()%>" width="140" height="140"></th>
                    <th>Codigo</th>
                    <th><input type="text" name="txtCodigo" value="<%= a.getCodigoArticulo()%>" readonly /></th>
                </tr><tr>
                    <th>Nombre</th>
                    <th><input type="text" name="txtNombre" value="<%= a.getNombre()%>" /></th>
                </tr><tr>
                    <th>Precio</th>
                    <th><input type="text" name="txtPrecio" value="<%= a.getPrecio()%>" /></th>
                </tr>
                    <th colspan="3"><input type="submit" value="Modificar" name="btnmodificar"/></th>
                </tr>
                <input type="hidden" name="accion" value="ModificarArticulo"/>
        </form>
    </body>
</html>

