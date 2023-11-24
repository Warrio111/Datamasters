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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestController {
    private Controller controller;
    private CustomerEntity standardCustomer;
    private CustomerEntity premiumCustomer;

    @Before
    public void setUp() throws DAOException {
        controller = new Controller();
        CustomerEntity standardCustomer= new CustomerEntity();
        CustomerEntity premiumCustomer = new CustomerEntity();
        standardCustomer.setName("John Doe");
        standardCustomer.setAddress("123 Main St");
        standardCustomer.setId(6);
        standardCustomer.setEmail("john@example.com");
        premiumCustomer.setName("Jane Smith");
        premiumCustomer.setAddress("456 Oak St");
        premiumCustomer.setId(7);
        premiumCustomer.setEmail("jane@gmail.com");
        premiumCustomer.setMembershipFee(1000.0);
        premiumCustomer.setShippingDiscount(0.1);
    }
    @BeforeEach
    public void setUpEach() throws DAOException {
        controller = new Controller();
        CustomerEntity standardCustomer= new CustomerEntity();
        CustomerEntity premiumCustomer = new CustomerEntity();
        standardCustomer.setName("John Doe");
        standardCustomer.setAddress("123 Main St");
        standardCustomer.setId(6);
        standardCustomer.setEmail("john@example.com");
        premiumCustomer.setName("Jane Smith");
        premiumCustomer.setAddress("456 Oak St");
        premiumCustomer.setId(7);
        premiumCustomer.setEmail("jane@gmail.com");
        premiumCustomer.setMembershipFee(1000.0);
        premiumCustomer.setShippingDiscount(0.1);
    }
    @After
    public void tearDownEach() throws DAOException, SQLException {
        removeAllCustomers();
        removeAllItems();
        removeAllOrders();

    }

    private void removeAllCustomers() throws DAOException, SQLException {
        ArrayList<CustomerEntity> customers = controller.getCustomers();
        for (CustomerEntity customer : customers) {
            controller.removeCustomer(customer);
        }
    }

    private void removeAllItems() throws DAOException {
        ArrayList<ItemEntity> items = controller.getItems();
        for (ItemEntity item : items) {
            controller.removeItem(item);
        }
    }

    private void removeAllOrders() throws DAOException {
        ArrayList<OrdersEntity> orders = controller.getOrders();
        for (OrdersEntity order : orders) {
            controller.deleteOrderByNumber(order.getOrderNumber());
        }
    }

    @Test
    public void testAddItem() throws DAOException, SQLException {
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(30);
        controller.addItem(item);
        ArrayList<ItemEntity> itemList = controller.getItems();
        int size = itemList.size();
        int lastPosition = itemList.size() -1;
        ItemEntity lastItem = itemList.get(size -1);

        assertEquals(size, controller.getDao().getItemDAO().getAll().size());
        assertEquals(lastItem.getCode(), controller.getDao().getItemDAO().getAll().get(lastPosition).getCode());
    }
    @Test
    public void testRemoveItem() throws DAOException {
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(30);
        controller.addItem(item);
        controller.addItem(item);
        ArrayList<ItemEntity> itemList = controller.getItems();
        int lastPosition = itemList.size() -1;
        item.setCode((controller.getItems().get(lastPosition).getCode()));
        controller.removeItem(item);

        assertEquals(itemList.size() -1, controller.getDao().getItemDAO().getAll().size());
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

        assertEquals(controller.getCustomers().size(),controller.getDao().getCustomerDAO().getAll().size());
        assertEquals(premiumCustomer.getId(),controller.getCustomers().get(controller.getCustomers().size() -1).getId());
    }

    @Test
    public void testRemoveCustomer() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        int sizeBeforeRemove = controller.getCustomers().size();
        CustomerEntity toDelete = controller.getCustomers().get(sizeBeforeRemove -1);
        controller.removeCustomer(toDelete);

        assertEquals(sizeBeforeRemove - 1, controller.getCustomers().size());
    }


    @Test
    public void testGetCustomers() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<CustomerEntity> customers = controller.getCustomers();
        int size = controller.getCustomers().size();
        assertEquals(size, customers.size());
        assertEquals(standardCustomer.getName(), customers.get(size -2).getName());
        assertEquals(premiumCustomer.getName(), customers.get(size -1).getName());
    }
    @Test
    public void testGetCustomerByType() throws DAOException, SQLException {
        controller.addCustomer(standardCustomer);
        controller.addCustomer(premiumCustomer);

        ArrayList<CustomerEntity> standardCustomers = controller.getCustomersByType(CustomerType.STANDARD);
        ArrayList<CustomerEntity> premiumCustomers = controller.getCustomersByType(CustomerType.PREMIUM);
        int Standardsize = controller.getCustomersByType(CustomerType.STANDARD).size();
        int Premiumsize = controller.getCustomersByType(CustomerType.PREMIUM).size();

        assertEquals(Standardsize, standardCustomers.size());
        //assertEquals(standardCustomer, standardCustomers.get(0));
        assertEquals(Premiumsize, premiumCustomers.size());
        //assertEquals(premiumCustomer, premiumCustomers.get(0));
    }
    @Test
    public void testAddOrder() throws DAOException, SQLException {
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(30);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        // get last customer from BBDD
        controller.addCustomer(standardCustomer);
        standardCustomer.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        OrdersEntity order = new OrdersEntity();
        order.setOrderNumber(1);
        order.setCustomer(standardCustomer);
        order.setItem(item);
        order.setQuantityUnits(3);
        order.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now().plusSeconds(5)));
        int sizeActual = controller.getOrders().size();
        controller.addOrder(order);

        assertEquals(sizeActual+1, controller.getOrders().size());
    }
    @Test
    public void testRemoveOrder() throws DAOException, SQLException {
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(30);
        controller.addItem(item);

        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        controller.addCustomer(standardCustomer);
        standardCustomer.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        OrdersEntity order = new OrdersEntity();
        order.setOrderNumber(1);
        order.setCustomer(standardCustomer);
        order.setItem(item);
        order.setQuantityUnits(3);
        order.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now()));
        int sizeActual = controller.getOrders().size();
        controller.addOrder(order);

        order.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());

        assertEquals(sizeActual+1, controller.getOrders().size());
        controller.removeOrder(order);

        assertEquals(sizeActual, controller.getOrders().size());
    }

    @Test
    public void testFindCustomerById() throws DAOException, SQLException {

        controller.addCustomer(standardCustomer);
        CustomerEntity lastCustomer = controller.getCustomers().get(controller.getCustomers().size() -1);
        standardCustomer.setId(lastCustomer.getId());
        CustomerEntity foundCustomer = controller.findCustomerById(standardCustomer.getId());

        assertNotNull(foundCustomer);
        assertEquals(standardCustomer.getId(), foundCustomer.getId());
    }
    @Test
    public void testGetItems() throws DAOException {
        ItemEntity item1 = new ItemEntity();
        item1.setCode(1);
        item1.setDescription("Sample Item");
        item1.setSellingPrice(10.0);
        item1.setShippingCost(2.0);
        item1.setPreparationTimeMinutes(30);
        controller.addItem(item1);
        ItemEntity item2 = new ItemEntity();
        item2.setCode(2);
        item2.setDescription("Sample Item");
        item2.setSellingPrice(10.0);
        item2.setShippingCost(2.0);
        item2.setPreparationTimeMinutes(30);
        controller.addItem(item1);
        ArrayList<ItemEntity> items = controller.getItems();
        int size  = controller.getItems().size();
        assertEquals(size, items.size());

    }
    @Test
    public void testFindItemByCode() throws DAOException {
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(30);
        controller.addItem(item);
        item.setCode((controller.getItems().get(controller.getItems().size() -1).getCode()));
        ItemEntity foundItem = controller.findItemByCode(item.getCode());
        assertNotNull(foundItem);
        assertEquals(item.getCode(), foundItem.getCode());
    }

    @Test
    public void testDeleteOrder() throws DAOException, SQLException {
        removeAllCustomers();
        removeAllItems();
        removeAllOrders();
        controller.addCustomer(standardCustomer);
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(30);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        standardCustomer.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        OrdersEntity order = new OrdersEntity();
        order.setOrderNumber(1);
        order.setCustomer(standardCustomer);
        order.setItem(item);
        order.setQuantityUnits(3);
        order.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now()));
        int sizeActual = controller.getOrders().size();
        controller.addOrder(order);
        order.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        assertEquals(sizeActual+1, controller.getOrders().size());

        int ordernumber = order.getOrderNumber();
        controller.deleteOrderByNumber(ordernumber);
        sizeActual = controller.getOrders().size();

        assertEquals(sizeActual, controller.getOrders().size());
    }



    @Test
    public void testGetPendingOrders() throws DAOException, SQLException {
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setName("John Doe");
        customer1.setAddress("123 Main St");
        customer1.setId(1);
        customer1.setEmail("john@example.com");
        controller.addCustomer(customer1);
        customer1.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        CustomerEntity customer2 = new CustomerEntity();
        customer2.setName("Jane Smith");
        customer2.setAddress("456 Elm St");
        customer2.setId(2);
        customer2.setEmail("jane@example.com");
        controller.addCustomer(customer2);
        customer2.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(30);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        OrdersEntity order1 = new OrdersEntity();
        order1.setOrderNumber(1);
        order1.setCustomer(standardCustomer);
        order1.setItem(item);
        order1.setQuantityUnits(3);
        order1.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now().plusSeconds(1)));
        OrdersEntity order2 = new OrdersEntity();
        order2.setOrderNumber(2);
        order2.setCustomer(standardCustomer);
        order2.setItem(item);
        order2.setQuantityUnits(3);
        order2.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now().plusSeconds(1)));
        controller.addOrder(order1);
        order1.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        controller.addOrder(order2);
        order2.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());

        ArrayList<OrdersEntity> pendingOrders = controller.getPendingOrders(customer1.getId());

        assertEquals(1, pendingOrders.size());
        assertEquals(pendingOrders.get(0).getCustomer().getId(), (customer1.getId()));
    }

    @Test
    public void testGetSentOrders() throws DAOException, SQLException {

        CustomerEntity customer1 = new CustomerEntity();
        customer1.setName("John Doe");
        customer1.setAddress("123 Main St");
        customer1.setId(1);
        customer1.setEmail("john@example.com");

        CustomerEntity customer2 = new CustomerEntity();
        customer2.setName("Jane Smith");
        customer2.setAddress("456 Elm St");
        customer2.setId(2);
        customer2.setEmail("jane@example.com");
        controller.addCustomer(customer1);
        customer1.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        controller.addCustomer(customer2);
        customer2.setId(controller.getCustomers().get(controller.getCustomers().size() -1).getId());
        ItemEntity item = new ItemEntity();
        item.setCode(1);
        item.setDescription("Sample Item");
        item.setSellingPrice(10.0);
        item.setShippingCost(2.0);
        item.setPreparationTimeMinutes(5);
        controller.addItem(item);
        item.setCode(controller.getItems().get(controller.getItems().size() -1).getCode());
        OrdersEntity order1 = new OrdersEntity();
        order1.setOrderNumber(1);
        order1.setCustomer(customer1);
        order1.setItem(item);
        order1.setQuantityUnits(3);
        order1.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now().minusMinutes(100)));

        OrdersEntity order2 = new OrdersEntity();
        order2.setOrderNumber(2);
        order2.setCustomer(customer2);
        order2.setItem(item);
        order2.setQuantityUnits(3);
        order2.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(100)));

        controller.addOrder(order1);
        order1.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        controller.addOrder(order2);
        order2.setOrderNumber(controller.getOrders().get(controller.getOrders().size() -1).getOrderNumber());
        ArrayList<OrdersEntity> sentOrders = controller.getSentOrders(customer1.getId());

        assertEquals(1, sentOrders.size());
        assertEquals(sentOrders.get(0).getCustomer().getId(), (customer1.getId()));
    }

}
