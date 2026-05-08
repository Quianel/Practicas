package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	private JButton CrearUsuarioBoton;


	/**
	 * Create the panel.
	 */
	public VentanaGestionUsuario() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		CrearUsuarioBoton = new JButton("Crear Usuario");
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
		
		table = new JTable(){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		table.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		table.setBounds(10, 71, 449, 193);
		
		
		add(table);
		
	}
	 public JTable getTabla() {
	        return table;
	    }
	 public JButton getBoton() {
	        return CrearUsuarioBoton;
	    }
	
	
}
