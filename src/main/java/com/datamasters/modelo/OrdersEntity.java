package com.datamasters.modelo;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@jakarta.persistence.Table(name = "orders", schema = "onlinestore")
@IdClass(OrdersEntityPK.class)
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "orderNumber", nullable = false)
    private int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Basic
    @Column(name = "quantityUnits", nullable = false)
    private int quantityUnits;

    public int getQuantityUnits() {
        return quantityUnits;
    }

    public void setQuantityUnits(int quantityUnits) {
        this.quantityUnits = quantityUnits;
    }

    @Basic
    @Column(name = "orderDateTime", nullable = false)
    private Timestamp orderDateTime;

    public Timestamp getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Timestamp orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    @Basic
    @Column(name = "preparationTimeMinutes", nullable = false)
    private int preparationTimeMinutes;

    public int getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(int preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Customer_id", nullable = false)
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Item_code", nullable = false)
    private int itemCode;

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderNumber != that.orderNumber) return false;
        if (quantityUnits != that.quantityUnits) return false;
        if (preparationTimeMinutes != that.preparationTimeMinutes) return false;
        if (customerId != that.customerId) return false;
        if (itemCode != that.itemCode) return false;
        if (orderDateTime != null ? !orderDateTime.equals(that.orderDateTime) : that.orderDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNumber;
        result = 31 * result + quantityUnits;
        result = 31 * result + (orderDateTime != null ? orderDateTime.hashCode() : 0);
        result = 31 * result + preparationTimeMinutes;
        result = 31 * result + customerId;
        result = 31 * result + itemCode;
        return result;
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "orderNumber=" + orderNumber +
                ", quantityUnits=" + quantityUnits +
                ", orderDateTime=" + orderDateTime +
                ", preparationTimeMinutes=" + preparationTimeMinutes +
                ", customerId=" + customerId +
                ", itemCode=" + itemCode +
                '}';
    }
}
