package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Ventana_MisTareas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblMisTareas;
	
	DefaultTableModel modeloTabla;

	/**
	 * Create the panel.
	 */
	public Ventana_MisTareas() {
		setLayout(null);
		
		JLabel lblMisTareas = new JLabel("Mis Tareas / Seleccion de trabajo");
		lblMisTareas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		lblMisTareas.setBounds(36, 11, 226, 26);
		add(lblMisTareas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 60, 403, 186);
		add(scrollPane);
		
		tblMisTareas = new JTable();
		scrollPane.setViewportView(tblMisTareas);
		
		JLabel lblTareasAsignadas = new JLabel("Tareas asignadas a:");
		lblTareasAsignadas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblTareasAsignadas.setBounds(24, 36, 309, 19);
		add(lblTareasAsignadas);
		
		JButton btnNewButton = new JButton("Refrescar Tareas");
		btnNewButton.setBounds(147, 257, 167, 32);
		add(btnNewButton);
		
		modeloTabla.setColumnIdentifiers(new Object[] {"Proyecto","Tarea","Estado","Comentario"});
		tblMisTareas.setModel(modeloTabla);
		

	}
}
