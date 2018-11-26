
package shop.dao;
import java.sql.*;
import java.util.ArrayList;
import shop.dao.DbConnection;

public class ArticuloDB {
    //static para no tener que instanciar la clase
    public static ArrayList<Articulo> obtenerProducto(){
        ArrayList<Articulo> lista = new ArrayList<Articulo>();
        try{
            //permite llamar al procedimiento almacenado con los datos necesarios para listar
            CallableStatement cl = DbConnection.getConnection().prepareCall("{call listarArticulos()}");
            ResultSet rs = cl.executeQuery();
            while (rs.next()){
                Articulo a = new Articulo(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                lista.add(a);
            }
            
        }catch(Exception e) {}
        return lista;
    }
    public static Articulo obtenerArticulo(int codigo){
        Articulo a = null;
        try{
            CallableStatement cl = DbConnection.getConnection().prepareCall("{CALL sp_ArticuloCod(?)}");
            cl.setInt(1, codigo);
            ResultSet rs = cl.executeQuery();
            while(rs.next()){
                a = new Articulo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            }
        }catch (Exception e){}
        return a;
    }
    public static synchronized boolean actualizarArticulo(Articulo varArticulo) {
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
