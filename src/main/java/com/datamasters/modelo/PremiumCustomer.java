package com.datamasters.modelo;

public class PremiumCustomer extends Customer {
    public PremiumCustomer(String name, String address, String id, String email, double membershipFee, double shippingDiscount) {
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
        return "PremiumCustomer{" +
                "name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", id='" + getId() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", customerType=" + getCustomerType() +
                ", membershipFee=" + getMembershipFee() +
                ", shippingDiscount=" + getShippingDiscount() +
                '}';
    }
}
