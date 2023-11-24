/*
package com.datamasters.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class OrdersEntityPK implements Serializable {
    @Column(name = "orderNumber")
    @Id
    private int orderNumber;
    @Column(name = "Customer_id")
    @Id
    private int customerId;
    @Column(name = "Item_code")
    @Id
    private int itemCode;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

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

        OrdersEntityPK that = (OrdersEntityPK) o;

        if (orderNumber != that.orderNumber) return false;
        if (customerId != that.customerId) return false;
        if (itemCode != that.itemCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNumber;
        result = 31 * result + customerId;
        result = 31 * result + itemCode;
        return result;
    }

    @Override
    public String toString() {
        return "OrdersEntityPK{" +
                "orderNumber=" + orderNumber +
                ", customerId=" + customerId +
                ", itemCode=" + itemCode +
                '}';
    }
}
*/
