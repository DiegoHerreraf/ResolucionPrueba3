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
    //Metodo utilizado para insertar un Detalle de Venta a nuestra Base de datos
    //Obtenemos la conexion de Venta debido a que la clase Venta es la que inicia
    //la transaccion
   public static synchronized boolean insertarVenta(Venta varventa, ArrayList<DetalleVenta> detalle) {

        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL sp_venta(?,?)}";

//Obtenemos la conexion
            cn = DbConnection.getConnection();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //Como el codigo se autogenera y es del tipo OUT en el procedimiento
            //almacenado le decimos que es OUT y el del tipo Integer en Java
            cl.registerOutParameter(1, Types.INTEGER);
            //El siguiente parametro del procedimiento almacenado es el cliente
            cl.setString(2, varventa.getCliente());
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = cl.executeUpdate() == 1 ? true : false;
            //Codigo que se genero producto de la insercion ---> codigoVenta
            varventa.setCodigoVenta(cl.getInt(1));
            if (rpta) {
                for (DetalleVenta det : detalle) {
                    //Establecemos al detalle el codigo genero producto de la venta
                    det.setCodigoVenta(varventa.getCodigoVenta());
                    //Insertamos el detalle y le pasamos la conexion
                    rpta = DetalleVentaDB.insertarDetalleVenta(det, cn);
                    //Si nos devuelve false salimos del for
                    if (!rpta) {
                        break;
                    }
                }
                if (rpta) {
                    //Confirmamos la transaccion
                    cn.commit();
                } else {
                    //Negamos la transaccion
                    DbConnection.deshacerCambios(cn);
                }
            } else {
                //Negamos la transaccion
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

    //Metodo utilizado para obtener todos las ventas de nuestra base de datos
    public static synchronized ArrayList<DetalleVenta> obtenerVentas() {
        //El array que contendra todos nuestros productos
        ArrayList<DetalleVenta> lista = new ArrayList<DetalleVenta>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs = null;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL sp_venta_All()}";

            cn = DbConnection.getConnection();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
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
