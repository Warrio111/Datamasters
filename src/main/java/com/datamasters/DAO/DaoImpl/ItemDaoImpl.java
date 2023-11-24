package com.datamasters.DAO.DaoImpl;
import com.datamasters.DAO.*;


import com.datamasters.modelo.ItemEntity;
import com.datamasters.modelo.OrdersEntity;
import org.hibernate.Session;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends DAOFactory implements ItemDAO {
    private final String GETALL = "FROM ItemEntity";
    private final String GETBYID = "FROM ItemEntity WHERE code = :code";
    OrdersEntity order = null;

    /**
     * @return
     */
    @Override
    public CustomerDAO getCustomerDAO() throws SQLException {
           return null;
    }

    /**
     * @return
     */
    @Override
    public ItemDAO getItemDAO() {
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
     * @param item
     * @throws DAOException
     */
    @Override
    public void insert(ItemEntity item) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();

        } catch (Exception ex) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in Insert ItemMethod", ex);

        } finally {

            if (session != null) {
                try {
                    HibernateUtil.cerrarSession(session);
                } catch (Exception e) {
                    throw new RuntimeException(e);

                }
            }
        }
    }

    /**
     * @param item
     * @throws DAOException
     */
    @Override
    public void update(ItemEntity item) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Update ItemMethod", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }
    /**
     * @param item
     * @throws DAOException
     */
    @Override
    public void remove(ItemEntity item) throws DAOException {
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            session.beginTransaction();
            session.remove(item);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new DAOException("Error in Remove ItemMethod", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public ArrayList<ItemEntity> getAll() throws DAOException {
        List<ItemEntity> itemList;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            itemList = session.createQuery(GETALL, ItemEntity.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get All Items", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        ArrayList<ItemEntity> itemEntityArrayList = new ArrayList<>(itemList);
        return itemEntityArrayList;
    }


    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public ItemEntity getById(int id) throws DAOException {
        ItemEntity item = null;
        Session session = null;
        try {
            session = HibernateUtil.abrirSession();
            item = session.createQuery(GETBYID, ItemEntity.class)
                    .setParameter("code", id)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error in Get Item by Id", ex);
        } finally {
            if (session != null) {
                HibernateUtil.cerrarSession(session);
            }
        }
        return item;
    }

}
