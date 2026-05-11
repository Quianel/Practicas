package controlador;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import modelo.Catalogo_tareas;
import modelo.CrearTareaDAO;
import modelo.Estado_tarea;
import modelo.Proyecto;
import modelo.Tarea_proyecto;
import vista.VentanaCrearTarea;

public class TareaYsubtareasController {
	private VentanaCrearTarea vista;
	private CrearTareaDAO modelo;
	
	public TareaYsubtareasController(VentanaCrearTarea vista) {
		this.vista=vista;
		this.modelo = new CrearTareaDAO();
		
		cargarComboProyectos();
		cargarComboTipo();
		cargarComboTareaPadre();
		cargarComboEstado();
		
		
	}
	
	 // =========================
    // COMBO PROYECTOS
    // =========================
	private void cargarComboProyectos() {
		try {
			CrearTareaDAO dao = new CrearTareaDAO();
			ArrayList<Proyecto> lista = dao.cargarProyectos();
			
			DefaultComboBoxModel<Proyecto> combo = new DefaultComboBoxModel<>();
			
            Proyecto todos = new Proyecto();
            
            for(Proyecto p : lista) {
            	combo.addElement(todos);
            }
            
            vista.getCmbProyectos().setModel(combo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 // =========================
    // COMBO TIPO TAREA
    // =========================
	private void cargarComboTipo() {
		try {
			CrearTareaDAO dao = new CrearTareaDAO();
			ArrayList<Catalogo_tareas> lista = dao.cargarTipoTarea();
			
			DefaultComboBoxModel<Catalogo_tareas> combo = new DefaultComboBoxModel<>();
			
			Catalogo_tareas todas = new Catalogo_tareas();
			
			for(Catalogo_tareas c : lista) {
				combo.addElement(c);
			}
			
			vista.getInputTipo().setModel(combo);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 // =========================
    // COMBO TAREA PADRE
    // =========================
	
	private void cargarComboTareaPadre() {
		try {
			CrearTareaDAO dao = new CrearTareaDAO();
			ArrayList<Tarea_proyecto> lista = dao.cargarTareaPadre();
			
			DefaultComboBoxModel<Tarea_proyecto> combo = new DefaultComboBoxModel<>();
			
			 // =========================
            // OPCIÓN NINGUNA
            // =========================
			Tarea_proyecto todas = new Tarea_proyecto();
            todas.setId_tarea_padre(0);
            todas.setNombre_visible("Ninguna");
            todas.setActiva(true);
            
            combo.addElement(todas);
				
			for(Tarea_proyecto t : lista) {
				combo.addElement(t);
			}
			
			vista.getInputTareaPadre().setModel(combo);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	 // =========================
   // COMBO ESTADO TAREA
   // =========================
	private void cargarComboEstado() {
		try {
			CrearTareaDAO dao = new CrearTareaDAO();
			ArrayList<Estado_tarea> lista = dao.cargarEstadoTar();
			
			DefaultComboBoxModel<Estado_tarea> combo = new DefaultComboBoxModel<>();
			
			Estado_tarea todos = new Estado_tarea();
			
			for(Estado_tarea e : lista) {
				combo.addElement(e);
			}
			
			vista.getInputEstado().setModel(combo);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
