package com.datamasters.vista;

import com.datamasters.DAO.DAOException;
import com.datamasters.controlador.Controller;
import com.datamasters.modelo.CustomerEntity;
import com.datamasters.modelo.CustomerType;
import com.datamasters.modelo.EntityBase;
import com.datamasters.modelo.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
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
    public ComboBox choiceCustomerType;
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
    public TextField txtPreparationTimeMinutes;
    public Button btnConfirmAddCustomer;
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
            // Logic to obtain customers data from the controller
            Controller controller = new Controller();
            ArrayList<CustomerEntity> customers = controller.getCustomersByType(CustomerType.STANDARD);
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

    
    public void btnShowItemsClick(ActionEvent event) {
        try{
            // Clean the actual list to add new data
            tableView.getColumns().clear();

            // Logic to obtain items data from the controller
            Controller controller = new Controller();
            ArrayList<ItemEntity> items = controller.getItems();
            System.out.println("Items size: " + items.size());

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

  
    public void btnShowPendingOrdersClick(ActionEvent event) {

    }
    public void btnShowSentOrdersClick(ActionEvent event) {

    }
    public void btnConfirmAddCustomerClick(ActionEvent actionEvent) {
    }
    public void btnConfirmAddItemClick(ActionEvent actionEvent) {
    }
    public void btnConfirmDeleteCustomerClick(ActionEvent actionEvent) {
    }
    public void btnConfirmDeleteItemClick(ActionEvent actionEvent) {
    }
    public void btnConfirmDeleteOrderClick(ActionEvent actionEvent) {
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
    }



}
