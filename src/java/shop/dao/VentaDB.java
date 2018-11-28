/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.dao;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Diego
 */
public class VentaDB {

    //Insertamos un Detalle de venta a la base de datos
   public static boolean insertarVenta(Venta varventa, ArrayList<DetalleVenta> detalle) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            String call = "{CALL sp_InresarVenta(?,?)}";
            cn = DbConnection.getConnection();
            cn.setAutoCommit(false);
            cl = cn.prepareCall(call);
            //el codigo se autogenera y es del tipo OUT en el procedimiento almacenado
            cl.registerOutParameter(1, Types.INTEGER);
            cl.setString(2, varventa.getCliente());
            rpta = cl.executeUpdate() == 1 ? true : false;
            //Producto de la insercion se genera el codigoventa
            varventa.setCodigoVenta(cl.getInt(1));
            if (rpta) {
                for (DetalleVenta det : detalle) {
                    det.setCodigoVenta(varventa.getCodigoVenta());
                    rpta = DetalleVentaDB.insertarDetalleVenta(det, cn);
                    //Si nos devuelve false salimos del for
                    if (!rpta) {
                        break;
                    }
                }
                if (rpta) {
                    cn.commit();
                } else {
                    DbConnection.deshacerCambios(cn);
                }
            } else {
                DbConnection.deshacerCambios(cn);
            }
            DbConnection.cerrarCall(cl);
            DbConnection.cerrarConnection(cn);
        } catch (SQLException e) {
            e.printStackTrace();
            DbConnection.deshacerCambios(cn);
            DbConnection.cerrarCall(cl);
            DbConnection.cerrarConnection(cn);
        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.deshacerCambios(cn);
            DbConnection.cerrarCall(cl);
            DbConnection.cerrarConnection(cn);
        }
        return rpta;
    }
    //Obtener todas las ventas de la bd
    public static  ArrayList<DetalleVenta> obtenerVentas() {
        ArrayList<DetalleVenta> lista = new ArrayList<DetalleVenta>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs = null;
        try {
            String call = "{CALL sp_venta_All()}";
            cn = DbConnection.getConnection();
            cl = cn.prepareCall(call);
            rs = cl.executeQuery();
            while (rs.next()) {
                Venta ven=new Venta();
                Articulo art=new Articulo();
                DetalleVenta det=new DetalleVenta();
                ven.setCodigoVenta(rs.getInt("CodigoVenta"));
                ven.setCliente(rs.getString("Cliente"));
                ven.setFecha(rs.getTimestamp("Fecha"));
                art.setCodigoArticulo(rs.getInt("CodigoArticulo"));
                art.setNombre(rs.getString("Nombre"));
                art.setPrecio(rs.getInt("Precio"));
                det.setCantidad(rs.getInt("Cantidad"));
                det.setDescuento(rs.getInt("Parcial"));
                det.setVenta(ven);
                det.setArticulo(art);
                lista.add(det);
            }
            DbConnection.cerrarCall(cl);
            DbConnection.cerrarConnection(cn);
        } catch (SQLException e) {
            e.printStackTrace();
            DbConnection.cerrarCall(cl);
            DbConnection.cerrarConnection(cn);
        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.cerrarCall(cl);
            DbConnection.cerrarConnection(cn);
        }
        return lista;
    }
}
