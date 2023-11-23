package com.datamasters.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void insert(T c) throws DAOException, SQLException;
    void update(T c) throws DAOException;
    void remove(T c) throws DAOException;
    List<Item> getAll() throws DAOException;
    T getById(int id) throws DAOException;
}