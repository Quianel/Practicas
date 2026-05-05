package modelo;

public class Catalogo_tareas {
	private int id_tarea_catalogo;
	private String nombre;
	private boolean soloSenior;
	
	public Catalogo_tareas() {
		id_tarea_catalogo=0;
		nombre="";
		soloSenior=false;
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
	
	

}
