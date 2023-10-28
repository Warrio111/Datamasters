package com.datamasters.modelo;

import java.time.LocalDateTime;

public class Orders {
    private int orderNumber;
    private Customer customer;
    private Item item;
    private int quantityUnits;
    private LocalDateTime orderDateTime;
    private int preparationTimeMinutes;

    public Orders(int orderNumber, Customer customer, Item item, int quantityUnits, LocalDateTime orderDateTime) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.item = item;
        this.quantityUnits = quantityUnits;
        this.orderDateTime = orderDateTime;
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
        return item.getPreparationTimeMinutes();
    }

    public void setPreparationTimeMinutes(int preparationTimeMinutes) {
        item.setPreparationTimeMinutes(preparationTimeMinutes);
    }

    public double calculateOrderPrice() {
        if (quantityUnits < 0) {
            throw new IllegalArgumentException("La cantidad de unidades no puede ser negativa.");
        }
        double itemPrice = item.getSellingPrice() * quantityUnits;
        double shippingCost = item.getShippingCost();
        return itemPrice + shippingCost;
    }

    public boolean orderIsSent(LocalDateTime currentTime) {
        LocalDateTime cutoffTime = this.orderDateTime.plusMinutes(getPreparationTimeMinutes() * this.quantityUnits);
        return currentTime.isAfter(cutoffTime);
    }

    public boolean isCancelable(LocalDateTime currentTime) {
        LocalDateTime cutoffTime = this.orderDateTime.plusMinutes(getPreparationTimeMinutes() * this.quantityUnits);
        return currentTime.isAfter(cutoffTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customer=" + customer.getId() + customer.getName() +
                ", item=" + item.getCode() +item.getDescription() +
                ", quantityUnits=" + quantityUnits +
                ", Item price=  " + item.getSellingPrice()+
                ", Order price= " + calculateOrderPrice() +
                ", orderDateTime=" + orderDateTime +
                ", preparationTimeMinutes=" + getPreparationTimeMinutes()*quantityUnits +
                ", orderIsCancelable=" + isCancelable(LocalDateTime.now()) +
                ", shippingCost= " + item.getShippingCost()+
                ", orderIsSent=" + orderIsSent(LocalDateTime.now()) +
                '}';
    }
}
