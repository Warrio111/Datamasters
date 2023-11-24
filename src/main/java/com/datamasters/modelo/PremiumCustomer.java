package com.datamasters.modelo;

public class PremiumCustomer extends Customer {
    public PremiumCustomer(String name, String address, String id, String email, double membershipFee,
            double shippingDiscount) {
        super(name, address, id, email, CustomerType.PREMIUM, membershipFee, shippingDiscount);
    }

    @Override
    public CustomerType typeCustomer() {
        return CustomerType.PREMIUM;
    }

    @Override
    public double calculateMembershipFee() {
        return getMembershipFee(); // La tarifa de membresía para clientes premium.
    }

    @Override
    public double shippingDiscount() {
        return getShippingDiscount(); // El descuento de envío para clientes premium.
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nombre: ").append(getName()).append("\n");
        stringBuilder.append("Dirección: ").append(getAddress()).append("\n");
        stringBuilder.append("ID: ").append(getId()).append("\n");
        stringBuilder.append("Correo electrónico: ").append(getEmail()).append("\n");
        stringBuilder.append("Tipo de cliente: ").append(getCustomerType()).append("\n");
        stringBuilder.append("Tarifa de membresía: ").append(String.format("%.2f", getMembershipFee())).append("\n");
        stringBuilder.append("Descuento de envío: ").append(String.format("%.2f", getShippingDiscount())).append("\n");
        stringBuilder.append("\n----------------------------------------").append("\n");
        return stringBuilder.toString();
    }

}
