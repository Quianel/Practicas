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

public class VentanaCrearProyecto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField InputNombre;
	private JTextField InputCodigo;

	/**
	 * Create the panel.
	 */
	public VentanaCrearProyecto() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane NombreTxt = new JTextPane();
		NombreTxt.setBackground(new Color(180, 180, 180));
		NombreTxt.setEditable(false);
		NombreTxt.setText("Nombre:");
		NombreTxt.setBounds(29, 37, 48, 20);
		add(NombreTxt);
		
		InputNombre = new JTextField();
		InputNombre.setBounds(133, 37, 96, 20);
		add(InputNombre);
		InputNombre.setColumns(10);
		
		JTextPane CodigoTxt = new JTextPane();
		CodigoTxt.setText("Codigo Interno:");
		CodigoTxt.setEditable(false);
		CodigoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		CodigoTxt.setBounds(29, 62, 86, 20);
		add(CodigoTxt);
		
		InputCodigo = new JTextField();
		InputCodigo.setBounds(133, 61, 96, 20);
		add(InputCodigo);
		InputCodigo.setColumns(10);
		
		JTextPane TipoTxt = new JTextPane();
		TipoTxt.setText("Tipo de Proyecto:");
		TipoTxt.setEditable(false);
		TipoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		TipoTxt.setBounds(29, 87, 96, 20);
		add(TipoTxt);
		
		JComboBox InputTipo = new JComboBox();
		InputTipo.setBounds(133, 85, 96, 22);
		add(InputTipo);
		
		JTextPane EstadoTxt = new JTextPane();
		EstadoTxt.setText("Estado:");
		EstadoTxt.setEditable(false);
		EstadoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		EstadoTxt.setBounds(29, 114, 48, 20);
		add(EstadoTxt);
		
		JComboBox InputEstado = new JComboBox();
		InputEstado.setBounds(133, 112, 96, 22);
		add(InputEstado);
		
		JTextPane FechaInicioTxt = new JTextPane();
		FechaInicioTxt.setText("Fecha Inicio:");
		FechaInicioTxt.setEditable(false);
		FechaInicioTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		FechaInicioTxt.setBounds(29, 139, 75, 20);
		add(FechaInicioTxt);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(133, 139, 96, 20);
		add(dateChooser);

	}
}

