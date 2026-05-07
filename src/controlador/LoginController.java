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

        String correo = vista.getCorreo();
        String contrasena = vista.getContrasena();

        boolean valido = modelo.validarUsuario(correo, contrasena);

        if (valido) {

            JOptionPane.showMessageDialog(null, "Login correcto");

            // Crear ventana principal
            VentanaMenuPrincipal menu = new VentanaMenuPrincipal();

            // Mostrar ventana principal
            menu.setVisible(true);

            // Cerrar login
            vista.dispose();

        } else {

            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");

        }
    }
}