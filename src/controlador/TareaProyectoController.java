package controlador;

import modelo.*;
import vista.VentanaCrearTarea;
import vista.VentanaTareas;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

				if (p.getId_proyecto() != -1) {
					cargarTrabajadoresSinAsignar(p.getId_proyecto());
				} else {
					vista.getListaAsignados().setModel(new DefaultListModel<>());
					vista.getListaSinAsignar().setModel(new DefaultListModel<>());
				}
			}
		});

		// =========================
		// NUEVA TAREA
		// =========================
		vista.getBtnNuevaTarea().addActionListener(e -> {
			VentanaCrearTarea vct = new VentanaCrearTarea();
			JFrame frame = new JFrame("Crear tarea");

			new TareaYsubtareasController(vct, () -> {
				Proyecto p = (Proyecto) vista.getInputProyecto().getSelectedItem();

				if (p != null) {
					cargarTabla(p.getId_proyecto());
				} else {
					cargarTabla(-1);
				}

				frame.dispose();
			});

			frame.setContentPane(vct);
			frame.setSize(400, 350);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});

		// =========================
		// SELECCION TABLA
		// =========================
		vista.getTable().getSelectionModel().addListSelectionListener(e -> {

			if (!e.getValueIsAdjusting()) {

				int fila = vista.getTable().getSelectedRow();

				if (fila != -1) {

					int idTareaProyecto = (int) vista.getTable().getValueAt(fila, 0);

					cargarTrabajadoresAsignados(idTareaProyecto);
				}
			}
		});

		// =========================
		// BOTON QUITAR SIN ASIGNAR
		// =========================
		vista.getBtnQuitarSinAsignar().addActionListener(e -> {

			quitarTrabajador();
		});

		// =========================
		// BOTON QUITAR ASIGNADO
		// =========================
		vista.getBtnQuitarAsignado().addActionListener(e -> {

			quitarAsignacion();
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

			Proyecto todos = new Proyecto();

			todos.setId_proyecto(-1);
			todos.setNombre("Seleccione un proyecto");

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

			DefaultTableModel modeloTabla = new DefaultTableModel() {

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {

					// SOLO editable la columna "Trabajador"
					return column == 5;
				}
			};

			modeloTabla.addColumn("ID");
			modeloTabla.addColumn("Proyecto");
			modeloTabla.addColumn("Tarea");
			modeloTabla.addColumn("Catálogo");
			modeloTabla.addColumn("Estado");
			modeloTabla.addColumn("Trabajador");

			for (Tarea_proyecto t : lista) {

				modeloTabla.addRow(new Object[] {

						t.getId_tarea_proyecto(),

						t.getProyec().getNombre(),

						t.getNombre_visible(),

						t.getCatalog().getNombre(),

						t.getEstadotar().getNombre(),

						"Asignar"

						// t.isActiva()
				});
			}

			vista.getTable().setModel(modeloTabla);

			// =========================
			// COMBOBOX EN TABLA
			// =========================
			Proyecto proyectoSeleccionado = (Proyecto) vista.getInputProyecto().getSelectedItem();

			if (proyectoSeleccionado != null && proyectoSeleccionado.getId_proyecto() != -1) {

				JComboBox<Trabajador> comboTrabajadores = new JComboBox<>();

				ArrayList<Trabajador> trabajadores = modelo
						.obtenerTrabajadoresProyecto(proyectoSeleccionado.getId_proyecto());

				for (Trabajador t : trabajadores) {

					comboTrabajadores.addItem(t);
				}

				vista.getTable().getColumnModel().getColumn(5)
						.setCellEditor(new DefaultCellEditor(comboTrabajadores));

				comboTrabajadores.addActionListener(e -> {

					int fila = vista.getTable().getSelectedRow();

					if (fila == -1) {

						return;
					}

					Trabajador trabajador = (Trabajador) comboTrabajadores.getSelectedItem();

					if (trabajador == null) {

						return;
					}

					int idTareaProyecto = (int) vista.getTable().getValueAt(fila, 0);

					try {

						modelo.asignarTrabajadorATarea(idTareaProyecto, trabajador.getId_trabajador());

						cargarTrabajadoresAsignados(idTareaProyecto);

						Proyecto p = (Proyecto) vista.getInputProyecto().getSelectedItem();

						if (p != null && p.getId_proyecto() != -1) {

							cargarTrabajadoresSinAsignar(p.getId_proyecto());
						}

						JOptionPane.showMessageDialog(null, "Trabajador asignado correctamente");

					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				});
			}

			// ocultar ID
			vista.getTable().getColumnModel().getColumn(0).setMinWidth(0);
			vista.getTable().getColumnModel().getColumn(0).setMaxWidth(0);
			vista.getTable().getColumnModel().getColumn(0).setWidth(0);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void cargarTrabajadoresAsignados(int idTareaProyecto) {

		try {

			ArrayList<Trabajador> lista = modelo.obtenerTrabajadoresPorTarea(idTareaProyecto);

			DefaultListModel<Trabajador> modeloLista = new DefaultListModel<>();

			for (Trabajador t : lista) {

				modeloLista.addElement(t);
			}

			vista.getListaAsignados().setModel(modeloLista);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void cargarTrabajadoresSinAsignar(int idProyecto) {

		try {

			ArrayList<Trabajador> lista = modelo.obtenerTrabajadoresSinAsignar(idProyecto);

			DefaultListModel<Trabajador> modeloLista = new DefaultListModel<>();

			for (Trabajador t : lista) {

				modeloLista.addElement(t);
			}

			vista.getListaSinAsignar().setModel(modeloLista);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void quitarAsignacion() {

		try {

			int fila = vista.getTable().getSelectedRow();

			if (fila == -1) {

				JOptionPane.showMessageDialog(null, "Seleccione una tarea");

				return;
			}

			Trabajador trabajador = vista.getListaAsignados().getSelectedValue();

			if (trabajador == null) {

				JOptionPane.showMessageDialog(null, "Seleccione un trabajador asignado");

				return;
			}

			int idTareaProyecto = (int) vista.getTable().getValueAt(fila, 0);

			modelo.quitarAsignacion(idTareaProyecto, trabajador.getId_trabajador());

			cargarTrabajadoresAsignados(idTareaProyecto);

			Proyecto p = (Proyecto) vista.getInputProyecto().getSelectedItem();

			if (p != null && p.getId_proyecto() != -1) {

				cargarTrabajadoresSinAsignar(p.getId_proyecto());
			}

			JOptionPane.showMessageDialog(null, "Asignación quitada correctamente");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void quitarTrabajador() {

		try {

			Trabajador trabajador = vista.getListaSinAsignar().getSelectedValue();

			if (trabajador == null) {

				JOptionPane.showMessageDialog(null, "Seleccione un trabajador");

				return;
			}

			modelo.desactivarTrabajador(trabajador.getId_trabajador());

			Proyecto p = (Proyecto) vista.getInputProyecto().getSelectedItem();

			if (p != null && p.getId_proyecto() != -1) {

				cargarTrabajadoresSinAsignar(p.getId_proyecto());
			}

			JOptionPane.showMessageDialog(null, "Trabajador desactivado correctamente");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}