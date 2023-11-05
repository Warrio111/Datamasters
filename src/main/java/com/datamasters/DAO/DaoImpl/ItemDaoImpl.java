package com.datamasters.DAO.DaoImpl;
import com.datamasters.DAO.*;
import com.datamasters.modelo.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl extends DAOFactory implements ItemDAO {
    public final String INSERT = "INSERT INTO Item(description,sellingPrice,shippingCost,preparationTimeMinutes) VALUES(?,?,?,?)";
    public final String UPDATE = "UPDATE Item SET description = ?, sellingPrice = ?, shippingCost = ?,preparationTimeMinutes = ? WHERE code=?";
    public final String DELETE = "DELETE FROM Item WHERE code= ?";
    public final String GETALL= "SELECT * FROM Item";
    public final String GETBYID= "SELECT * FROM Item WHERE code = ?;";
    PreparedStatement statement = null;
    public ItemDaoImpl() throws DAOException {
        getDAOFactory(1);
    }

    /**
     * @return
     */
    @Override
    public CustomerDAO getCustomerDAO() throws SQLException {
           return null;
    }

    /**
     * @return
     */
    @Override
    public ItemDAO getItemDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public OrderDAO getOrdersDAO() {
        return null;
    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void insert(Item c) throws DAOException {

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(INSERT);
            statement.setString(1, c.getDescription());
            statement.setDouble(2, c.getSellingPrice());
            statement.setDouble(3, c.getShippingCost());
            statement.setInt(4, c.getPreparationTimeMinutes());

            if (statement.executeUpdate() == 0) {
                throw new DAOException("Could not be iserted");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);
        } finally {
            if (statement != null) {
                try {
                    UtilityMySqlDAOFactory.closeConnection();
                } catch (SQLException ex) {
                    throw new DAOException("ERROR in SQL", ex);
                }
            }
        }
    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void update(Item c) throws DAOException {

    }

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void remove(Item c) throws DAOException {

    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public List<Item> getAll() throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Item getById(int id) throws DAOException {
        return null;
    }


    public static void main(String[] args) throws DAOException {
        ItemDaoImpl itemDao = new ItemDaoImpl();
        Item item = new Item();
        item.setDescription("Pizza");
        item.setSellingPrice(100.0);
        item.setShippingCost(50.0);
        item.setPreparationTimeMinutes(30);
        itemDao.insert(item);
    }


}
