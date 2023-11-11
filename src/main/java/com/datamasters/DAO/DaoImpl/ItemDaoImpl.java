package com.datamasters.DAO.DaoImpl;
import com.datamasters.DAO.*;
import com.datamasters.modelo.Customer;
import com.datamasters.modelo.Item;
import com.datamasters.modelo.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(UPDATE);
            statement.setString(1, c.getDescription());
            statement.setDouble(2, c.getSellingPrice());
            statement.setDouble(3, c.getShippingCost());
            statement.setInt(4, c.getPreparationTimeMinutes());
            statement.setInt(5,Integer.parseInt(c.getCode()));

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
    public void remove(Item c) throws DAOException {
        try{
            if(c != null) {
                statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(DELETE);
                statement.setString(1, c.getCode());
                int customerDelete = statement.executeUpdate();
                if (customerDelete == 0) {
                    throw new DAOException("Could not be deleted");
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL",ex);
        }finally{
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException ex){
                    throw new DAOException("ERROR in SQL",ex);
                }
            }

        }
    }
    public Item convertir(ResultSet rs) throws DAOException{
        Item it = null;
        try {

            String code = rs.getString("code");
            String description = rs.getString("description");
            double sellingPrice = rs.getDouble("sellingPrice");
            double shippingCost = rs.getDouble("shippingCost");
            int preparationTimeMinutes = rs.getInt("preparationTimeMinutes");

            it = new Item(code,description,sellingPrice,shippingCost,preparationTimeMinutes);

            return it;

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Can't converted");
        }

    }
    /**
     * @return
     * @throws DAOException
     */
    @Override
    public List<Item> getAll() throws DAOException {
        ResultSet rs = null;
        List<Item> itemList = new List<>(); // Cambia a tu clase List
        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETALL);
            rs = statement.executeQuery();
            while (rs.next()) {
                itemList.add(convertir(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error in SQL", ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error in SQL", ex);
                }
            }
        }
        return itemList;
    }


    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Item getById(int id) throws DAOException {
        ResultSet resultSet = null;
        Item it = null;

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETBYID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Customer c = null;
            if (resultSet.next()) {
                it = convertir(resultSet);
                System.out.println(it.toString());
            } else {
                throw new DAOException("Customer not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return it;
    }

}
