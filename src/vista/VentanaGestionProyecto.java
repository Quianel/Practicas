package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FocusTraversalPolicy;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

public class VentanaGestionProyecto extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField inputBuscar;
    private JTable tablaProyectos;
    private JButton botonLupa;
    private JButton botonProyecto;

    public VentanaGestionProyecto() {

        setBackground(new Color(53, 48, 105));
        setForeground(new Color(180, 180, 180));
        setLayout(null);

        // LABEL
        JTextPane BuscarTxt = new JTextPane();
        BuscarTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        BuscarTxt.setText("Buscar:");
        BuscarTxt.setEditable(false);
        BuscarTxt.setBackground(new Color(53, 48, 105));
        BuscarTxt.setForeground(new Color(240, 89, 68));
        BuscarTxt.setBounds(186, 104, 49, 20);
        BuscarTxt.setFocusable(false);
        add(BuscarTxt);

        // INPUT
        inputBuscar = new JTextField();
        inputBuscar.setBackground(new Color(211, 214, 254));
        inputBuscar.setBounds(231, 104, 226, 20);
        inputBuscar.setColumns(10);

        ColoresFoco.aplicarResaltadoFocus(inputBuscar);
        add(inputBuscar);

        inputBuscar.addActionListener(e -> botonLupa.doClick());

        // BOTÓN LUPA
        botonLupa = new JButton("");
        botonLupa.setBackground(new Color(187, 190, 253));
        botonLupa.setIcon(new ImageIcon("img/lupa.png"));
        botonLupa.setBounds(467, 104, 41, 20);

        ColoresFoco.aplicarResaltadoFocus(botonLupa);
        add(botonLupa);

        // BOTÓN CREAR
        botonProyecto = new JButton("Crear Proyecto");
        botonProyecto.setForeground(new Color(240, 89, 68));
        botonProyecto.setBackground(new Color(187, 190, 253));
        botonProyecto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        botonProyecto.setBounds(753, 104, 122, 22);

        ColoresFoco.aplicarResaltadoFocus(botonProyecto);

        botonProyecto.getInputMap(JButton.WHEN_FOCUSED)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "crearProyecto");

        botonProyecto.getActionMap().put("crearProyecto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonProyecto.doClick();
            }
        });

        add(botonProyecto);

        // TABLA
        tablaProyectos = new JTable() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer,
                                             int row,
                                             int column) {

                Component c = super.prepareRenderer(renderer, row, column);

                if (isRowSelected(row)) {

                    if (isFocusOwner()) {
                        c.setBackground(new Color(255, 165, 60));
                    } else {
                        c.setBackground(new Color(255, 210, 120));
                    }

                    c.setForeground(Color.BLACK);

                } else {
                    c.setBackground(new Color(187, 190, 253));
                    c.setForeground(new Color(240, 89, 68));
                }

                return c;
            }
        };

        tablaProyectos.setBackground(new Color(187, 190, 253));
        tablaProyectos.setForeground(new Color(240, 89, 68));
        tablaProyectos.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));

        tablaProyectos.setRowSelectionAllowed(true);
        tablaProyectos.setColumnSelectionAllowed(false);
        tablaProyectos.setCellSelectionEnabled(false);
        tablaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tablaProyectos.setFocusable(true);
        tablaProyectos.setFillsViewportHeight(true);

        ColoresFoco.aplicarResaltadoTabla(tablaProyectos);

        // TAB ABAJO
        tablaProyectos.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "selectNextRow");

        tablaProyectos.getActionMap().put("selectNextRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int fila = tablaProyectos.getSelectedRow();

                if (fila < tablaProyectos.getRowCount() - 1) {
                    tablaProyectos.setRowSelectionInterval(fila + 1, fila + 1);
                } else {
                    KeyboardFocusManager.getCurrentKeyboardFocusManager()
                            .focusNextComponent();
                }
            }
        });

        // SHIFT TAB
        tablaProyectos.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, KeyEvent.SHIFT_DOWN_MASK),
                        "selectPreviousRow");

        tablaProyectos.getActionMap().put("selectPreviousRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int fila = tablaProyectos.getSelectedRow();

                if (fila > 0) {
                    tablaProyectos.setRowSelectionInterval(fila - 1, fila - 1);
                } else {
                    KeyboardFocusManager.getCurrentKeyboardFocusManager()
                            .focusPreviousComponent();
                }
            }
        });

        // ENTER
        tablaProyectos.getInputMap(JTable.WHEN_FOCUSED)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "abrirProyecto");

        tablaProyectos.getActionMap().put("abrirProyecto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (java.awt.event.MouseListener ml : tablaProyectos.getMouseListeners()) {
                    ml.mouseClicked(
                            new java.awt.event.MouseEvent(
                                    tablaProyectos,
                                    java.awt.event.MouseEvent.MOUSE_CLICKED,
                                    System.currentTimeMillis(),
                                    0,
                                    0,
                                    0,
                                    2,
                                    false
                            )
                    );
                }
            }
        });

        tablaProyectos.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (tablaProyectos.getRowCount() > 0) {
                    tablaProyectos.setRowSelectionInterval(0, 0);
                }
                tablaProyectos.repaint();
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                tablaProyectos.clearSelection(); // <-- esto es lo que faltaba
                tablaProyectos.repaint();
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaProyectos);
        scrollPane.setBounds(186, 147, 689, 250);

        add(scrollPane);

        setFocusCycleRoot(true);

        List<Component> orden = Arrays.asList(
                inputBuscar,
                botonLupa,
                botonProyecto,
                tablaProyectos
        );

        setFocusTraversalPolicy(new FocusTraversalPolicy() {

            @Override
            public Component getComponentAfter(Container root, Component c) {
                int i = orden.indexOf(c);
                return orden.get((i + 1) % orden.size());
            }

            @Override
            public Component getComponentBefore(Container root, Component c) {
                int i = orden.indexOf(c);
                return orden.get((i - 1 + orden.size()) % orden.size());
            }

            @Override
            public Component getFirstComponent(Container root) {
                return inputBuscar;
            }

            @Override
            public Component getLastComponent(Container root) {
                return tablaProyectos;
            }

            @Override
            public Component getDefaultComponent(Container root) {
                return inputBuscar;
            }
        });

        inputBuscar.requestFocusInWindow();
    }

    public JTable getTablaProyectos() { return tablaProyectos; }
    public JTextField getInputBuscar() { return inputBuscar; }
    public String getInputBuscarValue() { return inputBuscar.getText(); }
    public void setInputBuscarValue(String t) { inputBuscar.setText(t); }
    public JButton getBotonLupa() { return botonLupa; }
    public JButton getBotonProyecto() { return botonProyecto; }
}