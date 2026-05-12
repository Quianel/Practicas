package modelo;

public class Rol_sistema {
	private int id_rol;
	private String nombre;
	
	public Rol_sistema() {
		id_rol=0;
		nombre="";
	}
	
	public Rol_sistema(int id_rol, String nombre) {
		this.id_rol=id_rol;
		this.nombre=nombre;
	}

	public Rol_sistema(String nombre) {
		this.nombre=nombre;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
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
