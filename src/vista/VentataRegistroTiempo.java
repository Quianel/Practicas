package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentataRegistroTiempo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField InputHoraInicio;
	private JTextField InputHoraFinal;
	private JTextField InputComentario;

	/**
	 * Create the panel.
	 */
	public VentataRegistroTiempo() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane ProyectoTxt = new JTextPane();
		ProyectoTxt.setText("Proyecto:");
		ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ProyectoTxt.setEditable(false);
		ProyectoTxt.setBackground(new Color(180, 180, 180));
		ProyectoTxt.setBounds(32, 40, 54, 20);
		add(ProyectoTxt);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(115, 38, 115, 22);
		add(comboBox);
		
		JTextPane TareaTxt = new JTextPane();
		TareaTxt.setText("Tarea:");
		TareaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		TareaTxt.setEditable(false);
		TareaTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		TareaTxt.setBounds(32, 76, 54, 20);
		add(TareaTxt);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(115, 76, 115, 22);
		add(comboBox_1);
		
		JTextPane FechaTxt = new JTextPane();
		FechaTxt.setText("Fecha:");
		FechaTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		FechaTxt.setEditable(false);
		FechaTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		FechaTxt.setBounds(32, 113, 54, 20);
		add(FechaTxt);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(115, 113, 115, 20);
		add(dateChooser);
		
		JTextPane HoraInicioTxt = new JTextPane();
		HoraInicioTxt.setText("Hora Inicio:");
		HoraInicioTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		HoraInicioTxt.setEditable(false);
		HoraInicioTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		HoraInicioTxt.setBounds(32, 144, 71, 20);
		add(HoraInicioTxt);
		
		InputHoraInicio = new JTextField();
		InputHoraInicio.setBounds(115, 144, 115, 20);
		add(InputHoraInicio);
		InputHoraInicio.setColumns(10);
		
		JTextPane txtpnHoraFinal = new JTextPane();
		txtpnHoraFinal.setText("Hora Final:");
		txtpnHoraFinal.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnHoraFinal.setEditable(false);
		txtpnHoraFinal.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnHoraFinal.setBounds(32, 175, 71, 20);
		add(txtpnHoraFinal);
		
		InputHoraFinal = new JTextField();
		InputHoraFinal.setColumns(10);
		InputHoraFinal.setBounds(115, 175, 115, 20);
		add(InputHoraFinal);
		
		JTextPane ComentarioTxt = new JTextPane();
		ComentarioTxt.setText("Comentario:");
		ComentarioTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ComentarioTxt.setEditable(false);
		ComentarioTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		ComentarioTxt.setBounds(32, 213, 71, 20);
		add(ComentarioTxt);
		
		InputComentario = new JTextField();
		InputComentario.setColumns(10);
		InputComentario.setBounds(115, 213, 173, 76);
		add(InputComentario);
		
		JButton GuardarBoton = new JButton("Guardar");
		GuardarBoton.setBounds(329, 267, 88, 22);
		add(GuardarBoton);

	}
}
