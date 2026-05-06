package controlador;

import vista.VentanaLogin;

public class Principal {

    public static void main(String[] args) {

        VentanaLogin vista = new VentanaLogin();
        new LoginController(vista);

        vista.cargaVentana(vista);
    }
}