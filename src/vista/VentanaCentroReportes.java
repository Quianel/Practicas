package vista;

import javax.swing.JPanel;

import modelo.CentroReportesDAO;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import controlador.ExportarComoCSV;
import controlador.ExportarComoXLSX;

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
import javax.swing.JButton;
import java.awt.Font;

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
	 * @param usuario 
	 */
	public VentanaCentroReportes(String usuario, String correo) {
		setBackground(new Color(53, 48, 105));
		setLayout(null);
		
		cmbProyecto = new JComboBox();
		cmbProyecto.setForeground(new Color(240, 89, 68));
		cmbProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		cmbProyecto.setBackground(new Color(187, 190, 253));
		cmbProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proyectoSeleccionado = (String) cmbProyecto.getSelectedItem();
				CentroReportesDAO centRepBD = new CentroReportesDAO();
				modelotabla.setRowCount(0);
				
				int horasTotales = centRepBD.obtenerHorasTotalesPorProyecto(proyectoSeleccionado, usuario, correo);
				centRepBD.TablaHorasTotales(modelotabla, horasTotales);
				int horasAsignadas = centRepBD.obtenerHorasAsignadasPorProyecto(proyectoSeleccionado, usuario, correo);
				centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
				centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
				centRepBD.TablaProyectosActivosPorProyecto(modelotabla, proyectoSeleccionado, usuario, correo);
				if(usuario.equals("Admnistrador")) {
					centRepBD.TablaTrabajadoresActivosPorProyecto(modelotabla, proyectoSeleccionado);
				}
			}
		});
		cmbProyecto.setBounds(295, 79, 225, 20);
		add(cmbProyecto);
		
		cmbTipoProyecto = new JComboBox();
		cmbTipoProyecto.setForeground(new Color(240, 89, 68));
		cmbTipoProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		cmbTipoProyecto.setBackground(new Color(187, 190, 253));
		cmbTipoProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoProyectoSeleccionado = (String) cmbTipoProyecto.getSelectedItem();
				CentroReportesDAO centRepBD = new CentroReportesDAO();
				modelotabla.setRowCount(0);
				
				int horasTotales = centRepBD.obtenerHorasTotalesPorTipoProyecto(tipoProyectoSeleccionado, usuario, correo);
				centRepBD.TablaHorasTotales(modelotabla, horasTotales);
				int horasAsignadas = centRepBD.obtenerHorasAsignadasPorTipoProyecto(tipoProyectoSeleccionado, usuario, correo);
				centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
				centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
				centRepBD.TablaProyectosActivosPorTipoProyecto(modelotabla, tipoProyectoSeleccionado, usuario, correo);
				if(usuario.equals("Admnistrador")) {
					centRepBD.TablaTrabajadoresActivosPorTipoProyecto(modelotabla, tipoProyectoSeleccionado);
				}
			}
		});
		cmbTipoProyecto.setBounds(295, 108, 225, 20);
		add(cmbTipoProyecto);
		
		JLabel lblProyecto = new JLabel("Proyecto:");
		lblProyecto.setForeground(new Color(240, 89, 68));
		lblProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblProyecto.setBounds(205, 83, 80, 12);
		add(lblProyecto);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(new Color(240, 89, 68));
		lblTipo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblTipo.setBackground(new Color(53, 48, 105));
		lblTipo.setBounds(205, 112, 54, 12);
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
						int horasTotales = centRepBD.obtenerHorasTotalesPorFechas(fechaFormateadaInicio, fechaFormateadaFin, usuario, correo);
						centRepBD.TablaHorasTotales(modelotabla, horasTotales);
						int horasAsignadas = centRepBD.obtenerHorasAsignadasPorFechas(fechaFormateadaInicio, fechaFormateadaFin, usuario, correo);
						centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
						centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
						centRepBD.TablaProyectosActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin, usuario, correo);
						if(usuario.equals("Admnistrador")) {
							centRepBD.TablaTrabajadoresActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin);
						}
					}
					
				}
			}
		});
		dtFechaInicio.setForeground(new Color(240, 89, 68));
		dtFechaInicio.setBackground(new Color(187, 190, 253));
		dtFechaInicio.setBounds(295, 139, 100, 20);
		add(dtFechaInicio);
		
		JLabel lblFecha1 = new JLabel("Fecha de:");
		lblFecha1.setForeground(new Color(240, 89, 68));
		lblFecha1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblFecha1.setBackground(new Color(53, 48, 105));
		lblFecha1.setBounds(205, 139, 80, 20);
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
						int horasTotales = centRepBD.obtenerHorasTotalesPorFechas(fechaFormateadaInicio, fechaFormateadaFin, usuario, correo);
						centRepBD.TablaHorasTotales(modelotabla, horasTotales);
						int horasAsignadas = centRepBD.obtenerHorasAsignadasPorFechas(fechaFormateadaInicio, fechaFormateadaFin, usuario, correo);
						centRepBD.TablaHorasAsignadas(modelotabla, horasAsignadas);
						centRepBD.TablaCumplimiento(modelotabla, horasTotales, horasAsignadas);
						centRepBD.TablaProyectosActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin, usuario, correo);
						if(usuario.equals("Admnistrador")) {
							centRepBD.TablaTrabajadoresActivosPorFechas(modelotabla, fechaFormateadaInicio, fechaFormateadaFin);
						}
					}
				}
			}
		});
		dtFechaFin.setForeground(new Color(240, 89, 68));
		dtFechaFin.setBackground(new Color(187, 190, 253));
		dtFechaFin.setBounds(445, 139, 100, 20);
		add(dtFechaFin);
		
		JLabel lblFecha2 = new JLabel("Hasta:");
		lblFecha2.setForeground(new Color(240, 89, 68));
		lblFecha2.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblFecha2.setBackground(new Color(53, 48, 105));
		lblFecha2.setBounds(405, 139, 50, 20);
		add(lblFecha2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(205, 169, 350, 150);
		add(scrollPane);
		
		tblHoras = new JTable();
		tblHoras.setBackground(new Color(187, 190, 253));
		tblHoras.setForeground(new Color(240, 89, 68));
		tblHoras.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		scrollPane.setViewportView(tblHoras);
		
		modelotabla.setColumnIdentifiers(new Object[] {"Resumen","Datos"});
		tblHoras.setModel(modelotabla);
		
		JButton btnExpoCSV = new JButton("Exportar como CSV");
		btnExpoCSV.setForeground(new Color(240, 89, 68));
		btnExpoCSV.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		btnExpoCSV.setBackground(new Color(187, 190, 253));
		btnExpoCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(
						null,
						"¿Desea exportar la tabla actual como CSV?",
						"Confirmación",
						JOptionPane.YES_NO_OPTION
				);
				if(respuesta == JOptionPane.YES_OPTION) {
					ExportarComoCSV expCSV = new ExportarComoCSV();
					Boolean exito = expCSV.ExportarComoCSV(modelotabla);
					if(exito) {
						JOptionPane.showMessageDialog(null,
							"Se ha exportado la tabla como CSV correctamente");
					}
				}else {
					JOptionPane.showMessageDialog(
							null, 
							"No se ha exportado la tabla"
					);
				}
			}
		});
		btnExpoCSV.setBounds(205, 329, 150, 20);
		add(btnExpoCSV);
		
		JButton btnExpoXLSX = new JButton("Exportar como Excel");
		btnExpoXLSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(
						null,
						"¿Desea exportar la tabla actual como XSLX (excel)?",
						"Confirmación",
						JOptionPane.YES_NO_OPTION
				);
				if(respuesta == JOptionPane.YES_OPTION) {
					ExportarComoXLSX expXLSX = new ExportarComoXLSX();
					boolean exito = expXLSX.ExportarComoXLSX(modelotabla);
					
					if(exito) {
					JOptionPane.showMessageDialog(null,
						"Se ha exportado la tabla como XSLX (excel) correctamente");
					}
					
				}else {
					JOptionPane.showMessageDialog(
							null, 
							"No se ha exportado la tabla"
					);
				}
			}
		});
		btnExpoXLSX.setForeground(new Color(240, 89, 68));
		btnExpoXLSX.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		btnExpoXLSX.setBackground(new Color(187, 190, 253));
		btnExpoXLSX.setBounds(405, 329, 150, 20);
		add(btnExpoXLSX);
		
		
		modelotabla.setRowCount(0);
		
		CargaDeComboBoxProyectos(usuario, correo);
		//cargaTabla();
	}
	
	private void CargaDeComboBoxProyectos(String usuario, String correo) {
		CentroReportesDAO centRepBD = new CentroReportesDAO();
		centRepBD.Combobox1(cmbProyecto, usuario, correo);
		centRepBD.Combobox2(cmbTipoProyecto, usuario, correo);
	}
}
