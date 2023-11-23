package com.datamasters.modelo;

import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name = "item", schema = "onlinestore")
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "code", nullable = false)
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "sellingPrice", nullable = false, precision = 0)
    private double sellingPrice;

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Basic
    @Column(name = "shippingCost", nullable = false, precision = 0)
    private double shippingCost;

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (code != that.code) return false;
        if (Double.compare(sellingPrice, that.sellingPrice) != 0) return false;
        if (Double.compare(shippingCost, that.shippingCost) != 0) return false;
        if (preparationTimeMinutes != that.preparationTimeMinutes) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = code;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(sellingPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(shippingCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + preparationTimeMinutes;
        return result;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", shippingCost=" + shippingCost +
                ", preparationTimeMinutes=" + preparationTimeMinutes +
                '}';
    }
}
