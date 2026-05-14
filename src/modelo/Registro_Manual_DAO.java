package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

public class Registro_Manual_DAO {
	
	public ArrayList<Proyecto> cargarProyecto() {
		ArrayList<Proyecto> lista = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order","root","");
			Statement consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("select * from proyecto");
			
			while(rs.next()) {
				Proyecto p = new Proyecto();
				p.setId_proyecto(rs.getInt("id_proyecto"));
				p.setCodigo_interno(rs.getString("codigo_interno"));
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setFecha_inicio(rs.getDate("fecha_inicio").toLocalDate());
				p.setFecha_limite(rs.getDate("fecha_limite").toLocalDate());
				p.setEs_generico(rs.getBoolean("es_generico"));
				Tipo_proyecto t = new Tipo_proyecto();
				t.setId_tipo_proyecto(rs.getInt("id_tipo_proyecto"));
				p.setTipoproyec(t);
				Estado_proyecto e = new Estado_proyecto();
				e.setId_estado_proyecto(rs.getInt("id_estado_proyecto"));
				p.setEstadoproyec(e);
				
				lista.add(p);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public ArrayList<Tarea_proyecto> cargarTarea(){
		ArrayList<Tarea_proyecto> lista = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order","root","");
			Statement consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("select * from tarea_proyecto");
			
			while(rs.next()) {
				Tarea_proyecto ta = new Tarea_proyecto();
				ta.setId_tarea_proyecto(rs.getInt("id_tarea_proyecto"));
				ta.setNombre_visible(rs.getString("nombre_visible"));
				ta.setActiva(rs.getBoolean("activa"));
				ta.setId_tarea_padre(rs.getInt("id_tarea_padre"));
				
				Proyecto p = new Proyecto();
				p.setId_proyecto(rs.getInt("id_proyecto"));
				ta.setProyec(p);
				
				Catalogo_tareas ca = new Catalogo_tareas();
				ca.setId_tarea_catalogo(rs.getInt("id_tarea_catalogo"));
				ta.setCatalog(ca);
				
				Estado_tarea est = new Estado_tarea();
				est.setId_estado_tarea(rs.getInt("id_estado_tarea"));
				ta.setEstadotar(est);
				
				lista.add(ta);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public boolean modificarRegistroManual(int idRegistro, String fecha, String horaInicio, String horaFin, String comentario,int idProyecto, int idTarea) {
		String sql = "UPDATE registro_tiempo SET fecha_hora_inicio = ?, fecha_hora_fin = ?, " +
	             "minutos_totales = ?, modo_registro = 'manual', comentario = ?, " +
	             "id_proyecto = ?, id_tarea_proyecto = ? WHERE id_registro = ?";
		try {
			
			java.time.LocalDate d = java.time.LocalDate.parse(fecha);
			java.time.LocalTime tInicio = java.time.LocalTime.parse(horaInicio);
			java.time.LocalTime tFin = java.time.LocalTime.parse(horaFin);
			
			
			java.time.LocalDateTime inicio = java.time.LocalDateTime.of(d, tInicio);
			java.time.LocalDateTime fin = java.time.LocalDateTime.of(d, tFin);
			
			
			long minutosTotales = java.time.temporal.ChronoUnit.MINUTES.between(inicio, fin);
			
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			java.sql.PreparedStatement ps = conexion.prepareStatement(sql);
			
		
			ps.setTimestamp(1, java.sql.Timestamp.valueOf(inicio));
			ps.setTimestamp(2, java.sql.Timestamp.valueOf(fin));
			ps.setLong(3, minutosTotales);
			ps.setString(4, comentario);
			ps.setInt(5, idProyecto);      
			ps.setInt(6, idTarea);         
			ps.setInt(7, idRegistro);
			
			int filasAfectadas = ps.executeUpdate();
			
			
			ps.close();
			conexion.close();
			
			
			if (filasAfectadas > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (java.time.format.DateTimeParseException e) {
			System.out.println("Error: Formato de texto inválido en fecha u hora.");
			e.printStackTrace();
		}
		return false;
	}

}
