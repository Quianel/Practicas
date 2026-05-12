package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.CrearUsuarioDAO;
import modelo.UsuarioDAO;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class VentanaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputCorreo;
    private JPasswordField InputContrasena;
    private JButton botonIS;

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
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700, 400, 418, 245);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(53, 48, 105));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        inputCorreo = new JTextField();
        inputCorreo.setBackground(new Color(187, 190, 253));
        inputCorreo.setBounds(147, 48, 175, 20);
        contentPane.add(inputCorreo);
        inputCorreo.setColumns(10);

        JTextPane CorreoTxt = new JTextPane();
        CorreoTxt.setForeground(new Color(240, 89, 68));
        CorreoTxt.setFocusable(false);
        CorreoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        CorreoTxt.setEditable(false);
        CorreoTxt.setBackground(new Color(53, 48, 105));
        CorreoTxt.setText("Correo (email):");
        CorreoTxt.setBounds(54, 48, 90, 20);
        contentPane.add(CorreoTxt);

        JTextPane ContrasenaTxt = new JTextPane();
        ContrasenaTxt.setFocusable(false);
        ContrasenaTxt.setForeground(new Color(240, 89, 68));
        ContrasenaTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        ContrasenaTxt.setEditable(false);
        ContrasenaTxt.setText("Contraseña:");
        ContrasenaTxt.setBackground(new Color(53, 48, 105));
        ContrasenaTxt.setBounds(77, 79, 67, 20);
        contentPane.add(ContrasenaTxt);

        JCheckBox RecordarTxt = new JCheckBox("Recordar Usuario");
        RecordarTxt.setForeground(new Color(240, 89, 68));
        RecordarTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        RecordarTxt.setBackground(new Color(53, 48, 105));
        RecordarTxt.setBounds(147, 106, 129, 22);
        contentPane.add(RecordarTxt);

        botonIS = new JButton("Iniciar Sesión");
        botonIS.setForeground(new Color(240, 89, 68));
        botonIS.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String correo = inputCorreo.getText().trim();
        		String contrasenia = new String(InputContrasena.getPassword());
        		
        		if(correo.isEmpty() || contrasenia.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Por favor rellene los campos","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        			
        		}else {
        			UsuarioDAO dao = new UsuarioDAO();
        			
        			try {
						boolean correcto = dao.validarUsuario(correo, contrasenia);
						
						if(correcto) {
							JOptionPane.showMessageDialog(null, "Bienvenido al sistema", "CORRECTO",JOptionPane.INFORMATION_MESSAGE);
							
							//dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						
						JOptionPane.showMessageDialog(null, "Error del sistema","ERROR",JOptionPane.ERROR_MESSAGE);
					}
        			
        		}
        	}
        });
        botonIS.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        botonIS.setBackground(new Color(166, 171, 252));
        botonIS.setBounds(147, 135, 119, 22);
        contentPane.add(botonIS);

        getRootPane().setDefaultButton(botonIS);

        InputContrasena = new JPasswordField();
        InputContrasena.setBackground(new Color(187, 190, 253));
        InputContrasena.setBounds(147, 79, 175, 20);
        contentPane.add(InputContrasena);
        
        JLabel lblNewLabel = new JLabel("Olvido su contraseña");
        lblNewLabel.setForeground(new Color(240, 89, 40));
        lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        lblNewLabel.setBounds(147, 168, 129, 14);
        contentPane.add(lblNewLabel);
    }

    // =========================
    // GETTERS PARA EL CONTROLADOR
    // =========================

    public String getCorreo() {
        return inputCorreo.getText();
    }

    public String getContrasena() {
        return new String(InputContrasena.getPassword());
    }

    public JButton getBotonLogin() {
        return botonIS;
    }
}