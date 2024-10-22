package Conexion;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConexionMysql {
    
    Connection cn;
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/miproyecto","root","");
            System.out.println("Conexion Exitosa");
            
        }catch (Exception e){
            System.out.println("Error de Conexion"+e);
        }
        return cn;
    }
}
