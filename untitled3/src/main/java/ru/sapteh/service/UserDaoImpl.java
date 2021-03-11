package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.User;

import java.util.List;

public class UserDaoImpl implements DAO<User, Integer> {
    private final SessionFactory factory;

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User findById(Integer integer) {
        return null;
    }

    @Override
    public List<User> findByAll() {
        try(Session session = factory.openSession()){
            Query<User> query = session.createQuery("from User");
            return query.list();
        }
    }

    @Override
    public void create(User user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}


