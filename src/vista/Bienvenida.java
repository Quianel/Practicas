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
		setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido a TIME ORDER");
		lblBienvenido.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 16));
		lblBienvenido.setBounds(117, 33, 307, 40);
		add(lblBienvenido);
		
		JLabel lblSeleccione = new JLabel("Seleccione una opción del menú");
		lblSeleccione.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 16));
		lblSeleccione.setBounds(105, 69, 277, 55);
		add(lblSeleccione);
		
		JLabel lblParaContin = new JLabel("para continuar");
		lblParaContin.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 16));
		lblParaContin.setBounds(168, 117, 117, 26);
		add(lblParaContin);
		
		JLabel lblReloj = new JLabel("");
		lblReloj.setBackground(new Color(200, 200, 200));
		ImageIcon imgreloj = new ImageIcon("img/relojj.png");
		
		Image imgescalada = imgreloj.getImage().getScaledInstance(125, 92, Image.SCALE_SMOOTH);
		lblReloj.setIcon(new ImageIcon(imgescalada));
		lblReloj.setBounds(167, 154, 131, 92);
		add(lblReloj);

	}
}
