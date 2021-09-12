package com.example.jenkins.dao;

import java.sql.SQLException;
import java.util.HashSet;

public interface Dao<T, PK> {

    int add(T entity) throws SQLException;

    HashSet<T> getAll();

    T getByLogin(String login);

    int update(T entity);

    int deleteByLogin(String login);
}
