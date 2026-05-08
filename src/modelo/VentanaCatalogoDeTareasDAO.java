package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class VentanaCatalogoDeTareasDAO {
	Connection getConexion() throws SQLException {
		String url = "jdbc:mysql://localhost/time_order";
		String usuario = "root";
		String constraseña = "";

	    return DriverManager.getConnection(url, usuario, constraseña);
	}

	public void Combobox(JComboBox<Object> cmbTiposProyectos) {
		Connection conexion;
		Statement consulta;
		ResultSet registro;
		
		try {
			conexion = getConexion();
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select nombre from tipo_proyecto");
			cmbTiposProyectos.addItem("(Todas)");
			while(registro.next()) {
				cmbTiposProyectos.addItem(registro.getObject("nombre"));
			}
			conexion.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<Catalogo_tareas> cargarArray(){
		ArrayList<Catalogo_tareas> Array = new ArrayList<>();
		try {
			Connection conexion = getConexion();
        	Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select tarea_proyecto.id_tarea_proyecto as 'Codigo', tarea_proyecto.id_tarea_proyecto as 'Codigo', catalogo_tareas.nombre as 'Nombre de tarea', solo_senior as 'Solo senior', proyecto.Nombre, activa\r\n"
					+ "from tarea_proyecto, catalogo_tareas, proyecto\r\n"
					+ "where tarea_proyecto.id_proyecto = proyecto.id_proyecto\r\n"
					+ "and tarea_proyecto.id_tarea_catalogo = catalogo_tareas.id_tarea_catalogo;\r\n");
			
            while (registro.next()) {
            	
            	Catalogo_tareas catalogo = new Catalogo_tareas();
            	catalogo.setNombre(registro.getString("Nombre de tarea"));
            	catalogo.setSoloSenior(registro.getBoolean("Solo senior"));
            	
            	Tarea_proyecto tarpro = new Tarea_proyecto();
            	tarpro.setId_tarea_proyecto(registro.getInt("Codigo"));
            	tarpro.setActiva(registro.getBoolean("activa"));
            	catalogo.setTareaProyecto(tarpro);
            	
            	Proyecto pro = new Proyecto();
            	pro.setNombre(registro.getString("Nombre"));
            	catalogo.setProyecto(pro);
            	
            	Array.add(catalogo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }		
		return Array;
	}

	public void cargaDatos(DefaultTableModel modeloTabla) {
		
		for(Catalogo_tareas ct : cargarArray()) {
			modeloTabla.addRow(new Object[]{
					ct.getTareaProyecto().getId_tarea_proyecto(),
					ct.getNombre(),
					ct.isSoloSenior(),
					ct.getProyecto().getNombre(),
					ct.getTareaProyecto().isActiva()
			});
		}

	}

	public void actualizarBaseDatos(DefaultTableModel modeloTabla) {
		int codigo;
		String nombreTarea;
		boolean soloSlider;
		String nombre;
		boolean activa;
		for(int fila = 0; fila < modeloTabla.getRowCount(); fila++) {
			codigo = (int) modeloTabla.getValueAt(fila, 0);
			nombreTarea = (String) modeloTabla.getValueAt(fila, 1);
			soloSlider = (boolean) modeloTabla.getValueAt(fila, 2);
			nombre = (String) modeloTabla.getValueAt(fila, 3);
			activa = (boolean) modeloTabla.getValueAt(fila, 4);
			
			Connection conexion;
			try {
				conexion = getConexion();
				Statement consulta = conexion.createStatement();
				int registro = consulta.executeUpdate("update catalogo_tareas "
						+ "set solo_senior = " + soloSlider + 
						" where nombre = '" + nombreTarea + "'");
				conexion.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conexion = getConexion();
				Statement consulta = conexion.createStatement();
				int registro = consulta.executeUpdate("update tarea_proyecto, proyecto set activa = " + activa + "\r\n"
						+ "where proyecto.id_proyecto = tarea_proyecto.id_proyecto\r\n"
						+ "and nombre = '" + nombre + "'");
				conexion.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

	public ArrayList<Catalogo_tareas> cargarArrayConFiltro(String tipoProyecto){
		ArrayList<Catalogo_tareas> ArrayFiltro = new ArrayList<>();
		
		try {
			Connection conexion = getConexion();
        	Statement consulta = conexion.createStatement();
        	ResultSet registro;
        	
        	if(tipoProyecto == "(Todas)") {
    			registro = consulta.executeQuery("select tarea_proyecto.id_tarea_proyecto as 'Codigo', catalogo_tareas.nombre as \"Nombre de tarea\", solo_senior as 'Solo senior', proyecto.nombre as \"Nombre\", activa\r\n"
    					+ "from tarea_proyecto, catalogo_tareas, tipo_proyecto, proyecto\r\n"
    					+ "where tarea_proyecto.id_tarea_catalogo = catalogo_tareas.id_tarea_catalogo\r\n"
    					+ "and catalogo_tareas.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto\r\n"
    					+ "and tipo_proyecto.id_tipo_proyecto = proyecto.id_tipo_proyecto\r\n"
    					+ "and proyecto.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto;");
        	}else {
        		System.out.println(tipoProyecto);
        		registro = consulta.executeQuery("select tarea_proyecto.id_tarea_proyecto as 'Codigo', "
        				+ "catalogo_tareas.nombre as 'Nombre de tarea', solo_senior as 'Solo senior', "
        				+ "proyecto.nombre as 'Nombre', activa  "
        				+ "from tarea_proyecto, catalogo_tareas, tipo_proyecto, proyecto "
        				+ "where tarea_proyecto.id_tarea_catalogo = catalogo_tareas.id_tarea_catalogo "
        				+ "and catalogo_tareas.id_tipo_proyecto = tipo_proyecto.id_tipo_proyecto "
        				+ "and tipo_proyecto.nombre = '" + tipoProyecto + "' "
        				+ "and tipo_proyecto.id_tipo_proyecto = proyecto.id_tipo_proyecto;");
        	}
			
            while (registro.next()) {
            	
            	Catalogo_tareas catalogo = new Catalogo_tareas();
            	catalogo.setNombre(registro.getString("Nombre de tarea"));
            	catalogo.setSoloSenior(registro.getBoolean("Solo senior"));
            	
            	Tarea_proyecto tarpro = new Tarea_proyecto();
            	tarpro.setId_tarea_proyecto(registro.getInt("Codigo"));
            	tarpro.setActiva(registro.getBoolean("activa"));
            	catalogo.setTareaProyecto(tarpro);
            	
            	Proyecto pro = new Proyecto();
            	pro.setNombre(registro.getString("Nombre"));
            	catalogo.setProyecto(pro);
            	
            	ArrayFiltro.add(catalogo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }	
		
		return ArrayFiltro;
	}
	
	public void cargarTipoProyecto(String tipoProyecto, DefaultTableModel modeloTabla) {
		for(Catalogo_tareas ct : cargarArrayConFiltro(tipoProyecto)) {
			modeloTabla.addRow(new Object[]{
					ct.getTareaProyecto().getId_tarea_proyecto(),
					ct.getNombre(),
					ct.isSoloSenior(),
					ct.getProyecto().getNombre(),
					ct.getTareaProyecto().isActiva()
			});
		}
	}
}