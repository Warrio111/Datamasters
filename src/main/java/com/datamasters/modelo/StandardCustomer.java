package com.datamasters.modelo;

public class StandardCustomer extends Customer {
    public StandardCustomer(String name, String address, String id, String email) {
        super(name, address, id, email, CustomerType.STANDARD, 0.0, 0.0);
    }

    @Override
    public CustomerType typeCustomer() {
        return CustomerType.STANDARD;
    }

    @Override
    public double calculateMembershipFee() {
        return 0.0; // La membresía estándar no tiene tarifa.
    }

    @Override
    public double shippingDiscount() {
        return 0.0; // La membresía estándar no tiene descuento de envío.
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nombre: ").append(getName()).append("\n");
        stringBuilder.append("Dirección: ").append(getAddress()).append("\n");
        stringBuilder.append("ID: ").append(getId()).append("\n");
        stringBuilder.append("Correo electrónico: ").append(getEmail()).append("\n");
        stringBuilder.append("Tipo de cliente: ").append(getCustomerType()).append("\n");
        stringBuilder.append("\n----------------------------------------").append("\n");
        return stringBuilder.toString();
    }

}
