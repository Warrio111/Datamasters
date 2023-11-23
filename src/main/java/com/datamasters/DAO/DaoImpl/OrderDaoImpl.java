package com.datamasters.DAO.DaoImpl;

import com.datamasters.DAO.*;
import com.datamasters.modelo.*;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;


public class OrderDaoImpl extends DAOFactory implements OrderDAO {

    public final String INSERT = "INSERT INTO Orders(quantityUnits,orderDateTime,preparationTimeMinutes,Customer_id,Item_code) VALUES(?,?,?,?,?)";

    public final String UPDATE = "UPDATE ORDERS SET  quantityUnits = ?, orderDateTime = ?, preparationTimeMinutes= ?, Customer_id = ? Item_code = ? WHERE orderNumber = ?";

    public final String DELETE = "DELETE FROM ORDERS WHERE Customer_id = ?";
    public final String GETALL = "SELECT * FROM ORDERS\n " +
            "JOIN Customer ON ORDERS.Customer_id = Customer.id\n" +
            "JOIN Item ON ORDERS.Item_code= Item.code\n";

    // Consulta SQL para obtener el objeto Customer y el objeto Item
    public final String objecItemCustomer = "SELECT * FROM Customer c " + "JOIN Item i ON c.id_Customer = i.customer_id " + "WHERE c.id_Customer = ?";

    PreparedStatement statement = null;

    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    Orders order = null;


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

            Connection connection = UtilityMySqlDAOFactory.getConnection();

            callableStatement = connection.prepareCall("{call InsertOrder(?,?,?,?,?)}");
            callableStatement.setInt(1, c.getQuantityUnits());
            Timestamp date = Timestamp.valueOf(c.getOrderDateTime());
            callableStatement.setTimestamp(2, date);
            callableStatement.setInt(3, c.getPreparationTimeMinutes());
            callableStatement.setString(4, c.getCustomer().getId());
            callableStatement.setString(5, c.getItem().getCode());

            resultSet = callableStatement.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);

        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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
            Connection connection = UtilityMySqlDAOFactory.getConnection();

            callableStatement = connection.prepareCall("{call UpdateOrder(?,?,?,?,?,?)}");
            callableStatement.setInt(1, c.getQuantityUnits());
            Timestamp date = Timestamp.valueOf(c.getOrderDateTime());
            callableStatement.setTimestamp(2, date);
            callableStatement.setInt(3, c.getPreparationTimeMinutes());
            callableStatement.setString(4, c.getCustomer().getId());
            callableStatement.setString(5, c.getItem().getCode());
            callableStatement.setInt(6, c.getOrderNumber());


            resultSet = callableStatement.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);

        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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
            Connection connection = UtilityMySqlDAOFactory.getConnection();

            callableStatement = connection.prepareCall("{call DeleteOrder(?)}");
            callableStatement.setInt(1, c.getOrderNumber());

            resultSet = callableStatement.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);

        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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

            if (customerType.equals("PREMIUM")) {

                c = new PremiumCustomer(name, address, id, email, membershipFee, shippingDiscount);
            } else {
                c = new StandardCustomer(name, address, id, email);
            }
            Orders order = new Orders(orderNumber, c, it, quantityUnits, orderDateTime);

            return order;

        } catch (SQLException e) {
            //e.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Can't converted",e);
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

        try {
            Connection connection = UtilityMySqlDAOFactory.getConnection();

            callableStatement = connection.prepareCall("{call orderById(?)}");
            callableStatement.setInt(1, id);

            resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                order = convertir(resultSet);
            } else {
                throw new DAOException("Order not found");
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

            if (callableStatement != null) {
                try {
                    callableStatement.close();
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