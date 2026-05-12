package controlador;

import vista.VentanaLogin;
import vista.VentanaMenuPrincipal;
import modelo.UsuarioDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginController {

    private VentanaLogin vista;
    private UsuarioDAO modelo;

    public LoginController(VentanaLogin vista) {

        this.vista = vista;
        this.modelo = new UsuarioDAO();

        this.vista.getBotonLogin().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {

        try {

            String correo = vista.getCorreo().trim();
            String contrasena = vista.getContrasena().trim();

            // =========================
            // VALIDAR CAMPOS VACÍOS
            // =========================

            if (correo.isEmpty() || contrasena.isEmpty()) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Debes completar todos los campos",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // =========================
            // VALIDAR FORMATO EMAIL
            // =========================

            if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Introduce un correo válido",
                        "Correo inválido",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // =========================
            // LOGIN
            // =========================

            boolean valido = modelo.validarUsuario(correo, contrasena);

            if (valido) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Login correcto",
                        "Bienvenido",
                        JOptionPane.INFORMATION_MESSAGE);
                
                String usuarioRol = UsuarioDAO.seleccionUsuario(correo); //RECIBE TRABAJADOR O ADMINISTRADOR
                
                java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userNodeForPackage(VentanaLogin.class);
                if(vista.esRecordarSeleccionado()) {
                	prefs.put("usuario_correo",correo);
                	prefs.put("usuario_rol",usuarioRol);
                }else {
                	prefs.remove("usuario_correo");
                	prefs.remove("usuario_rol");
                }
                
                // Abrir menú principal
                VentanaMenuPrincipal menu = new VentanaMenuPrincipal(usuarioRol);
                menu.setVisible(true);

                // Cerrar login
                vista.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        vista,
                        "Usuario o contraseña incorrectos",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Ha ocurrido un error:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            e.printStackTrace();
        }
    }
}