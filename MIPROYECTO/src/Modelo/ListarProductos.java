package Modelo;

import Conexion.ConexionMysql;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarProductos {
    
    ConexionMysql con = new ConexionMysql();
    Connection cn = con.conectar();
    
    public void MostrarTable(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");
        String consultasql = "SELECT*FROM producto";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultasql);
            while(rs.next()){
                Object [] lista = {rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getDouble(5)};
                modelo.addRow(lista);
            }
            tabla.setModel(modelo);
        }catch (Exception e){
            System.out.println("ERROR AL LSITAR LOS DATOS"+e);
        }
    }
}
