package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class VentanaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputCorreo;
    private JPasswordField InputContrasena;
    private JButton botonIS; // ← ahora es atributo

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
        setBounds(100, 100, 326, 245);
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
        CorreoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        CorreoTxt.setEditable(false);
        CorreoTxt.setBackground(new Color(228, 228, 228));
        CorreoTxt.setText("Correo (email):");
        CorreoTxt.setBounds(54, 48, 90, 20);
        contentPane.add(CorreoTxt);

        JTextPane ContrasenaTxt = new JTextPane();
        ContrasenaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        ContrasenaTxt.setEditable(false);
        ContrasenaTxt.setText("Contraseña:");
        ContrasenaTxt.setBackground(new Color(228, 228, 228));
        ContrasenaTxt.setBounds(77, 79, 67, 20);
        contentPane.add(ContrasenaTxt);

        JCheckBox RecordarTxt = new JCheckBox("Recordar Usuario");
        RecordarTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        RecordarTxt.setBackground(new Color(228, 228, 228));
        RecordarTxt.setBounds(114, 106, 129, 22);
        contentPane.add(RecordarTxt);

        botonIS = new JButton("Iniciar Sesión");
        botonIS.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        botonIS.setBackground(new Color(178, 178, 178));
        botonIS.setBounds(109, 135, 119, 22);
        contentPane.add(botonIS);

        InputContrasena = new JPasswordField();
        InputContrasena.setBounds(147, 79, 96, 20);
        contentPane.add(InputContrasena);
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