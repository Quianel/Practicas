package controlador;

import modelo.CrearProyectoDAO;
import modelo.GestionProyectoDAO;
import modelo.Proyecto;

import vista.VentanaCrearProyecto;
import vista.VentanaGestionProyecto;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class GestionProyectoController {

    private VentanaGestionProyecto vista;
    private GestionProyectoDAO modelo;

    public GestionProyectoController(VentanaGestionProyecto vista) {

        this.vista = vista;
        this.modelo = new GestionProyectoDAO();

        cargarTabla();

        // =========================
        // DOBLE CLICK PARA EDITAR
        // =========================

        this.vista.getTablaProyectos().addMouseListener(
            new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {

                    if (e.getClickCount() == 2) {
                        editarProyecto();
                    }
                }
            }
        );
        
        // =========================
        // BOTON BUSCAR
        // =========================
        
        vista.getBotonLupa().addActionListener(e -> {
        	cargarTablaConFiltro(vista.getInputBuscarValue());
        });

        // =========================
        // BOTÓN CREAR
        // =========================

        vista.getBotonProyecto().addActionListener(e -> {

            JFrame frame = new JFrame("Crear Proyecto");

            VentanaCrearProyecto ventana =
                    new VentanaCrearProyecto();

            frame.setContentPane(ventana);

            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);

            // FALTA ESTO
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {

                    vista.setInputBuscarValue("");
                    cargarTabla();
                }
            });
        });
    }

    // =========================
    // EDITAR PROYECTO
    // =========================

    private void editarProyecto() {

        try {

            int fila = vista.getTablaProyectos().getSelectedRow();

            if (fila == -1) return;

            int idProyecto = (int) vista
                    .getTablaProyectos()
                    .getValueAt(fila, 0);

            CrearProyectoDAO dao = new CrearProyectoDAO();

            Proyecto proyecto = dao.obtenerProyectoPorId(idProyecto);

            if (proyecto != null) {

                JFrame frame = new JFrame("Editar Proyecto");

                VentanaCrearProyecto ventanaEditar =
                        new VentanaCrearProyecto();

                ventanaEditar.mostrarCargaProyecto(proyecto);

                frame.setContentPane(ventanaEditar);

                frame.setSize(700, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frame.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                    	vista.setInputBuscarValue("");
                        cargarTabla();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // CARGAR TABLA
    // =========================

    public void cargarTabla() {

        try {

            ArrayList<Proyecto> lista = modelo.traerTodos();

            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Código");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Estado");
            modeloTabla.addColumn("Fecha Inicio");
            modeloTabla.addColumn("Fecha Límite");
            //modeloTabla.addColumn("Acciones");

            for (Proyecto p : lista) {

                modeloTabla.addRow(new Object[] {

                        p.getId_proyecto(),
                        p.getCodigo_interno(),
                        p.getNombre(),
                        p.getTipoproyec().getNombre(),
                        p.getEstadoproyec().getNombre(),
                        p.getFecha_inicio(),
                        p.getFecha_limite()
                        //"Editar | Ver | Del"
                });
            }

            vista.getTablaProyectos().setModel(modeloTabla);

            // ocultar ID
            vista.getTablaProyectos()
                    .getColumnModel().getColumn(0).setMinWidth(0);
            vista.getTablaProyectos()
                    .getColumnModel().getColumn(0).setMaxWidth(0);
            vista.getTablaProyectos()
                    .getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // =========================
    // CARGAR TABLA CON FILTROS
    // =========================
    
    public void cargarTablaConFiltro(String busqueda) {

        try {

            ArrayList<Proyecto> lista = modelo.traerConFiltro(busqueda);

            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Código");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Estado");
            modeloTabla.addColumn("Fecha Inicio");
            modeloTabla.addColumn("Fecha Límite");
            //modeloTabla.addColumn("Acciones");

            for (Proyecto p : lista) {

                modeloTabla.addRow(new Object[] {

                        p.getId_proyecto(),
                        p.getCodigo_interno(),
                        p.getNombre(),
                        p.getTipoproyec().getNombre(),
                        p.getEstadoproyec().getNombre(),
                        p.getFecha_inicio(),
                        p.getFecha_limite()
                        //"Editar | Ver | Del"
                });
            }

            vista.getTablaProyectos().setModel(modeloTabla);

            // ocultar ID
            vista.getTablaProyectos()
                    .getColumnModel().getColumn(0).setMinWidth(0);
            vista.getTablaProyectos()
                    .getColumnModel().getColumn(0).setMaxWidth(0);
            vista.getTablaProyectos()
                    .getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}