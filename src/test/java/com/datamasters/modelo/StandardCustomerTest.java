package com.datamasters.modelo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StandardCustomerTest {

    private StandardCustomer customer;

    @Before
    public void setUp() {
        customer = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
    }

    @Test
    public void testTypeCustomer() {
        assertEquals(CustomerType.STANDARD, customer.typeCustomer());
    }

    @Test
    public void testCalculateMembershipFee() {
        assertEquals(0.0, customer.calculateMembershipFee(), 0.0);
    }

    @Test
    public void testShippingDiscount() {
        assertEquals(0.0, customer.shippingDiscount(), 0.0);
    }

    @Test
    public void testToString() {
        String expected = "StandardCustomer{" +
                "name='John Doe', " +
                "address='123 Main St', " +
                "id='C123', " +
                "email='john@example.com', " +
                "customerType=STANDARD}";
        assertEquals(expected, customer.toString());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    public void testGetId() {
        assertEquals("C123", customer.getId());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john@example.com", customer.getEmail());
    }

    @Test
    public void testGetMembershipFee() {
        assertEquals(0.0, customer.getMembershipFee(), 0.0);
    }

    @Test
    public void testGetCustomerType() {
        assertEquals(CustomerType.STANDARD, customer.getCustomerType());
    }

    @Test
    public void testGetShippingDiscount() {
        assertEquals(0.0, customer.getShippingDiscount(), 0.0);
    }
}

