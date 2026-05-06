package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean validarUsuario(String correo, String contrasena) {
        boolean valido = false;

        String sql = "SELECT * FROM trabajador WHERE correo = ? AND password_hash = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                valido = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return valido;
    }
}