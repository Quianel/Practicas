package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelo.CentroReportesDAO;

public class ExportarComoXLSX {

	public boolean ExportarComoXLSX(DefaultTableModel modelotabla) {
		
		CentroReportesDAO bd = new CentroReportesDAO();
		String fecha = bd.obtenerFechaActual();
		File archivo = new File("Registro " + fecha + ".xlsx");
		boolean exito = false;
		try {
			XSSFWorkbook libro = new XSSFWorkbook();
			XSSFSheet hoja = libro.createSheet("Resultados");
			
			int columnas = modelotabla.getColumnCount();
			int filas = modelotabla.getRowCount();
			
			XSSFRow filaCabecera = hoja.createRow(0);
			for(int columnaActual = 0; columnaActual < columnas; columnaActual++) {
				XSSFCell celda = filaCabecera.createCell(columnaActual);
				celda.setCellValue(modelotabla.getColumnName(columnaActual));
			}
			
			for (int filaActual = 0; filaActual < filas; filaActual++) {
				XSSFRow filaExcel = hoja.createRow(filaActual + 1);
				
				for(int columnaActual = 0; columnaActual < columnas; columnaActual++) {
					Object valorCeldaActual = modelotabla.getValueAt(filaActual, columnaActual);
					String valorAString = "";
					if(valorCeldaActual != null) {
						valorAString = valorCeldaActual.toString();
					}
					
					XSSFCell celda = filaExcel.createCell(columnaActual);
					celda.setCellValue(valorAString);
				}
			}
			
			FileOutputStream out = new FileOutputStream(archivo);
			libro.write(out);
			
			out.close();
			libro.close();
			exito = true;
			
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido exportar porque hay alguien usando el XLSX (excel).", "Operación canelada", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exito;
	}

}
