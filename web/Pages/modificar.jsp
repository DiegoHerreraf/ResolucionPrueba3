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
        <%@include file="../Partials/Head.jsp" %>
    </head>
    <body>
        <nav>
        <%@include file="../Partials/Nav.jsp" %>
        </nav>
        <br>
        <h2 align="center">Modificar articulo</h2>
        <form onsubmit="return validar();" class="form-register" method="post" action="${pageContext.request.contextPath}/ServletController">
                    <tr>
                    <th rowspan="5"><img src="../Img/<%= a.getImagen()%>" width="140" height="140"></th>
                    <th>Codigo</th>
                    <th><input type="text" name="txtCodigo" value="<%= a.getCodigoArticulo()%>" readonly /></th>
                </tr><tr>
                    <th>Nombre</th>
                    <th><input type="text" id="nombrearticulo"  name="txtNombre" value="<%= a.getNombre()%>" /></th>
                </tr><tr>
                    <th>Precio</th>
                    <th><input type="text" id="precioarticulo" name="txtPrecio" value="<%= a.getPrecio()%>" /></th>
                </tr>
                    <th colspan="3"><input type="submit" value="Modificar" name="btnmodificar"/></th>
                </tr>
                <input type="hidden" name="accion" value="ModificarArticulo"/>
        </form>
    </body>
</html>

