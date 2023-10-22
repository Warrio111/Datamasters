package com.datamasters.modelo;

public class CustomerList extends List<Customer> {
    public CustomerList() {
        super(); // Call the constructor of the parent class
    }
    public void addCustomer(Customer customer) { add(customer); }

    public void removeCustomer(Customer customer) { delete(customer); }

    public List<Customer> getCustomers() { return this; }

    public List<Customer> filterCustomersByType(CustomerType type) {
        List<Customer> filteredCustomers = new List<>();
        for (int i = 0; i < getSize(); i++) {
            Customer customer = getAt(i);
            if (customer.getCustomerType() == type) {
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
}
