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

        String sql =
            "SELECT " +
            "tapr.id_tarea_proyecto, " +
            "tapr.nombre_visible, " +
            "tapr.activa, " +
            "tapr.id_tarea_padre, " +

            "p.id_proyecto, " +
            "p.nombre AS nombre_proyecto, " +

            "ct.id_tarea_catalogo, " +
            "ct.nombre AS nombre_catalogo, " +
            "ct.solo_senior, " +

            "et.id_estado_tarea, " +
            "et.nombre AS nombre_estado_tarea " +

            "FROM tarea_proyecto tapr, proyecto p, catalogo_tareas ct, estado_tarea et " +

            "WHERE tapr.id_proyecto = p.id_proyecto " +
            "AND tapr.id_tarea_catalogo = ct.id_tarea_catalogo " +
            "AND tapr.id_estado_tarea = et.id_estado_tarea ";

        // FILTRO OPCIONAL
        if (idProyecto != -1) {
            sql += "AND tapr.id_proyecto = ? ";
        }

        try (
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            if (idProyecto != -1) {
                ps.setInt(1, idProyecto);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Proyecto proyecto = new Proyecto();
                proyecto.setId_proyecto(rs.getInt("id_proyecto"));
                proyecto.setNombre(rs.getString("nombre_proyecto"));

                Catalogo_tareas catalogo = new Catalogo_tareas(
                    rs.getInt("id_tarea_catalogo"),
                    rs.getString("nombre_catalogo"),
                    rs.getBoolean("solo_senior")
                );

                Estado_tarea estado = new Estado_tarea(
                    rs.getInt("id_estado_tarea"),
                    rs.getString("nombre_estado_tarea")
                );

                Tarea_proyecto tarea = new Tarea_proyecto(
                    rs.getInt("id_tarea_proyecto"),
                    proyecto,
                    catalogo,
                    rs.getInt("id_tarea_padre"),
                    estado,
                    rs.getString("nombre_visible"),
                    rs.getBoolean("activa")
                );

                lista.add(tarea);
            }

        } catch (Exception e) {

            throw new Exception("Error al traer tareas: " + e.getMessage());
        }

        return lista;
    }
    
    public ArrayList<Trabajador> obtenerTrabajadoresPorProyecto(int idProyecto) throws Exception {

        ArrayList<Trabajador> lista = new ArrayList<>();

        String sql =
            "SELECT DISTINCT " +
            "tr.id_trabajador, " +
            "tr.nombre AS nombre_trabajador " +

            "FROM asignacion_tarea at, tarea_proyecto tp, proyecto pr, trabajador tr " +

            "WHERE at.id_trabajador = tr.id_trabajador " +
            "AND at.id_tarea_proyecto = tp.id_tarea_proyecto " +
            "AND tp.id_proyecto = pr.id_proyecto " +
            "AND at.activa = 1 " +
            "AND pr.id_proyecto = ?";

        try (
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idProyecto);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Trabajador t = new Trabajador();

                t.setId_trabajador(
                    rs.getInt("id_trabajador")
                );

                t.setNombre(
                    rs.getString("nombre_trabajador")
                );

                lista.add(t);
            }

        } catch (Exception e) {

            throw new Exception(
                "Error al obtener trabajadores: " + e.getMessage()
            );
        }

        return lista;
    }
}