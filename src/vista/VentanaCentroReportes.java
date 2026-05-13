package vista;

import javax.swing.JPanel;

import modelo.CentroReportesDAO;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.DefaultFocusTraversalPolicy;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCentroReportes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox cmbProyecto;
	private JComboBox cmbTipoProyecto;
	private JTable tblHoras;
	private JScrollPane scrollPane;

	
	DefaultTableModel modelotabla = new DefaultTableModel();
	/**
	 * Create the panel.
	 */
	public VentanaCentroReportes() {
		setLayout(null);
		
		cmbProyecto = new JComboBox();
		cmbProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proyectoSeleccionado = (String) cmbProyecto.getSelectedItem();
				CentroReportesDAO centRepBD = new CentroReportesDAO();
				modelotabla.setRowCount(0);
				
				int horasTotales = centRepBD.obtenerHorasTotalesConFiltro(proyectoSeleccionado);
				centRepBD.TablaHorasTotales(modelotabla, horasTotales);
				int horasAsignadas = centRepBD.obtenerHorasAsignadasConFiltro(proyectoSeleccionado);
				centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
				centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
				centRepBD.TablaProyectosActivosConFiltro(modelotabla, proyectoSeleccionado);
				centRepBD.TablaTrabajadoresActivosConFiltro(modelotabla, proyectoSeleccionado);
			}
		});
		cmbProyecto.setBounds(100, 10, 225, 20);
		add(cmbProyecto);
		
		cmbTipoProyecto = new JComboBox();
		cmbTipoProyecto.setBounds(300, 40, 150, 20);
		add(cmbTipoProyecto);
		
		JLabel lblProyecto = new JLabel("Proyecto:");
		lblProyecto.setBounds(10, 14, 80, 12);
		add(lblProyecto);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(246, 44, 54, 12);
		add(lblTipo);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setForeground(new Color(240, 89, 68));
		dateChooser.setBackground(new Color(187, 190, 253));
		dateChooser.setBounds(100, 70, 100, 20);
		add(dateChooser);
		
		JLabel lblFecha1 = new JLabel("Fecha de:");
		lblFecha1.setBounds(10, 70, 80, 20);
		add(lblFecha1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setForeground(new Color(240, 89, 68));
		dateChooser_1.setBackground(new Color(187, 190, 253));
		dateChooser_1.setBounds(250, 70, 100, 20);
		add(dateChooser_1);
		
		JLabel lblFecha2 = new JLabel("Hasta:");
		lblFecha2.setBounds(210, 70, 50, 20);
		add(lblFecha2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 340, 190);
		add(scrollPane);
		
		tblHoras = new JTable();
		scrollPane.setViewportView(tblHoras);
		
		modelotabla.setColumnIdentifiers(new Object[] {"Resumen","Datos"});
		tblHoras.setModel(modelotabla);
		modelotabla.setRowCount(0);
		
		CargaDeComboBoxProyectos();
		//cargaTabla();
	}
	
	private void CargaDeComboBoxProyectos() {
		CentroReportesDAO centRepBD = new CentroReportesDAO();
		centRepBD.Combobox1(cmbProyecto);
		centRepBD.Combobox2(cmbTipoProyecto);
	}
	
	private void cargaTabla() {
		CentroReportesDAO centRepBD = new CentroReportesDAO();
		int horasTotales = centRepBD.obtenerHorasTotales();
		centRepBD.TablaHorasTotales(modelotabla, horasTotales);
		int horasAsignadas = centRepBD.obtenerHorasAsignadas();
		centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
		centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
		centRepBD.TablaProyectosActivos(modelotabla);
		centRepBD.TablaTrabajadoresActivos(modelotabla);
	}
}
