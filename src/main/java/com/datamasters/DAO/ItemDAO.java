package com.datamasters.DAO;

import com.datamasters.modelo.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends DAO<ItemEntity>{

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void insert(ItemEntity c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void update(ItemEntity c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void remove(ItemEntity c) throws DAOException;

    /**
     * @return
     * @throws DAOException
     */
    @Override
    ArrayList<ItemEntity> getAll() throws DAOException;

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    ItemEntity getById(int id) throws DAOException;
}
