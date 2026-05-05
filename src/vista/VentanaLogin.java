package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputCorreo;
	private JTextField InputContrasena;

	/**
	 * Launch the application.
	 */
	public static void cargaVentana(VentanaLogin frame) {
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
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 234);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 228, 228));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputCorreo = new JTextField();
		inputCorreo.setBounds(147, 48, 96, 20);
		contentPane.add(inputCorreo);
		inputCorreo.setColumns(10);
		
		JTextPane CorreoTxt = new JTextPane();
		CorreoTxt.setEditable(false);
		CorreoTxt.setBackground(new Color(228, 228, 228));
		CorreoTxt.setText("Correo (email):");
		CorreoTxt.setBounds(54, 48, 90, 20);
		contentPane.add(CorreoTxt);
		
		JTextPane ContrasenaTxt = new JTextPane();
		ContrasenaTxt.setEditable(false);
		ContrasenaTxt.setText("Contraseña:");
		ContrasenaTxt.setBackground(new Color(228, 228, 228));
		ContrasenaTxt.setBounds(77, 79, 67, 20);
		contentPane.add(ContrasenaTxt);
		
		InputContrasena = new JTextField();
		InputContrasena.setBounds(147, 79, 96, 20);
		contentPane.add(InputContrasena);
		InputContrasena.setColumns(10);
		
		JCheckBox RecordarTxt = new JCheckBox("Recordar Usuario");
		RecordarTxt.setBackground(new Color(228, 228, 228));
		RecordarTxt.setBounds(114, 106, 129, 22);
		contentPane.add(RecordarTxt);
		
		JButton botonIS = new JButton("Iniciar Sesión");
		botonIS.setBackground(new Color(178, 178, 178));
		botonIS.setBounds(109, 135, 119, 22);
		contentPane.add(botonIS);

	}
}