package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ModeloConexionBaseDeDatos {
	public static void main(String[] args) {
		System.out.println("Prueba de ddbb: ");
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select id, texto "
														+ "from prueba");
			while(registro.next()) {
				String id = registro.getString("id");
				System.out.print("Id: " + id);
				System.out.print("");
				System.out.println(" - Texto: " + registro.getString("texto"));
			} 
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
