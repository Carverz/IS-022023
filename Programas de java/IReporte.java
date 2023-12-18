import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IReporte extends JFrame {

    private JTextArea generalTextArea; // Área de texto para mostrar los datos generales
    private JPanel contentPanel; // Panel para el contenido principal

    public IReporte() {
        setTitle("Reporte del Inventario del Centro de Investigacion");
        setBackground(new Color(155));

        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Panel con botones, mensaje y botón "Continuar"
        JPanel buttonPanel = crearPanelBotones();
        add(buttonPanel, BorderLayout.NORTH);

        // Panel para el contenido principal
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Crear un botón "Continuar" y agregarlo al panel continuePanel
        JButton Continue = new JButton("Continuar");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana actual (IReporte)
            }
        });
        JPanel continuePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        continuePanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // 10 píxeles en la parte inferior, 10 píxeles a la derecha
        continuePanel.add(Continue);
        add(continuePanel, BorderLayout.SOUTH);

        setSize(450, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para crear el panel con botones, mensaje y botón "Continuar"
    private JPanel crearPanelBotones() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel tipoReporteLabel = new JLabel("Tipo de reporte:");
        JRadioButton individualButton = new JRadioButton("Individual");
        JRadioButton generalButton = new JRadioButton("General");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(individualButton);
        buttonGroup.add(generalButton);

        // Agregar ActionListener para manejar los eventos de selección/deselección
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                System.out.println("Seleccionado: " + abstractButton.getText());

                if (individualButton.isSelected()) {
                    mostrarPanelIndividual();
                } else if (generalButton.isSelected()) {
                    mostrarDatosGenerales();
                }
            }
        };

        individualButton.addActionListener(actionListener);
        generalButton.addActionListener(actionListener);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(tipoReporteLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(individualButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        buttonPanel.add(generalButton, gbc);

        return buttonPanel;
    }

    // Método para mostrar los campos individuales
    private void mostrarPanelIndividual() {
        JPanel individualPanel = new JPanel();
        JLabel ciLabel = new JLabel("C.I. del responsable de equipos:");
        JTextField ciTextField = new JTextField(10);
        JButton totalizarButton = new JButton("Totalizar");

        // Agregar un área de texto para mostrar el mensaje de totalización individual
        JTextArea individualResultArea = new JTextArea();
        individualResultArea.setEditable(false);

        totalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalizarIndividual(ciTextField.getText(), individualResultArea);
            }
        });

        individualPanel.add(ciLabel);
        individualPanel.add(ciTextField);
        individualPanel.add(totalizarButton);
        individualPanel.add(individualResultArea);

        actualizarPanel(individualPanel);
    }

    // Método para mostrar los datos generales
    private void mostrarDatosGenerales() {
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());

        try (BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"))) {
            int totalEquipos = 0;
            double totalMonto = 0.0;

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                // Separar los datos por "#" y mostrarlos en el área de texto
                String[] datos = line.split("#");

                int cantidadEquipos = Integer.parseInt(datos[1]);
                double monto = cantidadEquipos * Double.parseDouble(datos[2]);

                totalEquipos += cantidadEquipos;
                totalMonto += monto;

                sb.append("C.I. Responsable: ").append(datos[5]).append(", Cantidad de equipos: ").append(cantidadEquipos)
                        .append(", Monto total (Bs): ").append(monto).append("\n");
            }

            // Agregar el mensaje de totalización al área de texto
            sb.append("\nTotalización General: ").append(totalEquipos).append(" equipos, ").append(totalMonto).append(" Bs\n");

            generalTextArea = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(generalTextArea);
            generalPanel.add(scrollPane, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Actualizar el panel en el marco
        actualizarPanel(generalPanel);
    }

    // Método para actualizar el panel en el marco
    private void actualizarPanel(JPanel nuevoPanel) {
        contentPanel.removeAll();
        contentPanel.add(nuevoPanel, BorderLayout.CENTER);
        revalidate();
    }

    // Método para verificar si el C.I. está registrado
    private boolean ciRegistrado(String ci) {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split("#");
                if (datos.length > 5 && datos[5].equals(ci)) {
                    return true; // El C.I. está registrado
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El C.I. no está registrado
    }

    // Método para totalizar individualmente
    private void totalizarIndividual(String ci, JTextArea resultArea) {
        if (ciRegistrado(ci)) {
            try (BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"))) {
                int totalEquipos = 0;
                double totalMonto = 0.0;

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split("#");

                    if (datos.length > 5 && datos[5].equals(ci)) {
                        int cantidadEquipos = Integer.parseInt(datos[1]);
                        double monto = cantidadEquipos * Double.parseDouble(datos[2]);

                        totalEquipos += cantidadEquipos;
                        totalMonto += monto;
                    }
                }

                // Mostrar el mensaje de totalización individual en el área de texto
                sb.append("Totalización Individual: ").append(totalEquipos).append(" equipos, ").append(totalMonto).append(" Bs\n");
                resultArea.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Mostrar mensaje de error si el C.I. no está registrado
            resultArea.setText("Error: El C.I. no está registrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IReporte();
        });
    }
}