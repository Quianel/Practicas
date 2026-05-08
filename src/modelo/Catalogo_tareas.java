package modelo;

public class Catalogo_tareas {
	private int id_tarea_catalogo;
	private String nombre;
	private boolean soloSenior;
	private Tarea_proyecto TareaProyecto;
	private Proyecto proyecto;
	
	public Catalogo_tareas() {
		id_tarea_catalogo=0;
		nombre="";
		soloSenior=false;
		TareaProyecto = null;
		proyecto = null;
	}
	
	public Catalogo_tareas(int id_tarea_catalogo,String nombre,boolean soloSenior, Tarea_proyecto TareaProyecto, Proyecto proyecto) {
		this.id_tarea_catalogo=id_tarea_catalogo;
		this.nombre=nombre;
		this.soloSenior=soloSenior;
		this.TareaProyecto=TareaProyecto;
		this.proyecto=proyecto;
	}
	
	public Catalogo_tareas(int id_tarea_catalogo,String nombre,boolean soloSenior) {
		this.id_tarea_catalogo=id_tarea_catalogo;
		this.nombre=nombre;
		this.soloSenior=soloSenior;
	}
	
	public Catalogo_tareas(String nombre,boolean soloSenior) {
		this.nombre=nombre;
		this.soloSenior=soloSenior;
	}

	public int getId_tarea_catalogo() {
		return id_tarea_catalogo;
	}

	public void setId_tarea_catalogo(int id_tarea_catalogo) {
		this.id_tarea_catalogo = id_tarea_catalogo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isSoloSenior() {
		return soloSenior;
	}

	public void setSoloSenior(boolean soloSenior) {
		this.soloSenior = soloSenior;
	}

	public Tarea_proyecto getTareaProyecto() {
		return TareaProyecto;
	}

	public void setTareaProyecto(Tarea_proyecto tareaProyecto) {
		TareaProyecto = tareaProyecto;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	

}
