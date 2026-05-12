package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class MisTareasDAO {
	public String obtenerNombre(String correo) {
		String nombre = null;
		try {
			Connection conexion = ConexionBD.getConexion();
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select nombre from trabajador where correo = '" + correo + "'");
			
			if(registro.next()) {
				nombre = registro.getString("nombre");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return nombre;
	}


	public String obtenerPerfil(String correo) {
		String PerfilLb = null;
		try {
			Connection conexion = ConexionBD.getConexion();
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select perfil_laboral.nombre as 'Nombre' "
					+ "from perfil_laboral, trabajador "
					+ "where correo = '" + correo + "' "
					+ "and trabajador.id_perfil = perfil_laboral.id_perfil");
			
			if(registro.next()) {
				PerfilLb = registro.getString("nombre");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return PerfilLb;
	}


	public static ArrayList<Tarea_proyecto> cargarLaTabla(String correo) {
		ArrayList<Tarea_proyecto> arrTareas =  new ArrayList<>();
		try {
			Connection conexion = ConexionBD.getConexion();
			Statement consulta = conexion.createStatement();
			ResultSet registro;
			
			registro = consulta.executeQuery("select proyecto.nombre as 'Proyecto', tarea_proyecto.nombre_visible as 'Tarea', "
					+ "estado_tarea.nombre as 'Estado', registro_tiempo.comentario as 'Comentario' "
					+ "from proyecto, tarea_proyecto, estado_tarea, registro_tiempo, trabajador "
					+ "where proyecto.id_proyecto=tarea_proyecto.id_proyecto "
					+ "and tarea_proyecto.id_estado_tarea = estado_tarea.id_estado_tarea "
					+ "and tarea_proyecto.id_tarea_proyecto = registro_tiempo.id_tarea_proyecto "
					+ "and registro_tiempo.id_trabajador = trabajador.id_trabajador "
					+ "and trabajador.correo = '" + correo + "'");
			
			while(registro.next()) {
				Tarea_proyecto TareaProyectoActual = new Tarea_proyecto();
				TareaProyectoActual.setNombre_visible(registro.getString("Tarea"));
				
				Proyecto ProyectoActual = new Proyecto();
				ProyectoActual.setNombre(registro.getString("Proyecto"));
				TareaProyectoActual.setProyec(ProyectoActual);
				
				Estado_tarea EstadoTareaActual = new Estado_tarea();
				EstadoTareaActual.setNombre(registro.getString("Estado"));
				TareaProyectoActual.setEstadotar(EstadoTareaActual);
				
			    Registro_tiempo RegistroTiempoActual =
			            new Registro_tiempo(
			                registro.getString("Comentario")
			            );

			    TareaProyectoActual.setRegTie(RegistroTiempoActual);
				
				
				arrTareas.add(TareaProyectoActual);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return arrTareas;
	}


	public void cargarDatos(DefaultTableModel modeloTabla, String correo) {
		for(Tarea_proyecto tp : MisTareasDAO.cargarLaTabla(correo)) {
			modeloTabla.addRow(new Object[] {
				tp.getProyec().getNombre(),
				tp.getNombre_visible(),
				tp.getEstadotar().getNombre(),
				tp.getRegTie().getComentario()
			});
		}
	}
}