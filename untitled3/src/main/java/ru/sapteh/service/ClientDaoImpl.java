package ru.sapteh.service;

import org.hibernate.SessionFactory;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;

import java.util.List;

public class ClientDaoImpl implements DAO<Client, Integer> {
    private final SessionFactory factory;

    public ClientDaoImpl(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Client client) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public Client findById(Integer key) {
        try(Session session = factory.openSession()){
            Client client = session.get(Client.class, key);
            Hibernate.initialize(client);
            return client;
        }
    }

    @Override
    public List<Client> findByAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM Client";
            Query<Client> query = session.createQuery(hql);
            Hibernate.initialize(query.list());
            return query.list();
        }
    }

    @Override
    public void update(Client client) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Client client) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        }
    }
}
