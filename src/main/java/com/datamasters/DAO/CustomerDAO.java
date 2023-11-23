package com.datamasters.DAO;

import com.datamasters.modelo.CustomerEntity;
import java.util.List;
import java.sql.SQLException;

public interface CustomerDAO extends DAO<CustomerEntity>{
    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void insert(CustomerEntity c) throws DAOException, SQLException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void update(CustomerEntity c) throws DAOException;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    void remove(CustomerEntity c) throws DAOException;

    /**
     * @return
     * @throws DAOException
     */
    @Override
    List<CustomerEntity> getAll() throws DAOException;

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    CustomerEntity getById(int id) throws DAOException;

    CustomerEntity getCustomerType(String customerType) throws DAOException;
    List<CustomerEntity> getCustomerByType(String customerType) throws DAOException;
    Double getSpendByCustomer(int id) throws DAOException;
    Double getShippingDiscountByCustomer(int id) throws DAOException;
    Double getMembershipFeeByCustomer(int id) throws DAOException;


}
