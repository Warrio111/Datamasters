package com.datamasters.controlador;

import com.datamasters.modelo.CustomerList;
import com.datamasters.modelo.CustomerType;
import com.datamasters.modelo.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.datamasters.modelo.Customer;

public class TestController {
    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer("John Doe", "123 Main St", "C12345", "john@example.com", CustomerType.STANDARD, 0.0, 0.0) {
            @Override
            public CustomerType typeCustomer() {
                return null;
            }

            @Override
            public double calculateMembershipFee() {
                return 0;
            }

            @Override
            public double shippingDiscount() {
                return 0;
            }
        };
        controller.addCustomer(customer);

        CustomerList customerList = controller.getData().getCustomers();

        Customer addedCustomer = customerList.getCustomers().getAt(0);

        assertNotNull(addedCustomer);
        assertEquals("John Doe", addedCustomer.getName());
    }

    @Test
    public void testRemoveCustomer() {
        Customer customer = new Customer("Jane Smith", "456 Oak St", "C67890", "jane@example.com", CustomerType.PREMIUM, 50.0, 10.0) {
            @Override
            public CustomerType typeCustomer() {
                return null;
            }

            @Override
            public double calculateMembershipFee() {
                return 0;
            }

            @Override
            public double shippingDiscount() {
                return 0;
            }
        };
        assertEquals(0, controller.getData().getCustomers().getCustomers().getSize());
        assertEquals(customer.getCustomerType() , CustomerType.PREMIUM);
        assertEquals(customer.getMembershipFee() , 50.0, 0.0);
        controller.addCustomer(customer);

        CustomerList customerList = controller.getData().getCustomers();

        customerList.removeCustomer(customer);

        List<Customer> innerList = customerList.getCustomers();
        assertTrue(innerList.isEmpty());
    }

    // Aqui hay que anadir mas tests para las otras clases.
}
