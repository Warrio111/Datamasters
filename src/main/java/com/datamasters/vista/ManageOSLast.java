/*
package com.datamasters.vista;

import com.datamasters.DAO.DAOException;
import com.datamasters.controlador.Controller;
import com.datamasters.modelo.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ManageOS {
    private final Controller controller;
    private final Scanner scanner;
    private final ExceptionHandler exceptionHandler;
    public ManageOS() throws DAOException {
        this.controller = new Controller();
        this.scanner = new Scanner(System.in);
        this.exceptionHandler = new ExceptionHandler();
    }

    public void run() throws DAOException, SQLException {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Manage Items");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Orders");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    manageItems();
                    break;
                case 2:
                    manageCustomers();
                    break;
                case 3:
                    manageOrders();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private void manageItems() throws DAOException {
        System.out.println("1. Add Item");
        System.out.println("2. Delete Item");
        System.out.println("3. Show Item");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addItems();
                break;
            case 2:
                removeItem();
                break;
            case 3:
                showItems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void manageCustomers() throws DAOException, SQLException {
        System.out.println("1. Add Customer");
        System.out.println("2. Delete Customer");
        System.out.println("3. Show Customers");
        System.out.println("4. Show Standard Customers");
        System.out.println("5. Show Premium Customers");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addCustomer();
                break;
            case 2:
                removeCustomer();
            case 3:
                showCustomers();
                break;
            case 4:
                showStandardCustomers();
                break;
            case 5:
                showPremiumCustomers();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void manageOrders() throws DAOException, SQLException {
        System.out.println("1. Add Order");
        System.out.println("2. Delete Order");
        System.out.println("3. Show Pending Orders");
        System.out.println("4. Show Sent Orders");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addOrder();
                break;
            case 2:
                deleteOrder();
                break;
            case 3:
                showPendingOrders();
                break;
            case 4:
                showSentOrders();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addItems() {
        System.out.print("Enter Item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter selling price: ");
        double sellingPrice = scanner.nextDouble();
        System.out.print("Enter shipping cost: ");
        double shippingCost = scanner.nextDouble();
        System.out.print("Enter preparation time (minutes): ");
        int preparationTimeMinutes = scanner.nextInt();
        ItemEntity item = new ItemEntity();
        item.setDescription(description);
        item.setSellingPrice(sellingPrice);
        item.setShippingCost(shippingCost);
        item.setPreparationTimeMinutes(preparationTimeMinutes);
        controller.addItem(item);
        System.out.println("Item added successfully.");
    }
    public void removeItem() throws DAOException {
        System.out.println("Enter code of Item to delete");
        int code = scanner.nextInt();
        ArrayList<ItemEntity> items = controller.getItems();
        for (ItemEntity item : items) {
            if(code==(item.getCode())){
                controller.removeItem(item);
                System.out.println("Item has been deleted");
            }
        }

    }
    private void showItems() throws DAOException {
        ArrayList<ItemEntity> items = controller.getItems();
        System.out.println("Items:");
        for (ItemEntity item : items) {
            System.out.println(item);
        }
    }

    private void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer type (STANDARD or PREMIUM): ");

        try {
            CustomerType customerType = CustomerType.valueOf(scanner.nextLine().toUpperCase());
            if (customerType == CustomerType.STANDARD) {
                CustomerEntity customer = new CustomerEntity();
                customer.setName(name);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setCustomerType(CustomerType.STANDARD);
                customer.setMembershipFee(0);
                customer.setShippingDiscount(0);
                controller.addCustomer(customer);
                System.out.println("Standard customer added successfully.");
            } else if (customerType == CustomerType.PREMIUM) {
                System.out.print("Enter membership fee: ");
                double membershipFee = scanner.nextDouble();
                System.out.print("Enter shipping discount: ");
                double shippingDiscount = scanner.nextDouble();
                CustomerEntity customer = new CustomerEntity();
                customer.setName(name);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setCustomerType(CustomerType.PREMIUM);
                customer.setMembershipFee(membershipFee);
                customer.setShippingDiscount(shippingDiscount);
                controller.addCustomer(customer);
                System.out.println("Premium customer added successfully.");
            } else {
                System.out.println("Invalid customer type. Please try again.");
            }
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }
    public void removeCustomer() throws DAOException, SQLException {
        System.out.println("Enter id of customer to remove");
        int id = scanner.nextInt();

        ArrayList<CustomerEntity> customers = controller.getCustomers();
        for (CustomerEntity customer : customers) {
            if(id == (customer.getId())){
                controller.removeCustomer(customer);
                System.out.println("Customer has been removed");
            }
        }

    }
    private void showCustomers() throws DAOException, SQLException {
        ArrayList<CustomerEntity> customers = controller.getCustomers();
        System.out.println("Customers:");
        for (CustomerEntity customer : customers) {
            System.out.println(customer);
        }
    }

    private void showStandardCustomers() throws DAOException, SQLException {
        ArrayList<CustomerEntity> standardCustomers = controller.getCustomersByType(CustomerType.STANDARD);
        System.out.println("Standard Customers:");
        for (CustomerEntity customer : standardCustomers) {
            System.out.println(customer);
        }
    }

    private void showPremiumCustomers() throws DAOException, SQLException {
        ArrayList<CustomerEntity> premiumCustomers = controller.getCustomersByType(CustomerType.PREMIUM);
        System.out.println("Premium Customers:");
        for (CustomerEntity customer : premiumCustomers) {
            System.out.println(customer);
        }
    }

    private void addOrder() throws DAOException, SQLException {

        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        CustomerEntity customer = controller.findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found. Please add the customer details.");
            scanner.nextLine(); // Consume the newline character
            addCustomer();
            customer = controller.getCustomers().get(controller.getCustomers().size()-1);
        }
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter item code: ");
        int itemCode = scanner.nextInt();
        ItemEntity item = controller.findItemByCode(itemCode);

        if (item == null) {
            System.out.println("Product not found. Please add the product details.");
            scanner.nextLine(); // Consume the newline character
            addItems();
            item = controller.getItems().get(controller.getItems().size()-1);

        }

        System.out.print("Enter quantity of units: ");
        int quantityUnits = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrdersEntity order = new OrdersEntity();
        order.setItemCode(item.getCode());
        order.setCustomerId(customer.getId());
        order.setQuantityUnits(quantityUnits);
        order.setPreparationTimeMinutes(item.getPreparationTimeMinutes());
        order.setOrderDateTime(Timestamp.valueOf(orderDateTime));
        controller.addOrder(order);
        System.out.println("Order added successfully.");
    }

    private void deleteOrder() throws DAOException {
        System.out.print("Enter order number to delete: ");
        int orderNumber = scanner.nextInt();
        OrdersEntity order = controller.findOrderByNumber(orderNumber);

        if (order != null) {

            if(controller.deleteOrderByNumber(orderNumber))
                System.out.println("Order deleted successfully.");
            else
                System.out.println("Order cannot be deleted as it has already been sent.");

        } else {
            System.out.println("Order not found.");
        }
    }

    private void showPendingOrders() throws DAOException {
        System.out.println("Enter Customer ID to find pending order: ");
        int customerId = scanner.nextInt();
        ArrayList<OrdersEntity> pendingOrders = controller.getPendingOrders(customerId);
        System.out.println("Pending Orders:");
        for (OrdersEntity order : pendingOrders) {
            System.out.println(order);
        }
    }

    private void showSentOrders() throws DAOException {
        System.out.println("Enter Customer ID to find sent orders: ");
        int customerId = scanner.nextInt();
        ArrayList<OrdersEntity> sentOrders = controller.getSentOrders(customerId);
        System.out.println("Sent Orders:");
        for (OrdersEntity order : sentOrders) {
            System.out.println(order);
        }
    }
}
*/
