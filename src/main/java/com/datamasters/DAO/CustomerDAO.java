package com.datamasters.DAO;

import com.datamasters.modelo.Customer;
import com.datamasters.modelo.List;
import java.sql.SQLException;

public interface CustomerDAO extends DAO<Customer>{
    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void insert(Customer c) throws DAOException, SQLException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void update(Customer c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void remove(Customer c) throws DAOException;

    /**
     * @return
     * @throws DAOException
     */
    @Override
    List<Customer> getAll() throws DAOException;

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    Customer getById(int id) throws DAOException;

    Customer getCustomerType(String customerType) throws DAOException;
    List<Customer> getCustomerByType(String customerType) throws DAOException;
    Double getSpendByCustomer(int id) throws DAOException;
    Double getShippingDiscountByCustomer(int id) throws DAOException;
    Double getMembershipFeeByCustomer(int id) throws DAOException;


}
