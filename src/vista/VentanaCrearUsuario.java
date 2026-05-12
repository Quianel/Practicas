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
import modelo.Proyecto;
import modelo.Rol_permiso;
import modelo.Rol_sistema;
import modelo.Trabajador;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaCrearUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField InputNombre;
	private JTextField InputCorreo;
	private JPasswordField InputContrasena;
	private JPasswordField InputConContrasena;
	private JComboBox InputRol;
	private JComboBox InputNivel;
	private JComboBox InputPerfil;
	private JCheckBox activoCheckBox;
	private Trabajador trabajadorEditando = null;

	/**
	 * Create the panel.
	 */
	public VentanaCrearUsuario() {
		setBackground(new Color(53, 48, 105));
		setLayout(null);
		

		JTextPane NombreTxt = new JTextPane();
		NombreTxt.setForeground(new Color(240, 89, 68));
		NombreTxt.setText("Nombre:");
		NombreTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		NombreTxt.setEditable(false);
		NombreTxt.setBackground(new Color(53, 48, 105));
		NombreTxt.setBounds(49, 68, 52, 20);
		add(NombreTxt);

		InputNombre = new JTextField();
		InputNombre.setBackground(new Color(187, 190, 253));
		InputNombre.setBounds(122, 68, 205, 20);
		add(InputNombre);
		InputNombre.setColumns(10);

		JTextPane CorreoTxt = new JTextPane();
		CorreoTxt.setForeground(new Color(240, 89, 68));
		CorreoTxt.setText("Correo:");
		CorreoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		CorreoTxt.setEditable(false);
		CorreoTxt.setBackground(new Color(53, 48, 105));
		CorreoTxt.setBounds(49, 97, 52, 20);
		add(CorreoTxt);

		InputCorreo = new JTextField();
		InputCorreo.setBackground(new Color(187, 190, 253));
		InputCorreo.setBounds(122, 97, 205, 20);
		add(InputCorreo);
		InputCorreo.setColumns(10);

		JTextPane ContrasenaTxt = new JTextPane();
		ContrasenaTxt.setForeground(new Color(240, 89, 68));
		ContrasenaTxt.setText("Contraseña:");
		ContrasenaTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		ContrasenaTxt.setEditable(false);
		ContrasenaTxt.setBackground(new Color(53, 48, 105));
		ContrasenaTxt.setBounds(49, 128, 68, 20);
		add(ContrasenaTxt);

		InputContrasena = new JPasswordField();
		InputContrasena.setBackground(new Color(187, 190, 253));
		InputContrasena.setBounds(122, 128, 205, 20);
		add(InputContrasena);

		JTextPane ConContrasenaTxt = new JTextPane();
		ConContrasenaTxt.setForeground(new Color(240, 89, 68));
		ConContrasenaTxt.setText("Confirmar\t\t\r\nContraseña:\r\n");
		ConContrasenaTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		ConContrasenaTxt.setEditable(false);
		ConContrasenaTxt.setBackground(new Color(53, 48, 105));
		ConContrasenaTxt.setBounds(49, 161, 68, 37);
		add(ConContrasenaTxt);

		InputConContrasena = new JPasswordField();
		InputConContrasena.setBackground(new Color(187, 190, 253));
		InputConContrasena.setBounds(122, 171, 205, 20);
		add(InputConContrasena);

		JTextPane RolTxt = new JTextPane();
		RolTxt.setForeground(new Color(240, 89, 68));
		RolTxt.setText("Rol:");
		RolTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		RolTxt.setEditable(false);
		RolTxt.setBackground(new Color(53, 48, 105));
		RolTxt.setBounds(428, 68, 32, 20);
		add(RolTxt);

		InputRol = new JComboBox();
		InputRol.setForeground(new Color(240, 89, 68));
		InputRol.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		InputRol.setBackground(new Color(187, 190, 253));
		InputRol.setBounds(526, 68, 217, 22);
		add(InputRol);

		JTextPane txtpnPerfilLaboral = new JTextPane();
		txtpnPerfilLaboral.setForeground(new Color(240, 89, 68));
		txtpnPerfilLaboral.setText("Perfil Laboral:");
		txtpnPerfilLaboral.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		txtpnPerfilLaboral.setEditable(false);
		txtpnPerfilLaboral.setBackground(new Color(53, 48, 105));
		txtpnPerfilLaboral.setBounds(428, 97, 76, 20);
		add(txtpnPerfilLaboral);

		InputPerfil = new JComboBox();
		InputPerfil.setForeground(new Color(240, 89, 68));
		InputPerfil.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		InputPerfil.setBackground(new Color(187, 190, 253));
		InputPerfil.setBounds(526, 97, 217, 22);
		add(InputPerfil);

		JTextPane NivelTxt = new JTextPane();
		NivelTxt.setForeground(new Color(240, 89, 68));
		NivelTxt.setText("Nivel Experiencia:");
		NivelTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		NivelTxt.setEditable(false);
		NivelTxt.setBackground(new Color(53, 48, 105));
		NivelTxt.setBounds(428, 128, 96, 20);
		add(NivelTxt);

		InputNivel = new JComboBox();
		InputNivel.setForeground(new Color(240, 89, 68));
		InputNivel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		InputNivel.setBackground(new Color(187, 190, 253));
		InputNivel.setBounds(526, 126, 217, 22);
		add(InputNivel);

		JTextPane ActivoTxt = new JTextPane();
		ActivoTxt.setForeground(new Color(240, 89, 68));
		ActivoTxt.setText("Activo");
		ActivoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		ActivoTxt.setEditable(false);
		ActivoTxt.setBackground(new Color(53, 48, 105));
		ActivoTxt.setBounds(428, 161, 43, 20);
		add(ActivoTxt);

		activoCheckBox = new JCheckBox("");
		activoCheckBox.setBackground(new Color(53, 48, 105));
		activoCheckBox.setBounds(483, 159, 21, 22);
		add(activoCheckBox);

		JButton GuardarBoton = new JButton("Guardar");
		GuardarBoton.setForeground(new Color(240, 89, 68));
		GuardarBoton.setBackground(new Color(187, 190, 253));
		GuardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (InputNombre.getText().trim().isEmpty() || InputCorreo.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre y el correo son obligatorios", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String pass = new String(InputContrasena.getPassword());
					String passConfirm = new String(InputConContrasena.getPassword());

					if (pass.isEmpty()) {
						JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacia", "ADVERTENCIA",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (!pass.equals(passConfirm)) {
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Trabajador nuevoTrab = new Trabajador();
							nuevoTrab.setNombre(InputNombre.getText().trim());
							nuevoTrab.setCorreo(InputCorreo.getText().trim());
							nuevoTrab.setPassword_hash(pass);
							nuevoTrab.setRol((Rol_sistema) InputRol.getSelectedItem());
							nuevoTrab.setPerfil((Perfil_laboral) InputPerfil.getSelectedItem());
							nuevoTrab.setNivel((Nivel_experiencia) InputNivel.getSelectedItem());
							nuevoTrab.setActivo(activoCheckBox.isSelected());

							CrearUsuarioDAO cu = new CrearUsuarioDAO();
							boolean correcto = cu.CrearUsuario(nuevoTrab);

							if (correcto) {
								JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "EXITO",
										JOptionPane.INFORMATION_MESSAGE);
								InputNombre.setText("");
								InputCorreo.setText("");
								InputContrasena.setText("");
								InputConContrasena.setText("");
								activoCheckBox.setSelected(false);
								if(InputRol.getItemCount()>0) {
									InputRol.setSelectedIndex(0);
								}
								if(InputPerfil.getItemCount()>0) {
									InputPerfil.setSelectedIndex(0);
								}
								if(InputNivel.getItemCount()>0){
									InputNivel.setSelectedIndex(0);
								}
								trabajadorEditando = null;
							} else {
								JOptionPane.showMessageDialog(null, "No se pudo registrar al usuario", "ERROR",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					}
				}

			}
		});
		GuardarBoton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		GuardarBoton.setBounds(274, 257, 86, 22);
		add(GuardarBoton);

		JButton CancelarBoton = new JButton("Cancelar");
		CancelarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				trabajadorEditando = null;
				java.awt.Window ventanaFlotante = javax.swing.SwingUtilities.getWindowAncestor(VentanaCrearUsuario.this);
				
				if(ventanaFlotante != null) {
					ventanaFlotante.dispose();
				}
				
			}
		});
		CancelarBoton.setForeground(new Color(240, 89, 68));
		CancelarBoton.setBackground(new Color(187, 190, 253));
		CancelarBoton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		CancelarBoton.setBounds(385, 257, 86, 22);
		add(CancelarBoton);
		
		cargarCombosUsuario();

	}
	
	public void cargarCombosUsuario() {
		CrearUsuarioDAO cu = new CrearUsuarioDAO();
		ArrayList<Rol_sistema> listaRoles = cu.cargarRol();
		ArrayList<Perfil_laboral> listaPerfiles = cu.cargarPerfil();
		ArrayList<Nivel_experiencia> listaNiveles = cu.cargarNivel();
		
		if(listaRoles != null) {
			for(Rol_sistema r : listaRoles) {
				InputRol.addItem(r);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se han podido cargar los roles","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
		if(listaPerfiles != null) {
			for(Perfil_laboral pl : listaPerfiles) {
				InputPerfil.addItem(pl);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se han podido cargar los perfiles","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
		if(listaNiveles != null) {
			for(Nivel_experiencia n : listaNiveles) {
				InputNivel.addItem(n);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se han podido cargar los niveles","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void mostrarCargaTrabajador(Trabajador t) {

		this.trabajadorEditando = t;

		if (t != null) {

			InputNombre.setText(t.getNombre());
			InputCorreo.setText(t.getCorreo());
			activoCheckBox.setSelected(t.isActivo());
			InputRol.setSelectedItem(t.getRol().getNombre());
			InputPerfil.setSelectedItem(t.getPerfil().getNombre());
			InputNivel.setSelectedItem(t.getNivel().getNombre());
		}
	}
	

}
