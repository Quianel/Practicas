package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CrearUsuarioDAO {

	public boolean CrearUsuario(Trabajador t) {
		boolean correcto = true;
		if (t.isActivo() == false) {
			return false;
		}
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into trabajador (nombre,correo,password_hash,activo,"
					+ "id_rol,id_perfil,id_nivel) values(" + "'" + t.getNombre() + "'," + "'" + t.getCorreo() + "',"
					+ "'" + t.getPassword_hash() + "'," + (t.isActivo() ? 1 : 0) + "," + t.getRol().getId_rol() + ","
					+ t.getPerfil().getId_perfil() + "," + t.getNivel().getId_nivel() + "," + ")");
			consulta.close();
		} catch (SQLException e) {
			e.printStackTrace();
			correcto = false;
		}
		return correcto;

	}

	public boolean modificarUsuario(int id_trabajador, String nombre, String correo, String password_hash,
			Rol_permiso rol, Perfil_laboral perfil, Nivel_experiencia nivel, boolean activo) {
		boolean correcto = true;

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta
					.executeQuery("select * from trabajador where id_trabajador= " + id_trabajador);
			if (!registro.next()) {
				correcto = false;
			} else {
				int valor = consulta.executeUpdate("update trabajador set nombre = '" + nombre + "', correo = '"
						+ correo + "', password_hash = '" + password_hash
						+ "', id_rol = " + rol.getId_rol() + ", id_perfil = "
						+ perfil.getId_perfil() + ", id_nivel = " + nivel.getId_nivel() 
						+ ", activo = " + (activo ? 1 : 0) + " where id_trabajador= " + id_trabajador);
				if (valor == 0) {
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
	
	public ArrayList<Rol_permiso> cargarRol(){
		ArrayList<Rol_permiso> lista = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from rol_permiso");
			
			while(registro.next()) {
				Rol_permiso rp = new Rol_permiso();
				rp.setId_rol(registro.getInt("id_rol"));
				rp.setId_permiso(registro.getInt("id_permiso"));
				
				lista.add(rp);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Perfil_laboral> cargarPerfil(){
		ArrayList<Perfil_laboral> lista = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from perfil_laboral");
			
			while(registro.next()) {
				Perfil_laboral pl = new Perfil_laboral();
				pl.setId_perfil(registro.getInt("id_perfil"));
				pl.setNombre(registro.getString("nombre"));
				
				lista.add(pl);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Nivel_experiencia> cargarNivel(){
		ArrayList<Nivel_experiencia> lista = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from nivel_experiencia");
			
			while(registro.next()) {
				Nivel_experiencia ne = new Nivel_experiencia();
				ne.setId_nivel(registro.getInt("id_nivel"));
				ne.setNombre(registro.getString("nombre"));
				
				lista.add(ne);
			}
			conexion.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
