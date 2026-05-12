package modelo;

public class Perfil_laboral {
	private int id_perfil;
	private String nombre;
	
	public Perfil_laboral() {
		id_perfil=0;
		nombre="";
	}
	
	public Perfil_laboral(int id_perfil,String nombre) {
		this.id_perfil=id_perfil;
		this.nombre=nombre;
	}
	
	public Perfil_laboral(String nombre) {
		this.nombre=nombre;
	}

	public int getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
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
