package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;

public class VentanaTareas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public VentanaTareas() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane ProyectoTxt = new JTextPane();
		ProyectoTxt.setText("Proyecto:");
		ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ProyectoTxt.setBackground(new Color(180, 180, 180));
		ProyectoTxt.setBounds(38, 34, 54, 20);
		add(ProyectoTxt);
		
		JComboBox InputProyecto = new JComboBox();
		InputProyecto.setBounds(98, 34, 193, 22);
		add(InputProyecto);
		
		table = new JTable();
		table.setBounds(39, 119, 312, 170);
		add(table);
		
		JButton QuitarAsignarBoton = new JButton("Quitar");
		QuitarAsignarBoton.setBounds(505, 175, 63, 22);
		add(QuitarAsignarBoton);
		
		JButton QuitarSinTxt = new JButton("Quitar");
		QuitarSinTxt.setBounds(505, 334, 63, 22);
		add(QuitarSinTxt);
		
		textField = new JTextField();
		textField.setBounds(375, 83, 193, 114);
		add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnTrabajadores = new JTextPane();
		txtpnTrabajadores.setText("Trabajadores Asignados al Proyecto");
		txtpnTrabajadores.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnTrabajadores.setBounds(375, 63, 193, 20);
		add(txtpnTrabajadores);
		
		JTextPane txtpnTrabajadoresSinAsignar = new JTextPane();
		txtpnTrabajadoresSinAsignar.setText("Trabajadores sin asignar Proyectos");
		txtpnTrabajadoresSinAsignar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnTrabajadoresSinAsignar.setBounds(375, 222, 193, 20);
		add(txtpnTrabajadoresSinAsignar);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(375, 242, 193, 114);
		add(textField_1);

	}
}
