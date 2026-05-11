package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import modelo.Proyecto;
import modelo.Trabajador;

public class VentanaTareas extends JPanel {

	private static final long serialVersionUID = 1L;

    private JTable table;

    private JComboBox<Proyecto> inputProyecto;

    private JList<Trabajador> listaAsignados;
    private JList<Trabajador> listaSinAsignar;

    private JButton btnQuitarAsignado;
    private JButton btnQuitarSinAsignar;

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

        btnQuitarAsignado = new JButton("Quitar");
        btnQuitarAsignado.setBounds(707, 175, 63, 22);
        add(btnQuitarAsignado);

        btnQuitarSinAsignar = new JButton("Quitar");
        btnQuitarSinAsignar.setBounds(707, 343, 63, 22);
        add(btnQuitarSinAsignar);

        JTextPane txtpnTrabajadores = new JTextPane();
        txtpnTrabajadores.setText("Trabajadores Asignados al Proyecto");
        txtpnTrabajadores.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        txtpnTrabajadores.setBounds(577, 66, 193, 20);
        add(txtpnTrabajadores);

        listaAsignados = new JList<>();

        JScrollPane scrollAsignados =
                new JScrollPane(listaAsignados);

        scrollAsignados.setBounds(577, 83, 193, 114);

        add(scrollAsignados);

        JTextPane txtpnTrabajadoresSinAsignar = new JTextPane();
        txtpnTrabajadoresSinAsignar.setText("Trabajadores sin asignar tareas");
        txtpnTrabajadoresSinAsignar.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
        txtpnTrabajadoresSinAsignar.setBounds(577, 231, 193, 20);
        add(txtpnTrabajadoresSinAsignar);

        listaSinAsignar = new JList<>();

        JScrollPane scrollSinAsignar =
                new JScrollPane(listaSinAsignar);

        scrollSinAsignar.setBounds(577, 251, 193, 114);

        add(scrollSinAsignar);
    }

    public JTable getTable() {
        return table;
    }

    public JComboBox<Proyecto> getInputProyecto() {
        return inputProyecto;
    }

    public JList<Trabajador> getListaAsignados() {
        return listaAsignados;
    }

    public JList<Trabajador> getListaSinAsignar() {
        return listaSinAsignar;
    }

    public JButton getBtnQuitarAsignado() {
        return btnQuitarAsignado;
    }

    public JButton getBtnQuitarSinAsignar() {
        return btnQuitarSinAsignar;
    }
}