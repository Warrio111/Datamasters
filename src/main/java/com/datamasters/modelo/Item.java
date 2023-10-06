package com.datamasters.modelo;

public abstract class Item {
    private String code;
    private String description;
    private double sellingPrice;
    private double shippingCost;
    private int preparationTimeMinutes;

    // Constructor por defecto
    public Item() {
    }

    // Constructor con parámetros
    public Item(String code, String description, double sellingPrice, double shippingCost, int preparationTimeMinutes) {
        this.code = code;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.shippingCost = shippingCost;
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    // Constructor de copia
    public Item(Item item) {
        this.code = item.code;
        this.description = item.description;
        this.sellingPrice = item.sellingPrice;
        this.shippingCost = item.shippingCost;
        this.preparationTimeMinutes = item.preparationTimeMinutes;
    }

    // Métodos para obtener y establecer los atributos

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public int getPreparationTimeMinutes() {
        return this.preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(int preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + this.code + '\'' +
                ", description='" + this.description + '\'' +
                ", sellingPrice=" + this.sellingPrice +
                ", shippingCost=" + this.shippingCost +
                ", preparationTimeMinutes=" + this.preparationTimeMinutes +
                '}';
    }
}

