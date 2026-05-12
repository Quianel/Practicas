package vista;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import controlador.ControlTiempoController;
import controlador.GestionProyectoController;
import controlador.GestionUsuarioController;
import controlador.TareaProyectoController;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCardLCarga;

	/**
	 * Launch the application.
	 */
	public static void ventanaPrincipal(VentanaMenuPrincipal frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usuario 
	 */
	public VentanaMenuPrincipal(String usuario) {
		
		setFont(new Font("Liberation Mono", Font.BOLD, 14));
		setTitle("Menú principal / Aside");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 414);
		

		contentPane = new JPanel();
		contentPane.setBackground(new Color(37, 33, 73));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setBounds(200,200,1300,600);
		
		JMenuBar mnuPrincipal = new JMenuBar();
		mnuPrincipal.setBackground(new Color(103, 95, 182));
		//setJMenuBar(menuBar);
		mnuPrincipal.setLayout(new GridLayout(0,1));
		mnuPrincipal.setPreferredSize(new Dimension(250, 0));
		contentPane.add(mnuPrincipal,BorderLayout.WEST);
		
		JMenu mnDashboIni = new JMenu("Dashboard/Inicio");
		mnDashboIni.setForeground(new Color(251, 123, 68));
		mnDashboIni.addMouseListener(new MouseAdapter() {//Al tener JMENU les añado action mouse pressed
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout)(panelCardLCarga.getLayout()); 
				cl.show(panelCardLCarga, "Inicio");//Hago visible la ventana inicio
			}
		});
	
		mnDashboIni.setIcon(new ImageIcon("img/inicio.png"));
		mnDashboIni.setBackground(new Color(53, 48, 105));
		mnDashboIni.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnDashboIni);
		
		if(usuario.equals("Administrador")) {
			JMenu mnProyectos = new JMenu("Proyectos");
			mnProyectos.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					CardLayout cl = (CardLayout)(panelCardLCarga.getLayout());
					cl.show(panelCardLCarga,"vGtnPro");
				}
			});
			mnProyectos.setIcon(new ImageIcon("img/proyecto.png"));
			mnProyectos.setForeground(new Color(251, 123, 68));
			mnProyectos.setBackground(new Color(180, 180, 180));
			mnProyectos.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
			mnuPrincipal.add(mnProyectos);
		}
		
		if(usuario.equals("Administrador")) {
			JMenu mnCatTareas = new JMenu("Catálogo de Tareas");
			mnCatTareas.setForeground(new Color(251, 123, 68));
			mnCatTareas.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					CardLayout cl = (CardLayout)(panelCardLCarga.getLayout());
					cl.show(panelCardLCarga, "ventCatTareas");
				}
			});
			mnCatTareas.setIcon(new ImageIcon("img/listaTareas.png"));
			mnCatTareas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
			mnuPrincipal.add(mnCatTareas);
		}
		
		if(usuario.equals("Administrador")) {
			JMenu mnTareasdelProy = new JMenu("Tareas del proyecto");
			mnTareasdelProy.setForeground(new Color(251, 123, 68));
			mnTareasdelProy.setIcon(new ImageIcon("img/tarea.png"));
			mnTareasdelProy.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
	
			mnTareasdelProy.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mousePressed(MouseEvent e) {
			        CardLayout cl = (CardLayout)(panelCardLCarga.getLayout());
			        cl.show(panelCardLCarga, "tareas"); // este es el nombre del panel
			    }
			});
	
			mnuPrincipal.add(mnTareasdelProy);
		}
		
		if(usuario.equals("Trabajador")) {
			JMenu mnMistareas = new JMenu("Mis tareas");
			mnMistareas.setForeground(new Color(251, 123, 68));
			mnMistareas.setIcon(new ImageIcon("img/misTareas.png"));
			mnMistareas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
			mnuPrincipal.add(mnMistareas);
		}
		
		if(usuario.equals("Trabajador")) {
			JMenu mnControlTiempo = new JMenu("Control de tiempo");
			mnControlTiempo.setForeground(new Color(251, 123, 68));
			mnControlTiempo.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					CardLayout cl = (CardLayout)(panelCardLCarga.getLayout());
					cl.show(panelCardLCarga,"ventContTiem");
				}
			});
			mnControlTiempo.setIcon(new ImageIcon("img/controlTiempo.png"));
			mnControlTiempo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
			mnuPrincipal.add(mnControlTiempo);
		}
		
		if(usuario.equals("Trabajador")) {
			JMenu mnRegistromanual = new JMenu("Registro manual");
			mnRegistromanual.setForeground(new Color(251, 123, 68));
			mnRegistromanual.setIcon(new ImageIcon("img/registroManual.png"));
			mnRegistromanual.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
			mnuPrincipal.add(mnRegistromanual);
		}
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(new Color(251, 123, 68));
		mnReportes.setBackground(new Color(53, 48, 105));
		mnReportes.setIcon(new ImageIcon("img/reportes.png"));
		mnReportes.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnReportes);
		
		if(usuario.equals("Administrador")) {
			JMenu mnUsuariosTrabajadores = new JMenu("Usuarios/Trabajadores");
			mnUsuariosTrabajadores.setForeground(new Color(251, 123, 68));
			mnUsuariosTrabajadores.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					CardLayout cl = (CardLayout)(panelCardLCarga.getLayout());
					cl.show(panelCardLCarga,"ventGestionUsuario");
				}
			});
			mnUsuariosTrabajadores.setIcon(new ImageIcon("img/usuariosTrabajadores.png"));
			mnUsuariosTrabajadores.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
			mnuPrincipal.add(mnUsuariosTrabajadores);
		}
		
		JMenu mnCerrarSesion = new JMenu("Cerrar sesión");
		mnCerrarSesion.setForeground(new Color(251, 123, 68));
		mnCerrarSesion.setBackground(new Color(53, 48, 105));
		mnCerrarSesion.setIcon(new ImageIcon("img/cerrarSesion.png"));
		mnCerrarSesion.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnCerrarSesion);
		
		JLabel lblTimeorder = new JLabel("                                       TIME  ORDER");
		lblTimeorder.setForeground(new Color(240, 89, 40));
		lblTimeorder.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblTimeorder.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTimeorder, BorderLayout.NORTH);
		
		panelCardLCarga = new JPanel();
		contentPane.add(panelCardLCarga, BorderLayout.CENTER);
		panelCardLCarga.setLayout(new CardLayout(0, 0));
		
		Bienvenida b = new Bienvenida();//creo objeto de la ventana 
		panelCardLCarga.add(b,"Inicio");//añado al cardLayout y le doy un nombre para la llamada
		
		//VentanaCrearProyecto vProyecto = new VentanaCrearProyecto();
		//panelCardLCarga.add(vProyecto,"CrearProyecto");
		
		VentanaGestionProyecto vGtPro = new VentanaGestionProyecto();
		new GestionProyectoController(vGtPro);

		panelCardLCarga.add(vGtPro,"vGtnPro");
		
		VentanaControlTiempo vCntTiem = new VentanaControlTiempo();
		panelCardLCarga.add(vCntTiem,"ventContTiem");
		
		VentanaCatalogoDeTareas vCatTareas = new VentanaCatalogoDeTareas();
		panelCardLCarga.add(vCatTareas, "ventCatTareas");
		
		VentanaTareas vt = new VentanaTareas();
		new TareaProyectoController(vt);

		panelCardLCarga.add(vt, "tareas");
		
		VentanaGestionUsuario venGestUsuario = new VentanaGestionUsuario();
		new GestionUsuarioController(venGestUsuario);

		panelCardLCarga.add(venGestUsuario, "ventGestionUsuario");
		
		VentanaCrearUsuario venCrearUsu = new VentanaCrearUsuario();
		
		panelCardLCarga.add(venCrearUsu, "ventCrearUsuario");
		
		VentanaCrearTarea ventCrearTar = new VentanaCrearTarea();
		
		panelCardLCarga.add(ventCrearTar, "ventCrearTarea");
		
		
		new ControlTiempoController(vCntTiem);
			
	}
	public void nuevoPanel(JPanel panelActual) {//Este metodo no es necesario en este caso, pero lo dejo por si tuvieramos que cambiar por no funcionamiento
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();
	}
}
