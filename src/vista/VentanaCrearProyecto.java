package vista;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import modelo.CrearProyectoDAO;
import modelo.Estado_proyecto;
import modelo.Proyecto;
import modelo.Tipo_proyecto;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCrearProyecto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField InputNombre;
	private JTextField InputCodigo;
	private JTextField textField;
	private JComboBox inputTipo;
	private JComboBox inputEstado;
	private JCheckBox esGenericoBox;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;

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
		
		inputTipo = new JComboBox();
		inputTipo.setBounds(133, 85, 143, 22);
		add(inputTipo);
		
		JTextPane EstadoTxt = new JTextPane();
		EstadoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		EstadoTxt.setText("Estado:");
		EstadoTxt.setEditable(false);
		EstadoTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		EstadoTxt.setBounds(29, 114, 48, 20);
		add(EstadoTxt);
		
		inputEstado = new JComboBox();
		inputEstado.setBounds(133, 112, 143, 22);
		add(inputEstado);
		
		JTextPane FechaInicioTxt = new JTextPane();
		FechaInicioTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		FechaInicioTxt.setText("Fecha Inicio:");
		FechaInicioTxt.setEditable(false);
		FechaInicioTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		FechaInicioTxt.setBounds(29, 139, 75, 20);
		add(FechaInicioTxt);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(133, 139, 96, 20);
		add(dateChooser);
		
		JTextPane FechaFinalTxt = new JTextPane();
		FechaFinalTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		FechaFinalTxt.setText("Fecha Final:");
		FechaFinalTxt.setEditable(false);
		FechaFinalTxt.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
		FechaFinalTxt.setBounds(29, 164, 75, 20);
		add(FechaFinalTxt);
		
		dateChooser_1 = new JDateChooser();
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
		
		esGenericoBox = new JCheckBox("Es Generico");
		esGenericoBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		esGenericoBox.setBackground(new Color(180, 180, 180));
		esGenericoBox.setBounds(385, 62, 98, 22);
		add(esGenericoBox);
		
		JButton GuardarBoton = new JButton("Guardar");//Logica del boton de guardado
		GuardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proyecto nuevoP = new Proyecto();//creo objeto proyecto para guardar en el lo que obtenga de lo que escriba en los inputs
				
				nuevoP.setNombre(InputNombre.getText());
				nuevoP.setCodigo_interno(InputCodigo.getText());
				nuevoP.setDescripcion(textField.getText());
				nuevoP.setEs_generico(esGenericoBox.isSelected());
				
				nuevoP.setTipoproyec((Tipo_proyecto) inputTipo.getSelectedItem());//necesito convertir al objeto lo que obtenga para guardarlo en proyecto
				nuevoP.setEstadoproyec((Estado_proyecto) inputEstado.getSelectedItem());
				
				if(dateChooser.getDate() != null) {
					nuevoP.setFecha_inicio(new java.sql.Date(dateChooser.getDate().getTime()).toLocalDate());//Unica manera de convertir lo que obtengo de fecha para la base de datos
				}
				
				if(dateChooser_1.getDate() != null) {
					nuevoP.setFecha_limite(new java.sql.Date(dateChooser_1.getDate().getTime()).toLocalDate());
				}
				
				CrearProyectoDAO dao = new CrearProyectoDAO();
				boolean exito = dao.insertarProyecto(nuevoP);//utilizo mi insertar proyecto en la base de datos para que ya se guarde
				
				if(exito) {
					JOptionPane.showMessageDialog(null, "Proyecto guardado correctamente","EXITO",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Error al guardar proyecto","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		GuardarBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		GuardarBoton.setBounds(420, 250, 75, 22);
		add(GuardarBoton);
		
		cargarCombosTipoyEstado();//cargo mis combobox
		
		CrearProyectoDAO dao = new CrearProyectoDAO();
		Proyecto p = dao.obtenerProyecto();//llamo a mi metodo de la base de datos
		
		mostrarCargaProyecto(p);//Le paso el objeto a mi metodo que rellenara los inputs

	}
	
	public void cargarCombosTipoyEstado() {
		CrearProyectoDAO cp = new CrearProyectoDAO();
		ArrayList<Tipo_proyecto> listaTipos = cp.cargarTipos();
		ArrayList<Estado_proyecto> listaEstado = cp.cargarEstado();
		
		if(listaTipos != null) {
			for(Tipo_proyecto t : listaTipos) {
				inputTipo.addItem(t);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el tipo de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}
		if(listaEstado != null) {
			for(Estado_proyecto e : listaEstado) {
				inputEstado.addItem(e);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el estado de proyecto", "ERROR DE CARGA",
					JOptionPane.ERROR_MESSAGE);
		}			
	}
	
	public void mostrarCargaProyecto(Proyecto p) {
		if(p != null) {
			InputNombre.setText(p.getNombre());
			InputCodigo.setText(p.getCodigo_interno());
			textField.setText(p.getDescripcion());
			esGenericoBox.setSelected(p.isEs_generico());
			
			if(p.getFecha_inicio() != null) {
				dateChooser.setDate(java.sql.Date.valueOf(p.getFecha_inicio()));
			}
			
			if(p.getFecha_limite() != null) {
				dateChooser_1.setDate(java.sql.Date.valueOf(p.getFecha_limite()));
			}
			
			inputTipo.setSelectedItem(p.getTipoproyec());
			inputEstado.setSelectedItem(p.getEstadoproyec());
			
		}
	}
}

