package modelo;

public class Estado_tarea {
	private int id_estado_tarea;
	private String nombre;
	
	public Estado_tarea() {
		id_estado_tarea=0;
		nombre="";
	}
	
	public Estado_tarea(int id_estado_tarea,String nombre) {
		this.id_estado_tarea=id_estado_tarea;
		this.nombre=nombre;
	}
	
	public Estado_tarea(String nombre) {
		this.nombre=nombre;
	}

	public int getId_estado_tarea() {
		return id_estado_tarea;
	}

	public void setId_estado_tarea(int id_estado_tarea) {
		this.id_estado_tarea = id_estado_tarea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
	    return nombre;
	}
}
