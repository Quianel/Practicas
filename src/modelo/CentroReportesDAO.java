package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class CentroReportesDAO {

	public void Combobox1(JComboBox<Object> cmbProyecto, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			if(usuario.equals("Administrador")) {
				registro = consulta.executeQuery("select nombre from proyecto");
			}else {
				registro = consulta.executeQuery("select proyecto.nombre "
						+ "from proyecto, asignacion_proyecto, trabajador "
						+ "where proyecto.id_proyecto = asignacion_proyecto.id_proyecto "
						+ "and asignacion_proyecto.id_trabajador = trabajador.id_trabajador "
						+ "and trabajador.correo = '" + correo + "'");
			}
			cmbProyecto.addItem("(Todos)");
			while (registro.next()) {
				cmbProyecto.addItem(registro.getObject("nombre"));	
			}
			conexion.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void Combobox2(JComboBox<Object> cmbTipoProyecto, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			if(usuario.equals("Administrador")) {
				registro = consulta.executeQuery("select nombre from tipo_proyecto");
			}else {
				registro = consulta.executeQuery("select tipo_proyecto.nombre "
						+ "from tipo_proyecto, proyecto, asignacion_proyecto, trabajador "
						+ "where tipo_proyecto.id_tipo_proyecto = proyecto.id_tipo_proyecto "
						+ "and proyecto.id_proyecto = asignacion_proyecto.id_proyecto "
						+ "and asignacion_proyecto.id_trabajador = trabajador.id_trabajador "
						+ "and trabajador.correo = '" + correo + "'");
			}
			cmbTipoProyecto.addItem("(Todos)");
			while (registro.next()) {
				cmbTipoProyecto.addItem(registro.getObject("nombre"));	
			}
			conexion.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void TablaHorasAsignadas(DefaultTableModel modelotabla, int horasAsignadas) {
		modelotabla.addRow(new Object[] {
				"Horas asignadas", horasAsignadas + "h"
		});	}

	public void TablaHorasTotales(DefaultTableModel modelotabla, int horasTotales) {
		modelotabla.addRow(new Object[] {
				"Horas totales", horasTotales + "h"
		});	
	}

	public void TablaCumplimiento(DefaultTableModel modelotabla, int horasTotales, int horasAsignadas) {
		float resultado = (horasTotales * 100f)/horasAsignadas;
		modelotabla.addRow(new Object[] {
				"Cumplimiento", resultado + "%"
		});	
	}

	public int obtenerHorasTotalesPorProyecto(String proyectoSeleccionado, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int horasTotales = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			
			if(usuario.equals("Administrador")) {
				if(proyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("SELECT round(SUM(minutos_totales)/60,2) as 'horasTotales'"
							+ "from registro_tiempo;");
				}else {
					registro = consulta.executeQuery("SELECT round(SUM(registro_tiempo.minutos_totales)/60,2) as 'horasTotales' "
							+ "from registro_tiempo, proyecto "
							+ "where registro_tiempo.id_proyecto = proyecto.id_proyecto "
							+ "and proyecto.nombre = '" + proyectoSeleccionado + "'");	
				}
			}else {
				if(proyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("SELECT round(SUM(registro_tiempo.minutos_totales)/60,2) as 'horasTotales' "
							+ "from registro_tiempo, trabajador "
							+ "where registro_tiempo.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "'");
				}else {
					registro = consulta.executeQuery("SELECT round(SUM(registro_tiempo.minutos_totales)/60,2) as 'horasTotales' "
							+ "from registro_tiempo, proyecto, trabajador "
							+ "where registro_tiempo.id_proyecto = proyecto.id_proyecto "
							+ "and registro_tiempo.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "'"
							+ "and proyecto.nombre = '" + proyectoSeleccionado + "'");	
				}
			}
			
			if(registro.next()) {
				horasTotales = registro.getInt("horasTotales");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return horasTotales;
	}

	public int obtenerHorasAsignadasPorProyecto(String proyectoSeleccionado, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int HorasAsignadas = 0;
		int diasLaborables = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			
			if(usuario.equals("Administrador")) {
				if(proyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea");
				}else {
					registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea, tarea_proyecto, proyecto "
							+ "where asignacion_tarea.id_tarea_proyecto = tarea_proyecto.id_tarea_proyecto "
							+ "and tarea_proyecto.id_proyecto = proyecto.id_proyecto "
							+ "and proyecto.nombre = '" + proyectoSeleccionado + "'");
				}
			}else {
				if(proyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("select asignacion_tarea.fecha_asignacion as 'FechaAsignacion', asignacion_tarea.fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea, trabajador "
							+ "where asignacion_tarea.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "'");
				}else {
					registro = consulta.executeQuery("select asignacion_tarea.fecha_asignacion as 'FechaAsignacion', asignacion_tarea.fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea, tarea_proyecto, proyecto, trabajador "
							+ "where asignacion_tarea.id_tarea_proyecto = tarea_proyecto.id_tarea_proyecto "
							+ "and tarea_proyecto.id_proyecto = proyecto.id_proyecto "
							+ "and asignacion_tarea.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "' "
							+ "and proyecto.nombre = '" + proyectoSeleccionado + "'");
				}			}
			while(registro.next()) {
				LocalDate inicio = registro.getDate("FechaAsignacion").toLocalDate();
				LocalDate fin = registro.getDate("FechaFin").toLocalDate();

				while (!inicio.isAfter(fin)) {

				    DayOfWeek dia = inicio.getDayOfWeek();

				    if (dia != DayOfWeek.SATURDAY &&
				        dia != DayOfWeek.SUNDAY) {

				        diasLaborables++;
				    }

				    inicio = inicio.plusDays(1);
				}
			}
			
			HorasAsignadas = diasLaborables * 8;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return HorasAsignadas;
	}

	public void TablaProyectosActivosPorProyecto(DefaultTableModel modelotabla, String proyectoSeleccionado, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int Proyectos_Activos = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			if(usuario.equals("Administrador")) {
				if(proyectoSeleccionado.equals("(Todos)")) {
				registro = consulta.executeQuery("select count(id_proyecto) as 'Proyectos_Activos' "
						+ "from proyecto, estado_proyecto "
						+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
						+ "and estado_proyecto.nombre = 'activo';");
				}else {
					registro = consulta.executeQuery("select count(id_proyecto) as 'Proyectos_Activos' "
							+ "from proyecto, estado_proyecto "
							+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
							+ "and estado_proyecto.nombre = 'activo'"
							+ "and proyecto.nombre = '" + proyectoSeleccionado + "'");		
				}
			}else {
				if(proyectoSeleccionado.equals("(Todos)")) {
				registro = consulta.executeQuery("select count(proyecto.id_proyecto) as 'Proyectos_Activos' "
						+ "from proyecto, estado_proyecto, asignacion_proyecto, trabajador "
						+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
						+ "and estado_proyecto.nombre = 'activo' "
						+ "and proyecto.id_proyecto = asignacion_proyecto.id_proyecto "
						+ "and asignacion_proyecto.id_trabajador = trabajador.id_trabajador "
						+ "and trabajador.correo = '" + correo + "'");
				}else {
					registro = consulta.executeQuery("select count(proyecto.id_proyecto) as 'Proyectos_Activos' "
							+ "from proyecto, estado_proyecto, asignacion_proyecto, trabajador "
							+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
							+ "and estado_proyecto.nombre = 'activo'"
							+ "and proyecto.id_proyecto = asignacion_proyecto.id_proyecto "
							+ "and asignacion_proyecto.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "' "
							+ "and proyecto.nombre = '" + proyectoSeleccionado + "'");		
				}
			}
			
			if(registro.next()) {
				Proyectos_Activos = registro.getInt("Proyectos_Activos");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelotabla.addRow(new Object[] {
				"Proyectos activos", Proyectos_Activos
		});	
	}

	public void TablaTrabajadoresActivosPorProyecto(DefaultTableModel modelotabla, String proyectoSeleccionado) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int Trabajadores_Activos = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			if(proyectoSeleccionado.equals("(Todos)")) {
				registro = consulta.executeQuery("select count(id_trabajador) as 'Trabajadores_Activos' "
						+ "from trabajador, rol_sistema "
						+ "where activo = true "
						+ "and trabajador.id_rol = rol_sistema.id_rol "
						+ "and rol_sistema.nombre = 'Trabajador'");
			}else {
			registro = consulta.executeQuery("select count(trabajador.id_trabajador) as 'Trabajadores_Activos' "
					+ "from trabajador, asignacion_proyecto, proyecto "
					+ "where activo = true "
					+ "and trabajador.id_trabajador = asignacion_proyecto.id_trabajador "
					+ "and asignacion_proyecto.id_proyecto = proyecto.id_proyecto "
					+ "and proyecto.nombre = '" + proyectoSeleccionado + "'");
			}
			if(registro.next()) {
				Trabajadores_Activos = registro.getInt("Trabajadores_Activos");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelotabla.addRow(new Object[] {
				"Trabajadores activos", Trabajadores_Activos
		});	
	}

	public int obtenerHorasTotalesPorTipoProyecto(String tipoProyectoSeleccionado, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int horasTotales = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			
			if(usuario.equals("Administrador")) {
				if(tipoProyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("SELECT round(SUM(minutos_totales)/60,2) as 'horasTotales'"
							+ "from registro_tiempo;");
				}else {
					registro = consulta.executeQuery("SELECT round(SUM(registro_tiempo.minutos_totales)/60,2) as 'horasTotales' "
							+ "from registro_tiempo, proyecto, tipo_proyecto "
							+ "where registro_tiempo.id_proyecto = proyecto.id_proyecto "
							+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
							+ "and tipo_proyecto.nombre = '" + tipoProyectoSeleccionado + "'");	
				}
			}else {
				if(tipoProyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("SELECT round(SUM(minutos_totales)/60,2) as 'horasTotales'"
							+ "from registro_tiempo, trabajador "
							+ "where registro_tiempo.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "'");
				}else {
					registro = consulta.executeQuery("SELECT round(SUM(registro_tiempo.minutos_totales)/60,2) as 'horasTotales' "
							+ "from registro_tiempo, proyecto, tipo_proyecto, trabajador "
							+ "where registro_tiempo.id_proyecto = proyecto.id_proyecto "
							+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
							+ "and registro_tiempo.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "' "
							+ "and tipo_proyecto.nombre = '" + tipoProyectoSeleccionado + "'");	
				}
			}
			
			if(registro.next()) {
				horasTotales = registro.getInt("horasTotales");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return horasTotales;
	}

	public int obtenerHorasAsignadasPorTipoProyecto(String tipoProyectoSeleccionado, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int HorasAsignadas = 0;
		int diasLaborables = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			
			if(usuario.equals("Administrador")) {
				if(tipoProyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea");
				}else {
					registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea, tarea_proyecto, proyecto, tipo_proyecto "
							+ "where asignacion_tarea.id_tarea_proyecto = tarea_proyecto.id_tarea_proyecto "
							+ "and tarea_proyecto.id_proyecto = proyecto.id_proyecto "
							+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
							+ "and tipo_proyecto.nombre = '" + tipoProyectoSeleccionado + "'");
				}
			}else {
				if(tipoProyectoSeleccionado.equals("(Todos)")) {
					registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea, trabajador "
							+ "where asignacion_tarea.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "'");
				}else {
					registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
							+ "from asignacion_tarea, tarea_proyecto, proyecto, tipo_proyecto, trabajador "
							+ "where asignacion_tarea.id_tarea_proyecto = tarea_proyecto.id_tarea_proyecto "
							+ "and tarea_proyecto.id_proyecto = proyecto.id_proyecto "
							+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
							+ "and asignacion_tarea.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "'"
							+ "and tipo_proyecto.nombre = '" + tipoProyectoSeleccionado + "'");
				}
			}
			while(registro.next()) {
				LocalDate inicio = registro.getDate("FechaAsignacion").toLocalDate();
				LocalDate fin = registro.getDate("FechaFin").toLocalDate();

				while (!inicio.isAfter(fin)) {

				    DayOfWeek dia = inicio.getDayOfWeek();

				    if (dia != DayOfWeek.SATURDAY &&
				        dia != DayOfWeek.SUNDAY) {

				        diasLaborables++;
				    }

				    inicio = inicio.plusDays(1);
				}
			}
			
			HorasAsignadas = diasLaborables * 8;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return HorasAsignadas;
	}

	public void TablaProyectosActivosPorTipoProyecto(DefaultTableModel modelotabla, String tipoProyectoSeleccionado, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int Proyectos_Activos = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			
			if(usuario.equals("Administrador")) {
				if(tipoProyectoSeleccionado.equals("(Todos)")) {
				registro = consulta.executeQuery("select count(id_proyecto) as 'Proyectos_Activos' "
						+ "from proyecto, estado_proyecto "
						+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
						+ "and estado_proyecto.nombre = 'activo';");
				}else {
					registro = consulta.executeQuery("select count(id_proyecto) as 'Proyectos_Activos' "
							+ "from proyecto, estado_proyecto, tipo_proyecto "
							+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
							+ "and estado_proyecto.nombre = 'activo' "
							+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
							+ "and tipo_proyecto.nombre = '" + tipoProyectoSeleccionado + "'");		
				}
			}else {
				if(tipoProyectoSeleccionado.equals("(Todos)")) {
				registro = consulta.executeQuery("select count(proyecto.id_proyecto) as 'Proyectos_Activos' "
						+ "from proyecto, estado_proyecto, asignacion_proyecto, trabajador "
						+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
						+ "and estado_proyecto.nombre = 'activo' "
						+ "and asignacion_proyecto.id_proyecto = proyecto.id_proyecto "
						+ "and asignacion_proyecto.id_trabajador = trabajador.id_trabajador "
						+ "and trabajador.correo = '" + correo + "'");
				}else {
					registro = consulta.executeQuery("select count(proyecto.id_proyecto) as 'Proyectos_Activos' "
							+ "from proyecto, estado_proyecto, tipo_proyecto, asignacion_proyecto, trabajador "
							+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
							+ "and estado_proyecto.nombre = 'activo' "
							+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
							+ "and asignacion_proyecto.id_proyecto = proyecto.id_proyecto "
							+ "and asignacion_proyecto.id_trabajador = trabajador.id_trabajador "
							+ "and trabajador.correo = '" + correo + "' "
							+ "and tipo_proyecto.nombre = '" + tipoProyectoSeleccionado + "'");		
				}	
			}
			
			
			if(registro.next()) {
				Proyectos_Activos = registro.getInt("Proyectos_Activos");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelotabla.addRow(new Object[] {
				"Proyectos activos", Proyectos_Activos
		});	
	}

	public void TablaTrabajadoresActivosPorTipoProyecto(DefaultTableModel modelotabla, String tipoProyectoSeleccionado) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int Trabajadores_Activos = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			if(tipoProyectoSeleccionado.equals("(Todos)")) {
				registro = consulta.executeQuery("select count(id_trabajador) as 'Trabajadores_Activos' "
						+ "from trabajador, rol_sistema "
						+ "where activo = true "
						+ "and trabajador.id_rol = rol_sistema.id_rol "
						+ "and rol_sistema.nombre = 'Trabajador'");
			}else {
			registro = consulta.executeQuery("select count(trabajador.id_trabajador) as 'Trabajadores_Activos' "
					+ "from trabajador, asignacion_proyecto, proyecto, tipo_proyecto "
					+ "where activo = true "
					+ "and trabajador.id_trabajador = asignacion_proyecto.id_trabajador "
					+ "and asignacion_proyecto.id_proyecto = proyecto.id_proyecto "
					+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
					+ "and tipo_proyecto.nombre = '" + tipoProyectoSeleccionado + "'");
			}
			if(registro.next()) {
				Trabajadores_Activos = registro.getInt("Trabajadores_Activos");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelotabla.addRow(new Object[] {
				"Trabajadores activos", Trabajadores_Activos
		});	
	}

	public int obtenerHorasTotalesPorFechas(String fechaFormateadaInicio, String fechaFormateadaFin, String usuario, String correo) {
		
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int horasTotales = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();

			if(usuario.equals("Administrador")) {
				registro = consulta.executeQuery("SELECT round(SUM(registro_tiempo.minutos_totales)/60,2) as 'horasTotales' "
						+ "from registro_tiempo, tarea_proyecto, asignacion_tarea "
						+ "where registro_tiempo.id_tarea_proyecto = tarea_proyecto.id_tarea_proyecto "
						+ "and tarea_proyecto.id_tarea_proyecto = asignacion_tarea.id_tarea_proyecto "
						+ "and asignacion_tarea.fecha_asignacion = '" + fechaFormateadaInicio + "' "
						+ "and asignacion_tarea.fecha_fin_asignacion = '" + fechaFormateadaFin + "'");	
			}else {
				registro = consulta.executeQuery("SELECT round(SUM(registro_tiempo.minutos_totales)/60,2) as 'horasTotales' "
						+ "from registro_tiempo, tarea_proyecto, asignacion_tarea, trabajador "
						+ "where registro_tiempo.id_tarea_proyecto = tarea_proyecto.id_tarea_proyecto "
						+ "and tarea_proyecto.id_tarea_proyecto = asignacion_tarea.id_tarea_proyecto "
						+ "and registro_tiempo.id_trabajador = trabajador.id_trabajador "
						+ "and trabajador.correo = '" + correo + "' "
						+ "and asignacion_tarea.fecha_asignacion = '" + fechaFormateadaInicio + "' "
						+ "and asignacion_tarea.fecha_fin_asignacion = '" + fechaFormateadaFin + "'");				
			}
			if(registro.next()) {
				horasTotales = registro.getInt("horasTotales");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return horasTotales;
	}

	public int obtenerHorasAsignadasPorFechas(String fechaFormateadaInicio, String fechaFormateadaFin, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int HorasAsignadas = 0;
		int diasLaborables = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			if(usuario.equals("Administrador")) {
				registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
						+ "from asignacion_tarea "
						+ "where fecha_asignacion = '" + fechaFormateadaInicio + "' "
						+ "and fecha_fin_asignacion = '" + fechaFormateadaFin + "'");
			}else {
				registro = consulta.executeQuery("select fecha_asignacion as 'FechaAsignacion', fecha_fin_asignacion as 'FechaFin' "
						+ "from asignacion_tarea, trabajador "
						+ "where asignacion_tarea.id_trabajador = trabajador.id_trabajador "
						+ "and trabajador.correo = '" + correo + "' "
						+ "and fecha_asignacion = '" + fechaFormateadaInicio + "' "
						+ "and fecha_fin_asignacion = '" + fechaFormateadaFin + "' ");			
			}
			while(registro.next()) {
				LocalDate inicio = registro.getDate("FechaAsignacion").toLocalDate();
				LocalDate fin = registro.getDate("FechaFin").toLocalDate();

				while (!inicio.isAfter(fin)) {

				    DayOfWeek dia = inicio.getDayOfWeek();

				    if (dia != DayOfWeek.SATURDAY &&
				        dia != DayOfWeek.SUNDAY) {

				        diasLaborables++;
				    }

				    inicio = inicio.plusDays(1);
				}
			}
			
			HorasAsignadas = diasLaborables * 8;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return HorasAsignadas;
	}

	public void TablaProyectosActivosPorFechas(DefaultTableModel modelotabla, String fechaFormateadaInicio, String fechaFormateadaFin, String usuario, String correo) {
		Connection conexion;
		Statement consulta;
		ResultSet registro = null;
		int Proyectos_Activos = 0;
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			
			if(usuario.equals("Administrador")) {
				registro = consulta.executeQuery("select count(proyecto.id_proyecto) as 'Proyectos_Activos' "
						+ "from proyecto, estado_proyecto, tarea_proyecto, asignacion_tarea "
						+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
						+ "and proyecto.id_proyecto = tarea_proyecto.id_proyecto "
						+ "and tarea_proyecto.id_tarea_proyecto = asignacion_tarea.id_tarea_proyecto "
						+ "and asignacion_tarea.fecha_asignacion = '" + fechaFormateadaInicio + "' "
						+ "and asignacion_tarea.fecha_fin_asignacion = '" + fechaFormateadaFin + "'"
						+ "and estado_proyecto.nombre = 'activo';");
			}else {
				registro = consulta.executeQuery("select count(proyecto.id_proyecto) as 'Proyectos_Activos' "
						+ "from proyecto, estado_proyecto, tarea_proyecto, asignacion_tarea, trabajador "
						+ "where proyecto.id_estado_proyecto = estado_proyecto.id_estado_proyecto "
						+ "and proyecto.id_proyecto = tarea_proyecto.id_proyecto "
						+ "and tarea_proyecto.id_tarea_proyecto = asignacion_tarea.id_tarea_proyecto "
						+ "and asignacion_tarea.id_trabajador = trabajador.id_trabajador "
						+ "and trabajador.correo = '" + correo + "' "
						+ "and asignacion_tarea.fecha_asignacion = '" + fechaFormateadaInicio + "' "
						+ "and asignacion_tarea.fecha_fin_asignacion = '" + fechaFormateadaFin + "'"
						+ "and estado_proyecto.nombre = 'activo';");			
				}
			if(registro.next()) {
				Proyectos_Activos = registro.getInt("Proyectos_Activos");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelotabla.addRow(new Object[] {
				"Proyectos activos", Proyectos_Activos
		});	
	}

	//ADMIN SOLO
	public void TablaTrabajadoresActivosPorFechas(DefaultTableModel modelotabla, String fechaFormateadaInicio, String fechaFormateadaFin) {
		Statement consulta;
		ResultSet registro = null;
		int Trabajadores_Activos = 0;
		try {
			Connection conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select count(trabajador.id_trabajador) as 'Trabajadores_Activos' "
					+ "from trabajador, asignacion_tarea "
					+ "where trabajador.id_trabajador = asignacion_tarea.id_trabajador "
					+ "and asignacion_tarea.fecha_asignacion = '" + fechaFormateadaInicio + "' "
					+ "and asignacion_tarea.fecha_fin_asignacion = '" + fechaFormateadaFin + "'");
			if(registro.next()) {
				Trabajadores_Activos = registro.getInt("Trabajadores_Activos");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelotabla.addRow(new Object[] {
				"Trabajadores activos", Trabajadores_Activos
		});	
	}

}
