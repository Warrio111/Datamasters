package com.datamasters.DAO.DaoImpl;

import com.datamasters.DAO.*;
import com.datamasters.modelo.Orders;

import java.util.List;

public class OrderDaoImpl extends DAOFactory implements OrderDAO {
    /**
     * @return
     */
    @Override
    public CustomerDAO getCustomerDAO() {
        return null;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public ItemDAO getItemDAO() throws DAOException {
        return null;
    }

    /**
     * @return
     */
    @Override
    public OrderDAO getOrdersDAO() {
        return null;
    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void insert(Orders c) throws DAOException {

    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void update(Orders c) throws DAOException {

    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void remove(Orders c) throws DAOException {

    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public List<Orders> getAll() throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Orders getById(int id) throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public boolean orderIsSent(int id) throws DAOException {
        return false;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public boolean orderIsCancelable(int id) throws DAOException {
        return false;
    }
}
