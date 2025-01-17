package ru.job4j.service.user;

import ru.job4j.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);

    Optional<User> findByLogin(String login);

    void deleteUser(String login);

    Collection<User> findAll();
}
