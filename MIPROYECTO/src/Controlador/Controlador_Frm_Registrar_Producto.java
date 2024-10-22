package Controlador;

import Modelo.ListarProductos;
import Modelo.Producto;
import Modelo.Registro;
import Vista.frm_RegistrarProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class Controlador_Frm_Registrar_Producto implements ActionListener, ListSelectionListener {

    frm_RegistrarProducto frm_rp;

    public Controlador_Frm_Registrar_Producto(frm_RegistrarProducto frm_rp) {
        this.frm_rp = frm_rp;
        this.frm_rp.btnGuardar.addActionListener(this);
        this.frm_rp.TablaProducto.getSelectionModel().addListSelectionListener(this);
        this.frm_rp.btnActualizar.addActionListener(this);
        this.frm_rp.btnCancelar.addActionListener(this);
        this.frm_rp.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm_rp.btnGuardar) {

            // Validación del campo de nombre (solo letras)
            String nombre = frm_rp.txtNombre.getText();
            if (!nombre.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.");
                return;
            }

            // Validación del campo de cantidad (solo números)
            String cantidadText = frm_rp.txtCantidad.getText();
            if (!cantidadText.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero.");
                return;
            }

            // Validación del campo de precio (solo números o decimales)
            String precioText = frm_rp.txtPrecio.getText();
            if (!precioText.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.");
                return;
            }

            // Conversión de los campos ya validados
            int cantidad = Integer.parseInt(cantidadText);
            double precio = Double.parseDouble(precioText);

            Producto P = new Producto(nombre, cantidad, precio);
            Registro R = new Registro();
            R.registrarbd(P);
            ListarProductos lp = new ListarProductos();
            lp.MostrarTable(frm_rp.TablaProducto);
            limpiarentradas();
        }
        
        if (e.getSource() == frm_rp.btnActualizar) {
            int id = Integer.parseInt(frm_rp.txtId.getText());
            String nombre = frm_rp.txtNombre.getText();
            int cantidad = Integer.parseInt(frm_rp.txtCantidad.getText());
            Double precio = Double.parseDouble(frm_rp.txtPrecio.getText());
            Producto producto = new Producto(nombre, cantidad, precio);
            Registro r = new Registro();
            r.actualizarbd(producto, id);
            limpiarentradas();
            
            ListarProductos LP = new ListarProductos();
            LP.MostrarTable(frm_rp.TablaProducto);
        }
        
        if (e.getSource() == frm_rp.btnCancelar) {
            frm_rp.btnActualizar.setEnabled(false);
            frm_rp.btnCancelar.setEnabled(false);
            frm_rp.btnGuardar.setEnabled(true);
            limpiarentradas();
        }
        
        if (e.getSource() == frm_rp.btnEliminar) {
            int FilaObtenida = frm_rp.TablaProducto.getSelectedRow();
            TableModel modelo = frm_rp.TablaProducto.getModel();
            Object id = modelo.getValueAt(FilaObtenida, 0);
            
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar Este Producto?", "Eliminar producto", JOptionPane.YES_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                Registro R = new Registro();
                R.eliminarbd((int) id);
                ListarProductos LP = new ListarProductos();
                LP.MostrarTable(frm_rp.TablaProducto);
            
                limpiarentradas();
                JOptionPane.showMessageDialog(null, "Registro Eliminado Correctamente ");
            } else {
                System.out.println("No presionó nada");
            }
        }
    }

    private void limpiarentradas() {
        frm_rp.txtId.setText("");
        frm_rp.txtNombre.setText("");
        frm_rp.txtCantidad.setText("");
        frm_rp.txtPrecio.setText("");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (e.getSource() == frm_rp.TablaProducto.getSelectionModel()) {
                int FilaObtenida = frm_rp.TablaProducto.getSelectedRow();
                if (FilaObtenida >= 0) {
                    TableModel modelo = frm_rp.TablaProducto.getModel();
                    Object id = modelo.getValueAt(FilaObtenida, 0);
                    Object nombre = modelo.getValueAt(FilaObtenida, 1);
                    Object cantidad = modelo.getValueAt(FilaObtenida, 2);
                    Object precio = modelo.getValueAt(FilaObtenida, 3);
                    
                    frm_rp.txtId.setText(id.toString());
                    frm_rp.txtNombre.setText(nombre.toString());
                    frm_rp.txtCantidad.setText(cantidad.toString());
                    frm_rp.txtPrecio.setText(precio.toString());
                    
                    frm_rp.btnGuardar.setEnabled(false);
                    frm_rp.btnActualizar.setEnabled(true);
                    frm_rp.btnCancelar.setEnabled(true);
                }
                System.out.println("Fila: " + FilaObtenida);
            }
        }
    }
}
