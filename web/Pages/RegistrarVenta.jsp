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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="0" width="1000" align="center">
            <tr bgcolor="skyblue">
                <th><a href="/ResolucionPrueba3/Pages/index.jsp">Catalogo</a></th>
                <th><a href="/ResolucionPrueba3/Pages/registrarVenta.jsp">Registrar Venta</a></th>
                <th><a href="/ResolucionPrueba3/Pages/verVentas.jsp">Ver Ventas</a></th>
                <th><a href="ServletLogueo?accion=cerrar">Cerrar Sesion</a></th>
                <th width="200"></th>
            </tr>    
        </table>
        <table border="0" width="1000" align="center">
            <form method="post" action="${pageContext.request.contextPath}/ServletController">
                <%-- Llamamos a la accion Registrar Venta --%>
                <input type="hidden" name="accion" value="RegistrarVenta" />
                <table border="0">
                        <tr>
                            <td colspan="5">Carrito de Compras</td>
                        </tr>
                        <tr>
                            <td>Cliente:</td>
                            <td colspan="4"><input type="text" name="txtCliente" value="" /></td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td>Precio</td>
                            <td>Cantidad</td>
                            <td>Descuento</td>
                            <td>Sub. Total</td>
                        </tr>
                        <%-- Los productos que tenemos en la sesion que se llama carrito --%>
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
                        <tr >
                            <td colspan="5"><input type="submit" value="RegistrarVenta" name="btnVenta" /></td>
                        </tr>
                    </table>
                </form>
        </table>
    </body>
</html>
