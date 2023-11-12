package com.datamasters.DAO;

import com.datamasters.modelo.Item;

import com.datamasters.modelo.List;

public interface ItemDAO extends DAO<Item> {

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void insert(Item c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void update(Item c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void remove(Item c) throws DAOException;

    /**
     * @return
     * @throws DAOException
     */
    @Override
    List<Item> getAll() throws DAOException;

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    Item getById(int id) throws DAOException;
}

