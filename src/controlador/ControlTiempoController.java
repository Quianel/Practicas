package controlador;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.ControlTiempoDAO;
import modelo.Registro_tiempo;
import modelo.Trabajador;
import vista.VentanaControlTiempo;
import vista.VentanaGestionUsuario;
import vista.VentanaRegistroManual;

public class ControlTiempoController {
	private VentanaControlTiempo vista;
	private ControlTiempoDAO modelo;

	public ControlTiempoController(VentanaControlTiempo vista) {

		this.vista = vista;
		this.modelo = new ControlTiempoDAO();

		cargarTabla();
		// =======================================================================
		// BLOQUE AÑADIDO: ESCUCHADOR DE DOBLE CLIC PARA EL MODO MANUAL
		// =======================================================================
		this.vista.getTabla().addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = vista.getTabla().getSelectedRow();
					if (fila != -1) {
						// Extraer datos de la fila seleccionada
						int idRegistro = (int) vista.getTabla().getModel().getValueAt(fila, 0);
						java.time.LocalDateTime fechaHoraInicioObj = (java.time.LocalDateTime) vista.getTabla()
								.getModel().getValueAt(fila, 1);
						java.time.LocalDateTime fechaHoraFinObj = (java.time.LocalDateTime) vista.getTabla().getModel()
								.getValueAt(fila, 2);
						String comentarioOriginal = (String) vista.getTabla().getModel().getValueAt(fila, 4);

						java.util.Date fechaParaChooser = java.sql.Date.valueOf(fechaHoraInicioObj.toLocalDate());
						String horaInicioTxt = fechaHoraInicioObj.toLocalTime().toString().substring(0, 5);
						String horaFinTxt = fechaHoraFinObj.toLocalTime().toString().substring(0, 5);

						javax.swing.JFrame framePadre = (javax.swing.JFrame) javax.swing.SwingUtilities
								.getWindowAncestor(vista);
						javax.swing.JDialog ventanaFlotante = new javax.swing.JDialog(framePadre,
								"Registro manual de tiempo", true);

						VentanaRegistroManual panelManual = new VentanaRegistroManual();
						panelManual.setDatosOriginales(fechaParaChooser, horaInicioTxt, horaFinTxt, comentarioOriginal);

						modelo.Registro_Manual_DAO daoManual = new modelo.Registro_Manual_DAO();

						// Cargar proyectos en el combo
						final ArrayList<modelo.Proyecto> proyectos = daoManual.cargarProyecto();
						if (proyectos != null) {
							panelManual.getCmbProyecto().removeAllItems();
							for (modelo.Proyecto p : proyectos) {
								panelManual.getCmbProyecto()
										.addItem(p.getNombre() + " (" + p.getCodigo_interno() + ")");
							}
						}

						// Cargar tareas en el combo
						final ArrayList<modelo.Tarea_proyecto> tareas = daoManual.cargarTarea();
						if (tareas != null) {
							panelManual.getCmbTarea().removeAllItems();
							for (modelo.Tarea_proyecto ta : tareas) {
								panelManual.getCmbTarea().addItem(ta.getNombre_visible());
							}
						}

						// Programar el botón Guardar
						panelManual.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
							@Override
							public void actionPerformed(java.awt.event.ActionEvent ev) {
								try {

									java.util.Date fechaSeleccionada = panelManual.getDteFecha().getDate();
									java.sql.Date sqlDate = new java.sql.Date(fechaSeleccionada.getTime());
									String fechaTxt = sqlDate.toString();

									String nuevaHoraInicio = panelManual.getTxtHoraInicio();
									String nuevaHoraFin = panelManual.getTxtHoraFin();
									String nuevoComentario = panelManual.getTxtComentario();

									int indexProyecto = panelManual.getCmbProyecto().getSelectedIndex();
									int indexTarea = panelManual.getCmbTarea().getSelectedIndex();
									
									int idProyectoSel = (indexProyecto >= 0 && proyectos != null) ? proyectos.get(indexProyecto).getId_proyecto() : -1;
									int idTareaSel = (indexTarea >= 0 && tareas != null) ? tareas.get(indexTarea).getId_tarea_proyecto() : -1;
									
									boolean exito = daoManual.modificarRegistroManual(idRegistro, fechaTxt,
											nuevaHoraInicio, nuevaHoraFin, nuevoComentario, idProyectoSel,idTareaSel);

									if (exito) {
										ventanaFlotante.dispose(); // Cierra el cuadro flotante
										cargarTabla(); // Llama a tu método original intacto
										javax.swing.JOptionPane.showMessageDialog(vista,
												"Registro modificado manualmente con éxito.");
									} else {
										javax.swing.JOptionPane.showMessageDialog(ventanaFlotante,
												"No se pudo actualizar el registro en la base de datos.", "Error",
												javax.swing.JOptionPane.ERROR_MESSAGE);
									}
								} catch (Exception ex) {
									javax.swing.JOptionPane.showMessageDialog(ventanaFlotante,
											"Error al procesar datos. Revise el formato de hora (HH:MM).", "Error",
											javax.swing.JOptionPane.ERROR_MESSAGE);
									ex.printStackTrace();
								}
							}
						});

						ventanaFlotante.getContentPane().add(panelManual);
						ventanaFlotante.setSize(480, 340);
						ventanaFlotante.setLocationRelativeTo(framePadre);
						ventanaFlotante.setResizable(false);
						ventanaFlotante.setVisible(true);
					}
				}
			}
		});
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

				modeloTabla.addRow(new Object[] { rt.getId_registro(), rt.getFecha_hora_inicio(),
						rt.getFecha_hora_fin(), rt.getMinutos_totales(), rt.getComentario()

						// "Editar | Ver | Del"
				});
			}

			vista.getTabla().setModel(modeloTabla);
			// ocultar ID
			vista.getTabla().getColumnModel().getColumn(0).setMinWidth(0);
			vista.getTabla().getColumnModel().getColumn(0).setMaxWidth(0);
			vista.getTabla().getColumnModel().getColumn(0).setWidth(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
