package com.datamasters.DAO.DaoImpl;

import com.datamasters.DAO.*;
import com.datamasters.modelo.*;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;


public class OrderDaoImpl extends DAOFactory implements OrderDAO {

    public final String INSERT = "INSERT INTO Orders(orderNumber,quantityUnits,orderDateTime,preparationTimeMinutes,Customer_id,Item_code) VALUES(?,?,?,?,?,?)";
    public final String UPDATE = "UPDATE ORDERS SET  orderNumber = ?, quantityUnits = ?, orderDateTime = ?, preparationTimeMinutes= ?, Customer_id = ? WHERE orderNumber = ?";
    public final String DELETE = "DELETE FROM ORDERS WHERE Customer_id = ?";
    public final String GETALL = "SELECT * FROM ORDERS\n " +
            "JOIN Customer ON ORDERS.Customer_id = Customer.id\n" +
            "JOIN Item ON ORDERS.Item_code= Item.code\n";

    // Consulta SQL para obtener el objeto Customer y el objeto Item
    public final String objecItemCustomer = "SELECT * FROM Customer c " + "JOIN Item i ON c.id_Customer = i.customer_id " + "WHERE c.id_Customer = ?";

    PreparedStatement statement = null;


    public final String GETBYID = "SELECT * FROM ORDERS\n" +
            "JOIN Customer ON ORDERS.Customer_id = Customer.id\n" +
            "JOIN Item ON ORDERS.Item_code = Item.code\n" +
            "WHERE orderNumber = ?;";

    /**
     * @return
     */
    @Override
    public CustomerDAO getCustomerDAO() {
        return null;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public ItemDAO getItemDAO() throws DAOException {
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
    public void insert(Orders c) throws DAOException {

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(INSERT);
            statement.setInt(1,c.getOrderNumber());
            statement.setInt(2, c.getQuantityUnits());
            Timestamp date = Timestamp.valueOf(c.getOrderDateTime());
            statement.setTimestamp(3, date);
            statement.setInt(4, c.getPreparationTimeMinutes());
            statement.setString(5, c.getCustomer().getId());
            statement.setString(6, c.getItem().getCode());

            if (statement.executeUpdate() == 0) {
                throw new DAOException("Could not be iserted");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
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
    public void update(Orders c) throws DAOException {
        try {
            if (c != null) {
                statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(UPDATE);
                statement.setInt(1,c.getOrderNumber());
                statement.setInt(2, c.getQuantityUnits());
                Timestamp date = Timestamp.valueOf(c.getOrderDateTime());
                statement.setTimestamp(3, date);
                statement.setInt(4, c.getPreparationTimeMinutes());
                statement.setString(5, c.getCustomer().getId());
                statement.setString(6, c.getItem().getCode());
                statement.setInt(7, c.getOrderNumber());

                if (statement.executeUpdate() == 0) {
                    throw new DAOException("Could not be updated");
                } else {
                    System.out.println("Registro actualizado");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
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
    public void remove(Orders c) throws DAOException {
        try {
            if (c != null) {
                statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(DELETE);
                statement.setString(1, c.getCustomer().getId());
                int customerDelete = statement.executeUpdate();
                if (customerDelete == 0) {
                    throw new DAOException("Could not be deleted");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new DAOException("ERROR in SQL", ex);
                }
            }

        }
    }

    public Orders convertir(ResultSet rs) throws DAOException {
        Customer c = null;
        try {
            String name = rs.getString("name");
            String address = rs.getString("address");
            String id = rs.getString("id");
            String email = rs.getString("email");
            String customerType = rs.getString("customerType");
            double membershipFee = rs.getDouble("membershipFee");
            double shippingDiscount = rs.getDouble("shippingDiscount");
            int orderNumber = rs.getInt("orderNumber");
            int quantityUnits = rs.getInt("quantityUnits");
            Timestamp orderDateTimeTimestamp = rs.getTimestamp("orderDateTime");
            LocalDateTime orderDateTime = orderDateTimeTimestamp.toLocalDateTime();
            String code = rs.getString("code");
            String description = rs.getString("description");
            double sellingPrice = rs.getDouble("sellingPrice");
            double shippingCost = rs.getDouble("shippingCost");
            int preparationTimeMinutes = rs.getInt("preparationTimeMinutes");

            Item it = new Item(code, description, sellingPrice, shippingCost, preparationTimeMinutes);
            if ("PREMIUM".equals(customerType)) {
                c = new PremiumCustomer(name, address, id, email, membershipFee, shippingDiscount);
            } else {
                c = new StandardCustomer(name, address, id, email);
            }
            Orders order = new Orders(orderNumber, c, it, quantityUnits, orderDateTime);

            return order;

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
    public List<Orders> getAll() throws DAOException {
        ResultSet rs = null;
        List<Orders> ordersList = new List<>();
        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETALL);
            rs = statement.executeQuery();
            while (rs.next()) {
                ordersList.add(convertir(rs));
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
            return ordersList;
        }
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Orders getById(int id) throws DAOException {
        ResultSet resultSet = null;
        Orders order = null;

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETBYID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Customer c = null;
            if (resultSet.next()) {
                order = convertir(resultSet);
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
        return order;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public boolean orderIsSent(int id) throws DAOException {
        return false;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public boolean orderIsCancelable(int id) throws DAOException {
        return false;
    }

}