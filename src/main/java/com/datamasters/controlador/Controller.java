package com.datamasters.controlador;

import com.datamasters.modelo.Data;
import com.datamasters.modelo.Customer;
import com.datamasters.modelo.Order;
import com.datamasters.modelo.Item;

public class Controller {
    private final Data data;

    public Controller() {
        this.data = new Data();
    }

    public Controller(Data data) {
        this.data = data;
    }

    public void addCustomer(Customer customer) {
        data.getCustomers().addCustomer(customer);
    }

    public void removeCustomer(Customer customer) {
        data.getCustomers().removeCustomer(customer);
    }

    public void addOrder(Order order) {
        data.getOrders().addOrder(order);
    }

    public void removeOrder(Order order) {
        data.getOrders().removeOrder(order);
    }

    public void addItem(Item item) {
        data.getItems().addItem(item);
    }

    public void removeItem(Item item) {
        data.getItems().removeItem(item);
    }

    public Data getData() {
        return data;
    }
}
