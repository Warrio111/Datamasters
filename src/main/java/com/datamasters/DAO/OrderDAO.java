package com.datamasters.DAO;

import com.datamasters.modelo.Orders;

import com.datamasters.modelo.List;

public interface OrderDAO extends DAO <Orders>{
    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void insert(Orders c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void update(Orders c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void remove(Orders c) throws DAOException;

    /**
     * @return
     * @throws DAOException
     */
    @Override
    List<Orders> getAll() throws DAOException;

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    Orders getById(int id) throws DAOException;

    boolean orderIsSent(int id) throws DAOException;
    boolean orderIsCancelable(int id) throws DAOException;

}
