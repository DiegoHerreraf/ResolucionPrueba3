
package shop.dao;
import java.sql.*;
import java.util.ArrayList;
import shop.dao.DbConnection;

public class ArticuloDB {
    //static para no tener que instanciar la clase

    public static  ArrayList<Articulo> obtenerArticulo() {
        //El array que contendra todos nuestros productos
        ArrayList<Articulo> lista = new ArrayList<Articulo>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs = null;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL listarArticulos}";
            cn = DbConnection.getConnection();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                Articulo a = new Articulo(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                //Lo adicionamos a nuestra lista
                lista.add(a);
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
    public static  Articulo obtenerArticulo(int codigo) {
        Articulo a = new Articulo();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs = null;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL sp_ArticuloCod(?)}";
            cn = DbConnection.getConnection();
            cl = cn.prepareCall(call);
            cl.setInt(1, codigo);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                a.setCodigoArticulo(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setPrecio(rs.getInt(3));
                a.setImagen(rs.getString(4));
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
        return a;
    }
    public static boolean actualizarArticulo(Articulo varArticulo) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL sp_Articulo(?,?,?)}";
            //Obtenemos la conexion
            cn = DbConnection.getConnection();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //El primer parametro del procedimiento almacenado es el codigo
            cl.setInt(1, varArticulo.getCodigoArticulo());
            //El siguiente parametro del procedimiento almacenado es el nombre
            cl.setString(2, varArticulo.getNombre());
            //Y por ultimo el precio
            cl.setInt(3, varArticulo.getPrecio());
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = cl.executeUpdate() == 1 ? true : false;
            if (rpta) {
                //Confirmamos la transaccion
                cn.commit();
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
    
}
