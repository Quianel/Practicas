package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Proyecto;

public class VentanaTareas extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private JTextArea textAreaAsignados;
    private JTextArea textAreaSinAsignar;
    private JComboBox<Proyecto> inputProyecto;

    public VentanaTareas() {

        setBackground(new Color(180, 180, 180));
        setLayout(null);

        JTextPane ProyectoTxt = new JTextPane();
        ProyectoTxt.setText("Proyecto:");
        ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        ProyectoTxt.setBackground(new Color(180, 180, 180));
        ProyectoTxt.setBounds(38, 34, 54, 20);
        add(ProyectoTxt);

        inputProyecto = new JComboBox<>();
        inputProyecto.setBounds(98, 34, 193, 22);
        add(inputProyecto);

        table = new JTable();

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(39, 119, 500, 170);
        add(scroll);

        JButton QuitarAsignarBoton = new JButton("Quitar");
        QuitarAsignarBoton.setBounds(707, 175, 63, 22);
        add(QuitarAsignarBoton);

        JButton QuitarSinTxt = new JButton("Quitar");
        QuitarSinTxt.setBounds(707, 343, 63, 22);
        add(QuitarSinTxt);

        textAreaAsignados = new JTextArea();
        textAreaAsignados.setEditable(false);

        JScrollPane scrollAsignados =
                new JScrollPane(textAreaAsignados);

        scrollAsignados.setBounds(577, 83, 193, 114);

        add(scrollAsignados);

        JTextPane txtpnTrabajadores = new JTextPane();
        txtpnTrabajadores.setText("Trabajadores asignados al Proyecto");
        txtpnTrabajadores.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        txtpnTrabajadores.setBounds(577, 66, 193, 20);
        add(txtpnTrabajadores);

        JTextPane txtpnTrabajadoresSinAsignar = new JTextPane();
        txtpnTrabajadoresSinAsignar.setText("Trabajadores sin asignar al proyecto");
        txtpnTrabajadoresSinAsignar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        txtpnTrabajadoresSinAsignar.setBounds(577, 231, 193, 20);
        add(txtpnTrabajadoresSinAsignar);

        textAreaSinAsignar = new JTextArea();
        textAreaSinAsignar.setEditable(false);

        JScrollPane scrollSinAsignar =
                new JScrollPane(textAreaSinAsignar);

        scrollSinAsignar.setBounds(577, 251, 193, 114);

        add(scrollSinAsignar);
    }

    public JTable getTable() {
        return table;
    }

    public JComboBox<Proyecto> getInputProyecto() {
        return inputProyecto;
    }
    public JTextArea getTextAreaAsignados() {
        return textAreaAsignados;
    }

    public JTextArea getTextAreaSinAsignar() {
        return textAreaSinAsignar;
    }
}
