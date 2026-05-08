package modelo;

import java.time.LocalDate;

public class Proyecto {//Clase proyecto principal
	private int id_proyecto;
	private String codigo_interno;
	private String nombre;
	private String descripcion;
	private LocalDate fecha_inicio;//Para solo fecha
	private LocalDate fecha_limite;
	private boolean es_generico;
	private Tipo_proyecto tipoproyec;//necesitamos de la tabla tipo proyecto para acceder individualmente a de cual se trata
	private Estado_proyecto estadoproyec;//necesitamos de la tabla estado_proyecto por acceder a sus especificaciones
	
	public Proyecto() {
		id_proyecto=0;
		codigo_interno="";
		nombre="";
		descripcion="";
		fecha_inicio=LocalDate.of(0001, 01, 01);
		fecha_limite=LocalDate.of(0001, 01, 01);
		es_generico=false;
		tipoproyec=null;
		estadoproyec=null;
	}
	
	public Proyecto(int id_proyecto,String codigo_interno,String nombre,String descripcion,
			LocalDate fecha_inicio,LocalDate fecha_limite,boolean es_generico,
			Tipo_proyecto tipoproyec,Estado_proyecto estadoproyec) {
		
		this.id_proyecto=id_proyecto;
		this.codigo_interno=codigo_interno;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.fecha_inicio=fecha_inicio;
		this.fecha_limite=fecha_limite;
		this.es_generico=es_generico;
		this.tipoproyec=tipoproyec;
		this.estadoproyec=estadoproyec;
		
	}
	
	public Proyecto(String codigo_interno,String nombre,String descripcion,
			LocalDate fecha_inicio,LocalDate fecha_limite,boolean es_generico) {
		
		this.codigo_interno=codigo_interno;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.fecha_inicio=fecha_inicio;
		this.fecha_limite=fecha_limite;
		this.es_generico=es_generico;
		
	}

	public int getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getCodigo_interno() {
		return codigo_interno;
	}

	public void setCodigo_interno(String codigo_interno) {
		this.codigo_interno = codigo_interno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFecha_limite() {
		return fecha_limite;
	}

	public void setFecha_limite(LocalDate fecha_limite) {
		this.fecha_limite = fecha_limite;
	}

	public boolean isEs_generico() {
		return es_generico;
	}

	public void setEs_generico(boolean es_generico) {
		this.es_generico = es_generico;
	}

	public Tipo_proyecto getTipoproyec() {
		return tipoproyec;
	}

	public void setTipoproyec(Tipo_proyecto tipoproyec) {
		this.tipoproyec = tipoproyec;
	}

	public Estado_proyecto getEstadoproyec() {
		return estadoproyec;
	}

	public void setEstadoproyec(Estado_proyecto estadoproyec) {
		this.estadoproyec = estadoproyec;
	}
	
	@Override
	public String toString() {
	    return nombre;
	}
	
}
