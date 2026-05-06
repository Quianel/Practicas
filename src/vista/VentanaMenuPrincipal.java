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
	 */
	public VentanaMenuPrincipal() {
		setFont(new Font("Liberation Mono", Font.BOLD, 14));
		setTitle("Menú principal / Aside");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 414);
		

		contentPane = new JPanel();
		contentPane.setBackground(new Color(180, 180, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar mnuPrincipal = new JMenuBar();
		mnuPrincipal.setBackground(new Color(200, 200, 200));
		//setJMenuBar(menuBar);
		mnuPrincipal.setLayout(new GridLayout(0,1));
		mnuPrincipal.setPreferredSize(new Dimension(250, 0));
		contentPane.add(mnuPrincipal,BorderLayout.WEST);
		
		JMenu mnDashboIni = new JMenu("Dashboard/Inicio");
		mnDashboIni.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout)(panelCardLCarga.getLayout());
				cl.show(panelCardLCarga, "Inicio");
			}
		});
	
		mnDashboIni.setIcon(new ImageIcon("img/inicio.png"));
		mnDashboIni.setBackground(new Color(180, 180, 180));
		mnDashboIni.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnDashboIni);
		
		JMenu mnProyectos = new JMenu("Proyectos");
		mnProyectos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout)(panelCardLCarga.getLayout());
				cl.show(panelCardLCarga,"CrearProyecto");
			}
		});
		mnProyectos.setIcon(new ImageIcon("img/proyecto.png"));
		mnProyectos.setBackground(new Color(180, 180, 180));
		mnProyectos.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnProyectos);
		
		JMenu mnCatTareas = new JMenu("Catálogo de Tareas");
		mnCatTareas.setIcon(new ImageIcon("img/listaTareas.png"));
		mnCatTareas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnCatTareas);
		
		JMenu mnTareasdelProy = new JMenu("Tareas del proyecto");
		mnTareasdelProy.setIcon(new ImageIcon("img/tarea.png"));
		mnTareasdelProy.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnTareasdelProy);
		
		JMenu mnMistareas = new JMenu("Mis tareas");
		mnMistareas.setIcon(new ImageIcon("img/misTareas.png"));
		mnMistareas.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnMistareas);
		
		JMenu mnControlTiempo = new JMenu("Control de tiempo");
		mnControlTiempo.setIcon(new ImageIcon("img/controlTiempo.png"));
		mnControlTiempo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnControlTiempo);
		
		JMenu mnRegistromanual = new JMenu("Registro manual");
		mnRegistromanual.setIcon(new ImageIcon("img/registroManual.png"));
		mnRegistromanual.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnRegistromanual);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon("img/reportes.png"));
		mnReportes.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnReportes);
		
		JMenu mnUsuariosTrabajadores = new JMenu("Usuarios/Trabajadores");
		mnUsuariosTrabajadores.setIcon(new ImageIcon("img/usuariosTrabajadores.png"));
		mnUsuariosTrabajadores.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnUsuariosTrabajadores);
		
		JMenu mnCerrarSesion = new JMenu("Cerrar sesión");
		mnCerrarSesion.setIcon(new ImageIcon("img/cerrarSesion.png"));
		mnCerrarSesion.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mnuPrincipal.add(mnCerrarSesion);
		
		JLabel lblTimeorder = new JLabel("                                       TIME  ORDER");
		lblTimeorder.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblTimeorder.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTimeorder, BorderLayout.NORTH);
		
		panelCardLCarga = new JPanel();
		contentPane.add(panelCardLCarga, BorderLayout.CENTER);
		panelCardLCarga.setLayout(new CardLayout(0, 0));
		
		Bienvenida b = new Bienvenida();
		panelCardLCarga.add(b,"Inicio");
		
		VentanaCrearProyecto vProyecto = new VentanaCrearProyecto();
		panelCardLCarga.add(vProyecto,"CrearProyecto");
			
	}
	public void nuevoPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();
	}


}
