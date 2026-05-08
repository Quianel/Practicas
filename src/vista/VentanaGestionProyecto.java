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

        setBackground(new Color(205, 205, 205));
        setForeground(new Color(180, 180, 180));
        setLayout(null);

        JTextPane BuscarTxt = new JTextPane();
        BuscarTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        BuscarTxt.setText("Buscar:");
        BuscarTxt.setEditable(false);
        BuscarTxt.setBackground(new Color(205, 205, 205));
        BuscarTxt.setForeground(new Color(0, 0, 0));
        BuscarTxt.setBounds(10, 26, 49, 20);
        add(BuscarTxt);

        inputBuscar = new JTextField();
        inputBuscar.setBounds(55, 26, 96, 20);
        add(inputBuscar);
        inputBuscar.setColumns(10);

        botonLupa = new JButton("");
        botonLupa.setIcon(new ImageIcon("img/lupa.png"));
        botonLupa.setBounds(161, 26, 19, 20);
        add(botonLupa);

        botonProyecto = new JButton("Crear Proyecto");
        botonProyecto.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        botonProyecto.setBounds(305, 24, 122, 22);
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
        JScrollPane scrollPane = new JScrollPane(tablaProyectos);
        scrollPane.setBounds(10, 69, 650, 250);

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

    public JButton getBotonLupa() {
        return botonLupa;
    }

    public JButton getBotonProyecto() {
        return botonProyecto;
    }
}
