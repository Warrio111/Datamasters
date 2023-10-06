package com.datamasters.modelo;

public class OrderList {
    private final List<Order> orders;

    public OrderList() {
        orders = new List<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.delete(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> filterOrdersByCustomer(Customer customer) {
        List<Order> filteredOrders = new List<>();
        for (int i = 0; i < orders.getSize(); i++) {
            Order order = orders.getAt(i);
            if (order.getCustomer() == customer) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
}
