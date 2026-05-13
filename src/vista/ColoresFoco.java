package vista;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;

public class ColoresFoco {

    private static final Color COLOR_FOCUS = new Color(255, 170, 70);

    public static void aplicarResaltadoFocus(JComponent componente) {

        Border bordeOriginal = componente.getBorder();

        componente.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                componente.setBorder(
                        BorderFactory.createLineBorder(COLOR_FOCUS, 2)
                );
            }

            @Override
            public void focusLost(FocusEvent e) {

                componente.setBorder(bordeOriginal);
            }
        });
    }

    public static void aplicarResaltadoTabla(JTable tabla) {

        tabla.setSelectionForeground(Color.BLACK);

        tabla.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                tabla.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                tabla.repaint();
            }
        });

        tabla.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {

            @Override
            public java.awt.Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                setOpaque(true);

                // SOLO pintamos selección si la tabla tiene foco
                if (isSelected && table.isFocusOwner()) {
                    c.setBackground(new Color(255, 165, 60));
                    c.setForeground(Color.BLACK);
                } else {
                    // TODO lo demás se pinta como normal SIEMPRE
                    c.setBackground(new Color(187, 190, 253));
                    c.setForeground(new Color(240, 89, 68));
                }

                return c;
            }
        });
    }
}