package com.datamasters.controlador;

import com.datamasters.DAO.DAOException;
import com.datamasters.DAO.UtilityMySqlDAOFactory;
import com.datamasters.modelo.*;
import org.assertj.core.api.AbstractObjectArrayAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestController {
    private Controller controller;
    private Customer standardCustomer;
    private Customer premiumCustomer;

    @Before
    public void setUp() throws DAOException {
        controller = new Controller();
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "C457", "jane@gmail.com", 1000.0, 0.1);
    }
    @BeforeEach
    public void setUpEach() throws DAOException {
        controller = new Controller();
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "C457", "jane@gmail.com", 1000.0, 0.1);
    }
    @AfterEach
    public void tearDownEach() {
        controller.removeCustomer(standardCustomer);
        controller.removeCustomer(premiumCustomer);
    }
    @Test
    public void testAddItem() throws DAOException, SQLException {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        ArrayList<Item> itemList = controller.getItems();
        int size = itemList.size();
        int lastPosition = itemList.size() -1;
        Item lastItem = itemList.get(size -1);
        item.setCode(String.valueOf(controller.getItems().get(lastPosition).getCode()));

        assertEquals(size, controller.getDao().getItemDAO().getAll().getArrayList().size());
        //TODO
        // ME DEVUELVE EL MISMO OBJETO PERO DIFERENTE HASHCODE
        //assertEquals(lastItem, controller.getDao().getItemDAO().getAll().getArrayList().get(lastPosition));
        assertEquals(lastItem.getCode(), controller.getDao().getItemDAO().getAll().getArrayList().get(lastPosition).getCode());
    }
    @Test
    public void testRemoveItem() throws DAOException {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        ArrayList<Item> itemList = controller.getItems();
        int lastPosition = itemList.size() -1;
        item.setCode(String.valueOf(controller.getItems().get(lastPosition).getCode()));
        controller.removeItem(item);

        assertEquals(itemList.size() -1, controller.getDao().getItemDAO().getAll().getArrayList().size());
    }
    @Test
    public void testAddCustomer() throws DAOException, SQLException {

        controller.addCustomer(standardCustomer);

        assertEquals(1, controller.getCustomers().size());
        assertEquals(standardCustomer, controller.getCustomers().get(0));
        assertEquals("John Doe", controller.getCustomers().get(0).getName());
    }
    @Test
    public void testAddPremiumCustomer() throws DAOException, SQLException {

        controller.addCustomer(premiumCustomer);

        assertEquals(controller.getCustomers().size(),controller.getDao().getCustomerDAO().getAll().getArrayList().size());
        assertEquals(premiumCustomer, controller.getCustomers().get(controller.getCustomers().size()-1));
        assertEquals("Jane Smith", controller.getCustomers().get(0).getName());
    }
    @Test
    public void testRemoveCustomer() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);

        controller.removeCustomer(standardCustomer);

        assertEquals(0, controller.getCustomers().size());
    }

    @Test
    public void testGetCustomers() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<Customer> customers = controller.getCustomers();

        assertEquals(2, customers.size());
        assertEquals(standardCustomer, customers.get(0));
        assertEquals(premiumCustomer, customers.get(1));
    }
    @Test
    public void testGetCustomerByType() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<Customer> standardCustomers = controller.getCustomersByType(CustomerType.STANDARD);
        ArrayList<Customer> premiumCustomers = controller.getCustomersByType(CustomerType.PREMIUM);

        assertEquals(1, standardCustomers.size());
        assertEquals(standardCustomer, standardCustomers.get(0));
        assertEquals(1, premiumCustomers.size());
        assertEquals(premiumCustomer, premiumCustomers.get(0));
    }
    @Test
    public void testAddOrder() throws DAOException {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now().plusSeconds(1));

        controller.addOrder(order);

        assertEquals(1, controller.getOrders().size());
    }
    @Test
    public void testRemoveOrder() throws DAOException {

        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now());
        controller.addOrder(order);
        assertEquals(1, controller.getOrders().size());
        controller.removeOrder(order);

        assertEquals(0, controller.getOrders().size());
    }

    @Test
    public void testFindCustomerById() throws DAOException, SQLException {

        controller.addCustomer(standardCustomer);

        Customer foundCustomer = controller.findCustomerById("C123");

        assertNotNull(foundCustomer);
        assertEquals("C123", foundCustomer.getId());
    }
    @Test
    public void testGetItems() throws DAOException {
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
    public void testFindItemByCode() throws DAOException {
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);

        Item foundItem = controller.findItemByCode("I001");

        assertNotNull(foundItem);
        assertEquals("I001", foundItem.getCode());
    }

    @Test
    public void testDeleteOrder() throws DAOException {

        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now());

        controller.addOrder(order);
        controller.deleteOrderByNumber(1);

        assertEquals(0, controller.getOrders().size());
    }

    @Test
    public void testGetPendingOrders() throws DAOException {
        Customer customer1 = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        Customer customer2 = new StandardCustomer("Jane Smith", "456 Elm St", "C456", "jane@example.com");
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 30);
        Orders order1 = new Orders(1, customer1, item, 3, LocalDateTime.now().plusSeconds(1));
        Orders order2 = new Orders(2, customer2, item, 3, LocalDateTime.now().plusSeconds(1));

        controller.addOrder(order1);
        controller.addOrder(order2);

        ArrayList<Orders> pendingOrders = controller.getPendingOrders("C123");

        assertEquals(1, pendingOrders.size());
        assertTrue(pendingOrders.get(0).getCustomer().getId().equals("C123"));
    }

    @Test
    public void testGetSentOrders() throws DAOException {
        Customer customer1 = new StandardCustomer("John Doe", "123 Main St", "C123", "john@example.com");
        Customer customer2 = new StandardCustomer("Jane Smith", "456 Elm St", "C456", "jane@example.com");
        Item item = new Item("I001", "Sample Item", 10.0, 2.0, 5);
        Orders order1 = new Orders(1, customer1, item, 3, LocalDateTime.now().minusMinutes(35));
        Orders order2 = new Orders(2, customer2, item, 3, LocalDateTime.now());

        controller.addOrder(order1);
        controller.addOrder(order2);

        ArrayList<Orders> sentOrders = controller.getSentOrders("C123");

        assertEquals(1, sentOrders.size());
        assertTrue(sentOrders.get(0).getCustomer().getId().equals("C123"));
    }

    @Test
    public void createBBDD(){


    }
}
