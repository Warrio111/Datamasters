package com.datamasters.DAO;

import com.datamasters.modelo.Orders;

import java.util.List;

public interface DAO<T> {
    void insert(T c) throws DAOException;
    void update(T c) throws DAOException;
    void remove(T c) throws DAOException;
    List<T> getAll() throws DAOException;
    T getById(int id) throws DAOException;
}
