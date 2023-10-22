package com.datamasters.modelo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PremiumCustomerTest {

    private PremiumCustomer customer;

    @Before
    public void setUp() {
        customer = new PremiumCustomer("Jane Smith", "456 Oak St", "P789", "jane@example.com", 99.99, 10.0);
    }

    @Test
    public void testTypeCustomer() {
        assertEquals(CustomerType.PREMIUM, customer.typeCustomer());
    }

    @Test
    public void testCalculateMembershipFee() {
        assertEquals(99.99, customer.calculateMembershipFee(), 0.0);
    }

    @Test
    public void testShippingDiscount() {
        assertEquals(10.0, customer.shippingDiscount(), 0.0);
    }

    @Test
    public void testToString() {
        String expected = "PremiumCustomer{" +
                "name='Jane Smith', " +
                "address='456 Oak St', " +
                "id='P789', " +
                "email='jane@example.com', " +
                "customerType=PREMIUM, " +
                "membershipFee=99.99, " +
                "shippingDiscount=10.0}";
        assertEquals(expected, customer.toString());
    }

    @Test
    public void testGetName() {
        assertEquals("Jane Smith", customer.getName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("456 Oak St", customer.getAddress());
    }

    @Test
    public void testGetId() {
        assertEquals("P789", customer.getId());
    }

    @Test
    public void testGetEmail() {
        assertEquals("jane@example.com", customer.getEmail());
    }

    @Test
    public void testGetMembershipFee() {
        assertEquals(99.99, customer.getMembershipFee(), 0.0);
    }

    @Test
    public void testGetCustomerType() {
        assertEquals(CustomerType.PREMIUM, customer.getCustomerType());
    }

    @Test
    public void testGetShippingDiscount() {
        assertEquals(10.0, customer.getShippingDiscount(), 0.0);
    }
}

