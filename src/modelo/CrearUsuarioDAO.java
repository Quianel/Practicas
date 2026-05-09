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
			Rol_sistema rol, Perfil_laboral perfil, Nivel_experiencia nivel, boolean activo) {
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
	
	public ArrayList<Rol_sistema> cargarRol(){
		ArrayList<Rol_sistema> lista = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/time_order", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select * from rol_sistema");
			
			while(registro.next()) {
				Rol_sistema rs = new Rol_sistema();
				rs.setId_rol(registro.getInt("id_rol"));
				rs.setNombre(registro.getString("nombre"));
				
				lista.add(rs);
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
	
	public Trabajador obtenerTrabajadorPorId(int idTrabajador) {

	    Trabajador t = null;

	    try {

	        Connection conexion = DriverManager.getConnection(
	                "jdbc:mysql://localhost/time_order",
	                "root",
	                "");

	        Statement consulta = conexion.createStatement();

	        ResultSet registro = consulta.executeQuery(
	                "SELECT * FROM trabajador WHERE id_trabajador = " + idTrabajador);

	        if (registro.next()) {

	            t = new Trabajador();

	            t.setId_trabajador(registro.getInt("id_trabajador"));
	            t.setNombre(registro.getString("nombre"));
	            t.setCorreo(registro.getString("correo"));
	            t.setPassword_hash(registro.getString("password_hash"));
	            t.setActivo(registro.getBoolean("activo"));

	            Rol_sistema rol = new Rol_sistema();
	            rol.setId_rol(registro.getInt("id_rol"));
	            
	            Perfil_laboral per = new Perfil_laboral();
	            per.setId_perfil(registro.getInt("id_perfil"));

	            Nivel_experiencia nivel = new Nivel_experiencia();
	            nivel.setId_nivel(registro.getInt("id_nivel"));

	            t.setRol(rol);
	            t.setPerfil(per);
	            t.setNivel(nivel);
	        }

	        conexion.close();

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return t;
	}

}
