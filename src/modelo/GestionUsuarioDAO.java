
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GestionUsuarioDAO {

    public ArrayList<Trabajador> traerTodos() throws Exception {

        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();

        String sql = "select tra.nombre, tra.correo, rl.nombre, prl.nombre, n.nombre, tra.activo " +
        		  "from trabajador tra, perfil_laboral prl, nivel_experiencia n, rol_sistema rl " +
        		"where tra.id_rol=rl.id_rol " +
        		"and tra.id_perfil=prl.id_perfil " +
        		"and tra.id_nivel=n.id_nivel";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	
            	Perfil_laboral pl = new Perfil_laboral(
            			
            			rs.getString("nombre")
            			);
            	Nivel_experiencia ne = new Nivel_experiencia(
            			
            			rs.getString("nombre")
            			);
            	Trabajador t = new Trabajador(
            			rs.getString("nombre"),
            			rs.getString("correo"),
            			rs.getString("rol"),
            			rs.getBoolean("activo")
            			
            			);
            	listaTrabajadores.add(t);
            	
            			
            			
            	

               
            }

        } catch (Exception e) {

            throw new Exception("Error al obtener trabajadores: " + e.getMessage());
        }

        return listaTrabajadores;
    }
}