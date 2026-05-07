package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}