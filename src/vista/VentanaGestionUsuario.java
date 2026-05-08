package vista;

import javax.swing.JPanel;


import java.awt.Color;
import javax.swing.JButton;

import javax.swing.JTextField;


import java.awt.Font;


import javax.swing.JTable;
import javax.swing.JScrollPane;


public class VentanaGestionUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton CrearUsuarioBoton;
	private JTable table;


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
		
		table  = new JTable() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 69, 650, 220);

        add(scrollPane);
		
	}
	 public JTable getTabla() {
	        return table;
	    }
	 public JButton getBoton() {
	        return CrearUsuarioBoton;
	    }
}
