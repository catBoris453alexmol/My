package ru.sapteh.service;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Service;

import java.util.List;

public class ServiceDaoImpl implements DAO<Service, Integer> {
    private final SessionFactory factory;

    public ServiceDaoImpl(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Service service) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
        }
    }

    @Override
    public Service findById(Integer key) {
        try(Session session = factory.openSession()){
            Service service = session.get(Service.class, key);
            Hibernate.initialize(service);
            return service;
        }
    }

    @Override
    public List<Service> findByAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM Service ";
            Query<Service> query = session.createQuery(hql);
            List<Service> serviceEntities = query.list();
            Hibernate.initialize(serviceEntities);
            return serviceEntities;
        }
    }

    @Override
    public void update(Service service) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(service);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Service service) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(service);
            session.getTransaction().commit();
        }
    }
}
