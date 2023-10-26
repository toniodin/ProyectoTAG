/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class Conexion {
    private Connection connection;
    private String host     = "localhost";
    private String usuario  = "root";
    private String password = "usbw";
    private String nombreBD = "proyecto_t_a_g";
    private String url = "jdbc:mysql://" + host + ":3307/" + nombreBD;
   
    public Connection DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url,this.usuario,this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.connection;
    }
}
