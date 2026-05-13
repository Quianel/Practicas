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
import javax.swing.UIManager;
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
	private JTable table;
	private Timer timer;
	private JTextPane inputComentario;

	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFinal;
	
	private long minutosTotales;
	private JComboBox inputTarea;
	private JComboBox inputProyecto;
	private int segundos = 0, minutos = 0, horas = 0;
	private boolean activo = false;
	private JLabel labelTiempo;
	private JButton btnIniciar;
	private JButton btnPausar;
	private ControlTiempoController controlador;


	/**
	 * Create the panel.
	 */
	public VentanaControlTiempo() {
		
		ControlTiempoDAO dao = new ControlTiempoDAO();
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
		
		inputProyecto = new JComboBox();
		inputProyecto.setForeground(new Color(240, 89, 68));
		inputProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputProyecto.setBounds(108, 67, 317, 22);
		add(inputProyecto);
		dao.cargarProyecto(inputProyecto);
		
		JTextPane TareaTxt = new JTextPane();
		TareaTxt.setForeground(new Color(240, 89, 68));
		TareaTxt.setText("Tarea:");
		TareaTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		TareaTxt.setEditable(false);
		TareaTxt.setBackground(new Color(53, 48, 105));
		TareaTxt.setBounds(47, 102, 54, 20);
		add(TareaTxt);
		
	    inputTarea = new JComboBox();
	    inputTarea.setForeground(new Color(240, 89, 68));
	    inputTarea.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputTarea.setBounds(108, 100, 317, 22);
		add(inputTarea);
		dao.cargarTarea(inputTarea);
		
		table = new JTable();
		table.setBounds(489, 66, 452, 224);
		
		add(table);
		
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
				if(!activo) {
				
					iniciarCronometro();
					btnIniciar.setEnabled(false);
					btnPausar.setEnabled(true);
					fechaInicio = LocalDateTime.now();
					activo = true;
					
					
					
					
				}
				
			}
		});
		add(btnIniciar);
		
		btnPausar = new JButton("Pausar");
		btnPausar.setForeground(new Color(240, 89, 68));
		btnPausar.setBackground(new Color(187, 190, 253));
		btnPausar.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		btnPausar.setBounds(238, 276, 69, 22);
		btnPausar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        timer.stop();               
		        btnPausar.setEnabled(false); 
		        btnIniciar.setEnabled(true);
		        fechaFinal = LocalDateTime.now();
		        activo = false;
		        
		        
		        minutosTotales = ChronoUnit.MINUTES.between(fechaInicio, fechaFinal);
		        dao.actualizarRegistroTiempo(fechaInicio, fechaFinal, minutosTotales,inputComentario);
		        controlador.cargarTabla();
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

	}
	private void iniciarCronometro() {
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
	        String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
	        labelTiempo.setText(tiempo);
	        
	    }
	 public JTable getTabla() {
	        return table;
	    }
	 public void setControlador(ControlTiempoController controlador) {
		    this.controlador = controlador;
		    
		}
}
