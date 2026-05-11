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
        setPreferredSize(new java.awt.Dimension(450, 300));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 416, 213);
        add(scrollPane);

        tbl = new JTable(modeloTabla);
        scrollPane.setViewportView(tbl);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CatalogoDeTareasDAO ventanaCatalogoDeTareasDAO = new CatalogoDeTareasDAO();
            	ventanaCatalogoDeTareasDAO.actualizarBaseDatos(modeloTabla);
            }
        });
        btnGuardar.setBounds(326, 243, 100, 20);
        add(btnGuardar);
        
        cmbTiposProyectos = new JComboBox();
        cmbTiposProyectos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String TipoProyecto = cmbTiposProyectos.getSelectedItem().toString();
                modeloTabla.setRowCount(0);
                cargarPorTipoProyecto(TipoProyecto);
            }
        });
        cmbTiposProyectos.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        cmbTiposProyectos.setBounds(125, 233, 150, 20);
        add(cmbTiposProyectos);
        
        JLabel lblTipoProyectos = new JLabel("Tipo de proyecto:");
        lblTipoProyectos.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
        lblTipoProyectos.setBounds(20, 237, 120, 12);
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