package Programas_de_java;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ICentro {

    public static void main(String[] args) {
        JFrame f = new JFrame("Registro y Control de Equipos en Centro de Investigacion");
        f.setBackground(new Color(155));

        JLabel L0 = new JLabel("Ingrese data del equipo:");
        JLabel Espacio1 = new JLabel("        ");
        JLabel Espacio2 = new JLabel("");
        JLabel Espacio3 = new JLabel("                  ");

        JLabel L1 = new JLabel("Descripcion:");
        JTextField t1 = new JTextField(25);

        JLabel L2 = new JLabel("Cantidad:");
        JTextField t2 = new JTextField(5);

        JLabel L3 = new JLabel("Costo unitario (Bs):");
        JTextField t3 = new JTextField(8);

        JLabel L4 = new JLabel("Fecha de adquisicion: dd/mm/aaaa");
        JTextField t4 = new JTextField(4);

        JLabel L5 = new JLabel("Nro. de factura:");
        JTextField t5 = new JTextField(4);

        JLabel L6 = new JLabel("C.I. del Responsable del equipo:");
        JTextField t6 = new JTextField(10);

        JButton Register = new JButton("Registrar data");
        JButton Exit = new JButton("Salir");
        JButton Report = new JButton("Generar Reporte");

        // Agregar ActionListener al botón Salir
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Agregar ActionListener al botón Registrar data
        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarData(t1.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText(), t6.getText());
                JOptionPane.showMessageDialog(f, "Equipo registrado correctamente.");
            }
        });

        // Agregar ActionListener al botón Generar Reporte
        Report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la clase IReporte y mostrarla
                IReporte reporteFrame = new IReporte();
                reporteFrame.setVisible(true);
            }
        });

        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Establecer padding para el inputPanel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10 píxeles de padding en cada lado
        inputPanel.add(L0, BorderLayout.NORTH); // Colocar L0 en la parte superior
        JPanel centerPanel = new JPanel();
        centerPanel.add(L1);
        centerPanel.add(t1);
        centerPanel.add(L2);
        centerPanel.add(t2);
        centerPanel.add(Espacio1);
        centerPanel.add(L3);
        centerPanel.add(t3);
        centerPanel.add(L4);
        centerPanel.add(t4);
        centerPanel.add(Espacio2);
        centerPanel.add(L5);
        centerPanel.add(t5);
        centerPanel.add(L6);
        centerPanel.add(t6);
        centerPanel.add(Espacio3);
        inputPanel.add(centerPanel, BorderLayout.CENTER);

        // Establecer padding para el panel de botones
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBorder(new EmptyBorder(0, 10, 10, 10)); // 10 píxeles en la parte inferior, 10 píxeles a la derecha
        panel.add(Register);
        panel.add(Exit);
        panel.add(Report);

        f.add(inputPanel, BorderLayout.CENTER);
        f.add(panel, BorderLayout.SOUTH);

        f.setSize(450, 300);
        f.setVisible(true);
    }

    // Método para guardar la información en un archivo
    private static void registrarData(String descripcion, String cantidad, String costoUnitario,
                                      String fecha, String numeroFactura, String ciResponsable) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventario.txt", true))) {
            // Formato de la línea en el archivo
            String linea = String.format("%s#%s#%s#%s#%s#%s%n",
                    descripcion, cantidad, costoUnitario, fecha, numeroFactura, ciResponsable);
            // Escribir la línea en el archivo
            writer.write(linea);
        } catch (IOException ex) {
        }
     } 
} 