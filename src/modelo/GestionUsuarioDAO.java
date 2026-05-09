
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GestionUsuarioDAO {

    public ArrayList<Trabajador> traerTodos() throws Exception {

        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();

        String sql = "SELECT tra.id_trabajador, tra.nombre AS nombre_tra, tra.correo, tra.password_hash, tra.activo, rl.id_rol, rl.id_permiso,  " +
        	    "rl.nombre AS nombre_rol, prl.id_perfil, prl.nombre AS nombre_perfil, n.id_nivel, n.nombre AS nombre_nivel " +
        	    "FROM trabajador tra, perfil_laboral prl, nivel_experiencia n, rol_sistema rl " +
        	    "where tra.id_rol=rl.id_rol " +
        	    "and tra.id_perfil=prl.id_perfil " +
        	    "and tra.id_nivel=n.id_nivel";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	
            	Perfil_laboral pl = new Perfil_laboral(
            			rs.getInt("id_perfil"),
            			rs.getString("nombre_perfil")
            			);
            	Nivel_experiencia ne = new Nivel_experiencia(
            			rs.getInt("id_nivel"),
            			rs.getString("nombre_nivel")
            			);
            	Rol_sistema rp = new Rol_sistema(
            			rs.getInt("id_rol"),
            			rs.getString("nombre_rol")
            			);
            	Trabajador t = new Trabajador(
            			rs.getInt("id_trabajador"),
            			rs.getString("nombre_tra"),
            			rs.getString("correo"),
            			rs.getString("password_hash"),
            			rs.getBoolean("activo"),
            			rp,
            			pl,
            			ne
            			);
            	listaTrabajadores.add(t);       
            }
           

        } catch (Exception e) {
        	e.printStackTrace();

            throw new Exception("Error al obtener trabajadores: " + e.getMessage());
        }

        return listaTrabajadores;
    }
}