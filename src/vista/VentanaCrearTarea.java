package vista;

import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import modelo.Proyecto;
import modelo.Tarea_proyecto;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCrearTarea extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private JComboBox cmbProyecto;

	/**
	 * Create the panel.
	 */
	public VentanaCrearTarea() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JTextPane txtpnProyecto = new JTextPane();
		txtpnProyecto.setText("Proyecto:");
		txtpnProyecto.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnProyecto.setEditable(false);
		txtpnProyecto.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnProyecto.setBounds(33, 46, 96, 20);
		add(txtpnProyecto);
		
		JTextPane txtpnTipoDeTarea = new JTextPane();
		txtpnTipoDeTarea.setText("Tipo de tarea:");
		txtpnTipoDeTarea.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnTipoDeTarea.setEditable(false);
		txtpnTipoDeTarea.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnTipoDeTarea.setBounds(33, 77, 96, 20);
		add(txtpnTipoDeTarea);
		
		JComboBox inputTipo = new JComboBox();
		inputTipo.setBounds(137, 75, 143, 22);
		add(inputTipo);
		
		JButton GuardarBoton = new JButton("Guardar");
		GuardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Tarea_proyecto nuevaTarea = new Tarea_proyecto();
				nuevaTarea.setProyec((Proyecto)(cmbProyecto.getSelectedItem()));
			}
		});
		GuardarBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		GuardarBoton.setBounds(170, 243, 75, 22);
		add(GuardarBoton);
		
		JTextPane txtpnTareaPadre = new JTextPane();
		txtpnTareaPadre.setText("Tarea padre:");
		txtpnTareaPadre.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnTareaPadre.setEditable(false);
		txtpnTareaPadre.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnTareaPadre.setBounds(33, 108, 96, 20);
		add(txtpnTareaPadre);
		
		JComboBox inputTareasPadre = new JComboBox();
		inputTareasPadre.setBounds(137, 108, 143, 22);
		add(inputTareasPadre);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnEstado.setEditable(false);
		txtpnEstado.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnEstado.setBounds(33, 139, 96, 20);
		add(txtpnEstado);
		
		JComboBox inputEstadoTarea = new JComboBox();
		inputEstadoTarea.setBounds(137, 141, 143, 22);
		add(inputEstadoTarea);
		
		JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setText("Nombre:");
		txtpnNombre.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnNombre.setEditable(false);
		txtpnNombre.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnNombre.setBounds(33, 170, 96, 20);
		add(txtpnNombre);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(137, 174, 143, 20);
		add(textField_1);
		
		cmbProyecto = new JComboBox();
		cmbProyecto.setBounds(137, 44, 143, 22);
		add(cmbProyecto);
		
	}
}
