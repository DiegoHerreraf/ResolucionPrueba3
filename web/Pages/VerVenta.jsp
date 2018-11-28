
<%@page import="shop.dao.VentaDB"%>
<%@page import="shop.dao.Venta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.dao.DetalleVenta"%>
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
            <th><a href="/ResolucionPrueba3/Pages/index.jsp">Articulos</a></th>
            <th><a href="/ResolucionPrueba3/Pages/RegistrarVenta.jsp">Registrar Venta</a></th>
            <th><a href="/ResolucionPrueba3/Pages/VerVenta.jsp">Ver Ventas</a></th>
            <th><a href="ServletLogueo?accion=cerrar">Cerrar Sesion</a></th>
            <th width="200"></th>
        </tr>
    </table>
        <table border="0" width="1000" align="center">
                <input type="hidden" name="accion" value="RegistrarVenta" />
                <table border="0">
                        <tr>
                            <td colspan="5">Ventas:</td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td>Precio</td>
                            <td>Cantidad</td>
                            <td>Descuento</td>
                            <td>Cliente</td>
                            <td>Fecha</td>
                            <td>Total a pagar</td>
                        </tr>
                        <%
                             ArrayList<DetalleVenta> lista = VentaDB.obtenerVentas();
                             if(lista!=null){
                             for (DetalleVenta v : lista) {
                        %>
                             <tr>
                               <td><%= v.getArticulo().getNombre() %></td><br>
                               <td> <%= v.getArticulo().getPrecio() %></td><br>
                               <td> <%= v.getCantidad() %></td><br>
                               <td><%= v.getVenta().getCliente() %></td><br> 
                               <td> <%= v.getVenta().getFecha() %></td><br> 
                               <td><%= (v.getArticulo().getPrecio() * v.getCantidad())-v.getDescuento()%></td>
                             </tr>
                        <%
                                }
                             }
                        %>
                        <tr>
                            <td><input type="button" value="Volver al inicio" onclick = "location='/ResolucionPrueba3/Pages/index.jsp'"/></td>
                        </tr>
                </table>
        </table>
    </body>
</html>
