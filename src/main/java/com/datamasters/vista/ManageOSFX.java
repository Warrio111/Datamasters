package com.datamasters.vista;

import com.datamasters.DAO.DAOException;
import com.datamasters.controlador.Controller;
import com.datamasters.modelo.CustomerEntity;
import com.datamasters.modelo.EntityBase;
import com.datamasters.modelo.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageOSFX implements Initializable {

    @FXML
    private TableView<EntityBase> tableView;
    private ObservableList<EntityBase> dataList = FXCollections.observableArrayList();

    public void btnAddCustomerClick(ActionEvent event) {

    }

    
    public void btnAddItemClick(ActionEvent event) {

    }

    
    public void btnAddOrderClick(ActionEvent event) {

    }

    
    public void btnDeleteCustomerClick(ActionEvent event) {

    }

    
    public void btnDeleteItemClick(ActionEvent event) {

    }

    
    public void btnDeleteOrderClick(ActionEvent event) {

    }


    public void btnShowCustomersClick(ActionEvent event) {
        try {
            // Lógica para obtener datos de clientes desde el controlador
            Controller controller = new Controller();
            ArrayList<CustomerEntity> customers = controller.getCustomers();
            System.out.println("Clientes obtenidos: " + customers.size());

            // Esto es Opcional: imprimir los datos en la consola
            for (CustomerEntity customer : customers) {
                System.out.println(customer);
            }

            // Limpia la lista actual antes de agregar nuevos datos
            dataList.clear();

            // Agrega los clientes a la lista observable
            dataList.addAll(customers);

            // Configuración dinámica de columnas
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
            // Maneja las excepciones adecuadamente, por ejemplo, muestra un mensaje de error
            e.printStackTrace();
        }
    }




    public void btnShowCustomersPremiumClick(ActionEvent event) {

    }

    
    public void btnShowCustomersStandardClick(ActionEvent event) {

    }

    
    public void btnShowItemsClick(ActionEvent event) {
        tableView.getColumns().clear();  // Limpia las columnas actuales
        // Lógica para obtener datos de artículos y actualizar la lista observable
        dataList.clear();  // Limpia la lista actual
        dataList.addAll(/* Lógica para obtener datos de artículos */);
    }

  
    public void btnShowPendingOrdersClick(ActionEvent event) {

    }

 
    public void btnShowSentOrdersClick(ActionEvent event) {

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
