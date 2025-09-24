import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class GameGUI extends JFrame {

    private static final int WORD_LENGTH = 5;
    private static final int MAX_ATTEMPTS = 6;

    private final JLabel[][] tiles;
    private final JTextField guessInput;
    private final JButton submitButton;
    private final JLabel messageLabel;

    private String secretWord;
    private int currentAttempt = 0;
    private final WordLoader wordLoader;

    public GameGUI() {
        // 1. Configuração da Janela Principal (JFrame)
        setTitle("Termo - O Jogo");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Carrega as palavras
        try {
            wordLoader = new WordLoader("data/palavras.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o arquivo de palavras: " + e.getMessage(), "Erro Crítico", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }

        // 2. Painel do Tabuleiro (Grid)
        JPanel boardPanel = new JPanel(new GridLayout(MAX_ATTEMPTS, WORD_LENGTH, 5, 5));
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tiles = new JLabel[MAX_ATTEMPTS][WORD_LENGTH];
        Border tileBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
        Font tileFont = new Font("Arial", Font.BOLD, 32);

        for (int row = 0; row < MAX_ATTEMPTS; row++) {
            for (int col = 0; col < WORD_LENGTH; col++) {
                tiles[row][col] = new JLabel("", SwingConstants.CENTER);
                tiles[row][col].setFont(tileFont);
                tiles[row][col].setOpaque(true);
                tiles[row][col].setBackground(Color.WHITE);
                tiles[row][col].setForeground(Color.BLACK);
                tiles[row][col].setBorder(tileBorder);
                boardPanel.add(tiles[row][col]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // 3. Painel de Entrada (Inferior)
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(Color.WHITE);
        guessInput = new JTextField(10);
        guessInput.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton = new JButton("Enviar");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(new JLabel("Seu palpite: "));
        inputPanel.add(guessInput);
        inputPanel.add(submitButton);
        add(inputPanel, BorderLayout.SOUTH);

        // 4. Rótulo de Mensagens (Superior)
        messageLabel = new JLabel("Digite uma palavra de 5 letras e clique em Enviar.", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(messageLabel, BorderLayout.NORTH);

        // 5. Lógica de Eventos
        submitButton.addActionListener(e -> processGuess());
        guessInput.addActionListener(e -> processGuess()); // Permite enviar com a tecla Enter

        // Inicia o primeiro jogo
        startNewGame();

        // Finaliza a configuração da janela
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private void startNewGame() {
        secretWord = wordLoader.getRandomWord().toUpperCase();
        System.out.println("Palavra secreta (para depuração): " + secretWord); // Debug
        currentAttempt = 0;

        // Limpa o tabuleiro
        for (int row = 0; row < MAX_ATTEMPTS; row++) {
            for (int col = 0; col < WORD_LENGTH; col++) {
                tiles[row][col].setText("");
                tiles[row][col].setBackground(Color.WHITE);
                tiles[row][col].setForeground(Color.BLACK);
            }
        }

        // Reativa os controles
        guessInput.setText("");
        guessInput.setEnabled(true);
        submitButton.setEnabled(true);
        messageLabel.setText("Novo jogo iniciado! Boa sorte.");
        guessInput.requestFocus();
    }

    private void processGuess() {
        String guess = guessInput.getText().trim().toUpperCase();

        if (guess.length() != WORD_LENGTH) {
            messageLabel.setText("O palpite deve ter 5 letras.");
            return;
        }

        updateBoard(guess);

        if (guess.equals(secretWord)) {
            endGame(true);
            return;
        }

        currentAttempt++;

        if (currentAttempt >= MAX_ATTEMPTS) {
            endGame(false);
            return;
        }
        
        guessInput.setText("");
        guessInput.requestFocus();
        messageLabel.setText("Tentativa " + (currentAttempt + 1) + " de " + MAX_ATTEMPTS);
    }

    private void updateBoard(String guess) {
        char[] secretChars = secretWord.toCharArray();
        char[] guessChars = guess.toCharArray();
        boolean[] secretLetterUsed = new boolean[WORD_LENGTH];
        Color[] feedbackColors = new Color[WORD_LENGTH];

        // 1ª Passada: Verde
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guessChars[i] == secretChars[i]) {
                feedbackColors[i] = new Color(106, 170, 100);
                secretLetterUsed[i] = true;
            }
        }

        // 2ª Passada: Amarelo e Cinza
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (feedbackColors[i] == null) {
                boolean foundInWrongPlace = false;
                for (int j = 0; j < WORD_LENGTH; j++) {
                    if (i != j && !secretLetterUsed[j] && guessChars[i] == secretChars[j]) {
                        feedbackColors[i] = new Color(201, 180, 88);
                        secretLetterUsed[j] = true;
                        foundInWrongPlace = true;
                        break;
                    }
                }
                if (!foundInWrongPlace) {
                     feedbackColors[i] = Color.DARK_GRAY;
                }
            }
        }
        
        // Atualiza a interface
        for (int i = 0; i < WORD_LENGTH; i++) {
            tiles[currentAttempt][i].setText(String.valueOf(guessChars[i]));
            tiles[currentAttempt][i].setBackground(feedbackColors[i]);
            tiles[currentAttempt][i].setForeground(Color.WHITE);
        }
    }
    
    private void endGame(boolean hasWon) {
        guessInput.setEnabled(false);
        submitButton.setEnabled(false);
        
        messageLabel.setText(hasWon ? "Parabéns, você acertou!" : "Fim de jogo. A palavra era: " + secretWord);
        
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Deseja jogar novamente?",
            "Fim de Jogo",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            startNewGame();
        } else {
            System.exit(0);
        }
    }
}
