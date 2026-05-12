package vista;

import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import modelo.Catalogo_tareas;
import modelo.CrearProyectoDAO;
import modelo.CrearTareaDAO;
import modelo.Estado_proyecto;
import modelo.Estado_tarea;
import modelo.Proyecto;
import modelo.Tarea_proyecto;
import modelo.Tipo_proyecto;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaCrearTarea extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private JComboBox<Proyecto> cmbProyecto;
	private JComboBox<Catalogo_tareas> inputTipo;
	private JComboBox<Tarea_proyecto> inputTareasPadre;
	private JComboBox<Estado_tarea> inputEstadoTarea;
	private JButton GuardarBoton;

	/**
	 * Create the panel.
	 */
	public VentanaCrearTarea() {
		setBackground(new Color(53, 48, 105));
		setLayout(null);
		
		JTextPane txtpnProyecto = new JTextPane();
		txtpnProyecto.setForeground(new Color(240, 89, 40));
		txtpnProyecto.setText("Proyecto:");
		txtpnProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		txtpnProyecto.setEditable(false);
		txtpnProyecto.setBackground(new Color(53, 48, 105));
		txtpnProyecto.setBounds(33, 46, 96, 20);
		add(txtpnProyecto);
		
		JTextPane txtpnTipoDeTarea = new JTextPane();
		txtpnTipoDeTarea.setForeground(new Color(240, 89, 40));
		txtpnTipoDeTarea.setText("Tipo de tarea:");
		txtpnTipoDeTarea.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		txtpnTipoDeTarea.setEditable(false);
		txtpnTipoDeTarea.setBackground(new Color(53, 48, 105));
		txtpnTipoDeTarea.setBounds(33, 77, 96, 20);
		add(txtpnTipoDeTarea);
		
		inputTipo = new JComboBox();
		inputTipo.setForeground(new Color(240, 89, 40));
		inputTipo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputTipo.setBackground(new Color(187, 190, 253));
		inputTipo.setBounds(137, 75, 143, 22);
		add(inputTipo);
		
		GuardarBoton = new JButton("Guardar");
		GuardarBoton.setForeground(new Color(240, 89, 40));
		GuardarBoton.setBackground(new Color(187, 190, 253));
		GuardarBoton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		GuardarBoton.setBounds(170, 243, 90, 22);
		add(GuardarBoton);
		
		JTextPane txtpnTareaPadre = new JTextPane();
		txtpnTareaPadre.setForeground(new Color(240, 89, 40));
		txtpnTareaPadre.setText("Tarea padre:");
		txtpnTareaPadre.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		txtpnTareaPadre.setEditable(false);
		txtpnTareaPadre.setBackground(new Color(53, 48, 105));
		txtpnTareaPadre.setBounds(33, 108, 96, 20);
		add(txtpnTareaPadre);
		
		inputTareasPadre = new JComboBox();
		inputTareasPadre.setForeground(new Color(240, 89, 40));
		inputTareasPadre.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputTareasPadre.setBackground(new Color(187, 190, 253));
		inputTareasPadre.setBounds(137, 108, 143, 22);
		add(inputTareasPadre);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setForeground(new Color(240, 89, 40));
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		txtpnEstado.setEditable(false);
		txtpnEstado.setBackground(new Color(53, 48, 105));
		txtpnEstado.setBounds(33, 139, 96, 20);
		add(txtpnEstado);
		
		inputEstadoTarea = new JComboBox();
		inputEstadoTarea.setForeground(new Color(240, 89, 40));
		inputEstadoTarea.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		inputEstadoTarea.setBackground(new Color(187, 190, 253));
		inputEstadoTarea.setBounds(137, 141, 143, 22);
		add(inputEstadoTarea);
		
		JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setForeground(new Color(240, 89, 40));
		txtpnNombre.setText("Nombre:");
		txtpnNombre.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		txtpnNombre.setEditable(false);
		txtpnNombre.setBackground(new Color(53, 48, 105));
		txtpnNombre.setBounds(33, 170, 96, 20);
		add(txtpnNombre);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(187, 190, 253));
		textField_1.setColumns(10);
		textField_1.setBounds(137, 174, 143, 20);
		add(textField_1);
		
		cmbProyecto = new JComboBox();
		cmbProyecto.setForeground(new Color(240, 89, 40));
		cmbProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		cmbProyecto.setBackground(new Color(187, 190, 253));
		cmbProyecto.setBounds(137, 44, 143, 22);
		add(cmbProyecto);
		
	}
	
	/*public void cargarCombos() {
		CrearTareaDAO ct = new CrearTareaDAO();
		ArrayList<Proyecto> listaProyectos = ct.cargarProyectos();
		ArrayList<Catalogo_tareas> listatipoTarea = ct.cargarTipoTarea();
		ArrayList<Estado_tarea> listaEstado = ct.cargarEstadoTar();
		ArrayList<Tarea_proyecto> listaTareaproy= ct.cargarTareaPadre();
		
		if(listaProyectos != null) {
			for(Proyecto p : listaProyectos) {
				cmbProyecto.addItem(p);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el tipo de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}
		if(listatipoTarea != null) {
			for(Catalogo_tareas t : listatipoTarea) {
				inputTipo.addItem(t);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el estado de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}	
		
		if(listaEstado != null) {
			for(Estado_tarea e : listaEstado) {
				cmbProyecto.addItem(e);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el tipo de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}
		
		if(listaTareaproy != null) {
			for(Tarea_proyecto tp : listaTareaproy) {
				cmbProyecto.addItem(tp);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el tipo de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}
	}*/
	
	public JComboBox<Proyecto> getCmbProyectos(){
		return cmbProyecto;
	}

	public JComboBox<Catalogo_tareas> getInputTipo(){
		return inputTipo;
	}
	
	public JComboBox<Tarea_proyecto> getInputTareaPadre(){
		return inputTareasPadre;
	}
	
	public JComboBox<Estado_tarea> getInputEstado(){
		return inputEstadoTarea;
	}
	
	public JButton getBtnGuardar() {
	    return GuardarBoton;
	}

	public JTextField getTxtNombre() {
	    return textField_1;
	}
}
