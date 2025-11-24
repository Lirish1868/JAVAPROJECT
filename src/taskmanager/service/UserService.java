package taskmanager.service;

import taskmanager.model.User;
import taskmanager.util.FileStorage;
import taskmanager.exception.TaskManagerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final String FILE_PATH = "data/users.txt";

    public void addUser(String username, String email) throws TaskManagerException {
        List<User> users = getAllUsers();
        String id = String.valueOf(users.size() + 1);
        User newUser = new User(id, username, email);
        FileStorage.appendLine(FILE_PATH, newUser.toString());
    }

    public List<User> getAllUsers() throws TaskManagerException {
        List<String> lines = FileStorage.readLines(FILE_PATH);
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(User.fromString(line));
        }
        return users;
    }

    public Optional<User> getUserById(String id) throws TaskManagerException {
        return getAllUsers().stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
