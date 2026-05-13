package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaRegistroManual extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtHorainicio;
	private JTextField txtHorafin;
	private JTextField txtComentario;

	/**
	 * Create the panel.
	 */
	public VentanaRegistroManual() {
		setBackground(new Color(53, 48, 105));
		setLayout(null);
		
		JLabel lblProyecto = new JLabel("Proyecto:");
		lblProyecto.setFont(new Font("Microsoft Tai Le", Font.BOLD, 12));
		lblProyecto.setBackground(new Color(53, 48, 105));
		lblProyecto.setForeground(new Color(240, 89, 68));
		lblProyecto.setBounds(33, 32, 62, 23);
		add(lblProyecto);
		
		JLabel lblTarea = new JLabel("Tarea:");
		lblTarea.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblTarea.setForeground(new Color(240, 89, 68));
		lblTarea.setBackground(new Color(53, 48, 105));
		lblTarea.setBounds(33, 66, 50, 23);
		add(lblTarea);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBackground(new Color(53, 48, 105));
		lblFecha.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblFecha.setForeground(new Color(240, 89, 68));
		lblFecha.setBounds(33, 98, 50, 23);
		add(lblFecha);
		
		JLabel lblHoraInicio = new JLabel("Hora inicio:");
		lblHoraInicio.setForeground(new Color(240, 89, 68));
		lblHoraInicio.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblHoraInicio.setBackground(new Color(53, 48, 105));
		lblHoraInicio.setBounds(33, 132, 71, 23);
		add(lblHoraInicio);
		
		JLabel lblHoraFin = new JLabel("Hora fin:");
		lblHoraFin.setForeground(new Color(240, 89, 68));
		lblHoraFin.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblHoraFin.setBackground(new Color(53, 48, 105));
		lblHoraFin.setBounds(33, 168, 62, 23);
		add(lblHoraFin);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setForeground(new Color(240, 89, 68));
		lblComentario.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblComentario.setBackground(new Color(53, 48, 105));
		lblComentario.setBounds(33, 202, 84, 23);
		add(lblComentario);
		
		JComboBox cmbProyecto = new JComboBox();
		cmbProyecto.setBounds(117, 32, 239, 23);
		add(cmbProyecto);
		
		JComboBox cmbTarea = new JComboBox();
		cmbTarea.setBounds(117, 66, 239, 23);
		add(cmbTarea);
		
		JDateChooser dteFecha = new JDateChooser();
		dteFecha.setBounds(117, 100, 157, 23);
		add(dteFecha);
		
		txtHorainicio = new JTextField();
		txtHorainicio.setBounds(117, 133, 135, 20);
		add(txtHorainicio);
		txtHorainicio.setColumns(10);
		
		txtHorafin = new JTextField();
		txtHorafin.setColumns(10);
		txtHorafin.setBounds(117, 167, 135, 20);
		add(txtHorafin);
		
		txtComentario = new JTextField();
		txtComentario.setColumns(10);
		txtComentario.setBounds(117, 203, 219, 86);
		add(txtComentario);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(346, 235, 94, 28);
		add(btnGuardar);

	}
}
