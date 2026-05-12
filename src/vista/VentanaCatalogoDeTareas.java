package vista;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.CatalogoDeTareasDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class VentanaCatalogoDeTareas extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tbl;

    DefaultTableModel modeloTabla = new DefaultTableModel() {	
    	@Override
    	public Class getColumnClass(int columnIndex) {
    	    if (columnIndex == 2 || columnIndex == 4) {
    	        return Boolean.class;
    	    }
    	    else{
    	    	return String.class;
    	    }
    	}
    	
    	public boolean isCellEditable(int fila, int columna) {
    		if(columna == 2 || columna == 4) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    };

	private JComboBox<Object> cmbTiposProyectos;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	VentanaCatalogoDeTareas frame = new VentanaCatalogoDeTareas();
                frame.setVisible(true);
                frame.CargaDeComboBoxTiposProyectos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaCatalogoDeTareas() {
    	setBackground(new Color(53, 48, 105));
        setPreferredSize(new Dimension(922, 504));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(328, 124, 508, 213);
        add(scrollPane);

        tbl = new JTable(modeloTabla);
        tbl.setBackground(new Color(187, 190, 253));
        tbl.setForeground(new Color(240, 89, 68));
        tbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        scrollPane.setViewportView(tbl);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setForeground(new Color(240, 89, 68));
        btnGuardar.setBackground(new Color(187, 190, 253));
        btnGuardar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CatalogoDeTareasDAO ventanaCatalogoDeTareasDAO = new CatalogoDeTareasDAO();
            	ventanaCatalogoDeTareasDAO.actualizarBaseDatos(modeloTabla);
            }
        });
        btnGuardar.setBounds(736, 364, 100, 20);
        add(btnGuardar);
        
        cmbTiposProyectos = new JComboBox();
        cmbTiposProyectos.setForeground(new Color(240, 89, 68));
        cmbTiposProyectos.setBackground(new Color(187, 190, 253));
        cmbTiposProyectos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String TipoProyecto = cmbTiposProyectos.getSelectedItem().toString();
                modeloTabla.setRowCount(0);
                cargarPorTipoProyecto(TipoProyecto);
            }
        });
        cmbTiposProyectos.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        cmbTiposProyectos.setBounds(436, 364, 210, 20);
        add(cmbTiposProyectos);
        
        JLabel lblTipoProyectos = new JLabel("Tipo de proyecto:");
        lblTipoProyectos.setForeground(new Color(240, 89, 68));
        lblTipoProyectos.setBackground(new Color(53, 48, 105));
        lblTipoProyectos.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
        lblTipoProyectos.setBounds(330, 368, 120, 12);
        add(lblTipoProyectos);

        modeloTabla.setColumnIdentifiers(new Object[] {
            "Codigo","Nombre de tarea", "Solo senior", "Nombre"
        });

        cargarDatos();
        CargaDeComboBoxTiposProyectos();
    }

	private void cargarPorTipoProyecto(String tipoProyecto) {
        CatalogoDeTareasDAO ventanaCatalogoDeTareasDAO = new CatalogoDeTareasDAO();
        ventanaCatalogoDeTareasDAO.cargarTipoProyecto(tipoProyecto, modeloTabla);
	}
	
	private void cargarDatos() {
		CatalogoDeTareasDAO ventanaCatalogoDeTareasDAO = new CatalogoDeTareasDAO();
		ventanaCatalogoDeTareasDAO.cargaDatos(modeloTabla);
    }
	
	private void CargaDeComboBoxTiposProyectos() {
		CatalogoDeTareasDAO ventanaCatalogoDeTareasDAO = new CatalogoDeTareasDAO();
		ventanaCatalogoDeTareasDAO.Combobox(cmbTiposProyectos);
	}
}