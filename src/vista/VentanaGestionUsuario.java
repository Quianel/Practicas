package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaGestionUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel modelo;

	/**
	 * Create the panel.
	 */
	public VentanaGestionUsuario() {
		setBackground(new Color(180, 180, 180));
		setLayout(null);
		
		JButton CrearUsuarioBoton = new JButton("Crear Usuario");
		CrearUsuarioBoton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		CrearUsuarioBoton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		CrearUsuarioBoton.setBounds(10, 27, 116, 23);
		add(CrearUsuarioBoton);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		textField.setBounds(245, 29, 116, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(359, 27, 41, 23);
		add(btnNewButton);
		
		table = new JTable();
		table.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		table.setBounds(10, 71, 449, 193);
		add(table);
		cargarDatosTabla();

	}
	public void cargarDatosTabla() {
        
        modelo.setRowCount(0);

        String sql = "select tra.nombre, tra.correo, rl.nombre, prl.nombre, n.nombre, tra.activo"
        		+ "from trabajador tra, perfil_laboral prl, nivel_experiencia n, rol_sistema rl"
        		+ "where tra.id_rol=rl.id_rol"
        		+ "and tra.id_perfil=prl.id_perfil"
        		+ "and tra.id_nivel=n.id_nivel;";
        
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/time_order", "root", "");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getObject(1);
                fila[1] = rs.getObject(2);
                fila[2] = rs.getObject(3);
                fila[3] = rs.getObject(4);
                fila[4] = rs.getObject(5);
                fila[5] = rs.getObject(6);
                
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }
    }
}
