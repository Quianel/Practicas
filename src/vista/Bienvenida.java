package vista;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class Bienvenida extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Bienvenida() {
		setBackground(new Color(53, 48, 105));
		setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido a TIME ORDER");
		lblBienvenido.setForeground(new Color(240, 89, 68));
		lblBienvenido.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		lblBienvenido.setBounds(392, 93, 277, 55);
		add(lblBienvenido);
		
		JLabel lblSeleccione = new JLabel("Seleccione una opción del menú");
		lblSeleccione.setForeground(new Color(240, 89, 68));
		lblSeleccione.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 16));
		lblSeleccione.setBounds(392, 143, 277, 55);
		add(lblSeleccione);
		
		JLabel lblParaContin = new JLabel("para continuar");
		lblParaContin.setForeground(new Color(240, 89, 68));
		lblParaContin.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 16));
		lblParaContin.setBounds(459, 209, 117, 26);
		add(lblParaContin);
		
		JLabel lblReloj = new JLabel("");
		lblReloj.setBackground(new Color(200, 200, 200));
		ImageIcon imgreloj = new ImageIcon("img/relojj.png");
		
		Image imgescalada = imgreloj.getImage().getScaledInstance(125, 92, Image.SCALE_SMOOTH);
		lblReloj.setIcon(new ImageIcon(imgescalada));
		lblReloj.setBounds(459, 252, 131, 92);
		add(lblReloj);

	}
}
