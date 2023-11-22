package com.datamasters.modelo;

import static org.junit.Assert.*;

import com.datamasters.DAO.DaoImpl.ItemDaoImpl;
import com.datamasters.controlador.Controller;
import org.junit.Before;
import org.junit.Test;
import com.datamasters.modelo.Item;
import com.datamasters.DAO.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.datamasters.modelo.List;

public class BBDDtest {

    private Item item;
    private Orders order;
    private Customer customer;
    private Connection connection;
    private Controller controller;


    public final String INSERT = "INSERT INTO Item(description,sellingPrice,shippingCost,preparationTimeMinutes) VALUES(?,?,?,?)";
    public final String UPDATE = "UPDATE Item SET description = ?, sellingPrice = ?, shippingCost = ?,preparationTimeMinutes = ? WHERE code=?";
    public final String DELETE = "DELETE FROM Item WHERE code= ?";
    public final String GETALL = "SELECT * FROM Item";
    public final String GETBYID = "SELECT * FROM Item WHERE code = ?;";

    public BBDDtest() throws SQLException {
        this.connection = UtilityMySqlDAOFactory.getConnection();
    }
    @Before
    public void setUp() throws Exception {
        controller = new Controller();
    }
    @Test
    public void insertItemBBDD() throws SQLException, DAOException {
        PreparedStatement statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(INSERT);

        statement.setString(1, "Pizza de jamón y queso");
        statement.setDouble(2, 12.50);
        statement.setDouble(3, 2.50);
        statement.setInt(4, 10);

        // Ejecuta la inserción
        int filasAfectadas = statement.executeUpdate();

        // Verifica si la inserción fue exitosa
        assertEquals(1, filasAfectadas); // Debería insertarse una sola fila

        // Realiza una consulta para verificar los datos insertados
        String consulta = "SELECT * FROM item WHERE description = 'Pizza de jamón y queso'";
        statement = connection.prepareStatement(consulta);

        ResultSet resultSet = statement.executeQuery();
        // Procesa los resultados de la consulta
        while (resultSet.next()) {
            String description = resultSet.getString("description");
            double sellingPrice = resultSet.getDouble("sellingPrice");
            double shippingCost = resultSet.getDouble("shippingCost");
            int preparationTimeMinutes = resultSet.getInt("preparationTimeMinutes");

            assertEquals("Pizza de jamón y queso", description);
            assertEquals(12.50, sellingPrice, 0.01);// Ajusta la precisión según sea necesario
            assertEquals(2.50, shippingCost, 0.01);
            assertEquals(10, preparationTimeMinutes);
        }
        resultSet.close();


    }

    @Test
    public void updateItemBBDD() throws SQLException, DAOException {
        insertItemBBDD();
        PreparedStatement statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(UPDATE);
        statement.setString(1, "Pizza de jamón y queso");
        statement.setDouble(2, 12.50);
        statement.setDouble(3, 2.50);
        statement.setInt(4, 10);
        statement.setInt(5, Integer.parseInt(controller.getItems().get(controller.getItems().size() -1).getCode()));

        // Ejecuta la inserción
        int filasAfectadas = statement.executeUpdate();

        // Verifica si la inserción fue exitosa
        assertEquals(1, filasAfectadas); // Debería insertarse una sola fila

        // Realiza una consulta para verificar los datos insertados
        String consulta = "SELECT * FROM item WHERE description = 'Pizza de jamón y queso'";
        statement = connection.prepareStatement(consulta);

        ResultSet resultSet = statement.executeQuery();
        // Procesa los resultados de la consulta
        while (resultSet.next()) {
            String description = resultSet.getString("description");
            double sellingPrice = resultSet.getDouble("sellingPrice");
            double shippingCost = resultSet.getDouble("shippingCost");
            int preparationTimeMinutes = resultSet.getInt("preparationTimeMinutes");

            assertEquals("Pizza de jamón y queso", description);
            assertEquals(12.50, sellingPrice, 0.01);// Ajusta la precisión según sea necesario
            assertEquals(2.50, shippingCost, 0.01);
            assertEquals(10, preparationTimeMinutes);
        }
        resultSet.close();
    }

   @Test
    public void removeItemBBDD() throws SQLException, DAOException {

        ItemDaoImpl dao = new ItemDaoImpl();

        int codeToDelete = 2;

        Item itemToDelete = null;

        List<Item> itemList = dao.getAll();

        for(int i = 0; i < itemList.getSize(); i++) {
            if(codeToDelete == Integer.parseInt(itemList.getAt(i).getCode())) {
                itemToDelete = itemList.getAt(i);
            }
        }

        try {
            // Llama al método remove para eliminar el elemento utilizando el ID como un String
            dao.remove(itemToDelete); // Convierte el ID a String
            // Si no se lanzó una excepción, la eliminación se realizó correctamente
            assertTrue(true);
        } catch (DAOException e) {
            e.printStackTrace();
            fail("Error en la eliminación: " + e.getMessage());
        }
    }
}


