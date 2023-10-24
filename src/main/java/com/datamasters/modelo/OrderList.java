package com.datamasters.modelo;

public class OrderList extends List<Orders> {
    public OrderList() {
        super(); // Call the constructor of the parent class
    }

    public void addOrder(Orders order) {
        add(order);
    }

    public void removeOrder(Orders order) {
        delete(order);
    }

    public List<Orders> getOrders() {
        return this;
    }

    public List<Orders> filterOrdersByCustomer(Customer customer) {
        List<Orders> filteredOrders = new List<>();
        for (int i = 0; i < getSize(); i++) {
            Orders order = getAt(i);
            if (order.getCustomer() == customer) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
}
