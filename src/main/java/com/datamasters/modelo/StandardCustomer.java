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
        return "StandardCustomer{" +
                "name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", id='" + getId() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", customerType=" + getCustomerType() +
                '}';
    }
}
