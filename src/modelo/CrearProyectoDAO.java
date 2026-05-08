package modelo;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CrearProyectoDAO {
	
	/* YA NO HACE FALTA YA QUE AHORA SE BUSCA POR ID
	 * public Proyecto obtenerProyecto() {
		Proyecto p = new Proyecto();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from proyecto");
			
			if(registro.next()) {
				p.setId_proyecto(registro.getInt("id_proyecto"));
				p.setCodigo_interno(registro.getString("codigo_interno"));
				p.setNombre(registro.getString("nombre"));
				p.setDescripcion(registro.getString("descripcion"));
				p.setEs_generico(registro.getBoolean("es_generico"));
				
				Tipo_proyecto tipo = new Tipo_proyecto();
				tipo.setId_tipo_proyecto(registro.getInt("id_tipo_proyecto"));//primero guardo en el objeto para luego hacer el set
				p.setTipoproyec(tipo);
				
				Estado_proyecto estado = new Estado_proyecto();
				estado.setId_estado_proyecto(registro.getInt("id_estado_proyecto"));
				p.setEstadoproyec(estado);
				
				if(registro.getDate("fecha_inicio") != null) {
					p.setFecha_inicio(registro.getDate("fecha_inicio").toLocalDate());//convierto en lo que necesito para el DAO el modo fecha
				}
				if(registro.getDate("fecha_limite") != null) {
					p.setFecha_limite(registro.getDate("fecha_limite").toLocalDate());
				}
				conexion.close();
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}*/
	
	public boolean insertarProyecto(Proyecto p) {
		boolean correcto = true;
		if (p.getFecha_inicio() == null || p.getFecha_limite() == null) {
		    return false;
		}
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into proyecto (codigo_interno,nombre,descripcion,fecha_inicio,fecha_limite,"
									+ "es_generico,id_tipo_proyecto,id_estado_proyecto) values("
									+"'" + p.getCodigo_interno() + "',"
									+"'" + p.getNombre() + "',"
									+"'" + p.getDescripcion() + "',"
									+"'" + java.sql.Date.valueOf(p.getFecha_inicio()) + "',"
									+"'" + java.sql.Date.valueOf(p.getFecha_limite()) + "',"
									+(p.isEs_generico() ? 1 : 0) + ","
									+ p.getTipoproyec().getId_tipo_proyecto() + ","
									+p.getEstadoproyec().getId_estado_proyecto()
									+")");
			consulta.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		    correcto = false;
		}
		return correcto;
	}
	
	public boolean modificarProyecto(int id_proyecto,String nombre,String codigo_interno,Tipo_proyecto tipoproyec,Estado_proyecto estadoproyec,
			LocalDate fecha_inicio,LocalDate fecha_limite,String descripcion,boolean es_generico) {
		boolean correcto = true;
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from proyecto where id_proyecto= "+ id_proyecto);
			if(!registro.next()) {
			    correcto = false;
			}else {
				int valor = consulta.executeUpdate("update proyecto set nombre = '"+ nombre 
													+ "', codigo_interno = '"+ codigo_interno
													+ "', id_tipo_proyecto = "+ tipoproyec.getId_tipo_proyecto()
												    + ", id_estado_proyecto = "+ estadoproyec.getId_estado_proyecto()
												    + ", fecha_inicio = '"+ fecha_inicio
												    + "', fecha_limite = '"+ fecha_limite
												    + "', descripcion = '"+ descripcion
												    + "', es_generico = "+ (es_generico ? 1 : 0)
												    + " where id_proyecto= "+ id_proyecto);
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
	
	public ArrayList<Tipo_proyecto> cargarTipos(){
		ArrayList<Tipo_proyecto> lista = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from tipo_proyecto");
			
			while(registro.next()) {
				Tipo_proyecto tp = new Tipo_proyecto();
				tp.setId_tipo_proyecto(registro.getInt("id_tipo_proyecto"));
				tp.setNombre(registro.getString("nombre"));
				
				lista.add(tp);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Estado_proyecto> cargarEstado(){
		ArrayList<Estado_proyecto> lista = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from estado_proyecto");
			
			while(registro.next()) {
				Estado_proyecto ep = new Estado_proyecto();
				ep.setId_estado_proyecto(registro.getInt("id_estado_proyecto"));
				ep.setNombre(registro.getString("nombre"));
				
				lista.add(ep);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Proyecto obtenerProyectoPorId(int idProyecto) {

	    Proyecto p = null;

	    try {

	        Connection conexion = DriverManager.getConnection(
	                "jdbc:mysql://localhost/time_order",
	                "root",
	                "");

	        Statement consulta = conexion.createStatement();

	        ResultSet registro = consulta.executeQuery(
	                "SELECT * FROM proyecto WHERE id_proyecto = " + idProyecto);

	        if (registro.next()) {

	            p = new Proyecto();

	            p.setId_proyecto(registro.getInt("id_proyecto"));
	            p.setCodigo_interno(registro.getString("codigo_interno"));
	            p.setNombre(registro.getString("nombre"));
	            p.setDescripcion(registro.getString("descripcion"));
	            p.setEs_generico(registro.getBoolean("es_generico"));

	            Tipo_proyecto tipo = new Tipo_proyecto();
	            tipo.setId_tipo_proyecto(registro.getInt("id_tipo_proyecto"));

	            Estado_proyecto estado = new Estado_proyecto();
	            estado.setId_estado_proyecto(registro.getInt("id_estado_proyecto"));

	            p.setTipoproyec(tipo);
	            p.setEstadoproyec(estado);

	            if (registro.getDate("fecha_inicio") != null) {
	                p.setFecha_inicio(
	                        registro.getDate("fecha_inicio").toLocalDate());
	            }

	            if (registro.getDate("fecha_limite") != null) {
	                p.setFecha_limite(
	                        registro.getDate("fecha_limite").toLocalDate());
	            }
	        }

	        conexion.close();

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return p;
	}

}
