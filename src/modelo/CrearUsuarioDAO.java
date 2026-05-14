package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CrearUsuarioDAO {

	public boolean CrearUsuario(Trabajador t) {

		if (t == null) return false;
		if (t.getRol() == null || t.getPerfil() == null || t.getNivel() == null) return false;

		boolean correcto = true;

		String hash = org.mindrot.jbcrypt.BCrypt.hashpw(
				t.getPassword_hash(),
				org.mindrot.jbcrypt.BCrypt.gensalt()
		);

		String sql = "INSERT INTO trabajador (nombre,correo,password_hash,activo,id_rol,id_perfil,id_nivel) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try (Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost/time_order", "root", "");
		     PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, t.getNombre());
			ps.setString(2, t.getCorreo());
			ps.setString(3, hash);
			ps.setInt(4, t.isActivo() ? 1 : 0);
			ps.setInt(5, t.getRol().getId_rol());
			ps.setInt(6, t.getPerfil().getId_perfil());
			ps.setInt(7, t.getNivel().getId_nivel());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			correcto = false;
		}

		return correcto;
	}

	public boolean modificarUsuario(int id_trabajador, String nombre, String correo,
			String password_hash, Rol_sistema rol, Perfil_laboral perfil,
			Nivel_experiencia nivel, boolean activo) {

		boolean correcto = true;

		String sqlCheck = "SELECT 1 FROM trabajador WHERE id_trabajador = ?";
		String sqlUpdate = "UPDATE trabajador SET nombre=?, correo=?, password_hash=?, id_rol=?, id_perfil=?, id_nivel=?, activo=? "
				+ "WHERE id_trabajador=?";

		try (Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost/time_order", "root", "");
		     PreparedStatement check = conexion.prepareStatement(sqlCheck);
		     PreparedStatement update = conexion.prepareStatement(sqlUpdate)) {

			check.setInt(1, id_trabajador);
			ResultSet rs = check.executeQuery();

			if (!rs.next()) return false;

			update.setString(1, nombre);
			update.setString(2, correo);
			update.setString(3, password_hash);
			update.setInt(4, rol.getId_rol());
			update.setInt(5, perfil.getId_perfil());
			update.setInt(6, nivel.getId_nivel());
			update.setInt(7, activo ? 1 : 0);
			update.setInt(8, id_trabajador);

			correcto = update.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			correcto = false;
		}

		return correcto;
	}

	public ArrayList<Rol_sistema> cargarRol() {

		ArrayList<Rol_sistema> lista = new ArrayList<>();

		try (Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost/time_order", "root", "");
		     Statement st = conexion.createStatement();
		     ResultSet rs = st.executeQuery("SELECT * FROM rol_sistema")) {

			while (rs.next()) {

				Rol_sistema r = new Rol_sistema();
				r.setId_rol(rs.getInt("id_rol"));
				r.setNombre(rs.getString("nombre"));

				lista.add(r);
			}

			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Perfil_laboral> cargarPerfil() {

		ArrayList<Perfil_laboral> lista = new ArrayList<>();

		try (Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost/time_order", "root", "");
		     Statement st = conexion.createStatement();
		     ResultSet rs = st.executeQuery("SELECT * FROM perfil_laboral")) {

			while (rs.next()) {

				Perfil_laboral p = new Perfil_laboral();
				p.setId_perfil(rs.getInt("id_perfil"));
				p.setNombre(rs.getString("nombre"));

				lista.add(p);
			}

			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Nivel_experiencia> cargarNivel() {

		ArrayList<Nivel_experiencia> lista = new ArrayList<>();

		try (Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost/time_order", "root", "");
		     Statement st = conexion.createStatement();
		     ResultSet rs = st.executeQuery("SELECT * FROM nivel_experiencia")) {

			while (rs.next()) {

				Nivel_experiencia n = new Nivel_experiencia();
				n.setId_nivel(rs.getInt("id_nivel"));
				n.setNombre(rs.getString("nombre"));

				lista.add(n);
			}

			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Trabajador obtenerTrabajadorPorId(int idTrabajador) {

		Trabajador t = null;

		String sql =
				"SELECT tra.*, rl.nombre as nombre_rol, pr.nombre as nombre_perfil, ne.nombre as nombre_nivel " +
				"FROM trabajador tra, rol_sistema rl, perfil_laboral pr, nivel_experiencia ne " +
				"WHERE tra.id_rol = rl.id_rol " +
				"AND tra.id_perfil = pr.id_perfil " +
				"AND tra.id_nivel = ne.id_nivel " +
				"AND tra.id_trabajador = ?";

		try (Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost/time_order", "root", "");
		     PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, idTrabajador);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				t = new Trabajador();

				t.setId_trabajador(rs.getInt("id_trabajador"));
				t.setNombre(rs.getString("nombre"));
				t.setCorreo(rs.getString("correo"));
				t.setPassword_hash(rs.getString("password_hash"));
				t.setActivo(rs.getBoolean("activo"));

				Rol_sistema r = new Rol_sistema();
				r.setId_rol(rs.getInt("id_rol"));
				r.setNombre(rs.getString("nombre_rol"));

				Perfil_laboral p = new Perfil_laboral();
				p.setId_perfil(rs.getInt("id_perfil"));
				p.setNombre(rs.getString("nombre_perfil"));

				Nivel_experiencia n = new Nivel_experiencia();
				n.setId_nivel(rs.getInt("id_nivel"));
				n.setNombre(rs.getString("nombre_nivel"));

				t.setRol(r);
				t.setPerfil(p);
				t.setNivel(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return t;
	}
}