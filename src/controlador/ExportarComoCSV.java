package controlador;

import java.io.File;
import java.io.FileWriter;

import javax.swing.table.DefaultTableModel;

public class ExportarComoCSV {

	public ExportarComoCSV(DefaultTableModel modelotabla) {
		File archivo = new File("Registros.csv");
		
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
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
