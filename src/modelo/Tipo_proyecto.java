package modelo;

public class Tipo_proyecto {
	private int id_tipo_proyecto;
	private String nombre;
	
	public Tipo_proyecto() {
		id_tipo_proyecto=0;
		nombre="";
	}
	public Tipo_proyecto(int id_tipo_proyecto,String nombre) {
		this.id_tipo_proyecto=id_tipo_proyecto;
		this.nombre=nombre;
	}
	public Tipo_proyecto(String nombre) {
		this.nombre=nombre;
	}
	public int getId_tipo_proyecto() {
		return id_tipo_proyecto;
	}
	public void setId_tipo_proyecto(int id_tipo_proyecto) {
		this.id_tipo_proyecto = id_tipo_proyecto;
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

	    Tipo_proyecto other = (Tipo_proyecto) obj;
	    return this.id_tipo_proyecto == other.id_tipo_proyecto;
	}

}
