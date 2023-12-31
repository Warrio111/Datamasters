package com.datamasters.modelo;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders", schema = "onlinestore")
//@IdClass(OrdersEntityPK.class)
public class OrdersEntity implements EntityBase{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "orderNumber", insertable = true, nullable = false)
    private int orderNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer_id", referencedColumnName = "id",insertable = false, updatable = false)
    private CustomerEntity customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Item_code", referencedColumnName = "code",insertable = false, updatable = false)
    private ItemEntity item;

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
        this.preparationTimeMinutes = preparationTimeMinutes*this.quantityUnits;
    }



    @Column(name = "Customer_id", nullable = false)
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



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

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
        if (customer != null) {
            this.customerId = customer.getId();
        }
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
        if (item != null) {
            this.itemCode = item.getCode();
        }
    }
    public ItemEntity getItem() {
        return item;
    }

    public Set<String> getProperties() {
        Set<String> properties = new HashSet<>();
        properties.add("orderNumber");
        properties.add("quantityUnits");
        properties.add("orderDateTime");
        properties.add("preparationTimeMinutes");
        properties.add("customerId");
        properties.add("itemCode");
        return properties;
    }
}
