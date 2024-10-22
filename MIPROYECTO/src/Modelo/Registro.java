package Modelo;

import Conexion.ConexionMysql;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Registro {
    ConexionMysql con = new ConexionMysql();
    Connection cn = con.conectar();
    
    public void registrarbd(Producto p){
        try{
            PreparedStatement ps = cn.prepareStatement("INSERT INTO producto(nombre,cantidad,precio,total)VALUES(?,?,?,?)");
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getCantidad());
            ps.setDouble(3, p.getPrecio());
            ps.setDouble(4, p.Total());
            ps.executeUpdate();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al Registrar Datos"+e);
            
        }
    }
    
    public void actualizarbd(Producto p,int id){
        try{
            String consulta= "UPDATE producto SET nombre=?, cantidad=?, precio=?, total=? WHERE id="+id+"";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getCantidad());
            ps.setDouble(3, p.getPrecio());
            ps.setDouble(4, p.Total());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Actualizacion Exitosa:");
            
            
        }catch (Exception e){
            JOptionPane.showInputDialog(null,"Error al Actualizar el Registro:"+e);
        }
    }
    
    public void eliminarbd (int id){
        try{
            String consulta="DELETE FROM producto WHERE id="+id+"";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ps.execute();
            System.out.println("El producto se elimino correctamente: ");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar un producto:"+e);
        }
    }

    
}
