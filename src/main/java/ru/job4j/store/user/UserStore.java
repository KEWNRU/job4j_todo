package ru.job4j.store.user;

import ru.job4j.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserStore {
    Optional<User> save(User user);

    Optional<User> findByLogin(String login);

    void deleteUser(String login);

    Collection<User> findAll();
}
