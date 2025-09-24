import javax.swing.SwingUtilities;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicia a interface gráfica (GUI) em vez do jogo de terminal.
        // SwingUtilities.invokeLater garante que a GUI seja criada e atualizada na thread correta.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Mensagem para confirmar que a nova versão está sendo executada
                System.out.println("Iniciando a interface gráfica...");
                new GameGUI();
            }
        });
    }
}

