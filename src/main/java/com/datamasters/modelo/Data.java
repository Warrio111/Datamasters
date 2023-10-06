package com.datamasters.modelo;

public class Data {
    private final CustomerList customersList;
    private final OrderList ordersList;
    private final ItemList itemsList;

    public Data() {
        customersList = new CustomerList();
        ordersList = new OrderList();
        itemsList = new ItemList();
    }

    public Data(CustomerList customersList, OrderList ordersList, ItemList itemsList) {
        this.customersList = customersList;
        this.ordersList = ordersList;
        this.itemsList = itemsList;
    }

    public CustomerList getCustomers() {
        return customersList;
    }

    public OrderList getOrders() {
        return ordersList;
    }

    public ItemList getItems() {
        return itemsList;
    }

    @Override
    public String toString() {
        return "Data{" +
                "customersList=" + customersList +
                ", ordersList=" + ordersList +
                ", itemsList=" + itemsList +
                '}';
    }
}
