package controlador;

import vista.VentanaLogin;
import vista.VentanaMenuPrincipal;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaLogin miVentana = new VentanaLogin();
		miVentana.cargaVentana(miVentana);
		VentanaMenuPrincipal ventanaMenu = new VentanaMenuPrincipal();
		ventanaMenu.ventanaPrincipal(ventanaMenu);
		
	}

}
