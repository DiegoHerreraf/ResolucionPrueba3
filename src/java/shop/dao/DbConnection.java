/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class DbConnection {
    public DbConnection() {
    }
    
    public static Connection getConnection(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/prueba3db?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pass = "";
        Connection con = null;
        try {
            Class.forName(driver);
            con  = (Connection)DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion satisfactoria");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }    
    public static synchronized void deshacerCambios(Connection cn) {
        try{cn.rollback();}catch (Exception e){}
    }
    //Metodo utilizado para cerrar el callablestatemente
    public static void cerrarCall(CallableStatement cl) {
        try{cl.close();}catch(Exception e){}
    }
    //Metodo utilizado para cerrar la conexion
    public static synchronized void cerrarConnection(Connection cn) {
        try{cn.close();} catch (Exception e) {}
    }
}
