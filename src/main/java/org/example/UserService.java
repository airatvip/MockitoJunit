package org.example;

import org.example.exception.UserNonUniqueException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllLogins() {
        List<String> logins = userRepository.getAllUsers().stream().map(User::getLogin).collect(Collectors.toList());
        return logins;
    }

    public void addNewUser(String login, String password) {
        User user = new User(login, password);

        if (login == null || login.isEmpty() || login.isBlank()) {
            throw new IllegalArgumentException("Логин не может быть пустым");
        }
        if (password == null || password.isEmpty() || password.isBlank()) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }

        boolean userExist = userRepository.getAllUsers().stream().anyMatch(u -> u.equals(user));
        if (userExist) {
            throw new UserNonUniqueException("Такой пользователь уже существует");
        }

        userRepository.addUser(user);

    }

}
