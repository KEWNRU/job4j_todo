package ru.job4j.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.store.user.HqlUserStore;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleUserService implements UserService {
    private final HqlUserStore userStore;

    @Override
    public Optional<User> save(User user) {
        return userStore.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userStore.findByLogin(login);
    }

    @Override
    public void deleteUser(String login) {
        userStore.deleteUser(login);
    }

    @Override
    public Collection<User> findAll() {
        return userStore.findAll();
    }
}
