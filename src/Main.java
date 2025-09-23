import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = new UserManager("data/users.json");
    private static User currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showGameMenu();
            }
        }
    }

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
                handleLogin();
                break;
            case "2":
                handleRegister();
                break;
            case "3":
                showRanking();
                break;
            case "4":
                System.out.println("Obrigado por jogar! Até mais.");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void showGameMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("Logado como: " + currentUser.getUsername() + " | Pontuação: " + currentUser.getScore());
        System.out.println("1. Jogar");
        System.out.println("2. Ver Ranking");
        System.out.println("3. Logout");
        System.out.print("Escolha uma opção: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                playGame();
                break;
            case "2":
                showRanking();
                break;
            case "3":
                currentUser = null;
                System.out.println("Logout realizado com sucesso.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void handleLogin() {
        System.out.print("Nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        User user = userManager.loginUser(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login bem-sucedido! Bem-vindo, " + username + "!");
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    private static void handleRegister() {
        System.out.print("Escolha um nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Escolha uma senha: ");
        String password = scanner.nextLine();

        if (userManager.registerUser(username, password)) {
            System.out.println("Usuário cadastrado com sucesso! Agora você pode fazer o login.");
        } else {
            System.out.println("Nome de usuário já existe. Tente outro.");
        }
    }
    
    private static void showRanking() {
        List<User> topUsers = userManager.getRanking();
        System.out.println("\n--- TOP 5 JOGADORES ---");
        if (topUsers.isEmpty()) {
            System.out.println("Ainda não há jogadores no ranking.");
        } else {
            for (int i = 0; i < topUsers.size(); i++) {
                User user = topUsers.get(i);
                System.out.printf("%d. %s - %d pontos\n", i + 1, user.getUsername(), user.getScore());
            }
        }
        System.out.println("-------------------------");
    }

    private static void playGame() {
        try {
            WordLoader wordLoader = new WordLoader("data/palavras.txt");
            String secretWord = wordLoader.getRandomWord();
            Game game = new Game(secretWord);
            
            boolean hasWon = game.play();

            if (hasWon) {
                System.out.println("Parabéns, você acertou a palavra!");
                currentUser.incrementScore();
                userManager.updateUser(currentUser);
            } else {
                System.out.println("Que pena, você não conseguiu adivinhar. A palavra era: " + secretWord);
            }
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o jogo: " + e.getMessage());
        }
    }
}
