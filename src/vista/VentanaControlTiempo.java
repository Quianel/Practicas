package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.UIManager;

public class VentanaControlTiempo extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public VentanaControlTiempo() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane ProyectoTxt = new JTextPane();
		ProyectoTxt.setText("Proyecto:");
		ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ProyectoTxt.setEditable(false);
		ProyectoTxt.setBackground(new Color(180, 180, 180));
		ProyectoTxt.setBounds(24, 31, 54, 20);
		add(ProyectoTxt);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(85, 31, 148, 22);
		add(comboBox);
		
		JTextPane TareaTxt = new JTextPane();
		TareaTxt.setText("Tarea:");
		TareaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		TareaTxt.setEditable(false);
		TareaTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		TareaTxt.setBounds(24, 66, 54, 20);
		add(TareaTxt);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(85, 64, 148, 22);
		add(comboBox_1);

	}

}
