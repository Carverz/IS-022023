import javax.swing.SwingUtilities;

public class EjercicioC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ICentro().main(args);
        });
    }
}