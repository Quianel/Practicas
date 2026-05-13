package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

public class CentroReportesDAO {

	public void Combobox1(JComboBox<Object> cmbProyecto) {
		Connection conexion;
		Statement consulta;
		ResultSet registro;
		
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select nombre from proyecto");
			cmbProyecto.addItem("(Todos)");
			while (registro.next()) {
				cmbProyecto.addItem(registro.getObject("nombre"));	
			}
			conexion.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void Combobox2(JComboBox<Object> cmbTipoProyecto) {
		Connection conexion;
		Statement consulta;
		ResultSet registro;
		
		try {
			conexion = ConexionBD.getConexion();
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select nombre from tipo_proyecto");
			cmbTipoProyecto.addItem("(Todos)");
			while (registro.next()) {
				cmbTipoProyecto.addItem(registro.getObject("nombre"));	
			}
			conexion.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
