package vista;

import javax.swing.JPanel;


import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JTextField;


import java.awt.Font;


import javax.swing.JTable;
import javax.swing.JScrollPane;


public class VentanaGestionUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField inputBuscar;
	private JButton CrearUsuarioBoton;
	 private JButton botonLupa;
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
		
		inputBuscar = new JTextField();
		inputBuscar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		inputBuscar.setBounds(245, 29, 116, 20);
		add(inputBuscar);
		inputBuscar.setColumns(10);
		
	    botonLupa = new JButton("");
	    botonLupa.setIcon(new ImageIcon("img/lupa.png"));
	    botonLupa.setBounds(359, 27, 41, 23);
		add(botonLupa);
		
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
	 public JButton getBotonLupa() {
	        return botonLupa;
	    }
	 public void setInputBuscarValue(String inputBuscarText) {
		 inputBuscar.setText(inputBuscarText);
	    }
	 public String getInputBuscarValue() {
	    	return inputBuscar.getText();
	    }
}
