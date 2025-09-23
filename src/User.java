public class User {
    private String username;
    private String password;
    private int score;

    // Construtor padrão necessário para desserialização do JSON
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }
    
    // Setters (necessários para a manipulação do JSON)
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Métodos
    public void incrementScore() {
        this.score++;
    }
}