package com.datamasters.controlador;

import com.datamasters.modelo.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestController {
    private Controller controller;
    private Customer standardCustomer;
    private Customer premiumCustomer;

    @Before
    public void setUp() {
        controller = new Controller();
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "C456", "jane@gmail.com", 1000.0, 0.1);
    }
    @BeforeEach
    public void setUpEach() {
        controller = new Controller();
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "C456", "jane@gmail.com", 1000.0, 0.1);
    }
    @AfterEach
    public void tearDownEach() {
        controller.removeCustomer(standardCustomer);
        controller.removeCustomer(premiumCustomer);
    }
    @Test
    public void testAddItem()
    {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);

        assertEquals(1, controller.getData().getItems().getArrayList().size());
        assertEquals(item, controller.getData().getItems().getArrayList().get(0));
        assertEquals("I001", controller.getData().getItems().getArrayList().get(0).getCode());
    }
    @Test
    public void testRemoveItem() {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);

        controller.removeItem(item);

        assertEquals(0, controller.getData().getItems().getArrayList().size());
    }
    @Test
    public void testAddCustomer() {

        controller.addCustomer(standardCustomer);

        assertEquals(1, controller.getCustomers().size());
        assertEquals(standardCustomer, controller.getCustomers().get(0));
        assertEquals("John Doe", controller.getCustomers().get(0).getName());
    }
    @Test
    public void testAddPremiumCustomer() {

        controller.addCustomer(premiumCustomer);

        assertEquals(1, controller.getCustomers().size());
        assertEquals(premiumCustomer, controller.getCustomers().get(0));
        assertEquals("Jane Smith", controller.getCustomers().get(0).getName());
    }
    @Test
    public void testRemoveCustomer() {
        controller.addCustomer(standardCustomer);

        controller.removeCustomer(standardCustomer);

        assertEquals(0, controller.getCustomers().size());
    }

    @Test
    public void testGetCustomers() {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<Customer> customers = controller.getCustomers();

        assertEquals(2, customers.size());
        assertEquals(standardCustomer, customers.get(0));
        assertEquals(premiumCustomer, customers.get(1));
    }
    @Test
    public void testGetCustomerByType() {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<Customer> standardCustomers = controller.getCustomerByType(CustomerType.STANDARD);
        ArrayList<Customer> premiumCustomers = controller.getCustomerByType(CustomerType.PREMIUM);

        assertEquals(1, standardCustomers.size());
        assertEquals(standardCustomer, standardCustomers.get(0));
        assertEquals(1, premiumCustomers.size());
        assertEquals(premiumCustomer, premiumCustomers.get(0));
    }
    @Test
    public void testAddOrder() {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Order order = new Order(1, standardCustomer, item, 3, LocalDateTime.now().plusSeconds(1));

        controller.addOrder(order);

        assertEquals(1, controller.getOrders().size());
    }
    @Test
    public void testRemoveOrder() {

        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Order order = new Order(1, standardCustomer, item, 3, LocalDateTime.now());
        controller.addOrder(order);
        assertEquals(1, controller.getOrders().size());
        controller.removeOrder(order);

        assertEquals(0, controller.getOrders().size());
    }

    @Test
    public void testFindCustomerById() {

        controller.addCustomer(standardCustomer);

        Customer foundCustomer = controller.findCustomerById("C123");

        assertNotNull(foundCustomer);
        assertEquals("C123", foundCustomer.getId());
    }
    @Test
    public void testGetItems() {
        Item item1 = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Item item2 = new Item("I002", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item1);
        controller.addItem(item2);

        ArrayList<Item> items = controller.getItems();

        assertEquals(2, items.size());
        assertEquals(item1, items.get(0));
        assertEquals(item2, items.get(1));
    }
    @Test
    public void testFindItemByCode() {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);

        Item foundItem = controller.findItemByCode("I001");

        assertNotNull(foundItem);
        assertEquals("I001", foundItem.getCode());
    }

    @Test
    public void testDeleteOrder() {

        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Order order = new Order(1, standardCustomer, item, 3, LocalDateTime.now());

        controller.addOrder(order);
        controller.deleteOrderByNumber(1);

        assertEquals(0, controller.getOrders().size());
    }

    @Test
    public void testGetPendingOrders() {
        Customer customer1 = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        Customer customer2 = new StandardCustomer("Jane Smith", "456 Elm St", "C456", "jane@example.com");
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Order order1 = new Order(1, customer1, item, 3, LocalDateTime.now().plusSeconds(1));
        Order order2 = new Order(2, customer2, item, 3, LocalDateTime.now().plusSeconds(1));

        controller.addOrder(order1);
        controller.addOrder(order2);

        ArrayList<Order> pendingOrders = controller.getPendingOrders("C123");

        assertEquals(1, pendingOrders.size());
        assertTrue(pendingOrders.get(0).getCustomer().getId().equals("C123"));
    }

    @Test
    public void testGetSentOrders() {
        Customer customer1 = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        Customer customer2 = new StandardCustomer("Jane Smith", "456 Elm St", "C456", "jane@example.com");
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 5);
        Order order1 = new Order(1, customer1, item, 3, LocalDateTime.now().minusMinutes(35));
        Order order2 = new Order(2, customer2, item, 3, LocalDateTime.now());

        controller.addOrder(order1);
        controller.addOrder(order2);

        ArrayList<Order> sentOrders = controller.getSentOrders("C123");

        assertEquals(1, sentOrders.size());
        assertTrue(sentOrders.get(0).getCustomer().getId().equals("C123"));
    }
}
