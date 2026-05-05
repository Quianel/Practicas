package modelo;

import java.time.LocalDateTime;

public class Registro_tiempo {// Clase registro_tiempo principal
	private int id_registro;
	private Trabajador trabajad;// Necesitamos de la tabla trabajador para registrarlo en el tiempo
	private Proyecto proye;// Necesitamos de la tabla proyecto para saber en cual esta trabajando
	private Tarea_proyecto tarproyec;// Necesitamos de la tabla tarea_proyecto para registrarla
	private LocalDateTime fecha_hora_inicio;// incluye la hora con la fecha
	private LocalDateTime fecha_hora_fin;
	private int minutos_totales;
	private String modo_registro;
	private String comentario;

	public Registro_tiempo() {
		id_registro = 0;
		trabajad = null;
		proye = null;
		tarproyec = null;
		fecha_hora_inicio = LocalDateTime.of(0000, 00, 00, 00, 00);
		fecha_hora_fin = LocalDateTime.of(0000, 00, 00, 00, 00);
		minutos_totales = 0;
		modo_registro = "";
		comentario = "";
	}

	public Registro_tiempo(int id_registro, Trabajador tabajad, Proyecto proye, Tarea_proyecto tarproye,
			LocalDateTime fecha_hora_inicio, LocalDateTime fecha_hora_fin, int minutos_totales, String modo_registro,
			String comentario) {

		this.id_registro = id_registro;
		this.trabajad = tabajad;
		this.proye = proye;
		this.tarproyec = tarproye;
		this.fecha_hora_inicio = fecha_hora_inicio;
		this.fecha_hora_fin = fecha_hora_fin;
		this.minutos_totales = minutos_totales;
		this.modo_registro = modo_registro;
		this.comentario = comentario;
	}

	public Registro_tiempo(LocalDateTime fecha_hora_inicio, LocalDateTime fecha_hora_fin, int minutos_totales, 
							String modo_registro,String comentario) {

		this.fecha_hora_inicio = fecha_hora_inicio;
		this.fecha_hora_fin = fecha_hora_fin;
		this.minutos_totales = minutos_totales;
		this.modo_registro = modo_registro;
		this.comentario = comentario;
	}

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public Trabajador getTrabajad() {
		return trabajad;
	}

	public void setTrabajad(Trabajador trabajad) {
		this.trabajad = trabajad;
	}

	public Proyecto getProye() {
		return proye;
	}

	public void setProye(Proyecto proye) {
		this.proye = proye;
	}

	public Tarea_proyecto getTarproyec() {
		return tarproyec;
	}

	public void setTarproyec(Tarea_proyecto tarproyec) {
		this.tarproyec = tarproyec;
	}

	public LocalDateTime getFecha_hora_inicio() {
		return fecha_hora_inicio;
	}

	public void setFecha_hora_inicio(LocalDateTime fecha_hora_inicio) {
		this.fecha_hora_inicio = fecha_hora_inicio;
	}

	public LocalDateTime getFecha_hora_fin() {
		return fecha_hora_fin;
	}

	public void setFecha_hora_fin(LocalDateTime fecha_hora_fin) {
		this.fecha_hora_fin = fecha_hora_fin;
	}

	public int getMinutos_totales() {
		return minutos_totales;
	}

	public void setMinutos_totales(int minutos_totales) {
		this.minutos_totales = minutos_totales;
	}

	public String getModo_registro() {
		return modo_registro;
	}

	public void setModo_registro(String modo_registro) {
		this.modo_registro = modo_registro;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
