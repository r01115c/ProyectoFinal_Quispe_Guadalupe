package Main;

import Controlador.Controlador_Frm_Registrar_Producto;
import Modelo.ListarProductos;
import Vista.frm_RegistrarProducto;

public class Principal {

    public static frm_RegistrarProducto frm_rp;
    public static Controlador.Controlador_Frm_Registrar_Producto c_frm_rp;
    public static ListarProductos lp;
    public static void main(String[] args) {
        
        
        frm_rp=new frm_RegistrarProducto();
        frm_rp.setVisible(true);
        frm_rp.setLocationRelativeTo(null);
        
        c_frm_rp=new Controlador_Frm_Registrar_Producto(frm_rp);
        lp = new ListarProductos();
        lp.MostrarTable(frm_rp.TablaProducto);
        
        frm_rp.btnActualizar.setEnabled(false);
        frm_rp.btnCancelar.setEnabled(false);
    }
}
