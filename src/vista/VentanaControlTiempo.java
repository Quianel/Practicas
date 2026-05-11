package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.UIManager;

import modelo.ControlTiempoDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaControlTiempo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Timer timer;
	private JComboBox inputTarea;
	private JComboBox inputProyecto;
	private int segundos = 0, minutos = 0, horas = 0;
	private boolean activo = false;
	private JLabel labelTiempo;
	private JButton btnIniciar;
	private JButton btnPausar;


	/**
	 * Create the panel.
	 */
	public VentanaControlTiempo() {
		ControlTiempoDAO dao = new ControlTiempoDAO();
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane ProyectoTxt = new JTextPane();
		ProyectoTxt.setText("Proyecto:");
		ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ProyectoTxt.setEditable(false);
		ProyectoTxt.setBackground(new Color(180, 180, 180));
		ProyectoTxt.setBounds(24, 31, 54, 20);
		add(ProyectoTxt);
		
		inputProyecto = new JComboBox();
		inputProyecto.setBounds(85, 31, 317, 22);
		add(inputProyecto);
		dao.cargarProyecto(inputProyecto);
		
		JTextPane TareaTxt = new JTextPane();
		TareaTxt.setText("Tarea:");
		TareaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		TareaTxt.setEditable(false);
		TareaTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		TareaTxt.setBounds(24, 66, 54, 20);
		add(TareaTxt);
		
	    inputTarea = new JComboBox();
		inputTarea.setBounds(85, 64, 317, 22);
		add(inputTarea);
		dao.cargarTarea(inputTarea);
		
		table = new JTable();
		table.setBounds(401, 114, 317, 125);
		add(table);
		
		labelTiempo = new JLabel("00:00:00");
		labelTiempo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 25));
		labelTiempo.setBounds(120, 128, 95, 61);
		add(labelTiempo);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		btnIniciar.setBounds(85, 200, 69, 22);
		btnIniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!activo) {
					iniciarCronometro();
					btnIniciar.setEnabled(false);
					btnPausar.setEnabled(true);
					
				}
				
			}
		});
		add(btnIniciar);
		
		btnPausar = new JButton("Pausar");
		btnPausar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		btnPausar.setBounds(179, 200, 69, 22);
		btnPausar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        timer.stop();               
		        btnPausar.setEnabled(false); 
		        btnIniciar.setEnabled(true);
		    }
		});
		add(btnPausar);
		
		JTextPane inputComentario = new JTextPane();
		inputComentario.setBounds(63, 272, 267, 139);
		add(inputComentario);
		
		JTextPane ComentarioTxt = new JTextPane();
		ComentarioTxt.setFocusable(false);
		ComentarioTxt.setEditable(false);
		ComentarioTxt.setText("Comentario\r\n");
		ComentarioTxt.setBackground(new Color(180, 180, 180));
		ComentarioTxt.setBounds(61, 249, 69, 20);
		add(ComentarioTxt);

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
}
