package com.datamasters.vista;

import com.datamasters.DAO.DAOException;
import com.datamasters.controlador.Controller;
import com.datamasters.modelo.*;

import java.sql.SQLException;
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
            System.out.print("\n========== Menu ==========\n" +
                    "1. Manage Items\n" +
                    "2. Manage Customers\n" +
                    "3. Manage Orders\n" +
                    "0. Exit\n" +
                    "==========================\n" +
                    "Add Your Selection: ");
            int choice = scanner.nextInt();
            System.out.println("\n----------------------------");
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
        System.out.print("\n========== Menu Item ==========\n" +
                "1. Add Item\n" +
                "2. Delete Item\n" +
                "3. Show Item\n" +
                "==========================\n" +
                "Insert your selection: ");
        int choice = scanner.nextInt();
        System.out.println("\n----------------------------\n");
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addItems();
                break;
            case 2:
                removeItem();
            case 3:
                showItems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void manageCustomers() throws DAOException, SQLException {
        System.out.print("\n========== Menu Customer ==========\n" +
                "1. Add Customer\n" +
                "2. Delete Customer\n" +
                "3. Show Customers\n" +
                "4. Show Standard Customers\n" +
                "5. Show Premium Customers\n" +
                "==========================\n" +
                "Insert your selection: ");
        int choice = scanner.nextInt();
        System.out.println("\n----------------------------\n");
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
        System.out.print("\n========== Menu Orders ==========\n" +
                "1. Add Order\n" +
                "2. Delete Order\n" +
                "3. Show Pending Orders\n" +
                "4. Show Sent Orders\n" +
                "==========================\n" +
                "Insert your selection: ");

        int choice = scanner.nextInt();
        System.out.println("\n----------------------------\n");
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
        try {
            String code = "1";
            if(!controller.getItems().isEmpty())
            code= controller.getItems().get(controller.getItems().size()-1).getCode();
            code = String.valueOf(Integer.parseInt(code)+1);
            System.out.print("Enter Item description: ");
            String description = scanner.nextLine();
            System.out.print("Enter selling price: ");
            double sellingPrice = scanner.nextDouble();
            System.out.print("Enter shipping cost: ");
            double shippingCost = scanner.nextDouble();
            System.out.print("Enter preparation time (minutes): ");
            int preparationTimeMinutes = scanner.nextInt();

            Item item = new Item(code, description, sellingPrice, shippingCost, preparationTimeMinutes);
            controller.addItem(item);
            System.out.println("Item added successfully.");
        }
        catch (Exception ex){
            exceptionHandler.handleException(ex);
        }

    }
    public void removeItem() throws DAOException {
        System.out.println("Enter code of Item to delete");
        String code = scanner.nextLine();
        ArrayList<Item> items = controller.getItems();
        for (Item item : items) {
            if(code.equals(item.getCode())){
                controller.removeItem(item);
                System.out.println("Item has been deleted");
            }
        }

    }
    private void showItems() throws DAOException {
        ArrayList<Item> items = controller.getItems();
        System.out.println("Items:\n");
        for (Item item : items) {
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
            String id = "1";
            if (customerType == CustomerType.STANDARD) {
                if (!controller.getCustomers().isEmpty())
                id= controller.getCustomers().get(controller.getCustomers().size()-1).getId();
                id = String.valueOf(Integer.parseInt(id)+1);
                Customer customer = new StandardCustomer(name, address, id, email);
                controller.addCustomer(customer);
                System.out.println("\nStandard customer added successfully.");
            } else if (customerType == CustomerType.PREMIUM) {
                System.out.print("Enter membership fee: ");
                double membershipFee = scanner.nextDouble();
                System.out.print("Enter shipping discount: ");
                double shippingDiscount = scanner.nextDouble();
                if (!controller.getCustomers().isEmpty())
                id = controller.getCustomers().get(controller.getCustomers().size()-1).getId();
                id = String.valueOf(Integer.parseInt(id)+1);
                Customer customer = new PremiumCustomer(name, address, id, email, membershipFee, shippingDiscount);
                controller.addCustomer(customer);
                System.out.println("\nPremium customer added successfully.");
            } else {
                System.out.println("Invalid customer type. Please try again.");
            }
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }
    public void removeCustomer() throws DAOException, SQLException {
        System.out.println("Enter id of customer to remove");
        String id = scanner.nextLine();

        ArrayList<Customer> customers = controller.getCustomers();
        for (Customer customer : customers) {
            if(id.equals(customer.getId())){
                controller.removeCustomer(customer);
                System.out.println("Customer has been removed");
            }
        }

    }
    private void showCustomers() throws DAOException, SQLException {
        ArrayList<Customer> customers = controller.getCustomers();
        if(customers.isEmpty()){
            System.out.println("\nNo customers found\n");
        }
        else
        {
            System.out.println("Customers:\n");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }

    }

    private void showStandardCustomers() throws DAOException, SQLException {
        ArrayList<Customer> standardCustomers = controller.getCustomersByType(CustomerType.STANDARD);
        if (standardCustomers.isEmpty()){
            System.out.println("\nNo standard customers found\n");
        }
        else {
            System.out.println("Standard Customers:\n");
            for (Customer customer : standardCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void showPremiumCustomers() throws DAOException, SQLException {
        ArrayList<Customer> premiumCustomers = controller.getCustomersByType(CustomerType.PREMIUM);
        if (premiumCustomers.isEmpty()){
            System.out.println("\nNo premium customers found\n");
        }
        else{
            System.out.println("Premium Customers:\n");
            for (Customer customer : premiumCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void addOrder() throws DAOException, SQLException {


        try {
            System.out.print("Enter customer ID: ");
            String customerId = scanner.nextLine();
            Customer customer = controller.findCustomerById(customerId);

            if (customer == null) {
                System.out.println("Customer not found. Please add the customer details.");
                addCustomer();
                customer = controller.getCustomers().get(controller.getCustomers().size()-1);
            }
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter item code: ");
            String itemCode = scanner.nextLine();
            Item item = controller.findItemByCode(itemCode);

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
            int orderNumber = 1;
            if(!controller.getOrders().isEmpty())
            {
                orderNumber= controller.getOrders().get(controller.getOrders().size()-1).getOrderNumber();
                orderNumber++;
            }
            Orders order = new Orders(orderNumber, customer, item, quantityUnits, orderDateTime);
            controller.addOrder(order);
            System.out.println("Order added successfully.");
        }catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    private void deleteOrder() throws DAOException {
        System.out.print("Enter order number to delete: ");
        int orderNumber = scanner.nextInt();
        Orders order = controller.findOrderByNumber(orderNumber);

        if (order != null) {
            if (!order.orderIsSent(LocalDateTime.now())) {
                controller.deleteOrderByNumber(orderNumber);
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Order cannot be deleted as it has already been sent.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }

    private void showPendingOrders() throws DAOException {
        System.out.println("Enter Customer ID to find pending order: ");
        String customerId = scanner.nextLine();
        ArrayList<Orders> pendingOrders = controller.getPendingOrders(customerId);
        if (pendingOrders.isEmpty()){
            System.out.println("\nNo pending orders found\n");
        }
        else
        {
            System.out.println("Pending Orders:");
            for (Orders order : pendingOrders) {
                System.out.println(order);
            }
        }
    }

    private void showSentOrders() throws DAOException {
        System.out.println("Enter Customer ID to find sent orders: ");
        String customerId = scanner.nextLine();
        ArrayList<Orders> sentOrders = controller.getSentOrders(customerId);
        if (sentOrders.isEmpty()){
            System.out.println("\nNo sent orders found\n");
        }
        else {
            System.out.println("Sent Orders:");
            for (Orders order : sentOrders) {
                System.out.println(order);
            }
        }
    }
}
