package ru.sapteh.dao;

import java.util.List;

public interface DAO<Entity, Key>{
    Entity findById(Key key);
    List<Entity> findByAll();
    void create(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
