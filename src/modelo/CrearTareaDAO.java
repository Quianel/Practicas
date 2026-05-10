package modelo;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CrearTareaDAO {

	public boolean insertarTarea(Tarea_proyecto t) {
		boolean correcto = true;
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into tarea_proyecto (id_proyecto,id_tarea_catalogo,id_tarea_padre,id_estado_tarea,nombre_visible,activa)"
									+ " values("
									+"'" + t.getProyec().getId_proyecto() + "',"
									+"'" + t.getCatalog().getId_tarea_catalogo() + "',"
									+"'" + t.getId_tarea_padre() + "',"
									+"'" + t.getEstadotar().getId_estado_tarea() + "',"
									+"'" + t.getNombre_visible() + "',"
									+"True,"
									+")");
			consulta.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		    correcto = false;
		}
		return correcto;
	}
	
	public boolean modificarTarea(int id_tarea, int id_proyecto, int id_tarea_catalogo,int id_tarea_padre, int id_estado_tarea, String nombreVisible, boolean activa,
			LocalDate fecha_inicio,LocalDate fecha_limite,String descripcion,boolean es_generico) {
		boolean correcto = true;
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from proyecto where id_proyecto= "+ id_tarea);
			if(!registro.next()) {
			    correcto = false;
			}else {
				int valor = consulta.executeUpdate("update tarea set id_proyecto = '"+ id_proyecto 
													+ "', id_tarea_catalogo = '"+ id_tarea_catalogo
													+ "', id_tarea_padre = "+ id_tarea_padre
												    + ", id_estado_tarea = "+ id_estado_tarea
												    + ", nombre_visible = '"+ nombreVisible
												    + "', activa = '"+ activa
												    + " where id_tarea_proyecto = "+ id_tarea);
				if(valor == 0) {
					correcto = false;
				}
			}
			conexion.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		    correcto = false;
		}
		return correcto;
		
	}
	
	public ArrayList<Catalogo_tareas> cargarTipoTarea(){
		ArrayList<Catalogo_tareas> lista = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from catalogo_tareas");
			
			while(registro.next()) {
				Catalogo_tareas tipo = new Catalogo_tareas();
				tipo.setId_tarea_catalogo(registro.getInt("id_tarea_catalogo"));
				tipo.setNombre(registro.getString("nombre"));
				tipo.setSoloSenior(registro.getBoolean("solo_senior"));
				Tarea_proyecto tp = new Tarea_proyecto();
				tp.setId_tarea_proyecto(registro.getInt("id_tarea_proyecto"));
				tipo.setTareaProyecto(tp);
				Proyecto p = new Proyecto();
				p.setId_proyecto(registro.getInt("id_proyecto"));
				tipo.setProyecto(p);
				
				lista.add(tipo);
			}
			
			conexion.close();
			return lista;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Estado_tarea> cargarEstadoTar(){
		ArrayList<Estado_tarea> lista = new ArrayList<>();
		
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from estado_tarea");
			
			while(registro.next()) {
				Estado_tarea estado = new Estado_tarea();
				estado.setId_estado_tarea(registro.getInt("id_estado_tarea"));
				estado.setNombre(registro.getString("nombre"));
				
				lista.add(estado);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public ArrayList<Tarea_proyecto> cargarTareaPadre(){
		ArrayList<Tarea_proyecto> lista = new ArrayList<>();
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from tarea_proyecto");
			
			while(registro.next()) {
				Tarea_proyecto tareapadre = new Tarea_proyecto();
				tareapadre.setId_tarea_padre(registro.getInt("id_tarea_padre"));
				tareapadre.setId_tarea_proyecto(registro.getInt("id_tarea_proyecto"));
				Proyecto proyect = new Proyecto();
				proyect.setId_proyecto(registro.getInt("id_proyecto"));
				tareapadre.setProyec(proyect);
				Catalogo_tareas catalogo = new Catalogo_tareas();
				catalogo.setId_tarea_catalogo(registro.getInt("id_tarea_catalogo"));
				tareapadre.setCatalog(catalogo);
				Estado_tarea estadtare = new Estado_tarea();
				estadtare.setId_estado_tarea(registro.getInt("id_estado_tarea"));
				tareapadre.setEstadotar(estadtare);
				tareapadre.setNombre_visible(registro.getString("nombre_visible"));
				tareapadre.setActiva(registro.getBoolean("activa"));
					
				lista.add(tareapadre);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public ArrayList<Proyecto> cargarProyectos(){
		ArrayList<Proyecto> lista = new ArrayList<>();
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from proyecto");
			
			while(registro.next()) {
				Proyecto proy = new Proyecto();
				proy.setId_proyecto(registro.getInt("id_proyecto"));
				proy.setCodigo_interno(registro.getString("codigo_interno"));
				proy.setNombre(registro.getString("nombre"));
				proy.setDescripcion(registro.getString("descripcion"));
				java.sql.Date sqlFechaInicio = registro.getDate("fecha_inicio");
				if(sqlFechaInicio != null) {
					proy.setFecha_inicio(sqlFechaInicio.toLocalDate());
				}
				
				java.sql.Date sqlFechaLimite = registro.getDate("fecha_limite");
				if(sqlFechaLimite != null) {
					proy.setFecha_limite(sqlFechaLimite.toLocalDate());
				}
				proy.setEs_generico(registro.getBoolean("es_generico"));
				Tipo_proyecto tp = new Tipo_proyecto();
				tp.setId_tipo_proyecto(registro.getInt("id_tipo_proyecto"));
				proy.setTipoproyec(tp);
				Estado_proyecto ep = new Estado_proyecto();
				ep.setId_estado_proyecto(registro.getInt("id_estado_proyecto"));
				proy.setEstadoproyec(ep);
					
				lista.add(proy);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	
		
}