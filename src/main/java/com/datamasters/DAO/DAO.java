package com.datamasters.DAO;

import com.datamasters.modelo.List;

import java.sql.SQLException;

public interface DAO<T> {
    void insert(T c) throws DAOException, SQLException;
    void update(T c) throws DAOException;
    void remove(T c) throws DAOException;
    List<T> getAll() throws DAOException;
    T getById(int id) throws DAOException;
}