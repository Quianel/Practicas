package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {

    public boolean validarUsuario(String correo, String contrasena) throws Exception {

        String sql = "SELECT password_hash FROM trabajador WHERE correo = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hashGuardado = rs.getString("password_hash");

                // Validación BCrypt
                if (org.mindrot.jbcrypt.BCrypt.checkpw(contrasena, hashGuardado)) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {

            // Lanzamos el error al controlador
            throw new Exception("Error al validar usuario: " + e.getMessage());
        }
    }

	public static String seleccionUsuario(String correo) {
		String tipo = null;
		try {
			Connection conexion = ConexionBD.getConexion();
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select rol_sistema.nombre Tipo from trabajador, rol_sistema "
					+ "where trabajador.id_rol = rol_sistema.id_rol "
					+ "and trabajador.correo = '" + correo + "'");
			
			if(registro.next()) {
				tipo = registro.getString("Tipo");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return tipo;
	}
}