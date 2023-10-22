package com.datamasters.modelo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class OrderTest {

    private Order order;
    private Customer customer;
    private Item item;

    @Before
    public void setUp() {
        customer = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        item = new Item("12345", "Sample Item", 19.99, 5.0, 30);
        LocalDateTime orderDateTime = LocalDateTime.now().plusMinutes(2);
        order = new Order(1, customer, item, 3, orderDateTime);
    }
    @BeforeEach
    public void setUpEach() {
        customer = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        item = new Item("12345", "Sample Item", 19.99, 5.0, 5);
        LocalDateTime orderDateTime = LocalDateTime.now().plusSeconds(2);
        order = new Order(1, customer, item, 3, orderDateTime);
    }

    @Test
    public void testGetOrderNumber() {
        assertEquals(1, order.getOrderNumber());
    }

    @Test
    public void testSetOrderNumber() {
        order.setOrderNumber(2);
        assertEquals(2, order.getOrderNumber());
    }

    @Test
    public void testGetCustomer() {
        assertEquals(customer, order.getCustomer());
    }

    @Test
    public void testSetCustomer() {
        Customer newCustomer = new StandardCustomer("Jane Smith", "456 Oak St", "C456", "jane@example.com");
        order.setCustomer(newCustomer);
        assertEquals(newCustomer, order.getCustomer());
    }

    @Test
    public void testGetItem() {
        assertEquals(item, order.getItem());
    }

    @Test
    public void testSetItem() {
        Item newItem = new Item("67890", "New Item", 29.99, 7.0, 45);
        order.setItem(newItem);
        assertEquals(newItem, order.getItem());
    }

    @Test
    public void testGetQuantityUnits() {
        assertEquals(3, order.getQuantityUnits());
    }

    @Test
    public void testSetQuantityUnits() {
        order.setQuantityUnits(5);
        assertEquals(5, order.getQuantityUnits());
    }

    @Test
    public void testGetOrderDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now().plusMinutes(1);
        long minutesDiff = ChronoUnit.MINUTES.between(currentDateTime, order.getOrderDateTime());
        assertTrue(minutesDiff <= 1); // The difference should be less than or equal to 1 minute.
    }

    @Test
    public void testSetOrderDateTime() {
        LocalDateTime newDateTime = LocalDateTime.now().plusHours(1);
        order.setOrderDateTime(newDateTime);
        assertEquals(newDateTime, order.getOrderDateTime());
    }

    @Test
    public void testGetPreparationTimeMinutes() {
        assertEquals(30, order.getPreparationTimeMinutes());
    }

    @Test
    public void testSetPreparationTimeMinutes() {
        order.setPreparationTimeMinutes(45);
        assertEquals(45, order.getPreparationTimeMinutes());
    }

    @Test
    public void testCalculateOrderPrice() {
        Item item = new Item("123", "Product", 10.0, 2.0, 5);
        Order order = new Order(1, new StandardCustomer("John", "123 Street", "001", "john@example.com"), item, 3, LocalDateTime.now());

        double expectedPrice = 3 * 10.0 + 2.0; // (3 units * price per unit) + shipping cost
        double actualPrice = order.calculateOrderPrice();

        assertEquals(expectedPrice, actualPrice, 0.0);
    }

    @Test
    public void testOrderSent() {
        LocalDateTime orderDateTime = LocalDateTime.now().plusSeconds(5); // 30 minutes ago
        Item item = new Item("123", "Product", 10.0, 2.0, 5);
        Order order = new Order(1, new StandardCustomer("John", "123 Street", "001", "john@example.com"), item, 1, orderDateTime);
        LocalDateTime currentTime = order.getOrderDateTime().plusMinutes(30);
        boolean isSent = order.orderIsSent(currentTime);
        assertTrue(isSent);
    }
    @Test
    public void testOrderNotSent() {
        LocalDateTime currentTime = order.getOrderDateTime().minusMinutes(29);
        assertFalse(order.orderIsSent(currentTime));
    }

    @Test
    public void testIsCancelable() {
        LocalDateTime orderDateTime = LocalDateTime.now();
        LocalDateTime  currentTime = orderDateTime.plusMinutes(30); // 30 minutes from now
        Order order = new Order(1, new StandardCustomer("John", "123 Street", "001", "john@example.com"), new Item(), 1, orderDateTime);

        boolean isCancelable = order.isCancelable(currentTime);

        assertTrue(isCancelable);
    }

    @Test
    public void testIsNotCancelable() {
        // Order preparation time is 30 minutes. Subtract 29 minutes from the order's orderDateTime.
        LocalDateTime currentTime = order.getOrderDateTime().minusMinutes(29);
        assertFalse(order.isCancelable(currentTime));
    }

    @Test
    public void testToString() {

        String expected = "Order{" +
                "orderNumber=1, " +
                "customer=" + customer + ", " +
                "item=" + item + ", " +
                "quantityUnits=3, " +
                "orderDateTime=" + order.getOrderDateTime() + ", " +
                "preparationTimeMinutes=30, " +
                "orderIsCancelable=false, " +
                "orderIsSent=false}";
        assertEquals(expected, order.toString());
    }
}

