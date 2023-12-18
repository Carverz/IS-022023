package Programas_de_java;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IReporte {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Reporte del Inventario del Centro de Investigacion");
            f.setBackground(new Color(155));

            JLabel L = new JLabel("Tipo de reporte:");

            f.setLayout(new BorderLayout());
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JRadioButton invidividuabButton = new JRadioButton("Individual");
            JRadioButton generalButton = new JRadioButton("General");

            // Crear un ButtonGroup y agregar los botones al grupo
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(invidividuabButton);
            buttonGroup.add(generalButton);

            // Agregar ActionListener para manejar los eventos de selección/deselección
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    System.out.println("Seleccionado: " + abstractButton.getText());
                }
            };

            invidividuabButton.addActionListener(actionListener);
            generalButton.addActionListener(actionListener);

            // Agregar botones al panel
            panel.add(invidividuabButton);
            panel.add(generalButton);

            // Establecer padding para el inputPanel
            JPanel inputPanel = new JPanel(new BorderLayout());
            inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10 píxeles de padding en cada lado
            JPanel centerPanel = new JPanel();
            centerPanel.add(L);
            centerPanel.add(panel);
            inputPanel.add(centerPanel, BorderLayout.CENTER);

            // Crear un botón "Continuar" y agregarlo al panel continuePanel
            JButton Continue = new JButton("Continuar");

            JPanel continuePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            continuePanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // 10 píxeles en la parte inferior, 10 píxeles a la derecha
            continuePanel.add(Continue);

            // Agregar panel continuePanel a la interfaz en la región SUR (SOUTH)
            f.add(continuePanel, BorderLayout.SOUTH);

            f.add(inputPanel, BorderLayout.CENTER);
            f.setSize(450, 300);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
