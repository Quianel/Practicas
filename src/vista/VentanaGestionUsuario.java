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

	public VentanaGestionUsuario() {
		setBackground(new Color(53, 48, 105));
		setLayout(null);

		CrearUsuarioBoton = new JButton("Crear Usuario");
		CrearUsuarioBoton.setForeground(new Color(240, 89, 68));
		CrearUsuarioBoton.setBackground(new Color(187, 190, 253));
		CrearUsuarioBoton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		CrearUsuarioBoton.setBounds(144, 143, 128, 23);
		add(CrearUsuarioBoton);

		inputBuscar = new JTextField();
		inputBuscar.setBackground(new Color(203, 205, 254));
		inputBuscar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		inputBuscar.setBounds(688, 144, 197, 20);
		add(inputBuscar);
		inputBuscar.setColumns(10);

		botonLupa = new JButton("");
		botonLupa.setBackground(new Color(187, 190, 253));
		botonLupa.setIcon(new ImageIcon("img/lupa.png"));
		botonLupa.setBounds(885, 143, 41, 23);
		add(botonLupa);

		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setBackground(new Color(187, 190, 253));
		table.setForeground(new Color(240, 89, 68));
		table.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(144, 185, 782, 220);

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
		if (inputBuscarText == null) {
			this.inputBuscar.setText("");
		} else {
			this.inputBuscar.setText(inputBuscarText);
		}
	}

	public String getInputBuscarValue() {
		return (inputBuscar.getText() != null) ? inputBuscar.getText() : "";
	}
}