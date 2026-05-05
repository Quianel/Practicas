package modelo;

import java.time.LocalDate;

public class Auditoria_tiempo {// Clase auditoria principal para creacion,edicion y borrado logico
	private int id_auditoria;
	private Registro_tiempo regtiemp;// Necesitamos de la tabla registro tiempo para tener en cuenta tiempo empleado
	private Trabajador trabaj; // Necesitamos de la tabla Trabajador para saber relacion trabajador-tiempos
	private String accion;
	private LocalDate fecha_evento;

	public Auditoria_tiempo() {
		id_auditoria = 0;
		regtiemp = null;
		trabaj = null;
		accion = "";
		fecha_evento = LocalDate.of(0000, 00, 00);
	}

	public Auditoria_tiempo(int id_auditoria, Registro_tiempo regtiemp, Trabajador trabaj, String accion,
			LocalDate fecha_Evento) {
		this.id_auditoria = id_auditoria;
		this.regtiemp = regtiemp;
		this.trabaj = trabaj;
		this.accion = accion;
		this.fecha_evento = fecha_Evento;
	}

	public Auditoria_tiempo(String accion,LocalDate fecha_Evento) {
		this.accion = accion;
		this.fecha_evento = fecha_Evento;
	}

	public int getId_auditoria() {
		return id_auditoria;
	}

	public void setId_auditoria(int id_auditoria) {
		this.id_auditoria = id_auditoria;
	}

	public Registro_tiempo getRegtiemp() {
		return regtiemp;
	}

	public void setRegtiemp(Registro_tiempo regtiemp) {
		this.regtiemp = regtiemp;
	}

	public Trabajador getTrabaj() {
		return trabaj;
	}

	public void setTrabaj(Trabajador trabaj) {
		this.trabaj = trabaj;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public LocalDate getFecha_evento() {
		return fecha_evento;
	}

	public void setFecha_evento(LocalDate fecha_evento) {
		this.fecha_evento = fecha_evento;
	}
	
	

}
