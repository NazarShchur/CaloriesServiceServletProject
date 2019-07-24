package com.nazar.model.dao.interfaces;

import java.util.List;

public interface CRUDDao<T> extends AutoCloseable {
    void create (T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    @Override
    void close();
}
