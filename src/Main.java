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

/*
    // O CÓDIGO ANTIGO DO JOGO DE TERMINAL FOI MANTIDO ABAIXO APENAS COMO REFERÊNCIA.
    // ELE NÃO SERÁ EXECUTADO.

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = new UserManager("data/users.json");
    private static User currentUser = null;

    private static void showLoginMenu() {
        System.out.println("\n--- BEM-VINDO AO TERMO ---");
        System.out.println("1. Login");
        System.out.println("2. Cadastrar");
        System.out.println("3. Ver Ranking");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                //handleLogin();
                break;
            case "2":
                //handleRegister();
                break;
            case "3":
                //showRanking();
                break;
            case "4":
                System.out.println("Obrigado por jogar! Até mais.");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void showGameMenu() { }
    private static void handleLogin() { }
    private static void handleRegister() { }
    private static void showRanking() { }
    private static void playGame() { }
*/

