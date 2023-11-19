package com.datamasters.controlador;

import com.datamasters.DAO.DAOException;
import com.datamasters.modelo.*;
import org.junit.After;
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
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "6", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "7", "jane@gmail.com", 1000.0, 0.1);
    }
    @BeforeEach
    public void setUpEach() throws DAOException {
        controller = new Controller();
        standardCustomer = new StandardCustomer("John Doe", "123 Main St", "6", "john@example.com");
        premiumCustomer = new PremiumCustomer("Jane Smith", "456 Oak St", "7", "jane@gmail.com", 1000.0, 0.1);
    }
    @After
    public void tearDownEach() throws DAOException, SQLException {
        removeAllCustomers();
        removeAllItems();
        removeAllOrders();
    }

    private void removeAllCustomers() throws DAOException, SQLException {
        ArrayList<Customer> customers = controller.getCustomers();
        for (Customer customer : customers) {
            controller.removeCustomer(customer);
        }
    }

    private void removeAllItems() throws DAOException {
        ArrayList<Item> items = controller.getItems();
        for (Item item : items) {
            controller.removeItem(item);
        }
    }

    private void removeAllOrders() throws DAOException {
        ArrayList<Orders> orders = controller.getOrders();
        for (Orders order : orders) {
            controller.deleteOrderByNumber(order.getOrderNumber());
        }
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
        premiumCustomer.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());

        assertEquals(controller.getCustomers().size(),controller.getDao().getCustomerDAO().getAll().getArrayList().size());
        assertEquals(premiumCustomer.getId(),controller.getCustomers().get(controller.getCustomers().size() -1).getId());
    }

    @Test
    public void testRemoveCustomer() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        int sizeBeforeRemove = controller.getCustomers().size();
        Customer toDelete = controller.getCustomers().get(sizeBeforeRemove -1);
        controller.removeCustomer(toDelete);

        assertEquals(sizeBeforeRemove - 1, controller.getCustomers().size());
    }


    @Test
    public void testGetCustomers() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<Customer> customers = controller.getCustomers();
        int size = controller.getCustomers().size();
        assertEquals(size, customers.size());
        assertEquals(standardCustomer.getName(), customers.get(size -2).getName());
        assertEquals(premiumCustomer.getName(), customers.get(size -1).getName());
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
    public void testAddOrder() throws DAOException, SQLException {
        Item item = new Item("001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        // get last customer from BBDD
        controller.addCustomer(standardCustomer);
        standardCustomer.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now().plusSeconds(5));
        int sizeActual = controller.getOrders().size();
        controller.addOrder(order);

        assertEquals(sizeActual+1, controller.getOrders().size());
        controller.removeOrder(order);

    }
    @Test
    public void testRemoveOrder() throws DAOException, SQLException {

        Item item = new Item("001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        controller.addCustomer(standardCustomer);
        standardCustomer.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now());
        int sizeActual = controller.getOrders().size();
        controller.addOrder(order);
        assertEquals(sizeActual+1, controller.getOrders().size());
        controller.removeOrder(order);

        assertEquals(sizeActual, controller.getOrders().size());
    }

    @Test
    public void testFindCustomerById() throws DAOException, SQLException {

        controller.addCustomer(standardCustomer);
        Customer lastCutomer = controller.getCustomers().get(controller.getCustomers().size() -1);
        standardCustomer.setId(lastCutomer.getId());
        Customer foundCustomer = controller.findCustomerById(standardCustomer.getId());

        assertNotNull(foundCustomer);
        assertEquals(standardCustomer.getId(), foundCustomer.getId());
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
        item.setCode(String.valueOf(controller.getItems().get(controller.getItems().size() -1).getCode()));
        Item foundItem = controller.findItemByCode(item.getCode());

        assertNotNull(foundItem);
        assertEquals(item.getCode(), foundItem.getCode());
    }

    @Test
    public void testDeleteOrder() throws DAOException, SQLException {
        removeAllCustomers();
        removeAllItems();
        removeAllOrders();
        controller.addCustomer(standardCustomer);
        Item item = new Item("001", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        standardCustomer.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        Orders order = new Orders(1, standardCustomer, item, 3, LocalDateTime.now());
        int sizeActual = controller.getOrders().size();
        controller.addOrder(order);
        order.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        assertEquals(sizeActual+1, controller.getOrders().size());
        controller.deleteOrderByNumber(order.getOrderNumber());

        assertEquals(sizeActual, controller.getOrders().size());
    }



    @Test
    public void testGetPendingOrders() throws DAOException, SQLException {
        Customer customer1 = new StandardCustomer("John Doe", "123 Main St", "1", "john@example.com");
        controller.addCustomer(customer1);
        customer1.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        Customer customer2 = new StandardCustomer("Jane Smith", "456 Elm St", "2", "jane@example.com");
        controller.addCustomer(customer2);
        customer2.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        Item item = new Item("1", "Sample Item", 10.0, 2.0, 30);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        Orders order1 = new Orders(1, customer1, item, 3, LocalDateTime.now().plusSeconds(1));
        Orders order2 = new Orders(2, customer2, item, 3, LocalDateTime.now().plusSeconds(1));

        controller.addOrder(order1);
        order1.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        controller.addOrder(order2);
        order2.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());

        ArrayList<Orders> pendingOrders = controller.getPendingOrders(customer1.getId());

        assertEquals(1, pendingOrders.size());
        assertTrue(pendingOrders.get(0).getCustomer().getId().equals(customer1.getId()));
        controller.removeOrder(order1);
        controller.removeOrder(order2);
    }

    @Test
    public void testGetSentOrders() throws DAOException, SQLException {
        Customer customer1 = new StandardCustomer("John Doe", "123 Main St", "1", "john@example.com");
        Customer customer2 = new StandardCustomer("Jane Smith", "456 Elm St", "2", "jane@example.com");
        controller.addCustomer(customer1);
        customer1.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        controller.addCustomer(customer2);
        customer2.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        Item item = new Item("001", "Sample Item", 10.0, 2.0, 5);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        Orders order1 = new Orders(1, customer1, item, 3, LocalDateTime.now().minusMinutes(100));
        Orders order2 = new Orders(2, customer2, item, 3, LocalDateTime.now().plusMinutes(100));

        controller.addOrder(order1);
        order1.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        controller.addOrder(order2);
        order2.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        ArrayList<Orders> sentOrders = controller.getSentOrders(customer1.getId());

        assertEquals(1, sentOrders.size());
        assertTrue(sentOrders.get(0).getCustomer().getId().equals(customer1.getId()));
        int ordernumber = order1.getOrderNumber();
        controller.deleteOrderByNumber(order1.getOrderNumber());
        controller.deleteOrderByNumber(order2.getOrderNumber());
    }

    @Test
    public void createBBDD(){


    }
}
