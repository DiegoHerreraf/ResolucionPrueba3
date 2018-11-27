
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
        <table border="0">
                        <tr>
                            <td>Nombre</td>
                            <td>Precio</td>
                            <td>Cantidad</td>
                            <td>Descuento</td>
                            <td>Sub. Total</td>
                        </tr>
                        <%-- Los productos que tenemos en la sesion que se llama carrito --%>
                        <%
                                    ArrayList<Venta> lista = (ArrayList<Venta>)session.getAttribute("carrito");
                                    if(lista!=null){
                                        for (Venta v : lista) {
                        %>

                        <%
                                        }
                                    }
                        %>
                    </table>
                </form>
        </table>
    </body>
</html>
