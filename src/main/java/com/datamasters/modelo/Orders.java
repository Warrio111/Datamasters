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
        this.preparationTimeMinutes = item.getPreparationTimeMinutes() * quantityUnits;
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
        return this.preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(int preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
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
        LocalDateTime cutoffTime = this.orderDateTime.plusMinutes(this.preparationTimeMinutes);
        return currentTime.isAfter(cutoffTime);
    }

    public boolean isCancelable(LocalDateTime currentTime) {
        LocalDateTime cutoffTime = this.orderDateTime.plusMinutes(this.preparationTimeMinutes);
        return currentTime.isAfter(cutoffTime);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Numero de pedido: ").append(orderNumber).append("\n");
        stringBuilder.append("Cliente: ").append(customer.getId()).append(", ").append(customer.getName()).append("\n");
        stringBuilder.append("Articulo: ").append(item.getCode()).append(", ").append(item.getDescription())
                .append("\n");
        stringBuilder.append("Cantidad de unidades: ").append(quantityUnits).append("\n");
        stringBuilder.append("Precio del articulo: ").append(item.getSellingPrice()).append("\n");
        stringBuilder.append("Precio del pedido: ").append(calculateOrderPrice()).append("\n");
        stringBuilder.append("Fecha y hora del pedido: ").append(orderDateTime).append("\n");
        stringBuilder.append("Tiempo de preparacion (minutos): ").append(preparationTimeMinutes).append("\n");
        stringBuilder.append("Es posible cancelar el pedido: ").append(isCancelable(LocalDateTime.now())).append("\n");
        stringBuilder.append("Costo de envio: ").append(item.getShippingCost()).append("\n");
        stringBuilder.append("El pedido ha sido enviado: ").append(orderIsSent(LocalDateTime.now())).append("\n");
        stringBuilder.append("\n----------------------------------------").append("\n");
        return stringBuilder.toString();
    }

}
