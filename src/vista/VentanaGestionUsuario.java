package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaGestionUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;


	/**
	 * Create the panel.
	 */
	public VentanaGestionUsuario() {
		VentanaGestionUsuario vgu = new VentanaGestionUsuario();
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JButton CrearUsuarioBoton = new JButton("Crear Usuario");
		CrearUsuarioBoton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				VentanaCrearUsuario nuevoPanel = new VentanaCrearUsuario(); 
				
				if (vgu != null) {
					vgu.removeAll();
					vgu.add(nuevoPanel);
					vgu.revalidate();
					vgu.repaint();
			    }
				
			}
		});
		CrearUsuarioBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		CrearUsuarioBoton.setBounds(10, 27, 116, 23);
		add(CrearUsuarioBoton);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		textField.setBounds(245, 29, 116, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(359, 27, 41, 23);
		add(btnNewButton);
		
		table = new JTable();
		table.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		table.setBounds(10, 71, 449, 193);
		add(table);
		
	}
	
}
