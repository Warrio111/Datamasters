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
                    "1. Gestionar Articulos\n" +
                    "2. Gestionar Clientes\n" +
                    "3. Gestionar Pedidos\n" +
                    "0. Salir\n" +
                    "==========================\n" +
                    "Ingrese su eleccion: ");

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
        System.out.print("\n========== Menu Articulos ==========\n" +
                "1. Añadir Articulo\n" +
                "2. Eliminar Articulo\n" +
                "3. Mostrar Articulo\n" +
                "==========================\n" +
                "Ingrese su eleccion: ");

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

        System.out.print("\n========== Menu Clientes ==========\n" +
                "1. Agregar Cliente\n" +
                "2. Eliminar Cliente\n" +
                "3. Mostrar Clientes\n" +
                "4. Mostrar Clientes STANDARD\n" +
                "5. Mostrar Clientes PREMIUM\n" +
                "==========================\n" +
                "Ingrese su eleccion: ");

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
        System.out.print("\n========== Menu Pedidos ==========\n" +
                "1. Agregar Pedido\n" +
                "2. Eliminar Pedido\n" +
                "3. Mostrar Pedidos Pendientes\n" +
                "4. Mostrar Pedidos Enviados\n" +
                "==========================\n" +
                "Ingrese su eleccion: ");

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
                System.out.println("Opción no válida. Por favor, inténtelo de nuevo.");
        }
    }

    private void addItems() {
        System.out.print("Ingrese el código del artículo: ");
        String code = scanner.nextLine();
        System.out.print("Ingrese la descripción del artículo: ");
        String description = scanner.nextLine();
        System.out.print("Ingrese el precio de venta: ");
        double sellingPrice = scanner.nextDouble();
        System.out.print("Ingrese el costo de envío: ");
        double shippingCost = scanner.nextDouble();
        System.out.print("Ingrese el tiempo de preparación (minutos): ");
        int preparationTimeMinutes = scanner.nextInt();

        Item item = new Item(code, description, sellingPrice, shippingCost, preparationTimeMinutes);
        controller.addItem(item);
        System.out.println("Articulo añadido correctamente.");
    }

    public void removeItem() throws DAOException {
        System.out.println("Ingrese el codigo del artículo a eliminar");
        String code = scanner.nextLine();
        ArrayList<Item> items = controller.getItems();
        for (Item item : items) {
            if (code.equals(item.getCode())) {
                controller.removeItem(item);
                System.out.println("\nEl articulo (" + item.getDescription() + ") ha sido eliminado.\n");
            }
        }

    }

    private void showItems() throws DAOException {
        ArrayList<Item> items = controller.getItems();
        System.out.println("Articulos:\n");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private void addCustomer() {
        System.out.print("Ingrese el nombre del cliente: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la direccion del cliente: ");
        String address = scanner.nextLine();
        System.out.print("Ingrese el ID del cliente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el correo electronico del cliente: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese el tipo de cliente (STANDARD or PREMIUM): ");

        try {
            CustomerType customerType = CustomerType.valueOf(scanner.nextLine().toUpperCase());
            if (customerType == CustomerType.STANDARD) {
                Customer customer = new StandardCustomer(name, address, id, email);
                controller.addCustomer(customer);
                customer.setId(controller.getCustomers().get(controller.getCustomers().size() - 1).getId());
                System.out.println("\nCliente STANDARD añadido exitosamente.");
            } else if (customerType == CustomerType.PREMIUM) {
                System.out.print("Ingrese la tarifa de membresia:");
                double membershipFee = scanner.nextDouble();
                System.out.print("Ingrese el descuento de envio:");
                double shippingDiscount = scanner.nextDouble();

                Customer customer = new PremiumCustomer(name, address, id, email, membershipFee, shippingDiscount);
                controller.addCustomer(customer);
                customer.setId(controller.getCustomers().get(controller.getCustomers().size() - 1).getId());
                System.out.println("\nCliente PREMIUM añadido exitosamente.");
            } else {
                System.out.println("Tipo de cliente no valido. Por favor, intentelo de nuevo.");
            }
        } catch (Exception ex) {
            exceptionHandler.handleException(ex);
        }
    }

    public void removeCustomer() throws DAOException, SQLException {
        System.out.println("Ingrese el ID del cliente a eliminar.");
        String id = scanner.nextLine();

        ArrayList<Customer> customers = controller.getCustomers();
        for (Customer customer : customers) {
            if (id.equals(customer.getId())) {
                controller.removeCustomer(customer);
                System.out.println("\nEl cliente (" + customer.getName() + ") ha sido eliminado.");
            }
        }

    }

    private void showCustomers() throws DAOException, SQLException {
        ArrayList<Customer> customers = controller.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("\nNo hay clientes.\n");
        } else {
            System.out.println("Clientes:\n");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private void showStandardCustomers() throws DAOException, SQLException {
        ArrayList<Customer> standardCustomers = controller.getCustomersByType(CustomerType.STANDARD);
        if (standardCustomers.isEmpty()) {
            System.out.println("\nNo hay clientes STANDARD.\n");
        } else {
            System.out.println("Clientes STANDARD:\n");
            for (Customer customer : standardCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void showPremiumCustomers() throws DAOException, SQLException {
        ArrayList<Customer> premiumCustomers = controller.getCustomersByType(CustomerType.PREMIUM);
        if (premiumCustomers.isEmpty()) {
            System.out.println("\nNo hay clientes PREMIUM.\n");
        } else {
            System.out.println("Clientes PREMIUM:\n");
            for (Customer customer : premiumCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void addOrder() throws DAOException, SQLException {
        System.out.print("Ingrese el numero de pedido:");
        int orderNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Ingrese el ID del cliente: ");
        String customerId = scanner.nextLine();
        Customer customer = controller.findCustomerById(customerId);

        if (customer == null) {
            System.out.println("\nCliente no encontrado. \n\nPor favor, agregue los detalles del cliente:\n");
            addCustomer();
            customer = controller.getCustomers().get(controller.getCustomers().size() - 1);
        }
        scanner.nextLine(); // Consume the newline character
        System.out.print("Ingrese el codigo del articulo:");
        String itemCode = scanner.nextLine();
        Item item = controller.findItemByCode(itemCode);

        if (item == null) {
            System.out.println("\nArticulo no encontrado. \n\nPor favor, agregue los detalles del articulo.");
            scanner.nextLine(); // Consume the newline character
            addItems();
            item = controller.getItems().get(controller.getItems().size() - 1);

        }

        System.out.print("Ingrese la cantidad de unidades:");
        int quantityUnits = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        LocalDateTime orderDateTime = LocalDateTime.now();

        Orders order = new Orders(orderNumber, customer, item, quantityUnits, orderDateTime);
        controller.addOrder(order);
        System.out.println("\nPedido añadido exitosamente.");
    }

    private void deleteOrder() throws DAOException {
        System.out.print("Ingrese el numero de pedido a eliminar: ");
        int orderNumber = scanner.nextInt();
        Orders order = controller.findOrderByNumber(orderNumber);

        if (order != null) {
            if (!order.orderIsSent(LocalDateTime.now())) {
                controller.deleteOrderByNumber(orderNumber);
                System.out.println("\nPedido eliminado exitosamente.");
            } else {
                System.out.println("\nEl pedido no se puede eliminar, ya que ya ha sido enviado.");
            }
        } else {
            System.out.println("\nPedido no encontrado. Por favor, intentalo de nuevo.");
        }
    }

    private void showPendingOrders() throws DAOException {
        System.out.println("Ingrese el ID del cliente para encontrar pedidos pendientes: ");
        String customerId = scanner.nextLine();
        ArrayList<Orders> pendingOrders = controller.getPendingOrders(customerId);
        if (pendingOrders.isEmpty()) {
            System.out.println("\nNo hay pedidos pendientes.");
        } else {
            for (Orders order : pendingOrders) {
                System.out.println("\nPedidos pendientes: \n");
                System.out.println(order);
            }
        }
    }

    private void showSentOrders() throws DAOException {
        System.out.println("Ingrese el ID del cliente para encontrar pedidos enviados:");
        String customerId = scanner.nextLine();
        ArrayList<Orders> sentOrders = controller.getSentOrders(customerId);
        if (sentOrders.isEmpty()) {
            System.out.println("\nNo hay pedidos enviados.");
        } else {
            for (Orders order : sentOrders) {
                System.out.println("\nPedidos enviados: \n");
                System.out.println(order);
            }
        }
    }
}
