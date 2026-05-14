package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class ControlTiempoDAO {

    public void cargarProyecto(JComboBox<String> combo) {
        String sql = "SELECT CONCAT(nombre, ' ', codigo_interno) FROM proyecto";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarTarea(JComboBox<String> combo) {
        String sql = "SELECT nombre_visible FROM tarea_proyecto";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString("nombre_visible"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Registro_tiempo> traerTodos() throws Exception {

        ArrayList<Registro_tiempo> listaRegistroTiempo = new ArrayList<>();

        // Query corregida: REGISTRO_TIEMPO hace JOIN directo con
        // trabajador, proyecto y tarea_proyecto por sus propias FKs.
        // Ya NO se une con asignacion_tarea, que era el JOIN incorrecto
        // que filtraba los registros.
        String sql =
            "SELECT rt.id_registro, rt.fecha_hora_inicio, rt.fecha_hora_fin, " +
            "       rt.minutos_totales, rt.modo_registro, rt.comentario, " +
            "       tra.id_trabajador, tra.nombre AS nombre_tra, tra.correo, " +
            "       tra.password_hash, tra.activo, " +
            "       r.id_rol, r.nombre AS nombre_rol, " +
            "       prl.id_perfil, prl.nombre AS nombre_prl, " +
            "       n.id_nivel, n.nombre AS nombre_nivel, " +
            "       p.id_proyecto, p.codigo_interno, p.nombre AS nombre_p, " +
            "       p.descripcion, p.fecha_inicio, p.fecha_limite, p.es_generico, " +
            "       ti.id_tipo_proyecto, ti.nombre AS nombre_ti, " +
            "       es.id_estado_proyecto, es.nombre AS nombre_es, " +
            "       ta.id_tarea_proyecto, ta.id_tarea_padre, ta.nombre_visible, ta.activa, " +
            "       ca.id_tarea_catalogo, ca.nombre AS nombre_ca, ca.solo_senior, " +
            "       est.id_estado_tarea, est.nombre AS nombre_est " +
            "FROM registro_tiempo rt " +
            "JOIN trabajador tra        ON rt.id_trabajador      = tra.id_trabajador " +
            "JOIN perfil_laboral prl    ON tra.id_perfil         = prl.id_perfil " +
            "JOIN nivel_experiencia n   ON tra.id_nivel          = n.id_nivel " +
            "JOIN rol_sistema r         ON tra.id_rol            = r.id_rol " +
            "JOIN proyecto p            ON rt.id_proyecto        = p.id_proyecto " +
            "JOIN tipo_proyecto ti      ON p.id_tipo_proyecto    = ti.id_tipo_proyecto " +
            "JOIN estado_proyecto es    ON p.id_estado_proyecto  = es.id_estado_proyecto " +
            "JOIN tarea_proyecto ta     ON rt.id_tarea_proyecto  = ta.id_tarea_proyecto " +
            "JOIN catalogo_tareas ca    ON ta.id_tarea_catalogo  = ca.id_tarea_catalogo " +
            "JOIN estado_tarea est      ON ta.id_estado_tarea    = est.id_estado_tarea " +
            "ORDER BY rt.fecha_hora_inicio DESC";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Perfil_laboral pl = new Perfil_laboral(
                        rs.getInt("id_perfil"),
                        rs.getString("nombre_prl"));

                Nivel_experiencia ne = new Nivel_experiencia(
                        rs.getInt("id_nivel"),
                        rs.getString("nombre_nivel"));

                Tipo_proyecto ti = new Tipo_proyecto(
                        rs.getInt("id_tipo_proyecto"),
                        rs.getString("nombre_ti"));

                Estado_proyecto esObj = new Estado_proyecto(
                        rs.getInt("id_estado_proyecto"),
                        rs.getString("nombre_es"));

                Rol_sistema rp = new Rol_sistema(
                        rs.getInt("id_rol"),
                        rs.getString("nombre_rol"));

                Estado_tarea est = new Estado_tarea(
                        rs.getInt("id_estado_tarea"),
                        rs.getString("nombre_est"));

                Proyecto p = new Proyecto(
                        rs.getInt("id_proyecto"),
                        rs.getString("codigo_interno"),
                        rs.getString("nombre_p"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_inicio").toLocalDate(),
                        rs.getDate("fecha_limite").toLocalDate(),
                        rs.getBoolean("es_generico"),
                        ti,
                        esObj);

                Trabajador t = new Trabajador(
                        rs.getInt("id_trabajador"),
                        rs.getString("nombre_tra"),
                        rs.getString("correo"),
                        rs.getString("password_hash"),
                        rs.getBoolean("activo"),
                        rp,
                        pl,
                        ne);

                Catalogo_tareas ca = new Catalogo_tareas(
                        rs.getInt("id_tarea_catalogo"),
                        rs.getString("nombre_ca"),
                        rs.getBoolean("solo_senior"));

                Tarea_proyecto ta = new Tarea_proyecto(
                        rs.getInt("id_tarea_proyecto"),
                        p,
                        ca,
                        rs.getInt("id_tarea_padre"),
                        est,
                        rs.getString("nombre_visible"),
                        rs.getBoolean("activa"),
                        null);

                Registro_tiempo rt = new Registro_tiempo(
                        rs.getInt("id_registro"),
                        t,
                        p,
                        ta,
                        rs.getTimestamp("fecha_hora_inicio").toLocalDateTime(),
                        rs.getTimestamp("fecha_hora_fin").toLocalDateTime(),
                        rs.getInt("minutos_totales"),
                        rs.getString("modo_registro"),
                        rs.getString("comentario"));

                listaRegistroTiempo.add(rt);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al obtener registros de tiempo: " + e.getMessage());
        }

        return listaRegistroTiempo;
    }

    // Se añaden idTrabajador, idProyecto e idTareaProyecto porque son
    // FKs obligatorias en REGISTRO_TIEMPO según el modelo E-R.
    public void actualizarRegistroTiempo(LocalDateTime fechaInicio, LocalDateTime fechaFin,
                                          long minutosTotales, JTextPane comentario,
                                          int idTrabajador, int idProyecto, int idTareaProyecto) {

        String sql = "INSERT INTO registro_tiempo " +
                     "(id_trabajador, id_proyecto, id_tarea_proyecto, " +
                     " fecha_hora_inicio, fecha_hora_fin, minutos_totales, modo_registro, comentario) " +
                     "VALUES (?, ?, ?, ?, ?, ?, 'manual', ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTrabajador);
            ps.setInt(2, idProyecto);
            ps.setInt(3, idTareaProyecto);
            ps.setTimestamp(4, Timestamp.valueOf(fechaInicio));
            ps.setTimestamp(5, Timestamp.valueOf(fechaFin));
            ps.setLong(6, minutosTotales);
            ps.setString(7, comentario.getText());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 // En ControlTiempoDAO.java — añadir estos dos métodos

    public int[] cargarProyectoConIds(JComboBox<String> combo) {
        String sql = "SELECT id_proyecto, CONCAT(nombre, ' ', codigo_interno) FROM proyecto";
        ArrayList<Integer> ids = new ArrayList<>();
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            while (rs.next()) {
                ids.add(rs.getInt(1));
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] cargarTareaConIds(JComboBox<String> combo) {
        String sql = "SELECT id_tarea_proyecto, nombre_visible FROM tarea_proyecto";
        ArrayList<Integer> ids = new ArrayList<>();
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            while (rs.next()) {
                ids.add(rs.getInt(1));
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids.stream().mapToInt(Integer::intValue).toArray();
    }
}