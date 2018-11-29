<%-- 
    Document   : RegistrarVenta
    Created on : 26-11-2018, 3:20:32
    Author     : Diego
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.dao.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Partials/Head.jsp" %>
    </head>
    <body>
        <nav>
        <%@include file="../Partials/Nav.jsp" %>
        </nav>
            <form onsubmit="return validarRegistrarVenta();" class="form-register" method="post" action="${pageContext.request.contextPath}/ServletController">
                <%-- Llamamos a la accion Registrar Venta --%>
                <input type="hidden" name="accion" value="RegistrarVenta" />
                <table border="0" class="table table-bordered">
                        <tr>
                            <td colspan="5">Carrito de Compras</td>
                        </tr>
                        <tr>
                            <td>Cliente:</td>
                            <td colspan="4"><input type="text" id="Cliente" name="txtCliente" value=""  minlength=">3"/></td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td>Precio</td>
                            <td>Cantidad</td>
                            <td>Descuento</td>
                            <td>Sub. Total</td>
                        </tr>
                        <%-- Los Articulos que tenemos en la sesion que se llama carrito --%>
                        <%
                           ArrayList<DetalleVenta> lista = (ArrayList<DetalleVenta>)session.getAttribute("carrito");
                           if(lista!=null){
                           for (DetalleVenta d : lista) {
                        %>
                            <tr>
                               <td><%= d.getArticulo().getNombre()%></td>
                               <td><%= d.getArticulo().getPrecio()%></td>
                               <td><%= d.getCantidad()%></td>
                               <td><%= d.getDescuento()%></td>
                               <td><%= (d.getArticulo().getPrecio() * d.getCantidad())-d.getDescuento()%></td>
                               </tr>
                        <%
                               }
                           }
                        %>
                        <tr>
                            <td colspan="5"><input type="submit" value="RegistrarVenta" name="btnVenta" />
                            <input align="right" type="button" value="Seguir comprando" onclick = "location='/ResolucionPrueba3/Pages/index.jsp'"/>
                            <input align="right" type="submit" value="Cancelarcompra"</td></td>
                        </tr>
                    </table>
                </form>
    </body>
</html>
