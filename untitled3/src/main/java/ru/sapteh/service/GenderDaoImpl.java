package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Gender;

import java.util.List;

public class GenderDaoImpl implements DAO<Gender, Integer> {
    private final SessionFactory factory;

    public GenderDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Gender findById(Integer integer) {
        return null;
    }

    @Override
    public List<Gender> findByAll() {
        try (Session session = factory.openSession()){
            Query<Gender> genderQuery = session.createQuery("FROM Gender");
            return genderQuery.list();
        }
    }

    @Override
    public void create(Gender gender) {

    }

    @Override
    public void update(Gender gender) {

    }

    @Override
    public void delete(Gender gender) {

    }
}
