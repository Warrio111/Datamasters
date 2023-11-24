package com.datamasters.DAO.DaoImpl;
import com.datamasters.DAO.*;


import com.datamasters.modelo.CustomerEntity;
import com.datamasters.modelo.ItemEntity;
import com.datamasters.modelo.OrdersEntity;
import com.datamasters.modelo.*;
import org.hibernate.Session;
import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends DAOFactory implements OrderDAO {
    private final String GETALL = "FROM OrdersEntity o JOIN FETCH o.customer JOIN FETCH o.item";
    private final String GETBYID = "FROM OrdersEntity o JOIN FETCH o.customer JOIN FETCH o.item WHERE o.orderNumber = :id";


    @Override
    public CustomerDAO getCustomerDAO() {
        return null;
    }

    @Override
    public ItemDAO getItemDAO() throws DAOException {
        return null;
    }

    @Override
    public OrderDAO getOrdersDAO() {
        return null;
    }

    @Override
    public void insert(OrdersEntity order) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Insert Order Method", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }

    @Override
    public void update(OrdersEntity order) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Update Order Method", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }

    @Override
    public void remove(OrdersEntity order) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.remove(order);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Remove Order Method", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }

    @Override
    public ArrayList<OrdersEntity> getAll() throws DAOException {
        List<OrdersEntity> orderList;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            orderList = session.createQuery(GETALL, OrdersEntity.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get All Orders", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        ArrayList<OrdersEntity> orderArrayList = new ArrayList<>(orderList);
        return orderArrayList;
    }
    @Override
    public OrdersEntity getById(int id) throws DAOException {
        OrdersEntity order = null;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            order = session.createQuery(GETBYID, OrdersEntity.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get Order by Id", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        return order;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public boolean orderIsSent(int id) throws DAOException {
        OrdersEntity orderEntity = getById(id);
        LocalDateTime currentTime = LocalDateTime.now();
        if (orderEntity != null) {
            LocalDateTime cutoffTime = orderEntity.getOrderDateTime().toLocalDateTime().plusMinutes(orderEntity.getPreparationTimeMinutes());
            return currentTime.isAfter(cutoffTime);
        }
        return false;
    }

    @Override
    public boolean orderIsCancelable(int id) throws DAOException {
        OrdersEntity orderEntity = getById(id);
        LocalDateTime currentTime = LocalDateTime.now();
        if (orderEntity != null) {
            LocalDateTime cutoffTime = orderEntity.getOrderDateTime().toLocalDateTime().plusMinutes(orderEntity.getPreparationTimeMinutes());
            return currentTime.isBefore(cutoffTime);
        }
        return false;
    }

}