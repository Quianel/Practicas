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
import java.awt.Color;

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
		setBackground(new Color(53, 48, 105));
		MisTareasDAO mt = new MisTareasDAO();
		setLayout(null);
		
		
		JLabel lblMisTareas = new JLabel("Mis Tareas / Seleccion de trabajo");
		lblMisTareas.setForeground(new Color(240, 89, 68));
		lblMisTareas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		lblMisTareas.setBounds(230, 65, 226, 26);
		add(lblMisTareas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(230, 114, 403, 186);
		add(scrollPane);
		
		tblMisTareas = new JTable();
		tblMisTareas.setBackground(new Color(187, 190, 253));
		tblMisTareas.setForeground(new Color(240, 89, 68));
		tblMisTareas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		scrollPane.setViewportView(tblMisTareas);
		
		JLabel lblTareasAsignadas = new JLabel("Tareas asignadas a:");
		lblTareasAsignadas.setForeground(new Color(240, 89, 68));
		lblTareasAsignadas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblTareasAsignadas.setBounds(230, 90, 309, 19);
		add(lblTareasAsignadas);
		
		JButton btnNewButton = new JButton("Refrescar Tareas");
		btnNewButton.setForeground(new Color(240, 89, 68));
		btnNewButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(187, 190, 253));
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
		btnNewButton.setBounds(353, 311, 167, 32);
		add(btnNewButton);
		
		JLabel nombre = new JLabel();
		nombre.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		nombre.setForeground(new Color(240, 89, 68));
		nombre.setText(mt.obtenerNombre(correo) + " " + mt.obtenerPerfil(correo) + " " + "(" + correo + ")");
		nombre.setBounds(340, 92, 349, 14);
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