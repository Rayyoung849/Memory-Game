
import javax.swing.SwingUtilities;

public class MainMemoryGame {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater() to ensure proper Swing threading
        SwingUtilities.invokeLater(() -> {
            // Create an instance of the MemoryGame class
            MemoryGame memoryGame = new MemoryGame();
        });
    }
}


