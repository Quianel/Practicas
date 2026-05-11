package controlador;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import modelo.CrearUsuarioDAO;
import modelo.GestionUsuarioDAO;
import modelo.Proyecto;
import modelo.Trabajador;
import vista.VentanaCrearUsuario;
import vista.VentanaGestionUsuario;

public class GestionUsuarioController {
	
	private VentanaGestionUsuario vista;
	private GestionUsuarioDAO modelo; 
	public GestionUsuarioController(VentanaGestionUsuario vista) {
		

        this.vista = vista;
        this.modelo = new GestionUsuarioDAO();
        
        cargarTabla();

        // =========================
        // DOBLE CLICK PARA EDITAR
        // =========================

        this.vista.getTabla().addMouseListener(
            new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {

                    if (e.getClickCount() == 2) {
                        editarUsuario();
                    }
                }
            }
        );
     // =========================
        // BOTON BUSCAR
        // =========================
        
        vista.getBotonLupa().addActionListener(e -> {
        	cargarTablaConFiltro(vista.getInputBuscarValue());
        });

        // =========================
        // BOTÓN CREAR USUARIO
        // =========================

        vista.getBoton().addActionListener(e -> {

            JFrame frame = new JFrame("Crear Usuario");

            VentanaCrearUsuario ventana =
                    new VentanaCrearUsuario();

            frame.setContentPane(ventana);

            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {

                    vista.setInputBuscarValue("");
                    cargarTabla();
                }
            });
        });
    }

    // =========================
    // EDITAR USUARIO
    // =========================

    private void editarUsuario() {

        try {

            int fila = vista.getTabla().getSelectedRow();

            if (fila == -1) return;

            int idTrabajador = (int) vista
                    .getTabla()
                    .getValueAt(fila, 0);

            CrearUsuarioDAO dao = new CrearUsuarioDAO();

            Trabajador trabajador = dao.obtenerTrabajadorPorId(idTrabajador);

            if (trabajador != null) {

                JFrame frame = new JFrame("Editar Usuario");

                VentanaCrearUsuario ventanaEditar =
                        new VentanaCrearUsuario();

                ventanaEditar.mostrarCargaTrabajador(trabajador);

                frame.setContentPane(ventanaEditar);

                frame.setSize(700, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frame.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        cargarTabla();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // CARGAR TABLA
    // =========================

    public void cargarTabla() {

        try {

            ArrayList<Trabajador> lista = modelo.traerTodos();
            

            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Correo");
            modeloTabla.addColumn("Rol");
            modeloTabla.addColumn("Perfil");
            modeloTabla.addColumn("Nivel");
            modeloTabla.addColumn("Activo");
            //modeloTabla.addColumn("Acciones");
            

            for (Trabajador t : lista) {

                modeloTabla.addRow(new Object[] {
                		t.getId_trabajador(),
                		t.getNombre(),
                        t.getCorreo(),
                        t.getRol().getNombre(),
                        t.getPerfil().getNombre(),
                        t.getNivel().getNombre(),
                        t.isActivo()
                        
                        
                        //"Editar | Ver | Del"
                });
            }

            vista.getTabla().setModel(modeloTabla);
            // ocultar ID
            vista.getTabla()
                    .getColumnModel().getColumn(0).setMinWidth(0);
            vista.getTabla()
                    .getColumnModel().getColumn(0).setMaxWidth(0);
            vista.getTabla()
                    .getColumnModel().getColumn(0).setWidth(0);
          

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cargarTablaConFiltro(String busqueda) {

        try {

            ArrayList<Trabajador> lista = modelo.traerConFiltro(busqueda);

            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Correo");
            modeloTabla.addColumn("Rol");
            modeloTabla.addColumn("Perfil");
            modeloTabla.addColumn("Nivel");
            modeloTabla.addColumn("Activo");
            //modeloTabla.addColumn("Acciones");

            for (Trabajador t : lista) {

                modeloTabla.addRow(new Object[] {

                		t.getId_trabajador(),
                		t.getNombre(),
                        t.getCorreo(),
                        t.getRol().getNombre(),
                        t.getPerfil().getNombre(),
                        t.getNivel().getNombre(),
                        t.isActivo()
                        //"Editar | Ver | Del"
                });
            }

            vista.getTabla().setModel(modeloTabla);

            // ocultar ID
            vista.getTabla()
                    .getColumnModel().getColumn(0).setMinWidth(0);
            vista.getTabla()
                    .getColumnModel().getColumn(0).setMaxWidth(0);
            vista.getTabla()
                    .getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
	

//}
