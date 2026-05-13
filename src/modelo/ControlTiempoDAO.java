package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class ControlTiempoDAO {
	public void cargarProyecto(JComboBox<String> combo) {
		String sql = "select concat(nombre, ' ', codigo_interno)from proyecto";
		 try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {	

	            combo.removeAllItems();

	            while (rs.next()) {
	                combo.addItem(rs.getString(1));
	                
	                
	            }

	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}
	public void cargarTarea(JComboBox<String> combo) {
		String sql = "select nombre_visible from tarea_proyecto";
		 try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {	

	            combo.removeAllItems();

	            while (rs.next()) {
	                combo.addItem(rs.getString("nombre_visible"));
	                
	               
	            }

	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}
	public ArrayList<Registro_tiempo> traerTodos() throws Exception {

        ArrayList<Registro_tiempo> listaRegistroTiempo = new ArrayList<>();

        String sql = "select id_asignacion_tarea, ta.id_tarea_proyecto, p.id_proyecto as id_proyecto_p, codigo_interno, "
        		+ "p.nombre as nombre_p, descripcion, fecha_inicio, fecha_limite, es_generico, ti.id_tipo_proyecto, "
        		+ "ti.nombre as nombre_ti, es.id_estado_proyecto, es.nombre as nombre_es, ca.id_tarea_catalogo, ca.nombre as nombre_ca, "
        		+ "solo_senior, tra.id_trabajador, tra.nombre as nombre_tra, correo, password_hash, activo, r.id_rol, r.nombre as nombre_rol, "
        		+ "prl.id_perfil, prl.nombre as nombre_prl, n.id_nivel, n.nombre as nombre_nivel, fecha_asignacion, fecha_fin_asignacion, "
        		+ "motivo_fin, asi.activa, est.id_estado_tarea, est.nombre as nombre_est, rt.id_registro,  fecha_hora_inicio, fecha_hora_fin, minutos_totales, modo_registro, comentario, id_tarea_padre, nombre_visible "
        		
        		+ "from asignacion_tarea asi, estado_tarea est, tarea_proyecto ta, proyecto p, tipo_proyecto ti, estado_proyecto es, catalogo_tareas ca, trabajador tra, "
        		+ "perfil_laboral prl, nivel_experiencia n, rol_sistema r, registro_tiempo rt "
        		+ "WHERE asi.id_tarea_proyecto = ta.id_tarea_proyecto "
        		+ "AND ta.id_proyecto = p.id_proyecto "
        		+ "AND p.id_tipo_proyecto = ti.id_tipo_proyecto "
        		+ "AND p.id_estado_proyecto = es.id_estado_proyecto "
        		+ "AND ta.id_tarea_catalogo = ca.id_tarea_catalogo "
        		+ "AND asi.id_trabajador = tra.id_trabajador "
        		+ "AND tra.id_perfil = prl.id_perfil "
        		+ "AND tra.id_nivel = n.id_nivel "
        		+ "AND tra.id_rol = r.id_rol "
        		+ "AND ta.id_estado_tarea = est.id_estado_tarea "
        		+ "AND rt.id_registro = asi.id_asignacion_tarea";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {	

            while (rs.next()) {
            	
            	Perfil_laboral pl = new Perfil_laboral(
            			rs.getInt("id_perfil"),
            			rs.getString("nombre_prl")
            			);
            	Nivel_experiencia ne = new Nivel_experiencia(
            			rs.getInt("id_nivel"),
            			rs.getString("nombre_nivel")
            			);
            	Tipo_proyecto ti = new Tipo_proyecto(
            			rs.getInt("id_tipo_proyecto"),
            			rs.getString("nombre_ti")
            			);
            	Estado_proyecto es = new Estado_proyecto(
            			rs.getInt("id_estado_proyecto"),
            			rs.getString("nombre_es")
            			);
            	Rol_sistema rp = new Rol_sistema(
            			rs.getInt("id_rol"),
            			rs.getString("nombre_rol")
            			);
            	Estado_tarea est = new Estado_tarea(
            			rs.getInt("id_estado_tarea"),
            			rs.getString("nombre_est")
            			);
            	
            	Proyecto p = new Proyecto(
            			rs.getInt("id_proyecto_p"),
            			rs.getString("codigo_interno"),
            			rs.getString("nombre_p"),
            			rs.getString("descripcion"),
            			rs.getDate("fecha_inicio").toLocalDate(),
            			rs.getDate("fecha_limite").toLocalDate(),
            			rs.getBoolean("es_generico"),
            			ti,
            			es
            			);
            	
            	Trabajador t = new Trabajador(
            			rs.getInt("id_trabajador"),
            			rs.getString("nombre_tra"),
            			rs.getString("correo"),
            			rs.getString("password_hash"),
            			rs.getBoolean("activo"),
            			rp,
            			pl,
            			ne
            			);
            	Catalogo_tareas ca = new Catalogo_tareas(
            			rs.getInt("id_tarea_catalogo"),
            			rs.getString("nombre_ca"),
            			rs.getBoolean("solo_senior")
            			);
            	Tarea_proyecto ta = new Tarea_proyecto(
            			rs.getInt("id_tarea_proyecto"),
            			p,
            			ca,
            			rs.getInt("id_tarea_padre"),
            			est,
            			rs.getString("nombre_visible"),
            			rs.getBoolean("activa"), 
            			null
            			);
            	Registro_tiempo rt = new Registro_tiempo(
            			rs.getInt("id_registro"),
            			t,
            			p,
            			ta,
            			rs.getTimestamp("fecha_hora_inicio").toLocalDateTime(),
            		    rs.getTimestamp("fecha_hora_fin").toLocalDateTime(),
            		    rs.getInt("minutos_totales"),
            		    rs.getString("modo_registro"),
            		    rs.getString("comentario")
            			);
            	
            	listaRegistroTiempo.add(rt);       
            }
           

        } catch (Exception e) {
        	e.printStackTrace();

            throw new Exception("Error al obtener trabajadores: " + e.getMessage());
        }

        return listaRegistroTiempo;
    }
	public void actualizarRegistroTiempo(LocalDateTime fechaInicio,LocalDateTime fechaFin, long minutosTotales,JTextPane comentario) {
		
		
	    String sql = "INSERT INTO registro_tiempo (fecha_hora_inicio, fecha_hora_fin, minutos_totales, comentario) "
	               + "VALUES (?, ?, ?, ?)"; 

	    try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql)) {	
	    	
	    	
	    	String textoComentario = comentario.getText();
	    	ps.setTimestamp(1, Timestamp.valueOf(fechaInicio));
	        ps.setTimestamp(2, Timestamp.valueOf(fechaFin));
	        ps.setLong(3, minutosTotales);
	        ps.setString(4, textoComentario);
	        ps.executeUpdate();
	        
	        

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }


	}
	
}
