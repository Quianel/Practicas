package controlador;

import modelo.GestionProyectoDAO;
import modelo.Proyecto;
import vista.VentanaGestionProyecto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class GestionProyectoController {

    private VentanaGestionProyecto vista;
    private GestionProyectoDAO modelo;

    public GestionProyectoController(VentanaGestionProyecto vista) {

        this.vista = vista;
        this.modelo = new GestionProyectoDAO();

        cargarTabla();
        eventos();
    }

    private void cargarTabla() {

        try {

            ArrayList<Proyecto> lista = modelo.traerTodos();

            DefaultTableModel modeloTabla = new DefaultTableModel();

            // =========================
            // COLUMNAS
            // =========================

            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Código");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Estado");
            //modeloTabla.addColumn("Descripción");
            modeloTabla.addColumn("Fecha Inicio");
            modeloTabla.addColumn("Fecha Límite");
            modeloTabla.addColumn("Acciones");
            //modeloTabla.addColumn("Genérico");
            

            // =========================
            // FILAS
            // =========================

            for (Proyecto p : lista) {

                modeloTabla.addRow(new Object[] {
                        p.getId_proyecto(),
                        p.getCodigo_interno(),
                        p.getNombre(),
                        p.getTipoproyec().getNombre(),
                        p.getEstadoproyec().getNombre(),
                        //p.getDescripcion(),
                        p.getFecha_inicio(),
                        p.getFecha_limite(),
                        "Editar | Ver | Del"
                        //p.isEs_generico()                        
                });
            }

            vista.getTablaProyectos().setModel(modeloTabla);
            vista.getTablaProyectos().getColumnModel().getColumn(0).setMinWidth(0);
            vista.getTablaProyectos().getColumnModel().getColumn(0).setMaxWidth(0);
            vista.getTablaProyectos().getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    private void eventos() {

        vista.getTablaProyectos().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int fila = vista.getTablaProyectos().rowAtPoint(e.getPoint());
                int columna = vista.getTablaProyectos().columnAtPoint(e.getPoint());

                // Columna acciones
                if (columna == 7) {

                    // Obtener ID oculto
                    int idProyecto = (int) vista.getTablaProyectos().getValueAt(fila, 0);

                    abrirVentanaEditar(idProyecto);
                }
            }
        });
    }
}