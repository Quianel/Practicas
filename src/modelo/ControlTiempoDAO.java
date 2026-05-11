package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class ControlTiempoDAO {
	public void cargarProyecto(JComboBox<String> combo) {
		String sql = "select nombre, codigo_interno from proyecto";
		 try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {	

	            combo.removeAllItems();

	            while (rs.next()) {
	                combo.addItem(rs.getString("nombre"));
	                
	                combo.addItem(rs.getString("codigo_interno"));
	            }

	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}
	public void cargarTarea(JComboBox<String> combo) {
		String sql = "select nombre from catalogo_tareas";
		 try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {	

	            combo.removeAllItems();

	            while (rs.next()) {
	                combo.addItem(rs.getString("nombre"));
	                
	               
	            }

	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}

}
