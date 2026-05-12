package controlador;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import modelo.Catalogo_tareas;
import modelo.CrearTareaDAO;
import modelo.Estado_tarea;
import modelo.Proyecto;
import modelo.Tarea_proyecto;
import vista.VentanaCrearTarea;

public class TareaYsubtareasController {

    private VentanaCrearTarea vista;
    private CrearTareaDAO modelo;
    private Runnable alGuardar;

    public TareaYsubtareasController(
            VentanaCrearTarea vista,
            Runnable alGuardar
    ) {

        this.vista = vista;
        this.modelo = new CrearTareaDAO();
        this.alGuardar = alGuardar;

        cargarComboProyectos();
        cargarComboTipo();
        cargarComboTareaPadre();
        cargarComboEstado();

        configurarGuardar();
    }

    // =========================
    // COMBO PROYECTOS
    // =========================
    private void cargarComboProyectos() {

        try {

            ArrayList<Proyecto> lista =
                    modelo.cargarProyectos();

            DefaultComboBoxModel<Proyecto> combo =
                    new DefaultComboBoxModel<>();

            for (Proyecto p : lista) {
                combo.addElement(p);
            }

            vista.getCmbProyectos().setModel(combo);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // COMBO TIPO TAREA
    // =========================
    private void cargarComboTipo() {

        try {

            ArrayList<Catalogo_tareas> lista =
                    modelo.cargarTipoTarea();

            DefaultComboBoxModel<Catalogo_tareas> combo =
                    new DefaultComboBoxModel<>();

            for (Catalogo_tareas c : lista) {
                combo.addElement(c);
            }

            vista.getInputTipo().setModel(combo);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // COMBO TAREA PADRE
    // =========================
    private void cargarComboTareaPadre() {

        try {

            ArrayList<Tarea_proyecto> lista =
                    modelo.cargarTareaPadre();

            DefaultComboBoxModel<Tarea_proyecto> combo =
                    new DefaultComboBoxModel<>();

            Tarea_proyecto ninguna = new Tarea_proyecto();

            ninguna.setId_tarea_proyecto(0);
            ninguna.setNombre_visible("Ninguna");

            combo.addElement(ninguna);

            for (Tarea_proyecto t : lista) {
                combo.addElement(t);
            }

            vista.getInputTareaPadre().setModel(combo);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // COMBO ESTADO TAREA
    // =========================
    private void cargarComboEstado() {

        try {

            ArrayList<Estado_tarea> lista =
                    modelo.cargarEstadoTar();

            DefaultComboBoxModel<Estado_tarea> combo =
                    new DefaultComboBoxModel<>();

            for (Estado_tarea e : lista) {
                combo.addElement(e);
            }

            vista.getInputEstado().setModel(combo);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    private void configurarGuardar() {

        vista.getBtnGuardar().addActionListener(e -> {

            Tarea_proyecto nuevaTarea =
                    new Tarea_proyecto();

            nuevaTarea.setProyec(
                (Proyecto) vista.getCmbProyectos().getSelectedItem()
            );

            nuevaTarea.setCatalog(
                (Catalogo_tareas) vista.getInputTipo().getSelectedItem()
            );

            Tarea_proyecto padre =
                    (Tarea_proyecto)
                    vista.getInputTareaPadre().getSelectedItem();

            nuevaTarea.setId_tarea_padre(
                padre.getId_tarea_proyecto()
            );

            nuevaTarea.setEstadotar(
                (Estado_tarea)
                vista.getInputEstado().getSelectedItem()
            );

            nuevaTarea.setNombre_visible(
                vista.getTxtNombre().getText()
            );

            boolean exito =
                    modelo.insertarTarea(nuevaTarea);

            if (exito) {

                JOptionPane.showMessageDialog(
                    null,
                    "Tarea guardada correctamente"
                );

                alGuardar.run();

            } else {

                JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar tarea"
                );
            }
        });
    }
}