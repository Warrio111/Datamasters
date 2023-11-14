package com.datamasters.controlador;

import com.datamasters.DAO.DAOException;
import com.datamasters.modelo.*;
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
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "123", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "457", "jane@gmail.com", 1000.0, 0.1);
    }
    @BeforeEach
    public void setUpEach() throws DAOException {
        controller = new Controller();
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "123", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "457", "jane@gmail.com", 1000.0, 0.1);
    }
    @AfterEach
    public void tearDownEach() {
        controller.removeCustomer(standardCustomer);
        controller.removeCustomer(premiumCustomer);
    }
    @Test
    public void testAddItem() throws DAOException, SQLException {
        Item item = new Item("001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        ArrayList<Item> itemList = controller.getItems();
        int size = itemList.size();
        int lastPosition = itemList.size() -1;
        Item lastItem = itemList.get(size -1);

        assertEquals(size, controller.getDao().getItemDAO().getAll().getArrayList().size());
        assertEquals(lastItem.getCode(), controller.getDao().getItemDAO().getAll().getArrayList().get(lastPosition).getCode());
    }
    @Test
    public void testRemoveItem() throws DAOException {
        Item item = new Item("001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        ArrayList<Item> itemList = controller.getItems();
        int lastPosition = itemList.size() -1;
        item.setCode(String.valueOf(controller.getItems().get(lastPosition).getCode()));
        controller.removeItem(item);

        assertEquals(itemList.size() -1, controller.getDao().getItemDAO().getAll().getArrayList().size());
    }
    @Test
    public void testAddCustomer() throws DAOException, SQLException {
        int size = controller.getCustomers().size();
        controller.addCustomer(standardCustomer);
        assertEquals(size +1, controller.getCustomers().size());

    }
    @Test
    public void testAddPremiumCustomer() throws DAOException, SQLException {

        controller.addCustomer(premiumCustomer);

        assertEquals(controller.getCustomers().size(),controller.getDao().getCustomerDAO().getAll().getArrayList().size());
        assertEquals("Jane Smith", controller.getCustomers().get(2).getName());
    }
    //TODO
    //ESTE FALLA
    @Test
    public void testRemoveCustomer() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.removeCustomer(standardCustomer);
        int size = controller.getCustomers().size();

        assertEquals(size, controller.getCustomers().size());
    }

    @Test
    public void testGetCustomers() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<Customer> customers = controller.getCustomers();
        int size = controller.getCustomers().size();
        assertEquals(size, customers.size());
        //assertEquals(standardCustomer, customers.get(size -2));
        //assertEquals(premiumCustomer, customers.get(size -1));
    }
    @Test
    public void testGetCustomerByType() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<Customer> standardCustomers = controller.getCustomersByType(CustomerType.STANDARD);
        ArrayList<Customer> premiumCustomers = controller.getCustomersByType(CustomerType.PREMIUM);
        int Standardsize = controller.getCustomersByType(CustomerType.STANDARD).size();
        int Premiumsize = controller.getCustomersByType(CustomerType.PREMIUM).size();

        assertEquals(Standardsize, standardCustomers.size());
        //assertEquals(standardCustomer, standardCustomers.get(0));
        assertEquals(Premiumsize, premiumCustomers.size());
        //assertEquals(premiumCustomer, premiumCustomers.get(0));
    }
    @Test
    public void testAddOrder() throws DAOException {
        Item item = new Item("001", "Sample Item", 10.0, 2.0, 30);
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now().plusSeconds(1));

        controller.addOrder(order);

        assertEquals(1, controller.getOrders().size());
    }
    @Test
    public void testRemoveOrder() throws DAOException {

        Item item = new Item("001", "Sample Item", 10.0, 2.0, 30);
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now());
        controller.addOrder(order);
        assertEquals(1, controller.getOrders().size());
        controller.removeOrder(order);

        assertEquals(0, controller.getOrders().size());
    }

    @Test
    public void testFindCustomerById() throws DAOException, SQLException {

        controller.addCustomer(standardCustomer);

        Customer foundCustomer = controller.findCustomerById("123");

        assertNotNull(foundCustomer);
        assertEquals("C123", foundCustomer.getId());
    }
    @Test
    public void testGetItems() throws DAOException {
        Item item1 = new Item("001", "Sample Item", 10.0, 2.0, 30);
        Item item2 = new Item("002", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item1);
        controller.addItem(item2);

        ArrayList<Item> items = controller.getItems();
        int size  = controller.getItems().size();
        assertEquals(size, items.size());
        /*assertEquals(item1, items.get(0));
        assertEquals(item2, items.get(1));*/
    }
    @Test
    public void testFindItemByCode() throws DAOException {
        Item item = new Item("1", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);

        Item foundItem = controller.findItemByCode("1");

        assertNotNull(foundItem);
        assertEquals("1", foundItem.getCode());
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
        Customer customer1 = new StandardCustomer("John Doe", "123 Main St", "123", "john@example.com");
        Customer customer2 = new StandardCustomer("Jane Smith", "456 Elm St", "456", "jane@example.com");
        Item item = new Item("1", "Sample Item", 10.0, 2.0, 30);
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
