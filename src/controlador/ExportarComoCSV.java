package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ExportarComoCSV {

	public boolean ExportarComoCSV(DefaultTableModel modelotabla) {
		File archivo = new File("Registros.csv");
		boolean exito = false;
		try {
			FileWriter csv = new FileWriter(archivo);
			
			int columnas = modelotabla.getColumnCount();
			int filas = modelotabla.getRowCount();
			
			for (int columnaActual = 0; columnaActual < columnas; columnaActual++) {
				csv.write(modelotabla.getColumnName(columnaActual));
				
				if(columnaActual < columnas -1) {
					csv.write(",");
				}
			}
			csv.write("\n");
			
			for (int filaActual = 0; filaActual < filas; filaActual++) {
				for(int columnaActual = 0; columnaActual < columnas; columnaActual++) {
					Object valorCeldaActual = modelotabla.getValueAt(filaActual, columnaActual);
					String valorAString = "";
					if(valorCeldaActual != null) {
						valorAString = valorCeldaActual.toString();
					}
					csv.write(valorAString);
					
					if(columnaActual < columnas -1) {
						csv.write(",");
					}
				}
				csv.write("\n");
			}
			
			csv.close();
			exito = true;
			
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido exportar porque hay alguien usando el CSV", "Operación canelada", JOptionPane.ERROR_MESSAGE);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
