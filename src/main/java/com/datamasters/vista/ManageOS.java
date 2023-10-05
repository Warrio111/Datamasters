package com.datamasters.vista;
import  com.datamasters.controlador.Controller;
import java.util.Scanner;

public class ManageOS {
    private final Controller controller;
    private final Scanner scanner;

    public  ManageOS() {
        this.controller = new Controller();
        this.scanner = new Scanner(System.in);
    }
    public ManageOS(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    public ManageOS(ManageOS manageOS) {
        this.controller = manageOS.controller;
        this.scanner = manageOS.scanner;
    }

    public void run() {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Orders");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    manageProducts();
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
            }
        }

        scanner.close();
    }

    private void manageProducts() {
        // Implement product management logic
    }

    private void manageCustomers() {
        // Implement customer management logic
    }

    private void manageOrders() {
        // Implement order management logic
    }
}
