package vista;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;

import modelo.CrearUsuarioDAO;
import modelo.Nivel_experiencia;
import modelo.Perfil_laboral;
import modelo.Rol_permiso;
import modelo.Trabajador;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCrearUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField InputNombre;
	private JTextField InputCorreo;
	private JPasswordField InputContrasena;
	private JPasswordField InputConContrasena;

	/**
	 * Create the panel.
	 */
	public VentanaCrearUsuario() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane NombreTxt = new JTextPane();
		NombreTxt.setText("Nombre:");
		NombreTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		NombreTxt.setEditable(false);
		NombreTxt.setBackground(new Color(180, 180, 180));
		NombreTxt.setBounds(46, 40, 52, 20);
		add(NombreTxt);
		
		InputNombre = new JTextField();
		InputNombre.setBounds(119, 40, 96, 20);
		add(InputNombre);
		InputNombre.setColumns(10);
		
		JTextPane CorreoTxt = new JTextPane();
		CorreoTxt.setText("Correo:");
		CorreoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		CorreoTxt.setEditable(false);
		CorreoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		CorreoTxt.setBounds(46, 69, 52, 20);
		add(CorreoTxt);
		
		InputCorreo = new JTextField();
		InputCorreo.setBounds(119, 69, 96, 20);
		add(InputCorreo);
		InputCorreo.setColumns(10);
		
		JTextPane ContrasenaTxt = new JTextPane();
		ContrasenaTxt.setText("Contraseña:");
		ContrasenaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ContrasenaTxt.setEditable(false);
		ContrasenaTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		ContrasenaTxt.setBounds(46, 100, 68, 20);
		add(ContrasenaTxt);
		
		InputContrasena = new JPasswordField();
		InputContrasena.setBounds(119, 100, 96, 20);
		add(InputContrasena);
		
		JTextPane ConContrasenaTxt = new JTextPane();
		ConContrasenaTxt.setText("Confirmar\t\t\r\nContraseña:\r\n");
		ConContrasenaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ConContrasenaTxt.setEditable(false);
		ConContrasenaTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		ConContrasenaTxt.setBounds(46, 133, 68, 37);
		add(ConContrasenaTxt);
		
		InputConContrasena = new JPasswordField();
		InputConContrasena.setBounds(119, 150, 96, 20);
		add(InputConContrasena);
		
		JTextPane RolTxt = new JTextPane();
		RolTxt.setText("Rol:");
		RolTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		RolTxt.setEditable(false);
		RolTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		RolTxt.setBounds(246, 40, 32, 20);
		add(RolTxt);
		
		JComboBox InputRol = new JComboBox();
		InputRol.setBounds(344, 40, 96, 22);
		add(InputRol);
		
		JTextPane txtpnPerfilLaboral = new JTextPane();
		txtpnPerfilLaboral.setText("Perfil Laboral:");
		txtpnPerfilLaboral.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnPerfilLaboral.setEditable(false);
		txtpnPerfilLaboral.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnPerfilLaboral.setBounds(246, 69, 76, 20);
		add(txtpnPerfilLaboral);
		
		JComboBox InputPerfil = new JComboBox();
		InputPerfil.setBounds(344, 69, 96, 22);
		add(InputPerfil);
		
		JTextPane NivelTxt = new JTextPane();
		NivelTxt.setText("Nivel Experiencia:");
		NivelTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		NivelTxt.setEditable(false);
		NivelTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		NivelTxt.setBounds(246, 100, 96, 20);
		add(NivelTxt);
		
		JComboBox InputNivel = new JComboBox();
		InputNivel.setBounds(344, 98, 96, 22);
		add(InputNivel);
		
		JTextPane ActivoTxt = new JTextPane();
		ActivoTxt.setText("Activo");
		ActivoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ActivoTxt.setEditable(false);
		ActivoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		ActivoTxt.setBounds(246, 133, 43, 20);
		add(ActivoTxt);
		
		JCheckBox activoCheckBox = new JCheckBox("");
		activoCheckBox.setBackground(new Color(180, 180, 180));
		activoCheckBox.setBounds(295, 131, 21, 22);
		add(activoCheckBox);
		
		JButton GuardarBoton = new JButton("Guardar");
		GuardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trabajador nuevoTrab = new Trabajador();
				nuevoTrab.setNombre(InputNombre.getText());
				nuevoTrab.setCorreo(InputCorreo.getText());
				nuevoTrab.setPassword_hash(new String(InputContrasena.getPassword()));
				nuevoTrab.setPassword_hash(new String(InputConContrasena.getPassword()));
				nuevoTrab.setRol((Rol_permiso)InputRol.getSelectedItem());
				nuevoTrab.setPerfil((Perfil_laboral)InputPerfil.getSelectedItem());
				nuevoTrab.setNivel((Nivel_experiencia)InputNivel.getSelectedItem());
				nuevoTrab.setActivo(activoCheckBox.isSelected());
			}
			CrearUsuarioDAO cu = new CrearUsuarioDAO();
			
			boolean correcto;
			
			
			
		});
		GuardarBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		GuardarBoton.setBounds(277, 182, 76, 22);
		add(GuardarBoton);
		
		JButton CancelarBoton = new JButton("Cancelar");
		CancelarBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		CancelarBoton.setBounds(363, 182, 76, 22);
		add(CancelarBoton);

	}
	


}
