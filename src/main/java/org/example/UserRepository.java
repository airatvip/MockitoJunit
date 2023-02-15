package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRepository {

    private List<User> user = new ArrayList<>();

    public Collection<User> getAllUsers() {
        return user;
    }

    public User findUserByLogin(String login) {

        return user.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
    }

    public User findUserByLoginAndPassword(String login, String password) {

        return user.stream().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password)).findFirst().orElse(null);
    }

    public void addUser(User user) {
        this.user.add(user);
    }
}
