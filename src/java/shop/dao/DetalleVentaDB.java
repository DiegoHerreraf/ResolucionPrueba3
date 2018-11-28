/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class DetalleVentaDB {
    public static synchronized boolean insertarDetalleVenta(DetalleVenta varDetalle, Connection cn) {
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y sus 4 parametros
            String call = "{CALL sp_detalleventa(?,?,?,?)}";
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //Codigo de la venta
            cl.setInt(1, varDetalle.getCodigoVenta());
            //Codigo del Articulo
            cl.setInt(2, varDetalle.getCodigoArticulo());
            //La cantidad
            cl.setInt(3, varDetalle.getCantidad());
            //El descuento
            cl.setInt(4, varDetalle.getDescuento());
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = cl.executeUpdate() == 1 ? true : false;
            DbConnection.cerrarCall(cl);
        } catch (SQLException e) {
            e.printStackTrace();
            DbConnection.cerrarCall(cl);
        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.cerrarCall(cl);
        }
        return rpta;
    }
}
