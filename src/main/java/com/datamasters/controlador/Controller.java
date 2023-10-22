package com.datamasters.controlador;

import com.datamasters.modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    private final Data data;
    private final ExceptionHandler exceptionHandler;

    public Controller() {
        this.data = new Data();
        this.exceptionHandler = new ExceptionHandler();
    }

    public Controller(Data data, ExceptionHandler exceptionHandler) {
        this.data = data;
        this.exceptionHandler = exceptionHandler;
    }

    public void addCustomer(Customer customer) {
        try {
            data.getCustomers().addCustomer(customer);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeCustomer(Customer customer) {
        try {
            data.getCustomers().removeCustomer(customer);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void addOrder(Order order) {
        try {
            data.getOrders().addOrder(order);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeOrder(Order order) {
        try {
            data.getOrders().removeOrder(order);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void addItem(Item item) {
        try {
            data.getItems().addItem(item);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeItem(Item item) {
        try {
            data.getItems().removeItem(item);
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public Data getData() {
        return data;
    }

    public ArrayList<Customer> getCustomers() {
        return data.getCustomers().getArrayList();
    }
    public ArrayList<Customer> getCustomerByType(CustomerType type) {
        return data.getCustomers().filterCustomersByType(type).getArrayList();
    }
    public Customer findCustomerById(String customerId) {

        for (Customer customer : getCustomers()) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
    public ArrayList<Item> getItems() {
        return data.getItems().getArrayList();
    }

    public Item findItemByCode(String itemCode) {
        for(Item item : getItems()) {
            if (item.getCode().equals(itemCode)) {
                return item;
            }
        }
        return null;
    }

    public void deleteOrderByNumber(int orderNumber) {
        Order order = findOrderByNumber(orderNumber);
        if (order != null && !order.isCancelable(LocalDateTime.now())) {
            removeOrder(order);
        }
    }
    public Order findOrderByNumber(int orderNumber) {
        for (Order order : getOrders()) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }
    public  ArrayList<Order> getOrders() {
        return data.getOrders().getArrayList();
    }
    public ArrayList<Order> getPendingOrders(String customer) {
        ArrayList <Order> pendingOrders = new ArrayList<>();
        for (Order order : getOrders()) {
            if (order.getCustomer().getId().equals(customer) && !order.orderIsSent(LocalDateTime.now())) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    public ArrayList<Order> getSentOrders(String customer) {
        ArrayList <Order> sentOrders = new ArrayList<>();
        for (Order order : getOrders()) {
            if (order.getCustomer().getId().equals(customer) && order.orderIsSent(LocalDateTime.now())) {
                sentOrders.add(order);
            }
        }
        return sentOrders;
    }

}



