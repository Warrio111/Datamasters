package com.datamasters.controlador;

import com.datamasters.DAO.CustomerDAO;
import com.datamasters.DAO.DAOException;
import com.datamasters.DAO.DAOFactory;
import com.datamasters.DAO.DaoImpl.OrderDaoImpl;
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

    public void addCustomer(Customer customer) {
        try {
            dao.getCustomerDAO().insert(customer);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeCustomer(Customer customer) {
        try {
            dao.getCustomerDAO().remove(customer);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void addOrder(Orders order) {
        try {
            dao.getOrdersDAO().insert(order);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeOrder(Orders order) {
        try {
            dao.getOrdersDAO().remove(order);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void addItem(Item item) {
        try {
            dao.getItemDAO().insert(item);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeItem(Item item) {
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
        return dao.getCustomerDAO().getCustomerType(String.valueOf(type));
    }
    public ArrayList<Customer> getCustomersByType(CustomerType type) throws DAOException, SQLException {
        return dao.getCustomerDAO().getCustomerByType(String.valueOf(type)).getArrayList();
    }
    public Customer findCustomerById(String customerId) throws DAOException, SQLException {
        return dao.getCustomerDAO().getById(Integer.parseInt(customerId));
    }
    public ArrayList<Item> getItems() throws DAOException {
        return dao.getItemDAO().getAll().getArrayList();
    }

    public Item findItemByCode(String itemCode) throws DAOException {
        return dao.getItemDAO().getById(Integer.parseInt(itemCode));
    }

    public void deleteOrderByNumber(int orderNumber) throws DAOException {
        Orders order = findOrderByNumber(orderNumber);
        if (order != null && !order.isCancelable(LocalDateTime.now())) {
            dao.getOrdersDAO().remove(order);
        }
    }
    public Orders findOrderByNumber(int orderNumber) throws DAOException {

        return dao.getOrdersDAO().getById(orderNumber);
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



