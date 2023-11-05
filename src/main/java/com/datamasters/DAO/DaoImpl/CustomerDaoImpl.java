package com.datamasters.DAO.DaoImpl;

import com.datamasters.DAO.*;
import com.datamasters.modelo.Customer;

import java.util.List;

public class CustomerDaoImpl extends DAOFactory implements CustomerDAO {

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void insert(Customer c) throws DAOException {

    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void update(Customer c) throws DAOException {

    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void remove(Customer c) throws DAOException {

    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public List<Customer> getAll() throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Customer getById(int id) throws DAOException {
        return null;
    }

    /**
     * @param customerType
     * @return
     * @throws DAOException
     */
    @Override
    public Customer getCustomerType(String customerType) throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Double getSpendByCustomer(int id) throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Double getShippingDiscountByCustomer(int id) throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Double getMembershipFeeByCustomer(int id) throws DAOException {
        return null;
    }

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
}
