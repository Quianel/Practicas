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
	private JComboBox cmbProyecto;
	private JComboBox inputTipo;
	private JComboBox inputTareasPadre;
	private JComboBox inputEstadoTarea;

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
		
		inputTipo = new JComboBox();
		inputTipo.setBounds(137, 75, 143, 22);
		add(inputTipo);
		
		JButton GuardarBoton = new JButton("Guardar");
		GuardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Tarea_proyecto nuevaTarea = new Tarea_proyecto();
				nuevaTarea.setProyec((Proyecto)(cmbProyecto.getSelectedItem()));
				nuevaTarea.setCatalog((Catalogo_tareas)(inputTipo.getSelectedItem()));
				nuevaTarea.setId_tarea_padre(inputTareasPadre.getSelectedIndex());//duda aqui
				nuevaTarea.setEstadotar((Estado_tarea)(inputEstadoTarea.getSelectedItem()));
				nuevaTarea.setNombre_visible(textField_1.getText());
				
				CrearTareaDAO dao = new CrearTareaDAO();
				boolean exito;
				
				exito = dao.insertarTarea(nuevaTarea);
				cmbProyecto.setSelectedIndex(-1);
				inputTipo.setSelectedIndex(-1);
				inputTareasPadre.setSelectedIndex(-1);
				inputEstadoTarea.setSelectedIndex(-1);
				textField_1.setText("");
				
				  if(exito) {

			            JOptionPane.showMessageDialog(
			                    null,
			                    "Tarea guardada correctamente",
			                    "EXITO",
			                    JOptionPane.INFORMATION_MESSAGE);

			        } else {

			            JOptionPane.showMessageDialog(
			                    null,
			                    "Error al guardar tarea",
			                    "ERROR",
			                    JOptionPane.ERROR_MESSAGE);
			        }
				
				
				
			
				
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
		
		inputTareasPadre = new JComboBox();
		inputTareasPadre.setBounds(137, 108, 143, 22);
		add(inputTareasPadre);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		txtpnEstado.setEditable(false);
		txtpnEstado.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		txtpnEstado.setBounds(33, 139, 96, 20);
		add(txtpnEstado);
		
		inputEstadoTarea = new JComboBox();
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
}
