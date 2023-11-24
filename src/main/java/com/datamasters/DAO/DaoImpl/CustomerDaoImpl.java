package com.datamasters.DAO.DaoImpl;
import com.datamasters.DAO.*;


import com.datamasters.modelo.CustomerEntity;
import com.datamasters.modelo.CustomerType;
import com.datamasters.modelo.ItemEntity;
import com.datamasters.modelo.OrdersEntity;
import org.hibernate.Session;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
public class CustomerDaoImpl extends DAOFactory implements CustomerDAO {
    private final String GETALL = "FROM CustomerEntity";
    private final String GETBYID = "FROM CustomerEntity WHERE id = :id";
    private final String GETBYCUSTOMERTYPE = "FROM CustomerEntity WHERE customerType = :customerType";
    private final String GETSHIPPINGDISCOUNTBYCOUSTOMET = "SELECT shippingDiscount FROM CustomerEntity WHERE id = :id";
    private final String GETMEMBERSHIPFEEBYCUSTOMER = "SELECT membershipFee FROM CustomerEntity WHERE id = :id";


    /**
     * @param customer
     * @throws DAOException
     */
    @Override
    public void insert(CustomerEntity customer) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Insert Customer Method", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }

    /**
     * @param customer
     * @throws DAOException
     */
    @Override
    public void update(CustomerEntity customer) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Update Customer Method", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }

    @Override
    public void remove(CustomerEntity customer) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.remove(customer);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Remove Customer Method", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }


    @Override
    public ArrayList<CustomerEntity> getAll() throws DAOException {
        List<CustomerEntity> list;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            list = session.createQuery(GETALL, CustomerEntity.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get All Customers", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        ArrayList<CustomerEntity> customerList = new ArrayList<>(list);
        return customerList;
    }


    @Override
    public CustomerEntity getById(int id) throws DAOException {
        CustomerEntity customer = null;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            customer = session.createQuery(GETBYID, CustomerEntity.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get Customer by Id", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        return customer;
    }

    @Override
    public ArrayList<CustomerEntity> getCustomerByType(CustomerType customerType) throws DAOException {
        List<CustomerEntity> customerList;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            customerList = session.createQuery(GETBYCUSTOMERTYPE, CustomerEntity.class)
                    .setParameter("customerType", customerType)
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get Customers by Type", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        ArrayList<CustomerEntity> customerArrayList = new ArrayList<>(customerList);
        return customerArrayList;
    }

    @Override
    public Double getSpendByCustomer(int id) throws DAOException {
        // TODO: Implementar lógica para obtener el gasto del cliente
        return null;
    }

    @Override
    public Double getShippingDiscountByCustomer(int id) throws DAOException {
        Double shippingDiscount = null;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            shippingDiscount = (Double) session.createQuery(GETSHIPPINGDISCOUNTBYCOUSTOMET)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get Shipping Discount by Customer", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        return shippingDiscount;
    }

    @Override
    public Double getMembershipFeeByCustomer(int id) throws DAOException {
        Double membershipFee = null;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            membershipFee = (Double) session.createQuery(GETMEMBERSHIPFEEBYCUSTOMER)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get Membership Fee by Customer", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        return membershipFee;
    }

    /**
     * @return
     */
    //TODO
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
