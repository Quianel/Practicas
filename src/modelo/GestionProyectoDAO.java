package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GestionProyectoDAO {

    public ArrayList<Proyecto> traerTodos() throws Exception {

        ArrayList<Proyecto> listaProyectos = new ArrayList<>();

        String sql =
            "SELECT pr.id_proyecto, codigo_interno, pr.nombre, descripcion, " +
            "fecha_inicio, fecha_limite, es_generico, " +
            "tp.id_tipo_proyecto, tp.nombre AS nombre_tipo_proyecto, " +
            "ep.id_estado_proyecto, ep.nombre AS nombre_estado_proyecto " +
            "FROM proyecto pr, tipo_proyecto tp, estado_proyecto ep " +
            "WHERE pr.id_tipo_proyecto = tp.id_tipo_proyecto " +
            "AND pr.id_estado_proyecto = ep.id_estado_proyecto";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Tipo_proyecto tp = new Tipo_proyecto(
                        rs.getInt("id_tipo_proyecto"),
                        rs.getString("nombre_tipo_proyecto")
                );

                Estado_proyecto ep = new Estado_proyecto(
                        rs.getInt("id_estado_proyecto"),
                        rs.getString("nombre_estado_proyecto")
                );

                Proyecto p = new Proyecto(
                        rs.getInt("id_proyecto"),
                        rs.getString("codigo_interno"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_inicio") != null
                                ? rs.getDate("fecha_inicio").toLocalDate()
                                : null,
                        rs.getDate("fecha_limite") != null
                                ? rs.getDate("fecha_limite").toLocalDate()
                                : null,
                        rs.getBoolean("es_generico"),
                        tp,
                        ep
                );

                listaProyectos.add(p);
            }

        } catch (Exception e) {
            throw new Exception("Error al obtener proyectos: " + e.getMessage());
        }

        return listaProyectos;
    }

    public ArrayList<Proyecto> traerConFiltro(String busqueda) throws Exception {

        ArrayList<Proyecto> listaProyectos = new ArrayList<>();

        String sql =
            "SELECT DISTINCT pr.id_proyecto, " +
            "pr.codigo_interno, pr.nombre, pr.descripcion, " +
            "pr.fecha_inicio, pr.fecha_limite, pr.es_generico, " +
            "tp.id_tipo_proyecto, tp.nombre AS nombre_tipo_proyecto, " +
            "ep.id_estado_proyecto, ep.nombre AS nombre_estado_proyecto " +
            "FROM proyecto pr, tipo_proyecto tp, estado_proyecto ep " +
            "WHERE pr.id_tipo_proyecto = tp.id_tipo_proyecto " +
            "AND pr.id_estado_proyecto = ep.id_estado_proyecto " +
            "AND (pr.nombre LIKE ? OR tp.nombre LIKE ? OR ep.nombre LIKE ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + busqueda + "%";

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tipo_proyecto tp = new Tipo_proyecto(
                        rs.getInt("id_tipo_proyecto"),
                        rs.getString("nombre_tipo_proyecto")
                );

                Estado_proyecto ep = new Estado_proyecto(
                        rs.getInt("id_estado_proyecto"),
                        rs.getString("nombre_estado_proyecto")
                );

                Proyecto p = new Proyecto(
                        rs.getInt("id_proyecto"),
                        rs.getString("codigo_interno"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_inicio") != null
                                ? rs.getDate("fecha_inicio").toLocalDate()
                                : null,
                        rs.getDate("fecha_limite") != null
                                ? rs.getDate("fecha_limite").toLocalDate()
                                : null,
                        rs.getBoolean("es_generico"),
                        tp,
                        ep
                );

                listaProyectos.add(p);
            }

        } catch (Exception e) {
            throw new Exception("Error al obtener proyectos filtrados: " + e.getMessage());
        }

        return listaProyectos;
    }
}