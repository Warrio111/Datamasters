package com.datamasters.DAO.DaoImpl;
import com.datamasters.DAO.*;


import com.datamasters.modelo.CustomerEntity;
import com.datamasters.modelo.ItemEntity;
import com.datamasters.modelo.OrdersEntity;
import com.datamasters.modelo.*;
import org.hibernate.Session;
import java.sql.*;

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
    public List<OrdersEntity> getAll() throws DAOException {
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
        return orderList;
    }

    public Orders convertir(ResultSet rs) throws DAOException {
        Customer c = null;
        try {
            String name = rs.getString("name");
            String address = rs.getString("address");
            String id = rs.getString("id");
            String email = rs.getString("email");
            String customerType = rs.getString("customerType");
            double membershipFee = rs.getDouble("membershipFee");
            double shippingDiscount = rs.getDouble("shippingDiscount");
            int orderNumber = rs.getInt("orderNumber");
            int quantityUnits = rs.getInt("quantityUnits");
            Timestamp orderDateTimeTimestamp = rs.getTimestamp("orderDateTime");
            LocalDateTime orderDateTime = orderDateTimeTimestamp.toLocalDateTime();
            String code = rs.getString("code");
            String description = rs.getString("description");
            double sellingPrice = rs.getDouble("sellingPrice");
            double shippingCost = rs.getDouble("shippingCost");
            int preparationTimeMinutes = rs.getInt("preparationTimeMinutes");

            Item it = new Item(code, description, sellingPrice, shippingCost, preparationTimeMinutes);

            if (customerType.equals("PREMIUM")) {

                c = new PremiumCustomer(name, address, id, email, membershipFee, shippingDiscount);
            } else {
                c = new StandardCustomer(name, address, id, email);
            }
            Orders order = new Orders(orderNumber, c, it, quantityUnits, orderDateTime);

            return order;

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Can't converted");
        }
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
        // TODO: Implementar lógica para verificar si el pedido está enviado
        return false;
    }

    @Override
    public boolean orderIsCancelable(int id) throws DAOException {
        // TODO: Implementar lógica para verificar si el pedido se puede cancelar
        return false;
    }

}