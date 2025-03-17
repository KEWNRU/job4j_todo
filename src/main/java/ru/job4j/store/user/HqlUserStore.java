package ru.job4j.store.user;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;
import ru.job4j.store.CrudRepository;

import java.util.*;
import java.util.logging.Logger;

@Repository
@AllArgsConstructor
public class HqlUserStore implements UserStore {
    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        try {
            crudRepository.run(session -> session.save(user));
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional("from User where login = :login", User.class,
                Map.of("login", login));
    }

    @Override
    public void deleteUser(String login) {
        crudRepository.run(session -> session.delete(session.get(User.class, login)));
    }

    @Override
    public Collection<User> findAll() {
        return crudRepository.query("from User", User.class);
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional("from Task where id = :id", User.class,
                Map.of("id", id));
    }
}
