package modelo;

public class Permiso {
	private int id_permiso;
	private String clave;
	private String descripcion;
	
	public Permiso() {
		id_permiso=0;
		clave="";
		descripcion="";
	}

	public Permiso(int id_permiso, String clave, String descripcion) {
		this.id_permiso=id_permiso;
		this.clave=clave;
		this.descripcion=descripcion;
	}
	
	public Permiso(String clave, String descripcion) {
		this.clave=clave;
		this.descripcion=descripcion;
	}

	public int getId_permiso() {
		return id_permiso;
	}

	public void setId_permiso(int id_permiso) {
		this.id_permiso = id_permiso;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
