package modelo;

public class Nivel_experiencia {
	private int id_nivel;
	private String nombre;
	
	public Nivel_experiencia() {
		id_nivel=0;
		nombre="";
	}
	
	public Nivel_experiencia(int id_nivel,String nombre) {
		this.id_nivel=id_nivel;
		this.nombre=nombre;
	}
	
	public Nivel_experiencia(String nombre) {
		this.nombre=nombre;
	}

	public int getId_nivel() {
		return id_nivel;
	}

	public void setId_nivel(int id_nivel) {
		this.id_nivel = id_nivel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

}
