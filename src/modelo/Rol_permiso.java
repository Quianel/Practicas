package modelo;

public class Rol_permiso {
	private int id_rol;
	private int id_permiso;
	

	public Rol_permiso() {
		id_rol=0;
		id_permiso=0;
	}
	
	public Rol_permiso(int id_rol,int id_permiso) {
		this.id_rol=id_rol;
		this.id_permiso=id_permiso;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public int getId_permiso() {
		return id_permiso;
	}

	public void setId_permiso(int id_permiso) {
		this.id_permiso = id_permiso;
	}
	
	
}
