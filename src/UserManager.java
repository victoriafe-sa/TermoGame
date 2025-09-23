import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserManager {
    private final String filePath;
    private final List<User> users;

    public UserManager(String filePath) {
        this.filePath = filePath;
        this.users = loadUsers();
    }

    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return userList; // Retorna lista vazia se o arquivo não existe
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Lógica simples de parsing de JSON "manual"
            String line = reader.readLine();
            if (line == null || line.equals("[]") || line.trim().isEmpty()) {
                return userList;
            }
            
            // Remove colchetes e divide por objetos
            line = line.substring(1, line.length() - 1).trim();
            if (line.isEmpty()) return userList;
            
            String[] userObjects = line.split("},\\{");

            for (String userStr : userObjects) {
                userStr = userStr.replace("{", "").replace("}", "").replace("\"", "");
                String[] fields = userStr.split(",");
                User user = new User();
                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    if (key.equals("username")) user.setUsername(value);
                    if (key.equals("password")) user.setPassword(value);
                    if (key.equals("score")) user.setScore(Integer.parseInt(value));
                }
                userList.add(user);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
        return userList;
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Lógica simples para gerar uma string JSON
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("[");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                jsonBuilder.append("{")
                           .append("\"username\":\"").append(user.getUsername()).append("\",")
                           .append("\"password\":\"").append(user.getPassword()).append("\",")
                           .append("\"score\":").append(user.getScore())
                           .append("}");
                if (i < users.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");
            writer.write(jsonBuilder.toString());
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public boolean registerUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Usuário e senha não podem ser vazios.");
            return false;
        }

        // Verifica se o usuário já existe
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return false; // Usuário já existe
            }
        }

        User newUser = new User(username, password);
        users.add(newUser);
        saveUsers();
        return true;
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return user; // Login bem-sucedido
            }
        }
        return null; // Credenciais inválidas
    }
    
    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                saveUsers();
                return;
            }
        }
    }
    
    public List<User> getRanking() {
        return users.stream()
                    .sorted(Comparator.comparingInt(User::getScore).reversed())
                    .limit(5)
                    .collect(Collectors.toList());
    }
}
