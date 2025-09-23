import java.util.Scanner;

public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GRAY_BACKGROUND = "\u001B[100m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private final String secretWord;
    private static final int MAX_ATTEMPTS = 6;
    private final Scanner scanner = new Scanner(System.in);

    public Game(String secretWord) {
        this.secretWord = secretWord.toUpperCase();
    }

    public boolean play() {
        int attempts = 0;
        System.out.println("\n--- NOVO JOGO ---");
        System.out.println("Adivinhe a palavra de 5 letras. Você tem 6 tentativas.");

        while (attempts < MAX_ATTEMPTS) {
            System.out.printf("\nTentativa %d de %d. Digite seu palpite: ", attempts + 1, MAX_ATTEMPTS);
            String guess = scanner.nextLine().toUpperCase();

            // Validação da entrada
            if (guess.length() != 5) {
                System.out.println("Palavra inválida. Por favor, digite uma palavra com 5 letras.");
                continue;
            }

            printFeedback(guess);
            attempts++;

            if (guess.equals(secretWord)) {
                return true; // Jogador venceu
            }
        }
        return false; // Jogador perdeu
    }

    private void printFeedback(String guess) {
        StringBuilder feedback = new StringBuilder();
        // Cria um array para marcar letras da palavra secreta que já foram "usadas" no feedback amarelo
        boolean[] secretWordLetterUsed = new boolean[5];

        // 1ª Passada: Verificar letras corretas na posição correta (Verde)
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == secretWord.charAt(i)) {
                feedback.append(ANSI_GREEN_BACKGROUND + ANSI_WHITE + " " + guess.charAt(i) + " " + ANSI_RESET);
                secretWordLetterUsed[i] = true;
            } else {
                feedback.append(" "); // Placeholder para a próxima passada
            }
        }

        // 2ª Passada: Verificar letras corretas na posição errada (Amarelo) e incorretas (Cinza)
        for (int i = 0; i < 5; i++) {
             // Pula as que já foram marcadas como verde
            if (guess.charAt(i) == secretWord.charAt(i)) continue;

            boolean foundInWrongPlace = false;
            for (int j = 0; j < 5; j++) {
                if (i != j && guess.charAt(i) == secretWord.charAt(j) && !secretWordLetterUsed[j]) {
                    // Encontrou a letra em outra posição que ainda não foi usada
                    feedback.setCharAt(i*18, ANSI_YELLOW_BACKGROUND.charAt(0)); // A cor é um placeholder para o char
                    feedback.insert(i*18, ANSI_YELLOW_BACKGROUND + ANSI_WHITE + " " + guess.charAt(i) + " " + ANSI_RESET);
                    secretWordLetterUsed[j] = true;
                    foundInWrongPlace = true;
                    break;
                }
            }
            // Se não encontrou em lugar nenhum, é cinza
            if (!foundInWrongPlace) {
                 feedback.insert(i*18, ANSI_GRAY_BACKGROUND + ANSI_WHITE + " " + guess.charAt(i) + " " + ANSI_RESET);
            }
        }
        
        // Limpa os placeholders e imprime
        System.out.println(feedback.toString().replaceAll("\\s{18}", ""));
    }
}
