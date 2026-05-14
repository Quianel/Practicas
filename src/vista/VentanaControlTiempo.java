package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.JComboBox;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controlador.ControlTiempoController;
import modelo.ControlTiempoDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaControlTiempo extends JPanel {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private JTextPane inputComentario;

	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFinal;

	private long minutosTotales;
	private JComboBox<String> inputTarea;
	private JComboBox<String> inputProyecto;
	private int segundos = 0, minutos = 0, horas = 0;
	private boolean activo = false;
	private JLabel labelTiempo;
	private JButton btnIniciar;
	private JButton btnPausar;
	private ControlTiempoController controlador;
	private JTable table;

	private int[] idsProyecto;
	private int[] idsTarea;
	private ControlTiempoDAO dao;

	public VentanaControlTiempo() {

		dao = new ControlTiempoDAO();
		setBackground(new Color(53, 48, 105));
		setLayout(null);

		JTextPane ProyectoTxt = new JTextPane();
		ProyectoTxt.setForeground(new Color(240, 89, 68));
		ProyectoTxt.setText("Proyecto:");
		ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		ProyectoTxt.setEditable(false);
		ProyectoTxt.setBackground(new Color(53, 48, 105));
		ProyectoTxt.setBounds(47, 67, 54, 20);
		add(ProyectoTxt);

		inputProyecto = new JComboBox<>();
		inputProyecto.setBackground(new Color(187, 190, 253));
		inputProyecto.setForeground(new Color(240, 89, 68));
		inputProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputProyecto.setBounds(108, 67, 317, 22);
		add(inputProyecto);
		idsProyecto = dao.cargarProyectoConIds(inputProyecto);

		JTextPane TareaTxt = new JTextPane();
		TareaTxt.setForeground(new Color(240, 89, 68));
		TareaTxt.setText("Tarea:");
		TareaTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		TareaTxt.setEditable(false);
		TareaTxt.setBackground(new Color(53, 48, 105));
		TareaTxt.setBounds(47, 102, 54, 20);
		add(TareaTxt);

		inputTarea = new JComboBox<>();
		inputTarea.setBackground(new Color(187, 190, 253));
		inputTarea.setForeground(new Color(240, 89, 68));
		inputTarea.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputTarea.setBounds(108, 100, 317, 22);
		add(inputTarea);
		idsTarea = dao.cargarTareaConIds(inputTarea);

		labelTiempo = new JLabel("00:00:00");
		labelTiempo.setForeground(new Color(240, 89, 68));
		labelTiempo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 28));
		labelTiempo.setBounds(172, 189, 154, 61);
		add(labelTiempo);

		btnIniciar = new JButton("Iniciar");
		btnIniciar.setForeground(new Color(240, 89, 68));
		btnIniciar.setBackground(new Color(187, 190, 253));
		btnIniciar.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		btnIniciar.setBounds(159, 276, 69, 22);
		btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!activo) {
					iniciarCronometro();
					btnIniciar.setEnabled(false);
					btnPausar.setEnabled(true);
					fechaInicio = LocalDateTime.now();
					activo = true;
				}
			}
		});
		add(btnIniciar);

		btnPausar = new JButton("Detener");
		btnPausar.setForeground(new Color(240, 89, 68));
		btnPausar.setBackground(new Color(187, 190, 253));
		btnPausar.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		btnPausar.setBounds(238, 276, 69, 22);
		btnPausar.setEnabled(false); // empieza deshabilitado
		btnPausar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 1. Para el timer
				timer.stop();
				activo = false;
				fechaFinal = LocalDateTime.now();

				// 2. Guarda en BD
				minutosTotales = ChronoUnit.MINUTES.between(fechaInicio, fechaFinal);

				int idxProyecto = inputProyecto.getSelectedIndex();
				int idxTarea = inputTarea.getSelectedIndex();

				int idProyectoSel = (idxProyecto >= 0 && idsProyecto != null) ? idsProyecto[idxProyecto] : -1;

				int idTareaSel = (idxTarea >= 0 && idsTarea != null) ? idsTarea[idxTarea] : -1;

				int idTrabajadorActual = 1; // reemplaza por el trabajador de sesión

				dao.actualizarRegistroTiempo(fechaInicio, fechaFinal, minutosTotales, inputComentario,
						idTrabajadorActual, idProyectoSel, idTareaSel);

				// 3. Refresca la tabla
				if (controlador != null) {
					controlador.cargarTabla();
				}

				// 4. Resetea cronómetro y UI
				segundos = 0;
				minutos = 0;
				horas = 0;

				actualizarEtiqueta();

				inputComentario.setText("");

				btnPausar.setEnabled(false);
				btnIniciar.setEnabled(true);
			}
		});
		add(btnPausar);

		inputComentario = new JTextPane();
		inputComentario.setBackground(new Color(187, 190, 253));
		inputComentario.setBounds(491, 301, 339, 184);
		add(inputComentario);

		JTextPane ComentarioTxt = new JTextPane();
		ComentarioTxt.setForeground(new Color(240, 89, 68));
		ComentarioTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		ComentarioTxt.setFocusable(false);
		ComentarioTxt.setEditable(false);
		ComentarioTxt.setText("Comentario\r\n");
		ComentarioTxt.setBackground(new Color(53, 48, 105));
		ComentarioTxt.setBounds(489, 278, 69, 20);
		add(ComentarioTxt);

		JTextPane HistorialHoyTxt = new JTextPane();
		StyledDocument doc = HistorialHoyTxt.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		HistorialHoyTxt.setText("Historial de hoy");
		HistorialHoyTxt.setForeground(new Color(240, 89, 40));
		HistorialHoyTxt.setBackground(new Color(187, 190, 253));
		HistorialHoyTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		HistorialHoyTxt.setBounds(489, 46, 452, 20);
		add(HistorialHoyTxt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(491, 67, 450, 200);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	private void iniciarCronometro() {
		// Si había un timer anterior, lo cancela antes de crear uno nuevo
		if (timer != null && timer.isRunning()) {
			timer.stop();
		}
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				segundos++;
				if (segundos == 60) {
					segundos = 0;
					minutos++;
				}
				if (minutos == 60) {
					minutos = 0;
					horas++;
				}
				actualizarEtiqueta();
			}
		});
		timer.start();
	}

	private void actualizarEtiqueta() {
		labelTiempo.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
	}

	public JTable getTabla() {
		return table;
	}

	public void setControlador(ControlTiempoController controlador) {
		this.controlador = controlador;
	}
}