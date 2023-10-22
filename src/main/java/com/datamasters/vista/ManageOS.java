package com.datamasters.vista;

import com.datamasters.controlador.Controller;
import com.datamasters.modelo.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ManageOS {
    private final Controller controller;
    private final Scanner scanner;
    private final ExceptionHandler exceptionHandler;
    public ManageOS() {
        this.controller = new Controller();
        this.scanner = new Scanner(System.in);
        this.exceptionHandler = new ExceptionHandler();
    }

    public void run() {
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

    private void manageItems() {
        System.out.println("1. Add Item");
        System.out.println("2. Show Item");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addItems();
                break;
            case 2:
                showItems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void manageCustomers() {
        System.out.println("1. Add Customer");
        System.out.println("2. Show Customers");
        System.out.println("3. Show Standard Customers");
        System.out.println("4. Show Premium Customers");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addCustomer();
                break;
            case 2:
                showCustomers();
                break;
            case 3:
                showStandardCustomers();
                break;
            case 4:
                showPremiumCustomers();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void manageOrders() {
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
        System.out.print("Enter Item code: ");
        String code = scanner.nextLine();
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

    private void showItems() {
        ArrayList<Item> items = controller.getItems();
        System.out.println("Items:");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer type (STANDARD or PREMIUM): ");

        try {
            CustomerType customerType = CustomerType.valueOf(scanner.nextLine().toUpperCase());
            if (customerType == CustomerType.STANDARD) {
                Customer customer = new StandardCustomer(name, address, id, email);
                controller.addCustomer(customer);
                System.out.println("Standard customer added successfully.");
            } else if (customerType == CustomerType.PREMIUM) {
                System.out.print("Enter membership fee: ");
                double membershipFee = scanner.nextDouble();
                System.out.print("Enter shipping discount: ");
                double shippingDiscount = scanner.nextDouble();

                Customer customer = new PremiumCustomer(name, address, id, email, membershipFee, shippingDiscount);
                controller.addCustomer(customer);
                System.out.println("Premium customer added successfully.");
            } else {
                System.out.println("Invalid customer type. Please try again.");
            }
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    private void showCustomers() {
        ArrayList<Customer> customers = controller.getCustomers();
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void showStandardCustomers() {
        ArrayList<Customer> standardCustomers = controller.getCustomerByType(CustomerType.STANDARD);
        System.out.println("Standard Customers:");
        for (Customer customer : standardCustomers) {
            System.out.println(customer);
        }
    }

    private void showPremiumCustomers() {
        ArrayList<Customer> premiumCustomers = controller.getCustomerByType(CustomerType.PREMIUM);
        System.out.println("Premium Customers:");
        for (Customer customer : premiumCustomers) {
            System.out.println(customer);
        }
    }

    private void addOrder() {
        System.out.print("Enter order number: ");
        int orderNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = controller.findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found. Please add the customer details.");
            addCustomer();
            customer = controller.findCustomerById(customerId);
        }

        System.out.print("Enter item code: ");
        String itemCode = scanner.nextLine();
        Item item = controller.findItemByCode(itemCode);

        if (item == null) {
            System.out.println("Product not found. Please add the product details.");
            addItems();
            item = controller.findItemByCode(itemCode);
        }

        System.out.print("Enter quantity of units: ");
        int quantityUnits = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        LocalDateTime orderDateTime = LocalDateTime.now();
        System.out.print("Enter preparation time (minutes): ");
        int preparationTimeMinutes = scanner.nextInt();

        Order order = new Order(orderNumber, customer, item, quantityUnits, orderDateTime);
        controller.addOrder(order);
        System.out.println("Order added successfully.");
    }

    private void deleteOrder() {
        System.out.print("Enter order number to delete: ");
        int orderNumber = scanner.nextInt();
        Order order = controller.findOrderByNumber(orderNumber);

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

    private void showPendingOrders() {
        System.out.println("Enter Customer ID to find pending order: ");
        String customerId = scanner.nextLine();
        ArrayList<Order> pendingOrders = controller.getPendingOrders(customerId);
        System.out.println("Pending Orders:");
        for (Order order : pendingOrders) {
            System.out.println(order);
        }
    }

    private void showSentOrders() {
        System.out.println("Enter Customer ID to find sent orders: ");
        String customerId = scanner.nextLine();
        ArrayList<Order> sentOrders = controller.getSentOrders(customerId);
        System.out.println("Sent Orders:");
        for (Order order : sentOrders) {
            System.out.println(order);
        }
    }
}
