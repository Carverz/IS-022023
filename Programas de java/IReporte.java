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

        individualPanel.add(ciLabel);
        individualPanel.add(ciTextField);
        individualPanel.add(totalizarButton);

        actualizarPanel(individualPanel);
    }

    // Método para mostrar los datos generales
    private void mostrarDatosGenerales() {
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());

        try (BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // Separar los datos por "#" y mostrarlos en el área de texto
                String[] datos = line.split("#");
                sb.append("C.I. Responsable: ").append(datos[5]).append(", Cantidad de equipos: ").append(datos[1])
                        .append(", Monto total (Bs): ").append(Integer.parseInt(datos[1]) * Double.parseDouble(datos[2]))
                        .append("\n");
            }
            generalTextArea = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(generalTextArea);
            generalPanel.add(scrollPane, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        actualizarPanel(generalPanel);
    }

    // Método para actualizar el panel en el marco
    private void actualizarPanel(JPanel nuevoPanel) {
        contentPanel.removeAll();
        contentPanel.add(nuevoPanel, BorderLayout.CENTER);
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IReporte();
        });
    }
}