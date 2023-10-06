package com.datamasters.modelo;

public class CustomerList {
    private final List<Customer> customers;

    public CustomerList() {
        customers = new List<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.delete(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Customer> filterCustomersByType(CustomerType type) {
        List<Customer> filteredCustomers = new List<>();
        for (int i = 0; i < customers.getSize(); i++) {
            Customer customer = customers.getAt(i);
            if (customer.getCustomerType() == type) {
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
}
