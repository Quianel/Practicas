package modelo;

public class Estado_proyecto {
	private int id_estado_proyecto;
	private String nombre;
	
	public Estado_proyecto() {
		id_estado_proyecto=0;
		nombre="";
	}
	
	public Estado_proyecto(int id_estado_proyecto,String nombre) {
		this.id_estado_proyecto=id_estado_proyecto;
		this.nombre=nombre;
	}
	
	public Estado_proyecto(String nombre) {
		this.nombre=nombre;
	}

	public int getId_estado_proyecto() {
		return id_estado_proyecto;
	}

	public void setId_estado_proyecto(int id_estado_proyecto) {
		this.id_estado_proyecto = id_estado_proyecto;
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

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Estado_proyecto other = (Estado_proyecto) obj;
	    return this.id_estado_proyecto == other.id_estado_proyecto;
	}
	
}
