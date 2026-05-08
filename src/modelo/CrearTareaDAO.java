package modelo;

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
}