package vista;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class VentanaGestionProyecto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField inputBuscar;
	private JTable tablaProyectos;

	/**
	 * Create the panel.
	 */
	public VentanaGestionProyecto() {
		setBackground(new Color(205, 205, 205));
		setForeground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane BuscarTxt = new JTextPane();
		BuscarTxt.setText("Buscar:");
		BuscarTxt.setEditable(false);
		BuscarTxt.setBackground(new Color(205, 205, 205));
		BuscarTxt.setForeground(new Color(0, 0, 0));
		BuscarTxt.setBounds(10, 26, 49, 20);
		add(BuscarTxt);
		
		inputBuscar = new JTextField();
		inputBuscar.setBounds(55, 26, 96, 20);
		add(inputBuscar);
		inputBuscar.setColumns(10);
		
		JButton botonLupa = new JButton("");
		botonLupa.setIcon(new ImageIcon("C:\\Users\\DAM\\Desktop\\lupa2 (1).jpg"));
		botonLupa.setBounds(161, 26, 19, 20);
		add(botonLupa);
		
		JButton botonProyecto = new JButton("Crear Proyecto");
		botonProyecto.setBounds(322, 24, 105, 22);
		add(botonProyecto);
		
		tablaProyectos = new JTable();
		tablaProyectos.setBounds(10, 69, 417, 163);
		add(tablaProyectos);

	}
}
