package com.datamasters.controlador;

import com.datamasters.DAO.DAOException;
import com.datamasters.DAO.DAOFactory;
import com.datamasters.modelo.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    private final DAOFactory dao;
    private final ExceptionHandler exceptionHandler;

    public Controller() throws DAOException {
        this.dao = DAOFactory.getDAOFactory(1);
        this.exceptionHandler = new ExceptionHandler();
    }

    public Controller(DAOFactory dao, ExceptionHandler exceptionHandler) {
        this.dao = dao;
        this.exceptionHandler = exceptionHandler;
    }
    public DAOFactory getDao() {
        return dao;
    }

    public void addCustomer(CustomerEntity customer) {
        try {
            dao.getCustomerDAO().insert(customer);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeCustomer(CustomerEntity customer) {
        try {
            dao.getCustomerDAO().remove(customer);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void addOrder(OrdersEntity order) {
        try {
            dao.getOrdersDAO().insert(order);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeOrder(OrdersEntity order) {
        try {
            dao.getOrdersDAO().remove(order);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void addItem(ItemEntity item) {
        try {
            dao.getItemDAO().insert(item);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeItem(ItemEntity item) {
        try {
            dao.getItemDAO().remove(item);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }
    public ArrayList<CustomerEntity> getCustomers() throws DAOException, SQLException {

        return dao.getCustomerDAO().getAll();

    }
    public ArrayList<CustomerEntity> getCustomersByType(CustomerType type) throws DAOException, SQLException {
        try {
            return dao.getCustomerDAO().getCustomerByType(type);
        } catch ( Exception e) {
            return null;
        }
    }
    public CustomerEntity findCustomerById(int customerId) throws DAOException, SQLException {
        try {
            return dao.getCustomerDAO().getById(customerId);
        } catch ( Exception e) {
            return null;
        }
    }
    public ArrayList<ItemEntity> getItems() throws DAOException {
        return dao.getItemDAO().getAll();
    }

    public ItemEntity findItemByCode(int itemCode) throws DAOException {
        try {
            return dao.getItemDAO().getById(itemCode);
        } catch ( Exception e) {
            return null;
        }
    }

    public boolean deleteOrderByNumber(int orderNumber) throws DAOException {
        OrdersEntity order = findOrderByNumber(orderNumber);
        if (order != null && dao.getOrdersDAO().orderIsCancelable(order.getOrderNumber())) {
            dao.getOrdersDAO().remove(order);
            return true;
        }
        return false;
    }
    public OrdersEntity findOrderByNumber(int orderNumber) throws DAOException {
        try {
            return dao.getOrdersDAO().getById(orderNumber);
        } catch ( Exception e) {
            return null;
        }
    }
    public  ArrayList<OrdersEntity> getOrders() throws DAOException {
        return dao.getOrdersDAO().getAll();
    }
    public ArrayList<OrdersEntity> getPendingOrders(int customerID) throws DAOException {

        ArrayList <OrdersEntity> pendingOrders = new ArrayList<>();
        for (OrdersEntity order : getOrders()) {
            if (order.getCustomer().getId() == customerID && !dao.getOrdersDAO().orderIsSent(order.getOrderNumber())) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    public ArrayList<OrdersEntity> getSentOrders(int customerID) throws DAOException {
        ArrayList <OrdersEntity> sentOrders = new ArrayList<>();
        for (OrdersEntity order : getOrders()) {
            if (order.getCustomer().getId() == customerID && dao.getOrdersDAO().orderIsSent(order.getOrderNumber())) {
                sentOrders.add(order);
            }
        }
        return sentOrders;
    }

}



