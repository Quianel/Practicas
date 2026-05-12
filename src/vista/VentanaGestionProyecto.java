package vista;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;

public class VentanaGestionProyecto extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField inputBuscar;
    private JTable tablaProyectos;
    private JButton botonLupa;
    private JButton botonProyecto;

    /**
     * Create the panel.
     */
    public VentanaGestionProyecto() {

        setBackground(new Color(53, 48, 105));
        setForeground(new Color(180, 180, 180));
        setLayout(null);

        JTextPane BuscarTxt = new JTextPane();
        BuscarTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        BuscarTxt.setText("Buscar:");
        BuscarTxt.setEditable(false);
        BuscarTxt.setBackground(new Color(53, 48, 105));
        BuscarTxt.setForeground(new Color(240, 89, 68));
        BuscarTxt.setBounds(186, 104, 49, 20);
        add(BuscarTxt);

        inputBuscar = new JTextField();
        inputBuscar.setBackground(new Color(211, 214, 254));
        inputBuscar.setBounds(231, 104, 226, 20);
        add(inputBuscar);
        inputBuscar.setColumns(10);

        botonLupa = new JButton("");
        botonLupa.setBackground(new Color(187, 190, 253));
        botonLupa.setIcon(new ImageIcon("img/lupa.png"));
        botonLupa.setBounds(467, 104, 41, 20);
        add(botonLupa);

        botonProyecto = new JButton("Crear Proyecto");
        botonProyecto.setForeground(new Color(240, 89, 68));
        botonProyecto.setBackground(new Color(187, 190, 253));
        botonProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        botonProyecto.setBounds(753, 104, 122, 22);
        add(botonProyecto);

        // =========================
        // TABLA
        // =========================
        tablaProyectos = new JTable() {
        	

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaProyectos.setBackground(new Color(187, 190, 253));
        tablaProyectos.setForeground(new Color(240, 89, 68));
        tablaProyectos.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        JScrollPane scrollPane = new JScrollPane(tablaProyectos);
        scrollPane.setBounds(186, 147, 689, 250);

        add(scrollPane);
    }

    // =========================
    // GETTERS
    // =========================
    
    public JTable getTablaProyectos() {
        return tablaProyectos;
    }

    public JTextField getInputBuscar() {
        return inputBuscar;
    }
    
    public String getInputBuscarValue() {
    	return inputBuscar.getText();
    }
    
    public void setInputBuscarValue(String inputBuscarText) {
    	inputBuscar.setText(inputBuscarText);
    }

    public JButton getBotonLupa() {
        return botonLupa;
    }

    public JButton getBotonProyecto() {
        return botonProyecto;
    }
}
