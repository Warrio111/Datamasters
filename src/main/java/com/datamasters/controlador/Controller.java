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
    public DAOFactory getDao() {
        return dao;
    }

    public ArrayList<Customer> getCustomers() throws DAOException, SQLException {

        return dao.getCustomerDAO().getAll().getArrayList();

    }
    public Customer getCustomerByType(CustomerType type) throws DAOException, SQLException {

        try {
            return dao.getCustomerDAO().getCustomerType(String.valueOf(type));
        } catch ( Exception e) {
            return null;
        }
    }
    public ArrayList<Customer> getCustomersByType(CustomerType type) throws DAOException, SQLException {
        try {
            return dao.getCustomerDAO().getCustomerByType(String.valueOf(type)).getArrayList();
        } catch ( Exception e) {
            return null;
        }
    }
    public Customer findCustomerById(String customerId) throws DAOException, SQLException {
        try {
            return dao.getCustomerDAO().getById(Integer.parseInt(customerId));
        } catch ( Exception e) {
            return null;
        }
    }
    public ArrayList<ItemEntity> getItems() throws DAOException {
        return dao.getItemDAO().getAll().getArrayList();
    }

    public Item findItemByCode(String itemCode) throws DAOException {
        try {
            return dao.getItemDAO().getById(Integer.parseInt(itemCode));
        } catch ( Exception e) {
            return null;
        }
    }

    public void deleteOrderByNumber(int orderNumber) throws DAOException {
        OrdersEntity order = findOrderByNumber(orderNumber);
        if (order != null && !order.isCancelable(LocalDateTime.now())) {
            dao.getOrdersDAO().remove(order);
        }
    }
    public Orders findOrderByNumber(int orderNumber) throws DAOException {
        try {
            return dao.getOrdersDAO().getById(orderNumber);
        } catch ( Exception e) {
            return null;
        }
    }
    public  ArrayList<Orders> getOrders() throws DAOException {
        return dao.getOrdersDAO().getAll().getArrayList();
    }
    public ArrayList<Orders> getPendingOrders(String customer) throws DAOException {

        ArrayList <Orders> pendingOrders = new ArrayList<>();
        for (Orders order : getOrders()) {
            if (order.getCustomer().getId().equals(customer) && !order.orderIsSent(LocalDateTime.now())) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    public ArrayList<Orders> getSentOrders(String customer) throws DAOException {
        ArrayList <Orders> sentOrders = new ArrayList<>();
        for (Orders order : getOrders()) {
            if (order.getCustomer().getId().equals(customer) && order.orderIsSent(LocalDateTime.now())) {
                sentOrders.add(order);
            }
        }
        return sentOrders;
    }

}



