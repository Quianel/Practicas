package modelo;

public class Trabajador { // Clase trabajador principal
	private int id_trabajador;
	private String nombre;
	private String correo;
	private String password_hash;
	private boolean activo;
	private Rol_permiso rol;// necesitamos este de la tabla rol_permiso por si es administrador o no
	private Perfil_laboral perfil;// necesitamos este de la tabla perfil_laboral para saber que proyectos se pueden asignar
	private Nivel_experiencia nivel;// necesitamos de la tabla nivel_experiencia para saber si cumple con nivel para un proyecto
									 

	public Trabajador() {
		id_trabajador = 0;
		nombre = "";
		correo = "";
		password_hash = "";
		activo = false;
		rol = null;
		perfil = null;
		nivel = null;
	}

	public Trabajador(int id_trabajador, String nombre, String correo, String password_hash, boolean activo,
			Rol_permiso rol, Perfil_laboral perfil, Nivel_experiencia nivel) {

		this.id_trabajador = id_trabajador;
		this.nombre = nombre;
		this.correo = correo;
		this.password_hash = password_hash;
		this.activo = activo;
		this.rol = rol;
		this.perfil = perfil;
		this.nivel = nivel;

	}

	public Trabajador(String nombre, String correo, String password_hash, boolean activo) {

		this.nombre = nombre;
		this.correo = correo;
		this.password_hash = password_hash;
		this.activo = activo;

	}

	public int getId_trabajador() {
		return id_trabajador;
	}

	public void setId_trabajador(int id_trabajador) {
		this.id_trabajador = id_trabajador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Rol_permiso getRol() {
		return rol;
	}

	public void setRol(Rol_permiso rol) {
		this.rol = rol;
	}

	public Perfil_laboral getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil_laboral perfil) {
		this.perfil = perfil;
	}

	public Nivel_experiencia getNivel() {
		return nivel;
	}

	public void setNivel(Nivel_experiencia nivel) {
		this.nivel = nivel;
	}
	
	
}
