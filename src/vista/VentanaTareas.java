package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Proyecto;

public class VentanaTareas extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
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

        textField = new JTextField();
        textField.setBounds(577, 83, 193, 114);
        add(textField);

        JTextPane txtpnTrabajadores = new JTextPane();
        txtpnTrabajadores.setText("Trabajadores Asignados al Proyecto");
        txtpnTrabajadores.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        txtpnTrabajadores.setBounds(577, 66, 193, 20);
        add(txtpnTrabajadores);

        JTextPane txtpnTrabajadoresSinAsignar = new JTextPane();
        txtpnTrabajadoresSinAsignar.setText("Trabajadores sin asignar Proyectos");
        txtpnTrabajadoresSinAsignar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        txtpnTrabajadoresSinAsignar.setBounds(577, 231, 193, 20);
        add(txtpnTrabajadoresSinAsignar);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(577, 251, 193, 114);
        add(textField_1);
    }

    public JTable getTable() {
        return table;
    }

    public JComboBox<Proyecto> getInputProyecto() {
        return inputProyecto;
    }
}
