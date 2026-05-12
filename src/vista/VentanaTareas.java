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

        setBackground(new Color(53, 48, 105));
        setLayout(null);

        JTextPane ProyectoTxt = new JTextPane();
        ProyectoTxt.setForeground(new Color(240, 89, 68));
        ProyectoTxt.setText("Proyecto:");
        ProyectoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        ProyectoTxt.setBackground(new Color(53, 48, 105));
        ProyectoTxt.setBounds(38, 34, 54, 20);
        add(ProyectoTxt);

        inputProyecto = new JComboBox<>();
        inputProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        inputProyecto.setForeground(new Color(240, 89, 68));
        inputProyecto.setBackground(new Color(187, 190, 253));
        inputProyecto.setBounds(98, 34, 265, 22);
        add(inputProyecto);

        table = new JTable();
        table.setBackground(new Color(187, 190, 253));
        table.setForeground(new Color(240, 89, 68));
        table.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(39, 119, 574, 239);
        add(scroll);

        btnQuitarAsignado = new JButton("Quitar");
        btnQuitarAsignado.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        btnQuitarAsignado.setForeground(new Color(240, 89, 68));
        btnQuitarAsignado.setBackground(new Color(187, 190, 253));
        btnQuitarAsignado.setBounds(873, 196, 79, 22);
        add(btnQuitarAsignado);

        btnQuitarSinAsignar = new JButton("Quitar");
        btnQuitarSinAsignar.setForeground(new Color(240, 89, 68));
        btnQuitarSinAsignar.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        btnQuitarSinAsignar.setBackground(new Color(187, 190, 253));
        btnQuitarSinAsignar.setBounds(873, 429, 79, 22);
        add(btnQuitarSinAsignar);

        JTextPane txtpnTrabajadores = new JTextPane();
        txtpnTrabajadores.setForeground(new Color(240, 89, 68));
        txtpnTrabajadores.setBackground(new Color(187, 190, 253));
        txtpnTrabajadores.setText("Trabajadores Asignados al Proyecto");
        txtpnTrabajadores.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        txtpnTrabajadores.setBounds(701, 63, 251, 20);
        add(txtpnTrabajadores);

        listaAsignados = new JList<>();
        listaAsignados.setForeground(new Color(240, 89, 68));
        listaAsignados.setBackground(new Color(187, 190, 253));

        JScrollPane scrollAsignados =
                new JScrollPane(listaAsignados);

        scrollAsignados.setBounds(701, 82, 251, 114);

        add(scrollAsignados);

        JTextPane txtpnTrabajadoresSinAsignar = new JTextPane();
        txtpnTrabajadoresSinAsignar.setForeground(new Color(240, 89, 68));
        txtpnTrabajadoresSinAsignar.setBackground(new Color(187, 190, 253));
        txtpnTrabajadoresSinAsignar.setText("Trabajadores sin asignar tareas");
        txtpnTrabajadoresSinAsignar.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        txtpnTrabajadoresSinAsignar.setBounds(701, 296, 251, 20);
        add(txtpnTrabajadoresSinAsignar);

        listaSinAsignar = new JList<>();
        listaSinAsignar.setForeground(new Color(240, 89, 68));
        listaSinAsignar.setBackground(new Color(187, 190, 253));
        listaSinAsignar.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));

        JScrollPane scrollSinAsignar =
                new JScrollPane(listaSinAsignar);

        scrollSinAsignar.setBounds(701, 316, 251, 114);

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