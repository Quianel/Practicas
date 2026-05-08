package controlador;

import modelo.*;
import vista.VentanaTareas;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class TareaProyectoController {

    private VentanaTareas vista;
    private TareasYAsignacionesDAO modelo;

    public TareaProyectoController(VentanaTareas vista) {

        this.vista = vista;
        this.modelo = new TareasYAsignacionesDAO();

        cargarProyectosCombo();
        cargarTabla(-1);

        // =========================
        // FILTRO COMBOBOX
        // =========================
        vista.getInputProyecto().addActionListener(e -> {

            Proyecto p = (Proyecto) vista.getInputProyecto().getSelectedItem();

            if (p != null) {
                cargarTabla(p.getId_proyecto());
            }
        });
    }

    // =========================
    // COMBO PROYECTOS
    // =========================
    private void cargarProyectosCombo() {

        try {

            GestionProyectoDAO dao = new GestionProyectoDAO();
            ArrayList<Proyecto> lista = dao.traerTodos();

            DefaultComboBoxModel<Proyecto> combo = new DefaultComboBoxModel<>();

            // =========================
            // OPCIÓN TODOS
            // =========================
            Proyecto todos = new Proyecto();
            todos.setId_proyecto(-1);
            todos.setNombre("Todos los proyectos");

            combo.addElement(todos);

            for (Proyecto p : lista) {
                combo.addElement(p);
            }

            vista.getInputProyecto().setModel(combo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // TABLA
    // =========================
    private void cargarTabla(int idProyecto) {

        try {

            ArrayList<Tarea_proyecto> lista;

            if (idProyecto == -1) {
                lista = modelo.traerTodas();
            } else {
                lista = modelo.traerPorProyecto(idProyecto);
            }

            DefaultTableModel modeloTabla = new DefaultTableModel();

            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Proyecto");
            modeloTabla.addColumn("Tarea");
            modeloTabla.addColumn("Catálogo");
            modeloTabla.addColumn("Estado");
            modeloTabla.addColumn("Activa");

            for (Tarea_proyecto t : lista) {

                modeloTabla.addRow(new Object[] {

                    t.getId_tarea_proyecto(),
                    t.getProyec().getNombre(),
                    t.getNombre_visible(),
                    t.getCatalog().getNombre(),
                    t.getEstadotar().getNombre(),
                    t.isActiva()
                });
            }

            vista.getTable().setModel(modeloTabla);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}