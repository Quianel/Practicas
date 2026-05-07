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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaControlTiempo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Timer timer;
	private int segundos = 0, minutos = 0, horas = 0;
	private boolean activo = false;


	/**
	 * Create the panel.
	 */
	public VentanaControlTiempo(String tiempo) {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane ProyectoTxt = new JTextPane();
		ProyectoTxt.setText("Proyecto:");
		ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ProyectoTxt.setEditable(false);
		ProyectoTxt.setBackground(new Color(180, 180, 180));
		ProyectoTxt.setBounds(24, 31, 54, 20);
		add(ProyectoTxt);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(85, 31, 148, 22);
		add(comboBox);
		
		JTextPane TareaTxt = new JTextPane();
		TareaTxt.setText("Tarea:");
		TareaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		TareaTxt.setEditable(false);
		TareaTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		TareaTxt.setBounds(24, 66, 54, 20);
		add(TareaTxt);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(85, 64, 148, 22);
		add(comboBox_1);
		
		table = new JTable();
		table.setBounds(246, 99, 194, 94);
		add(table);
		
		JLabel lblNewLabel = new JLabel(tiempo);
		lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 19));
		lblNewLabel.setBounds(65, 129, 78, 44);
		add(lblNewLabel);
		
		JButton IniciarBoton = new JButton("Iniciar");
		IniciarBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		IniciarBoton.setBounds(24, 201, 69, 22);
		IniciarBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!activo) {
					iniciarCronometro();
				}
				
			}
		});
		add(IniciarBoton);
		
		JButton PausarBoton = new JButton("Pausar");
		PausarBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		PausarBoton.setBounds(118, 200, 69, 22);
		add(PausarBoton);

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
	 private String actualizarEtiqueta() {
	        String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
	        return tiempo;
	    }
	
}
