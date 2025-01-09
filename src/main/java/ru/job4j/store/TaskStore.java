package ru.job4j.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class TaskStore implements HqlTaskStore {
    private final SessionFactory sf;

    @Override
    public Task add(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public void update(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("update Task set done =: done, description =: description where id =:id")
                    .setParameter("done", task.isDone())
                    .setParameter("description", task.getDescription())
                    .setParameter("id", task.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from Task where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Task> findAll() {
        Session session = sf.openSession();
        List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("from Task order by id", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Task findById(Integer id) {
        Session session = sf.openSession();
        Task task = null;
        try {
            session.beginTransaction();
            task = session.createQuery("from Task where id = :id", Task.class)
                    .setParameter("id", id)
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public List<Task> findDone(boolean done) {
        Session session = sf.openSession();
        List<Task> list = new ArrayList<>();
        try {
            session.beginTransaction();
            list = session.createQuery("from Task where done = :done order by id", Task.class)
                    .setBoolean("done", done).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return list;
    }
}