package controlador;

import modelo.CrearProyectoDAO;
import modelo.GestionProyectoDAO;
import modelo.Proyecto;

import vista.VentanaCrearProyecto;
import vista.VentanaGestionProyecto;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionProyectoController {

    private VentanaGestionProyecto vista;
    private GestionProyectoDAO modelo;

    public GestionProyectoController(VentanaGestionProyecto vista) {

        this.vista = vista;
        this.modelo = new GestionProyectoDAO();

        cargarTabla();

        // DOBLE CLICK PARA EDITAR
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

        // BOTÓN BUSCAR
        vista.getBotonLupa().addActionListener(e -> {

            String filtro = vista.getInputBuscarValue();

            if (filtro == null || filtro.trim().isEmpty()) {
                cargarTabla();
            } else {
                cargarTablaConFiltro(filtro.trim());
            }
        });

        // BOTÓN CREAR
        vista.getBotonProyecto().addActionListener(e -> {

            JFrame frame = new JFrame("Crear Proyecto");
            VentanaCrearProyecto ventana = new VentanaCrearProyecto();

            frame.setContentPane(ventana);
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);
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

            if (fila < 0) {
                JOptionPane.showMessageDialog(vista,
                        "Selecciona un proyecto primero.");
                return;
            }

            Object valor = vista.getTablaProyectos().getValueAt(fila, 0);

            if (valor == null) {
                JOptionPane.showMessageDialog(vista,
                        "No se pudo obtener el ID del proyecto.");
                return;
            }

            int idProyecto = ((Number) valor).intValue();

            CrearProyectoDAO dao = new CrearProyectoDAO();
            Proyecto proyecto = dao.obtenerProyectoPorId(idProyecto);

            if (proyecto == null) {
                JOptionPane.showMessageDialog(vista,
                        "No se encontró el proyecto.");
                return;
            }

            JFrame frame = new JFrame("Editar Proyecto");
            VentanaCrearProyecto ventanaEditar = new VentanaCrearProyecto();

            ventanaEditar.mostrarCargaProyecto(proyecto);

            frame.setContentPane(ventanaEditar);
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    vista.setInputBuscarValue("");
                    cargarTabla();
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error al editar proyecto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // =========================
    // CARGAR TABLA
    // =========================
    public void cargarTabla() {

        try {

            ArrayList<Proyecto> lista = modelo.traerTodos();

            if (lista == null) {
                JOptionPane.showMessageDialog(vista,
                        "No se pudieron cargar los proyectos.");
                return;
            }

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

            for (Proyecto p : lista) {

                String tipo = (p.getTipoproyec() != null)
                        ? p.getTipoproyec().getNombre()
                        : "Sin tipo";

                String estado = (p.getEstadoproyec() != null)
                        ? p.getEstadoproyec().getNombre()
                        : "Sin estado";

                modeloTabla.addRow(new Object[]{
                        p.getId_proyecto(),
                        p.getCodigo_interno(),
                        p.getNombre(),
                        tipo,
                        estado,
                        p.getFecha_inicio(),
                        p.getFecha_limite()
                });
            }

            vista.getTablaProyectos().setModel(modeloTabla);

            ocultarID();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error cargando proyectos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // =========================
    // FILTRO
    // =========================
    public void cargarTablaConFiltro(String busqueda) {

        try {

            ArrayList<Proyecto> lista = modelo.traerConFiltro(busqueda);

            if (lista == null || lista.isEmpty()) {
                cargarTabla();
                return;
            }

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

            for (Proyecto p : lista) {

                modeloTabla.addRow(new Object[]{
                        p.getId_proyecto(),
                        p.getCodigo_interno(),
                        p.getNombre(),
                        p.getTipoproyec() != null ? p.getTipoproyec().getNombre() : "",
                        p.getEstadoproyec() != null ? p.getEstadoproyec().getNombre() : "",
                        p.getFecha_inicio(),
                        p.getFecha_limite()
                });
            }

            vista.getTablaProyectos().setModel(modeloTabla);

            ocultarID();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error en filtro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void ocultarID() {

        try {
            vista.getTablaProyectos().getColumnModel().getColumn(0).setMinWidth(0);
            vista.getTablaProyectos().getColumnModel().getColumn(0).setMaxWidth(0);
            vista.getTablaProyectos().getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception ignored) {
        }
    }
}