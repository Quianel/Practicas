package controlador;

import vista.VentanaLogin;

public class Principal {

    public static void main(String[] args) {

        VentanaLogin vista = new VentanaLogin();
        new LoginController(vista);
        
        System.out.println("Trabajador de prueba: trabajador1@prueba.com");
        System.out.println("Administrador de prueba: alvaro_administrador@prueba.com");
        System.out.println("Contraseña: 1234 (para ambos)");

        vista.cargaVentana(vista);
    }
}