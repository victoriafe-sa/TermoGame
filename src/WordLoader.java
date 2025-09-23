import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordLoader {
    private final List<String> words;
    private final Random random;

    public WordLoader(String filePath) throws IOException {
        this.words = new ArrayList<>();
        this.random = new Random();
        loadWordsFromFile(filePath);
    }

    private void loadWordsFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Adiciona a palavra se ela tiver exatamente 5 letras e não estiver vazia
                if (!line.trim().isEmpty() && line.trim().length() == 5) {
                    words.add(line.trim());
                }
            }
        }
        if (words.isEmpty()) {
            throw new IOException("Nenhuma palavra válida de 5 letras foi encontrada no arquivo: " + filePath);
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            return "ERRO"; // Palavra de fallback caso a lista esteja vazia
        }
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}