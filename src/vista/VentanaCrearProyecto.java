package vista;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;

public class VentanaCrearProyecto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField InputNombre;
	private JTextField InputCodigo;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public VentanaCrearProyecto() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane NombreTxt = new JTextPane();
		NombreTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		NombreTxt.setBackground(new Color(180, 180, 180));
		NombreTxt.setEditable(false);
		NombreTxt.setText("Nombre:");
		NombreTxt.setBounds(29, 37, 48, 20);
		add(NombreTxt);
		
		InputNombre = new JTextField();
		InputNombre.setBounds(133, 37, 143, 20);
		add(InputNombre);
		InputNombre.setColumns(10);
		
		JTextPane CodigoTxt = new JTextPane();
		CodigoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		CodigoTxt.setText("Codigo Interno:");
		CodigoTxt.setEditable(false);
		CodigoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		CodigoTxt.setBounds(29, 62, 94, 20);
		add(CodigoTxt);
		
		InputCodigo = new JTextField();
		InputCodigo.setBounds(133, 61, 143, 20);
		add(InputCodigo);
		InputCodigo.setColumns(10);
		
		JTextPane TipoTxt = new JTextPane();
		TipoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		TipoTxt.setText("Tipo de Proyecto:");
		TipoTxt.setEditable(false);
		TipoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		TipoTxt.setBounds(29, 87, 96, 20);
		add(TipoTxt);
		
		JComboBox InputTipo = new JComboBox();
		InputTipo.setBounds(133, 85, 143, 22);
		add(InputTipo);
		
		JTextPane EstadoTxt = new JTextPane();
		EstadoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		EstadoTxt.setText("Estado:");
		EstadoTxt.setEditable(false);
		EstadoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		EstadoTxt.setBounds(29, 114, 48, 20);
		add(EstadoTxt);
		
		JComboBox InputEstado = new JComboBox();
		InputEstado.setBounds(133, 112, 143, 22);
		add(InputEstado);
		
		JTextPane FechaInicioTxt = new JTextPane();
		FechaInicioTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		FechaInicioTxt.setText("Fecha Inicio:");
		FechaInicioTxt.setEditable(false);
		FechaInicioTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		FechaInicioTxt.setBounds(29, 139, 75, 20);
		add(FechaInicioTxt);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(133, 139, 96, 20);
		add(dateChooser);
		
		JTextPane FechaFinalTxt = new JTextPane();
		FechaFinalTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		FechaFinalTxt.setText("Fecha Final:");
		FechaFinalTxt.setEditable(false);
		FechaFinalTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		FechaFinalTxt.setBounds(29, 164, 75, 20);
		add(FechaFinalTxt);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(133, 164, 96, 20);
		add(dateChooser_1);
		
		JTextPane DescripcionTxt = new JTextPane();
		DescripcionTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		DescripcionTxt.setText("Descripcion:");
		DescripcionTxt.setEditable(false);
		DescripcionTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		DescripcionTxt.setBounds(29, 199, 75, 20);
		add(DescripcionTxt);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(133, 199, 184, 76);
		add(textField);
		
		JCheckBox EsGenericoBox = new JCheckBox("Es Generico");
		EsGenericoBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		EsGenericoBox.setBackground(new Color(180, 180, 180));
		EsGenericoBox.setBounds(385, 62, 98, 22);
		add(EsGenericoBox);
		
		JButton GuardarBoton = new JButton("Guardar");
		GuardarBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		GuardarBoton.setBounds(420, 250, 75, 22);
		add(GuardarBoton);

	}
}

