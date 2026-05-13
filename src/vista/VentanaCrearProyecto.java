package vista;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import modelo.CrearProyectoDAO;
import modelo.Estado_proyecto;
import modelo.Proyecto;
import modelo.Tipo_proyecto;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// 👇 AÑADIDO
import javax.swing.SwingUtilities;

public class VentanaCrearProyecto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField InputNombre;
	private JTextField InputCodigo;
	private JTextField textField;
	private JComboBox inputTipo;
	private JComboBox inputEstado;
	private JCheckBox esGenericoBox;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private Proyecto proyectoEditando = null;

	public VentanaCrearProyecto() {
		setBackground(new Color(53, 48, 105));
		setLayout(null);

		JTextPane NombreTxt = new JTextPane();
		NombreTxt.setForeground(new Color(240, 89, 68));
		NombreTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		NombreTxt.setBackground(new Color(53, 48, 105));
		NombreTxt.setEditable(false);
		NombreTxt.setText("Nombre:");
		NombreTxt.setBounds(29, 37, 48, 20);
		add(NombreTxt);

		InputNombre = new JTextField();
		InputNombre.setBackground(new Color(187, 190, 253));
		InputNombre.setBounds(133, 37, 198, 20);
		add(InputNombre);
		InputNombre.setColumns(10);

		JTextPane CodigoTxt = new JTextPane();
		CodigoTxt.setForeground(new Color(240, 89, 68));
		CodigoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		CodigoTxt.setText("Codigo Interno:");
		CodigoTxt.setEditable(false);
		CodigoTxt.setBackground(new Color(53, 48, 105));
		CodigoTxt.setBounds(29, 62, 94, 20);
		add(CodigoTxt);

		InputCodigo = new JTextField();
		InputCodigo.setBackground(new Color(187, 190, 253));
		InputCodigo.setBounds(133, 61, 198, 20);
		add(InputCodigo);
		InputCodigo.setColumns(10);

		JTextPane TipoTxt = new JTextPane();
		TipoTxt.setForeground(new Color(240, 89, 68));
		TipoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		TipoTxt.setText("Tipo de Proyecto:");
		TipoTxt.setEditable(false);
		TipoTxt.setBackground(new Color(53, 48, 105));
		TipoTxt.setBounds(29, 87, 96, 20);
		add(TipoTxt);

		inputTipo = new JComboBox();
		inputTipo.setForeground(new Color(240, 89, 68));
		inputTipo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputTipo.setBackground(new Color(187, 190, 253));
		inputTipo.setBounds(133, 85, 198, 22);
		add(inputTipo);

		JTextPane EstadoTxt = new JTextPane();
		EstadoTxt.setForeground(new Color(240, 89, 68));
		EstadoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		EstadoTxt.setText("Estado:");
		EstadoTxt.setEditable(false);
		EstadoTxt.setBackground(new Color(53, 48, 105));
		EstadoTxt.setBounds(29, 114, 48, 20);
		add(EstadoTxt);

		inputEstado = new JComboBox();
		inputEstado.setForeground(new Color(240, 89, 68));
		inputEstado.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputEstado.setBackground(new Color(187, 190, 253));
		inputEstado.setBounds(133, 112, 198, 22);
		add(inputEstado);

		JTextPane FechaInicioTxt = new JTextPane();
		FechaInicioTxt.setForeground(new Color(240, 89, 68));
		FechaInicioTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		FechaInicioTxt.setText("Fecha Inicio:");
		FechaInicioTxt.setEditable(false);
		FechaInicioTxt.setBackground(new Color(53, 48, 105));
		FechaInicioTxt.setBounds(29, 139, 75, 20);
		add(FechaInicioTxt);

		dateChooser = new JDateChooser();
		dateChooser.setForeground(new Color(240, 89, 68));
		dateChooser.setBackground(new Color(187, 190, 253));
		dateChooser.setBounds(133, 139, 162, 20);
		add(dateChooser);

		JTextPane FechaFinalTxt = new JTextPane();
		FechaFinalTxt.setForeground(new Color(240, 89, 68));
		FechaFinalTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		FechaFinalTxt.setText("Fecha Final:");
		FechaFinalTxt.setEditable(false);
		FechaFinalTxt.setBackground(new Color(53, 48, 105));
		FechaFinalTxt.setBounds(29, 164, 75, 20);
		add(FechaFinalTxt);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setForeground(new Color(240, 89, 68));
		dateChooser_1.setBackground(new Color(187, 190, 253));
		dateChooser_1.setBounds(133, 164, 162, 20);
		add(dateChooser_1);

		JTextPane DescripcionTxt = new JTextPane();
		DescripcionTxt.setForeground(new Color(240, 89, 68));
		DescripcionTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		DescripcionTxt.setText("Descripcion:");
		DescripcionTxt.setEditable(false);
		DescripcionTxt.setBackground(new Color(53, 48, 105));
		DescripcionTxt.setBounds(29, 199, 75, 20);
		add(DescripcionTxt);

		textField = new JTextField();
		textField.setBackground(new Color(187, 190, 253));
		textField.setColumns(10);
		textField.setBounds(133, 199, 350, 176);
		add(textField);

		esGenericoBox = new JCheckBox("Es Generico");
		esGenericoBox.setForeground(new Color(240, 89, 68));
		esGenericoBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		esGenericoBox.setBackground(new Color(53, 48, 105));
		esGenericoBox.setBounds(525, 60, 98, 22);
		add(esGenericoBox);

		JButton GuardarBoton = new JButton("Guardar");
		GuardarBoton.setForeground(new Color(240, 89, 68));
		GuardarBoton.setBackground(new Color(187, 190, 253));

		GuardarBoton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String nombre = InputNombre.getText().trim();
				String codigo = InputCodigo.getText().trim();
				String descripcion = textField.getText().trim();

				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (codigo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El código interno no puede estar vacío", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (inputTipo.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Debes seleccionar un tipo de proyecto", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (inputEstado.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Debes seleccionar un estado de proyecto", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (dateChooser.getDate() == null || dateChooser_1.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Debes seleccionar ambas fechas", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (dateChooser.getDate().after(dateChooser_1.getDate())) {
					JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser posterior a la final",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Proyecto nuevoP = new Proyecto();

				nuevoP.setNombre(nombre);
				nuevoP.setCodigo_interno(codigo);
				nuevoP.setDescripcion(descripcion);
				nuevoP.setEs_generico(esGenericoBox.isSelected());

				nuevoP.setTipoproyec((Tipo_proyecto) inputTipo.getSelectedItem());
				nuevoP.setEstadoproyec((Estado_proyecto) inputEstado.getSelectedItem());

				nuevoP.setFecha_inicio(
						new java.sql.Date(dateChooser.getDate().getTime()).toLocalDate());

				nuevoP.setFecha_limite(
						new java.sql.Date(dateChooser_1.getDate().getTime()).toLocalDate());

				CrearProyectoDAO dao = new CrearProyectoDAO();
				boolean exito;

				if (proyectoEditando == null) {

					exito = dao.insertarProyecto(nuevoP);

					if (exito) {

						JOptionPane.showMessageDialog(null, "Proyecto guardado correctamente", "EXITO",
								JOptionPane.INFORMATION_MESSAGE);

						// 👇 SOLO AQUÍ: cerrar ventana al crear nuevo
						java.awt.Window w = SwingUtilities.getWindowAncestor(VentanaCrearProyecto.this);
						if (w != null) {
							w.dispose();
						}
					}

					proyectoEditando = null;

					InputNombre.setText("");
					InputCodigo.setText("");
					textField.setText("");
					esGenericoBox.setSelected(false);
					dateChooser.setDate(null);
					dateChooser_1.setDate(null);
					inputTipo.setSelectedIndex(-1);
					inputEstado.setSelectedIndex(-1);

				} else {

					exito = dao.modificarProyecto(
							proyectoEditando.getId_proyecto(),
							nuevoP.getNombre(),
							nuevoP.getCodigo_interno(),
							nuevoP.getTipoproyec(),
							nuevoP.getEstadoproyec(),
							nuevoP.getFecha_inicio(),
							nuevoP.getFecha_limite(),
							nuevoP.getDescripcion(),
							nuevoP.isEs_generico());

					if (exito) {
						JOptionPane.showMessageDialog(null, "Proyecto guardado correctamente", "EXITO",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Error al guardar proyecto", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		GuardarBoton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		GuardarBoton.setBounds(527, 353, 96, 22);
		add(GuardarBoton);

		cargarCombosTipoyEstado();
	}

	public void cargarCombosTipoyEstado() {
		CrearProyectoDAO cp = new CrearProyectoDAO();
		ArrayList<Tipo_proyecto> listaTipos = cp.cargarTipos();
		ArrayList<Estado_proyecto> listaEstado = cp.cargarEstado();

		if (listaTipos != null) {
			for (Tipo_proyecto t : listaTipos) {
				inputTipo.addItem(t);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el tipo de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}

		if (listaEstado != null) {
			for (Estado_proyecto e : listaEstado) {
				inputEstado.addItem(e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el estado de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void mostrarCargaProyecto(Proyecto p) {

		this.proyectoEditando = p;

		if (p != null) {

			InputNombre.setText(p.getNombre());
			InputCodigo.setText(p.getCodigo_interno());
			textField.setText(p.getDescripcion());

			esGenericoBox.setSelected(p.isEs_generico());

			if (p.getFecha_inicio() != null) {
				dateChooser.setDate(java.sql.Date.valueOf(p.getFecha_inicio()));
			}

			if (p.getFecha_limite() != null) {
				dateChooser_1.setDate(java.sql.Date.valueOf(p.getFecha_limite()));
			}

			inputTipo.setSelectedItem(p.getTipoproyec());
			inputEstado.setSelectedItem(p.getEstadoproyec());
		}
	}
}