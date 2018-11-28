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
        <%@include file="../Partials/Head.jsp" %>
    </head>
    <body>
        <nav>
        <%@include file="../Partials/Nav.jsp" %>
        </nav>
        <h2 align="center">Añadir Articulo a carrito de compras</h2>
        <table border="0" width="500" align="center">
            <form onsubmit="return validarAnadir();" class="form-register" method="post" action="${pageContext.request.contextPath}/ServletController">
                <tr>
                    <th rowspan="5"><img src="../Img/<%= a.getImagen()%>" width="140" height="140"></th>
                    <th>Codigo</th>
                    <th><input type="text"  name="txtCodigo" value="<%= a.getCodigoArticulo()%>" readonly></th>
                </tr><tr>
                    <th>Nombre</th>
                    <th><input type="text" name="txtNombre" value="<%= a.getNombre()%>" readonly></th>
                </tr><tr>
                    <th>Precio</th>
                    <th><input type="text" name="txtPrecio" value="<%= a.getPrecio()%>" readonly /></th>
                </tr><tr>
                    <th>Cantidad</th>
                    <th><input type="number" id="cantidad" value=""  name="txtCantidad" value="0" /></th>
                </tr><tr>
                    <th colspan="3"><input type="submit" value="Añadir" name="btnanadir"/></th>
                </tr>
                <input type="hidden" name="accion" value="AnadirCarrito"/>
            </form> 
        </table>
    </body>
</html>
