package modelo;

public class Tarea_proyecto {//Clase tarea principal
	private int id_tarea_proyecto;
	private Proyecto proyec;//necesitamos de la tabla proyecto para ver a cual pertenece la tarea
	private Catalogo_tareas catalog;//necesitamos de la tabla catalogo_tareas para ver de que tipo de tarea tratamos
	private int id_tarea_padre;//es nullable, una tarea puede tener subtareas
	private Estado_tarea estadotar;//necesitamos de la tabla estado_tarea para acceder a especificaciones de cumplimiento
	private String nombre_visible;
	private boolean activa;
	
	public Tarea_proyecto() {
		id_tarea_proyecto=0;
		proyec=null;
		catalog=null;
		id_tarea_padre=0;
		estadotar=null;
		nombre_visible="";
		activa=false;
	}
	
	public Tarea_proyecto(int id_tarea_proyecto,Proyecto proyec,Catalogo_tareas catalog,int id_tarea_padre,
					Estado_tarea estadotar,String nombre_visible,boolean activa) {
		
		this.id_tarea_proyecto=id_tarea_proyecto;
		this.proyec=proyec;
		this.catalog=catalog;
		this.id_tarea_padre=id_tarea_padre;
		this.estadotar=estadotar;
		this.nombre_visible=nombre_visible;
		this.activa=activa;
	}
	
	public Tarea_proyecto(String nombre_visible,boolean activa) {
		this.nombre_visible=nombre_visible;
		this.activa=activa;
	}
}
