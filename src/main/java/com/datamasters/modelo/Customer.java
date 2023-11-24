package com.datamasters.modelo;

public abstract class Customer {
    private String name;
    private String address;
    private String id;
    private String email;
    private CustomerType customerType;
    private double membershipFee;
    private double shippingDiscount;

    public Customer(String name, String address, String id, String email, CustomerType customerType,
            double membershipFee, double shippingDiscount) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.email = email;
        this.customerType = customerType;
        this.membershipFee = membershipFee;
        this.shippingDiscount = shippingDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getMembershipFee() {
        return this.membershipFee;
    }

    public void setMembershipFee(double membershipFee) {
        this.membershipFee = membershipFee;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public double getShippingDiscount() {
        return shippingDiscount;
    }

    public void setShippingDiscount(double shippingDiscount) {
        this.shippingDiscount = shippingDiscount;
    }

    public abstract CustomerType typeCustomer();

    public abstract double calculateMembershipFee();

    public abstract double shippingDiscount();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nombre: ").append(name).append("\n");
        stringBuilder.append("Direccion: ").append(address).append("\n");
        stringBuilder.append("ID: ").append(id).append("\n");
        stringBuilder.append("Correo electronico: ").append(email).append("\n");
        stringBuilder.append("Tipo de cliente: ").append(customerType).append("\n");
        stringBuilder.append("Tarifa de membresia: ").append(String.format("%.2f", membershipFee)).append("\n");
        stringBuilder.append("Descuento de envio: ").append(String.format("%.2f", shippingDiscount)).append("\n");
        stringBuilder.append("\n----------------------------------------").append("\n");
        return stringBuilder.toString();
    }
}
