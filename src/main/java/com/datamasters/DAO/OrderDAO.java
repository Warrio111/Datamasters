package com.datamasters.DAO;

import com.datamasters.modelo.OrdersEntity;
import java.util.ArrayList;
import java.util.List;

public interface OrderDAO extends DAO <OrdersEntity>{
    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void insert(OrdersEntity c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void update(OrdersEntity c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void remove(OrdersEntity c) throws DAOException;

    /**
     * @return
     * @throws DAOException
     */
    @Override
    ArrayList<OrdersEntity> getAll() throws DAOException;

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    OrdersEntity getById(int id) throws DAOException;

    boolean orderIsSent(int id) throws DAOException;
    boolean orderIsCancelable(int id) throws DAOException;

}
