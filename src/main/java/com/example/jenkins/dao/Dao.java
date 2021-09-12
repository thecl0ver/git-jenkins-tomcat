package com.example.jenkins.dao;

import java.sql.SQLException;
import java.util.HashSet;

public interface Dao<T, PK> {

    void add(T entity) throws SQLException;

    HashSet<T> getAll();

    T getByLogin(String login);

    void update(T entity);

    void deleteByLogin(String login);
}
