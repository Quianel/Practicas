package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.security.PrivateKey;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.MisTareasDAO;
import modelo.Proyecto;
import modelo.Tarea_proyecto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana_MisTareas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblMisTareas;
	
	DefaultTableModel modeloTabla = new DefaultTableModel() {
    	public boolean isCellEditable(int fila, int columna) {
    		return false;
    	}
	};

	/**
	 * Create the panel.
	 */
	public Ventana_MisTareas(String correo) {
		MisTareasDAO mt = new MisTareasDAO();
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloTabla.setRowCount(0);
				cargaTabla(correo);
				System.out.println("La tabla se ha refrescado correctamente");
                JOptionPane.showMessageDialog(
                        null,
                        "La tabla se ha recargado correctamente",
                        null,
                        JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(147, 257, 167, 32);
		add(btnNewButton);
		
		JLabel nombre = new JLabel();
		nombre.setText(mt.obtenerNombre(correo) + " " + mt.obtenerPerfil(correo) + " " + "(" + correo + ")");
		nombre.setBounds(136, 36, 291, 14);
		add(nombre);
		
		modeloTabla.setColumnIdentifiers(new Object[] {"Proyecto","Tarea","Estado","Comentario"});
		tblMisTareas.setModel(modeloTabla);
		modeloTabla.setRowCount(0);
		
		cargaTabla(correo);
	}
	
	private void cargaTabla(String correo) {
		
		MisTareasDAO mt = new MisTareasDAO();
		mt.cargarDatos(modeloTabla, correo);
	}
}