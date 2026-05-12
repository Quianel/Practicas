package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TareasYAsignacionesDAO {

	// =========================
	// TRAER TODAS
	// =========================
	public ArrayList<Tarea_proyecto> traerTodas() throws Exception {

		return traerPorProyecto(-1);
	}

	// =========================
	// TRAER POR PROYECTO
	// =========================
	public ArrayList<Tarea_proyecto> traerPorProyecto(int idProyecto) throws Exception {

		ArrayList<Tarea_proyecto> lista = new ArrayList<>();

		String sql = "SELECT " + "tapr.id_tarea_proyecto, " + "tapr.nombre_visible, " + "tapr.activa, "
				+ "tapr.id_tarea_padre, " +

				"p.id_proyecto, " + "p.nombre AS nombre_proyecto, " +

				"ct.id_tarea_catalogo, " + "ct.nombre AS nombre_catalogo, " + "ct.solo_senior, " +

				"et.id_estado_tarea, " + "et.nombre AS nombre_estado_tarea " +

				"FROM tarea_proyecto tapr, proyecto p, catalogo_tareas ct, estado_tarea et " +

				"WHERE tapr.id_proyecto = p.id_proyecto " + "AND tapr.id_tarea_catalogo = ct.id_tarea_catalogo "
				+ "AND tapr.id_estado_tarea = et.id_estado_tarea ";

		// FILTRO OPCIONAL
		if (idProyecto != -1) {
			sql += "AND tapr.id_proyecto = ? ";
		}

		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			if (idProyecto != -1) {
				ps.setInt(1, idProyecto);
			}

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Proyecto proyecto = new Proyecto();
				proyecto.setId_proyecto(rs.getInt("id_proyecto"));
				proyecto.setNombre(rs.getString("nombre_proyecto"));

				Catalogo_tareas catalogo = new Catalogo_tareas(rs.getInt("id_tarea_catalogo"),
						rs.getString("nombre_catalogo"), rs.getBoolean("solo_senior"));

				Estado_tarea estado = new Estado_tarea(rs.getInt("id_estado_tarea"),
						rs.getString("nombre_estado_tarea"));

				Tarea_proyecto tarea = new Tarea_proyecto(rs.getInt("id_tarea_proyecto"), proyecto, catalogo,
						rs.getInt("id_tarea_padre"), estado, rs.getString("nombre_visible"), rs.getBoolean("activa"), null);

				lista.add(tarea);
			}

		} catch (Exception e) {

			throw new Exception("Error al traer tareas: " + e.getMessage());
		}

		return lista;
	}

	public ArrayList<Trabajador> obtenerTrabajadoresSinAsignar(int idProyecto) throws Exception {

		ArrayList<Trabajador> lista = new ArrayList<>();

		String sql = "SELECT DISTINCT " + "tr.id_trabajador, " + "tr.nombre AS nombre_trabajador " +

				"FROM trabajador tr " +

				"WHERE tr.activo = 1 " + "AND tr.id_trabajador NOT IN ( " +

				"SELECT at.id_trabajador " +

				"FROM asignacion_tarea at, tarea_proyecto tp " +

				"WHERE at.id_tarea_proyecto = tp.id_tarea_proyecto " + "AND at.activa = 1 " + "AND tp.id_proyecto = ? "
				+

				")";

		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idProyecto);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Trabajador t = new Trabajador();

				t.setId_trabajador(rs.getInt("id_trabajador"));

				t.setNombre(rs.getString("nombre_trabajador"));

				lista.add(t);
			}

		} catch (Exception e) {

			throw new Exception("Error al obtener trabajadores sin asignar: " + e.getMessage());
		}

		return lista;
	}

	public void quitarAsignacion(int idTareaProyecto, int idTrabajador) throws Exception {

		String sql = "UPDATE asignacion_tarea " + "SET activa = 0, " + "fecha_fin_asignacion = CURDATE(), "
				+ "motivo_fin = 'Desasignado manualmente' " + "WHERE id_tarea_proyecto = ? " + "AND id_trabajador = ? "
				+ "AND activa = 1";

		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idTareaProyecto);
			ps.setInt(2, idTrabajador);

			ps.executeUpdate();

		} catch (Exception e) {

			throw new Exception("Error al quitar asignación: " + e.getMessage());
		}
	}

	public ArrayList<Trabajador> obtenerTrabajadoresPorTarea(int idTareaProyecto) throws Exception {

		ArrayList<Trabajador> lista = new ArrayList<>();

		String sql = "SELECT DISTINCT " + "tr.id_trabajador, " + "tr.nombre AS nombre_trabajador " +

				"FROM asignacion_tarea at, trabajador tr " +

				"WHERE at.id_trabajador = tr.id_trabajador " + "AND at.id_tarea_proyecto = ? " + "AND at.activa = 1";

		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idTareaProyecto);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Trabajador t = new Trabajador();

				t.setId_trabajador(rs.getInt("id_trabajador"));

				t.setNombre(rs.getString("nombre_trabajador"));

				lista.add(t);
			}

		} catch (Exception e) {

			throw new Exception("Error al obtener trabajadores por tarea: " + e.getMessage());
		}

		return lista;
	}

	public void desactivarTrabajador(int idTrabajador) throws Exception {

		String sql = "UPDATE trabajador " + "SET activo = 0 " + "WHERE id_trabajador = ?";

		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idTrabajador);

			ps.executeUpdate();

		} catch (Exception e) {

			throw new Exception("Error al desactivar trabajador: " + e.getMessage());
		}
	}

	// =========================
	// ASIGNAR TRABAJADOR
	// =========================
	public void asignarTrabajadorATarea(int idTareaProyecto, int idTrabajador) throws Exception {

		String verificar = "SELECT * FROM asignacion_tarea " + "WHERE id_tarea_proyecto = ? " + "AND id_trabajador = ? "
				+ "AND activa = 1";

		String insertar = "INSERT INTO asignacion_tarea (" + "id_tarea_proyecto, " + "id_trabajador, "
				+ "fecha_asignacion, " + "activa" + ") VALUES (?, ?, CURDATE(), 1)";

		try (Connection con = ConexionBD.getConexion()) {

			// =========================
			// VERIFICAR DUPLICADOS
			// =========================
			PreparedStatement psVerificar = con.prepareStatement(verificar);

			psVerificar.setInt(1, idTareaProyecto);
			psVerificar.setInt(2, idTrabajador);

			ResultSet rs = psVerificar.executeQuery();

			if (rs.next()) {

				throw new Exception("El trabajador ya está asignado");
			}

			// =========================
			// INSERTAR
			// =========================
			PreparedStatement psInsertar = con.prepareStatement(insertar);

			psInsertar.setInt(1, idTareaProyecto);
			psInsertar.setInt(2, idTrabajador);

			psInsertar.executeUpdate();

		} catch (Exception e) {

			throw new Exception("Error al asignar trabajador: " + e.getMessage());
		}
	}

//=========================
//TRABAJADORES DEL PROYECTO
//=========================
	public ArrayList<Trabajador> obtenerTrabajadoresProyecto(int idProyecto) throws Exception {

		ArrayList<Trabajador> lista = new ArrayList<>();

		String sql = "SELECT * FROM trabajador " + "WHERE activo = 1";

		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Trabajador t = new Trabajador();

				t.setId_trabajador(rs.getInt("id_trabajador"));

				t.setNombre(rs.getString("nombre"));

				lista.add(t);
			}

		} catch (Exception e) {

			throw new Exception("Error al obtener trabajadores");
		}

		return lista;
	}
}