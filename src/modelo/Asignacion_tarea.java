package modelo;

import java.time.LocalDate;

public class Asignacion_tarea {// Clase asignacion tarea principal, para reasignaciones
	private int id_asignacion_tarea;
	private Tarea_proyecto tarPro;// Necesitamos de la tabla tarea proyecto para saber a que tarea nos referimos
	private Trabajador trab;// Necesitamos de la tabla trabajador para ver a quien le asignamos
	private LocalDate fecha_asignacion;
	private LocalDate fecha_fin_asignacion;
	private String motivo_fin;// Motivo por el que se asigna
	private boolean activa;// Para ver si es una tarea disponible

	public Asignacion_tarea() {
		id_asignacion_tarea = 0;
		tarPro = null;
		trab = null;
		fecha_asignacion = LocalDate.of(0000, 00, 00);
		fecha_fin_asignacion = LocalDate.of(0000, 00, 00);
		motivo_fin = "";
		activa = false;
	}

	public Asignacion_tarea(int id_asignacion_tarea, Tarea_proyecto tarPro, Trabajador trab, LocalDate fecha_asignacion,
			LocalDate fecha_fin_asignacion, String motivo_fin, boolean activa) {

		this.id_asignacion_tarea = id_asignacion_tarea;
		this.tarPro = tarPro;
		this.trab = trab;
		this.fecha_asignacion = fecha_asignacion;
		this.fecha_fin_asignacion = fecha_fin_asignacion;
		this.motivo_fin = motivo_fin;
		this.activa = activa;
	}

	public Asignacion_tarea(LocalDate fecha_asignacion,LocalDate fecha_fin_asignacion, 
							String motivo_fin, boolean activa) {

		this.fecha_asignacion = fecha_asignacion;
		this.fecha_fin_asignacion = fecha_fin_asignacion;
		this.motivo_fin = motivo_fin;
		this.activa = activa;
	}

	public int getId_asignacion_tarea() {
		return id_asignacion_tarea;
	}

	public void setId_asignacion_tarea(int id_asignacion_tarea) {
		this.id_asignacion_tarea = id_asignacion_tarea;
	}

	public Tarea_proyecto getTarPro() {
		return tarPro;
	}

	public void setTarPro(Tarea_proyecto tarPro) {
		this.tarPro = tarPro;
	}

	public Trabajador getTrab() {
		return trab;
	}

	public void setTrab(Trabajador trab) {
		this.trab = trab;
	}

	public LocalDate getFecha_asignacion() {
		return fecha_asignacion;
	}

	public void setFecha_asignacion(LocalDate fecha_asignacion) {
		this.fecha_asignacion = fecha_asignacion;
	}

	public LocalDate getFecha_fin_asignacion() {
		return fecha_fin_asignacion;
	}

	public void setFecha_fin_asignacion(LocalDate fecha_fin_asignacion) {
		this.fecha_fin_asignacion = fecha_fin_asignacion;
	}

	public String getMotivo_fin() {
		return motivo_fin;
	}

	public void setMotivo_fin(String motivo_fin) {
		this.motivo_fin = motivo_fin;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	
	
}
