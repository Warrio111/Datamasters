package com.datamasters.vista;

import com.datamasters.DAO.DAOException;
import com.datamasters.controlador.Controller;
import com.datamasters.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.query.Order;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageOSFX implements Initializable {
    @FXML
    public GridPane customerGridPane;
    @FXML
    public Label lblAddCustomer;
    @FXML
    public Label lblAddItem;
    @FXML
    public Label lblAddOrder;
    public TextField txtCustomerName;
    public TextField txtAddress;
    public TextField txtEmail;
    @FXML
    public ComboBox<CustomerType> choiceCustomerType;
    public TextField txtMembershipFee;
    public TextField txtShippingDiscount;
    public GridPane itemGridPane;
    public TextField txtItemDescription;
    public TextField txtSellingPrice;
    public TextField txtShippingCost;
    public TextField txtPreparationTime;
    public GridPane orderGridPane;
    public TextField txtCustomerId;
    public TextField txtItemCode;
    public TextField txtQuantityUnits;
    public Button btnConfirmAddCustomer;
    public Button btnConfirmAddItem;
    public Button btnConfirmAddOrder;
    public Label lblDeleteCustomer;
    public GridPane deleteCustomerPane;
    public TextField txtDeleteCustomerId;
    public Button btnConfirmDeleteCustomer;
    public Label lblDeleteItem;
    public Label lblDeleteOrder;
    public Button btnConfirmDeleteOrder;
    public TextField txtOrderId;
    public GridPane deleteOrderPane;
    public Button btnConfirmDeleteItem;
    public GridPane deleteItemPane;
    public TextField txtItemCodeId;
    public Label lblShowPendingOrders;
    public GridPane showPendingOrdersPane;
    public Label lblShowSentOrders;
    public GridPane showSentOrdersPane;
    public TextField txtPendingCustomerID;
    public Button btnConfirmShowPendingOrders;
    public TextField txtSentCustomerID;
    public Button btnConfirmShowSentOrders;

    @FXML
    private TableView<EntityBase> tableView;
    private ObservableList<EntityBase> dataList = FXCollections.observableArrayList();
    public void cleanVisibleControls(){
        // Add controls grid panes
        lblAddCustomer.setVisible(false);
        lblAddItem.setVisible(false);
        lblAddOrder.setVisible(false);
        customerGridPane.setVisible(false);
        itemGridPane.setVisible(false);
        orderGridPane.setVisible(false);
        // Delete controls grid panes
        lblDeleteCustomer.setVisible(false);
        lblDeleteItem.setVisible(false);
        lblDeleteOrder.setVisible(false);
        deleteCustomerPane.setVisible(false);
        deleteItemPane.setVisible(false);
        deleteOrderPane.setVisible(false);
        // Show controls grid panes
        lblShowPendingOrders.setVisible(false);
        lblShowSentOrders.setVisible(false);
        showPendingOrdersPane.setVisible(false);
        showSentOrdersPane.setVisible(false);

    }

    public void btnAddCustomerClick(ActionEvent event) {
        cleanVisibleControls();
        lblAddCustomer.setVisible(true);
        customerGridPane.setVisible(true);
    }

    
    public void btnAddItemClick(ActionEvent event) {
        cleanVisibleControls();
        lblAddItem.setVisible(true);
        itemGridPane.setVisible(true);
    }

    
    public void btnAddOrderClick(ActionEvent event) {
        cleanVisibleControls();
        lblAddOrder.setVisible(true);
        orderGridPane.setVisible(true);

    }

    
    public void btnDeleteCustomerClick(ActionEvent event) {
        cleanVisibleControls();
        lblDeleteCustomer.setVisible(true);
        deleteCustomerPane.setVisible(true);

    }

    
    public void btnDeleteItemClick(ActionEvent event) {
        cleanVisibleControls();
        lblDeleteItem.setVisible(true);
        deleteItemPane.setVisible(true);

    }

    
    public void btnDeleteOrderClick(ActionEvent event) {
        cleanVisibleControls();
        lblDeleteOrder.setVisible(true);
        deleteOrderPane.setVisible(true);

    }

    public void btnShowCustomersClick(ActionEvent event) {
        try {
            // Clean the actual list to add new data
            tableView.getColumns().clear();
            // Logic to obtain customers data from the controller
            Controller controller = new Controller();
            ArrayList<CustomerEntity> customers = controller.getCustomers();
            System.out.println("Clients size: " + customers.size());

            // Opcional iteration for printing the data in the console
            for (CustomerEntity customer : customers) {
                System.out.println(customer);
            }

            // Clean the actual list to add new data
            dataList.clear();

            // Add clients to the observable lsit
            dataList.addAll(customers);

            // Configuring dinamically the columns of the TableView
            tableView.getColumns().clear();
            if (!customers.isEmpty()) {
                CustomerEntity sampleCustomer = customers.get(0);
                for (String property : sampleCustomer.getProperties()) {
                    TableColumn<EntityBase, String> column = new TableColumn<>(property);
                    column.setCellValueFactory(new PropertyValueFactory<>(property));
                    tableView.getColumns().add(column);
                }
            }

        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnShowCustomersPremiumClick(ActionEvent event) {
        try {
            // Clean the actual list to add new data
            tableView.getColumns().clear();
            // Logic to obtain customers data from the controller
            Controller controller = new Controller();
            ArrayList<CustomerEntity> customers = controller.getCustomersByType(CustomerType.PREMIUM);
            System.out.println("Clients size: " + customers.size());

            // Opcional iteration for printing the data in the console
            for (CustomerEntity customer : customers) {
                System.out.println(customer);
            }

            // Clean the actual list to add new data
            dataList.clear();

            // Add clients to the observable lsit
            dataList.addAll(customers);

            // Configuring dinamically the columns of the TableView
            tableView.getColumns().clear();
            if (!customers.isEmpty()) {
                CustomerEntity sampleCustomer = customers.get(0);
                for (String property : sampleCustomer.getProperties()) {
                    TableColumn<EntityBase, String> column = new TableColumn<>(property);
                    column.setCellValueFactory(new PropertyValueFactory<>(property));
                    tableView.getColumns().add(column);
                }
            }

        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnShowCustomersStandardClick(ActionEvent event) {
        try {
            // Clean the actual list to add new data
            tableView.getColumns().clear();
            // Logic to obtain customers data from the controller
            Controller controller = new Controller();
            ArrayList<CustomerEntity> customers = controller.getCustomersByType(CustomerType.STANDARD);
            if(customers.isEmpty()){
                showWarning("There are no standard customers");
            }

            // Opcional iteration for printing the data in the console
            for (CustomerEntity customer : customers) {
                System.out.println(customer);
            }

            // Clean the actual list to add new data
            dataList.clear();

            // Add clients to the observable lsit
            dataList.addAll(customers);

            // Configuring dinamically the columns of the TableView
            tableView.getColumns().clear();
            if (!customers.isEmpty()) {
                CustomerEntity sampleCustomer = customers.get(0);
                for (String property : sampleCustomer.getProperties()) {
                    TableColumn<EntityBase, String> column = new TableColumn<>(property);
                    column.setCellValueFactory(new PropertyValueFactory<>(property));
                    tableView.getColumns().add(column);
                }
            }

        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void btnShowItemsClick(ActionEvent event) {
        try{
            // Clean the actual list to add new data
            tableView.getColumns().clear();

            // Logic to obtain items data from the controller
            Controller controller = new Controller();
            ArrayList<ItemEntity> items = controller.getItems();
            if(items.isEmpty()){
                showWarning("There are no items");
            }
            // Opcional iteration for printing the data in the console
            for (ItemEntity item : items) {
                System.out.println(item);
            }

            // Clean the actual list to add new data
            dataList.clear();

            // Add items to the observable lsit
            dataList.addAll(items);

            // Configuring dinamically the columns of the TableView
            tableView.getColumns().clear();
            if (!items.isEmpty()) {
                ItemEntity sampleItem = items.get(0);
                for (String property : sampleItem.getProperties()) {
                    TableColumn<EntityBase, String> column = new TableColumn<>(property);
                    column.setCellValueFactory(new PropertyValueFactory<>(property));
                    tableView.getColumns().add(column);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnShowOrdersClick(ActionEvent actionEvent) {
        try{
            // Clean the actual list to add new data
            tableView.getColumns().clear();

            // Logic to obtain items data from the controller
            Controller controller = new Controller();
            ArrayList<OrdersEntity> orders = controller.getOrders();
            if(orders.isEmpty()){
                showWarning("There are no orders");
            }
            // Opcional iteration for printing the data in the console
            for (OrdersEntity order : orders) {
                System.out.println(order);
            }

            // Clean the actual list to add new data
            dataList.clear();

            // Add items to the observable lsit
            dataList.addAll(orders);

            // Configuring dinamically the columns of the TableView
            tableView.getColumns().clear();
            if (!orders.isEmpty()) {
                OrdersEntity sampleOrder = orders.get(0);
                for (String property : sampleOrder.getProperties()) {
                    TableColumn<EntityBase, String> column = new TableColumn<>(property);
                    column.setCellValueFactory(new PropertyValueFactory<>(property));
                    tableView.getColumns().add(column);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void btnShowPendingOrdersClick(ActionEvent event) {
        cleanVisibleControls();
        lblShowPendingOrders.setVisible(true);
        showPendingOrdersPane.setVisible(true);
    }
    public void btnShowSentOrdersClick(ActionEvent event) {
        cleanVisibleControls();
        lblShowSentOrders.setVisible(true);
        showSentOrdersPane.setVisible(true);
    }
    public void btnConfirmAddCustomerClick(ActionEvent actionEvent) {
        // Get values from the text fields
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        CustomerType customerType = choiceCustomerType.getValue();
        String membershipFee = txtMembershipFee.getText();
        String shippingDiscount = txtShippingDiscount.getText();
        // Validate that required fields are not empty
        if (customerName.isEmpty() || address.isEmpty() || email.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        if ( customerType != CustomerType.PREMIUM){
            customerType = CustomerType.STANDARD;
            membershipFee = "0";
            shippingDiscount = "0";
        }
        // Create a new customer
        try {
            Controller controller = new Controller();
            CustomerEntity customer = new CustomerEntity();
            customer.setName(customerName);
            customer.setAddress(address);
            customer.setEmail(email);
            customer.setCustomerType(customerType);
            customer.setMembershipFee(Double.parseDouble(membershipFee));
            customer.setShippingDiscount(Double.parseDouble(shippingDiscount));
            controller.addCustomer(customer);
            btnShowCustomersClick(actionEvent);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void btnConfirmAddItemClick(ActionEvent actionEvent) {
        // Get values from the text fields
        String itemDescription = txtItemDescription.getText();
        String sellingPrice = txtSellingPrice.getText();
        String shippingCost = txtShippingCost.getText();
        String preparationTime = txtPreparationTime.getText();
        // Validate that required fields are not empty
        if (itemDescription.isEmpty() || sellingPrice.isEmpty() || shippingCost.isEmpty() || preparationTime.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        // Create a new item
        try {
            Controller controller = new Controller();
            ItemEntity item = new ItemEntity();
            item.setDescription(itemDescription);
            item.setSellingPrice(Double.parseDouble(sellingPrice));
            item.setShippingCost(Double.parseDouble(shippingCost));
            item.setPreparationTimeMinutes(Integer.parseInt(preparationTime));
            controller.addItem(item);
            btnShowItemsClick(actionEvent);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnConfirmAddOrderClick(ActionEvent actionEvent) {
        // Get values from the text fields
        String customerId = txtCustomerId.getText();
        String itemCode = txtItemCode.getText();
        String quantityUnits = txtQuantityUnits.getText();
        // Validate that required fields are not empty
        if (customerId.isEmpty() || itemCode.isEmpty() || quantityUnits.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        // Create a new order
        try {
            Controller controller = new Controller();
            // Validate if customer exists
            CustomerEntity customer = controller.findCustomerById(Integer.parseInt(customerId));
            if (customer == null){
                showAlert("Customer not found");
                btnAddCustomerClick(actionEvent);
                return;
            }
            // Validate if item exists
            ItemEntity item = controller.findItemByCode(Integer.parseInt(itemCode));
            if (item == null){
                showAlert("Item not found");
                btnAddItemClick(actionEvent);
                return;
            }
            OrdersEntity order = new OrdersEntity();
            order.setCustomerId(Integer.parseInt(customerId));
            order.setItemCode(Integer.parseInt(itemCode));
            order.setQuantityUnits(Integer.parseInt(quantityUnits));
            order.setPreparationTimeMinutes(item.getPreparationTimeMinutes());
            LocalDateTime orderDateTime = LocalDateTime.now();
            order.setOrderDateTime(Timestamp.valueOf(orderDateTime));
            controller.addOrder(order);
            btnShowOrdersClick(actionEvent);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnConfirmDeleteCustomerClick(ActionEvent actionEvent) {
        String customerId = txtDeleteCustomerId.getText();
        if (customerId.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        try {
            Controller controller = new Controller();
            CustomerEntity customer = controller.findCustomerById(Integer.parseInt(customerId));
            if (customer == null) {
                showAlert("Customer not found");
                btnDeleteCustomerClick(actionEvent);
                return;
            }
            controller.removeCustomer(customer);
            btnShowCustomersClick(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnConfirmDeleteItemClick(ActionEvent actionEvent) {
        String itemCode = txtItemCodeId.getText();
        if (itemCode.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        try {
            Controller controller = new Controller();
            ItemEntity item = controller.findItemByCode(Integer.parseInt(itemCode));
            if (item == null) {
                showAlert("Item not found");
                btnDeleteItemClick(actionEvent);
                return;
            }
            controller.removeItem(item);
            btnShowItemsClick(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnConfirmDeleteOrderClick(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        if (orderId.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        try {
            Controller controller = new Controller();
            OrdersEntity order = controller.findOrderByNumber(Integer.parseInt(orderId));
            if (order == null) {
                showAlert("Order not found");
                btnDeleteOrderClick(actionEvent);
                return;
            }
            controller.removeOrder(order);
            btnShowOrdersClick(actionEvent);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnConfirmShowPendingOrders(ActionEvent actionEvent) {
        String customerId = txtPendingCustomerID.getText();
        if (customerId.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        try{
            // Clean the actual list to add new data
            tableView.getColumns().clear();

            // Logic to obtain items data from the controller
            Controller controller = new Controller();
            ArrayList<OrdersEntity> orders = controller.getPendingOrders(Integer.parseInt(customerId));
            if(orders.isEmpty()){
                showWarning("There are no pending orders");
            }
            // Opcional iteration for printing the data in the console
            for (OrdersEntity order : orders) {
                System.out.println(order);
            }

            // Clean the actual list to add new data
            dataList.clear();

            // Add items to the observable lsit
            dataList.addAll(orders);

            // Configuring dinamically the columns of the TableView
            tableView.getColumns().clear();
            if (!orders.isEmpty()) {
                OrdersEntity sampleOrder = orders.get(0);
                for (String property : sampleOrder.getProperties()) {
                    TableColumn<EntityBase, String> column = new TableColumn<>(property);
                    column.setCellValueFactory(new PropertyValueFactory<>(property));
                    tableView.getColumns().add(column);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void btnConfirmShowSentOrders(ActionEvent actionEvent) {
        String customerId = txtSentCustomerID.getText();
        if (customerId.isEmpty()) {
            // Show an alert or handle the validation failure as needed
            showAlert("All fields are required");
            return;
        }
        try{
            // Clean the actual list to add new data
            tableView.getColumns().clear();

            // Logic to obtain items data from the controller
            Controller controller = new Controller();
            ArrayList<OrdersEntity> orders = controller.getSentOrders(Integer.parseInt(customerId));
            if(orders.isEmpty()){
                showWarning("There are no Sent orders");
            }
            // Opcional iteration for printing the data in the console
            for (OrdersEntity order : orders) {
                System.out.println(order);
            }

            // Clean the actual list to add new data
            dataList.clear();

            // Add items to the observable lsit
            dataList.addAll(orders);

            // Configuring dinamically the columns of the TableView
            tableView.getColumns().clear();
            if (!orders.isEmpty()) {
                OrdersEntity sampleOrder = orders.get(0);
                for (String property : sampleOrder.getProperties()) {
                    TableColumn<EntityBase, String> column = new TableColumn<>(property);
                    column.setCellValueFactory(new PropertyValueFactory<>(property));
                    tableView.getColumns().add(column);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configuración inicial del controlador, si es necesario
        tableView.setItems(dataList);  // Establece la lista observable en el TableView
        choiceCustomerType.setItems(FXCollections.observableArrayList(CustomerType.values()));
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
