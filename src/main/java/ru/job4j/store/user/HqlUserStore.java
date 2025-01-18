package ru.job4j.store.user;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlUserStore implements UserStore {
    private final SessionFactory sf;

    @Override
    public Optional<User> save(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Optional<User> users = null;
        try {
            session.beginTransaction();
            users = session.createQuery("from User where login = :login", User.class)
                    .setParameter("login", login)
                    .uniqueResultOptional();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void deleteUser(String login) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from User where login = :login", User.class)
                    .executeUpdate();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Collection<User> findAll() {
        Session session = sf.openSession();
        Collection<User> users = null;
        try {
            session.beginTransaction();
            users = session.createQuery("from User", User.class)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return users;
    }
}
