package controlador;


import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.ControlTiempoDAO;
import modelo.Registro_tiempo;
import modelo.Trabajador;
import vista.VentanaControlTiempo;
import vista.VentanaGestionUsuario;

public class ControlTiempoController {
	private VentanaControlTiempo vista;
	private ControlTiempoDAO modelo; 
	public ControlTiempoController(VentanaControlTiempo vista) {
		

        this.vista = vista;
        this.modelo = new ControlTiempoDAO();
        
        cargarTabla();
	}
	public void cargarTabla() {

        try {

            ArrayList<Registro_tiempo> lista = modelo.traerTodos();
            

            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Inicio");
            modeloTabla.addColumn("Fin");
            modeloTabla.addColumn("Total");
            modeloTabla.addColumn("Comentario");
           
            

            for (Registro_tiempo rt : lista) {

                modeloTabla.addRow(new Object[] {
                		rt.getId_registro(),
                		rt.getFecha_hora_inicio(),
                		rt.getFecha_hora_fin(),
                		rt.getMinutos_totales(),
                		rt.getComentario()
                        
                        
                        //"Editar | Ver | Del"
                });
            }

            vista.getTabla().setModel(modeloTabla);
            // ocultar ID
            vista.getTabla()
                    .getColumnModel().getColumn(0).setMinWidth(0);
            vista.getTabla()
                    .getColumnModel().getColumn(0).setMaxWidth(0);
            vista.getTabla()
                    .getColumnModel().getColumn(0).setWidth(0);
          

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
