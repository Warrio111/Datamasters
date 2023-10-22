package com.datamasters.modelo;

public class OrderList extends List<Order> {
    public OrderList() {
        super(); // Call the constructor of the parent class
    }

    public void addOrder(Order order) {
        add(order);
    }

    public void removeOrder(Order order) {
        delete(order);
    }

    public List<Order> getOrders() {
        return this;
    }

    public List<Order> filterOrdersByCustomer(Customer customer) {
        List<Order> filteredOrders = new List<>();
        for (int i = 0; i < getSize(); i++) {
            Order order = getAt(i);
            if (order.getCustomer() == customer) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
}
