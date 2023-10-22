package com.datamasters.modelo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

    private Item item;

    @Before
    public void setUp() {
        item = new Item("12345", "Sample Item", 19.99, 5.0, 30);
    }

    @Test
    public void testGetCode() {
        assertEquals("12345", item.getCode());
    }

    @Test
    public void testSetCode() {
        item.setCode("67890");
        assertEquals("67890", item.getCode());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Sample Item", item.getDescription());
    }

    @Test
    public void testSetDescription() {
        item.setDescription("Updated Description");
        assertEquals("Updated Description", item.getDescription());
    }

    @Test
    public void testGetSellingPrice() {
        assertEquals(19.99, item.getSellingPrice(), 0.0);
    }

    @Test
    public void testSetSellingPrice() {
        item.setSellingPrice(29.99);
        assertEquals(29.99, item.getSellingPrice(), 0.0);
    }

    @Test
    public void testGetShippingCost() {
        assertEquals(5.0, item.getShippingCost(), 0.0);
    }

    @Test
    public void testSetShippingCost() {
        item.setShippingCost(7.5);
        assertEquals(7.5, item.getShippingCost(), 0.0);
    }

    @Test
    public void testGetPreparationTimeMinutes() {
        assertEquals(30, item.getPreparationTimeMinutes());
    }

    @Test
    public void testSetPreparationTimeMinutes() {
        item.setPreparationTimeMinutes(45);
        assertEquals(45, item.getPreparationTimeMinutes());
    }

    @Test
    public void testToString() {
        String expected = "Item{" +
                "code='12345', " +
                "description='Sample Item', " +
                "sellingPrice=19.99, " +
                "shippingCost=5.0, " +
                "preparationTimeMinutes=30}";
        assertEquals(expected, item.toString());
    }
}

