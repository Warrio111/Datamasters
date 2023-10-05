package com.datamasters.modelo;

import java.time.LocalDateTime;

public class Order {
    private int orderNumber;
    private Customer customer;
    private Item item;
    private int quantityUnits;
    private LocalDateTime orderDateTime;
    private int preparationTimeMinutes;

    public Order(int orderNumber, Customer customer, Item item, int quantityUnits, LocalDateTime orderDateTime, int preparationTimeMinutes) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.item = item;
        this.quantityUnits = quantityUnits;
        this.orderDateTime = orderDateTime;
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantityUnits() {
        return quantityUnits;
    }

    public void setQuantityUnits(int quantityUnits) {
        this.quantityUnits = quantityUnits;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public int getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(int preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public double calculateOrderPrice() {
        double itemPrice = item.getSellingPrice() * quantityUnits;
        double shippingCost = item.getShippingCost();
        return itemPrice + shippingCost;
    }

    public boolean isCancelable() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime cutoffTime = orderDateTime.minusMinutes(preparationTimeMinutes);
        return currentTime.isBefore(cutoffTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customer=" + customer +
                ", item=" + item +
                ", quantityUnits=" + quantityUnits +
                ", orderDateTime=" + orderDateTime +
                ", preparationTimeMinutes=" + preparationTimeMinutes +
                '}';
    }
}
