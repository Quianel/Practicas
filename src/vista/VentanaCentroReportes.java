package vista;

import javax.swing.JPanel;

import modelo.CentroReportesDAO;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.beans.PropertyChangeEvent;

public class VentanaCentroReportes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox cmbProyecto;
	private JComboBox cmbTipoProyecto;
	private JTable tblHoras;
	private JScrollPane scrollPane;

	String fechaFormateadaInicio;
	String fechaFormateadaFin;
	
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
				
				int horasTotales = centRepBD.obtenerHorasTotalesPorProyecto(proyectoSeleccionado);
				centRepBD.TablaHorasTotales(modelotabla, horasTotales);
				int horasAsignadas = centRepBD.obtenerHorasAsignadasPorProyecto(proyectoSeleccionado);
				centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
				centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
				centRepBD.TablaProyectosActivosPorProyecto(modelotabla, proyectoSeleccionado);
				centRepBD.TablaTrabajadoresActivosPorProyecto(modelotabla, proyectoSeleccionado);
			}
		});
		cmbProyecto.setBounds(100, 10, 225, 20);
		add(cmbProyecto);
		
		cmbTipoProyecto = new JComboBox();
		cmbTipoProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoProyectoSeleccionado = (String) cmbTipoProyecto.getSelectedItem();
				CentroReportesDAO centRepBD = new CentroReportesDAO();
				modelotabla.setRowCount(0);
				
				int horasTotales = centRepBD.obtenerHorasTotalesPorTipoProyecto(tipoProyectoSeleccionado);
				centRepBD.TablaHorasTotales(modelotabla, horasTotales);
				int horasAsignadas = centRepBD.obtenerHorasAsignadasPorTipoProyecto(tipoProyectoSeleccionado);
				centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
				centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
				centRepBD.TablaProyectosActivosPorTipoProyecto(modelotabla, tipoProyectoSeleccionado);
				centRepBD.TablaTrabajadoresActivosPorTipoProyecto(modelotabla, tipoProyectoSeleccionado);
			}
		});
		cmbTipoProyecto.setBounds(300, 40, 150, 20);
		add(cmbTipoProyecto);
		
		JLabel lblProyecto = new JLabel("Proyecto:");
		lblProyecto.setBounds(10, 14, 80, 12);
		add(lblProyecto);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(246, 44, 54, 12);
		add(lblTipo);
		
		JDateChooser dtFechaInicio = new JDateChooser();
		dtFechaInicio.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date fechaInicio = dtFechaInicio.getDate();
				if(fechaInicio != null){
			        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					fechaFormateadaInicio = formato.format(fechaInicio);
					
					if(fechaFormateadaFin != null) {
						modelotabla.setRowCount(0);
						CentroReportesDAO centRepBD = new CentroReportesDAO();
						int horasTotales = centRepBD.obtenerHorasTotalesPorFechas(fechaFormateadaInicio, fechaFormateadaFin);
						centRepBD.TablaHorasTotales(modelotabla, horasTotales);
						int horasAsignadas = centRepBD.obtenerHorasAsignadasPorFechas(fechaFormateadaInicio, fechaFormateadaFin);
						centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
						centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
						centRepBD.TablaProyectosActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin);
						centRepBD.TablaTrabajadoresActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin);
					}
					
				}
			}
		});
		dtFechaInicio.setForeground(new Color(240, 89, 68));
		dtFechaInicio.setBackground(new Color(187, 190, 253));
		dtFechaInicio.setBounds(100, 70, 100, 20);
		add(dtFechaInicio);
		
		JLabel lblFecha1 = new JLabel("Fecha de:");
		lblFecha1.setBounds(10, 70, 80, 20);
		add(lblFecha1);
		
		JDateChooser dtFechaFin = new JDateChooser();
		dtFechaFin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date fechaFin = dtFechaFin.getDate();
				if(fechaFin != null){
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					fechaFormateadaFin = formato.format(fechaFin);
					
					if(fechaFormateadaInicio != null) {
						modelotabla.setRowCount(0);
						CentroReportesDAO centRepBD = new CentroReportesDAO();
						int horasTotales = centRepBD.obtenerHorasTotalesPorFechas(fechaFormateadaInicio, fechaFormateadaFin);
						centRepBD.TablaHorasTotales(modelotabla, horasTotales);
						int horasAsignadas = centRepBD.obtenerHorasAsignadasPorFechas(fechaFormateadaInicio, fechaFormateadaFin);
						centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
						centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
						centRepBD.TablaProyectosActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin);
						centRepBD.TablaTrabajadoresActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin);
					}
				}
			}
		});
		dtFechaFin.setForeground(new Color(240, 89, 68));
		dtFechaFin.setBackground(new Color(187, 190, 253));
		dtFechaFin.setBounds(250, 70, 100, 20);
		add(dtFechaFin);
		
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
