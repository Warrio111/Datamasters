/*
package com.datamasters.DAO.DaoImpl;

import com.datamasters.DAO.DAOException;
import com.datamasters.DAO.*;
import com.datamasters.modelo.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MysqlOrderDAO implements OrderDAO {

    public final String INSERT = "INSERT INTO Orders(orderNumber,id_Customer,code_item,quantityUnits,date_item) VALUES(?,?,?,?,?)";
    public final String UPDATE = "UPDATE ORDERS SET  id_Customer = ?, code_item = ?, quantityUnits = ?, date_Item = ? WHERE orderNumber = ?";
    public final String DELETE = "DELETE FROM ORDERS WHERE id_Customer = ?";
    public final String GETALL= "SELECT * FROM ORDERS\n "+
            "JOIN Customer ON ORDERS.id_Customer = Customer.id\n" +
            "JOIN Item ON ORDERS.code_item = Item.code\n";

    // Consulta SQL para obtener el objeto Customer y el objeto Item
    public final String objecItemCustomer =  "SELECT * FROM Customer c " + "JOIN Item i ON c.id_Customer = i.customer_id " + "WHERE c.id_Customer = ?";

    public final String GETBYID= "SELECT * FROM ORDERS\n" +
            "JOIN Customer ON ORDERS.id_Customer = Customer.id\n" +
            "JOIN Item ON ORDERS.code_item = Item.code\n" +
            "WHERE orderNumber = ?;";

    private Connection connection;

    public MysqlOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Orders c) throws DAOException {
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(INSERT);
            statement.setInt(1,c.getOrderNumber());
            statement.setString(2,c.getCustomer().getId());
            statement.setString(3,c.getItem().getCode());
            statement.setInt(4,c.getQuantityUnits());
            Timestamp date = Timestamp.valueOf(c.getOrderDateTime());
            statement.setTimestamp(5,date);

            if(statement.executeUpdate() == 0){
                throw new DAOException("Could not be iserted");
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
    @Override
    public void update(Orders c) throws DAOException{

        PreparedStatement statement = null;
        try{
            if(c != null) {
                statement = connection.prepareStatement(UPDATE);
                statement.setString(1, c.getCustomer().getId());
                statement.setString(2, c.getItem().getCode());
                statement.setInt(3, c.getQuantityUnits());
                Timestamp date = Timestamp.valueOf(c.getOrderDateTime());
                statement.setTimestamp(4, date);
                statement.setInt(5,c.getOrderNumber());

                if (statement.executeUpdate() == 0) {
                    throw new DAOException("Could not be updated");
                }else{
                    System.out.println("Registro eliminado");
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
    @Override
    public void remove(Orders c) throws DAOException{
        PreparedStatement statement = null;
        try{
            if(c != null) {
                statement = connection.prepareStatement(DELETE);
                statement.setString(1, c.getCustomer().getId());
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
    //Convert data of ITEM and CUSTOMER TO OBJECT
    public Orders convertir(ResultSet rs) throws DAOException{
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

            Item it = new Item(code,description,sellingPrice,shippingCost,preparationTimeMinutes);
            if ("PREMIUM".equals(customerType)){
                 c = new PremiumCustomer(name,address,id,email,membershipFee,shippingDiscount);
            }else{
                 c = new StandardCustomer(name,address,id,email);
            }
            Orders order = new Orders(orderNumber,c,it,quantityUnits,orderDateTime);

            return order;

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Can't converted");
        }

    }
    @Override
    public List<Orders> getAll() throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Orders> ordersList = new ArrayList<>();
        //"SELECT (orderNumber,id_Customer,code_item,quantityUnits,date_item) FROM ORDERS WHERE orderNUMBER = ?";
        try {
            statement = connection.prepareStatement(GETALL);
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
        }

        return ordersList;
    }

    @Override
    public Orders getById(String id) throws DAOException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Orders order = null;

        try {
            statement = connection.prepareStatement(GETBYID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Customer c = null;
            if (resultSet.next()) {
                order = convertir(resultSet);
                System.out.println(order.toString());
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

    public static void main(String[] args) throws DAOException {
        Connection connection = null;
        Customer c = new PremiumCustomer("Jordi", "Barcelona", "2", "sdggs", 10.05, 30.50);
        Item it = new Item("1", "dsgdsgs", 10.00, 12.00, 2);
        */
/*try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");

            OrderDAO dao = new MysqlOrderDAO(connection);
            Orders order = new Orders(2, c, it, 3, LocalDateTime.now());
            dao.insert(order);

            System.out.println("Registro insertado exitosamente.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new DAOException(ex);
                }
            }

        }*//*

        */
/*try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");

            OrderDAO dao = new MysqlOrderDAO(connection);
            Orders order = new Orders(2,c,it,50, LocalDateTime.now());

            dao.update(order);

            System.out.println("Registro actualizado exitosamente.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new DAOException(ex);
                }
            }

        }
    }*//*

       */
/* try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");


            OrderDAO dao = new MysqlOrderDAO(connection);
            Orders order = new Orders(2, c, it, 50, LocalDateTime.now());

            dao.remove(order);

            System.out.println("Registro eliminado exitosamente.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new DAOException(ex);
                }
            }

        }*//*

       */
/* try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");


            OrderDAO dao = new MysqlOrderDAO(connection); // Reemplaza "TuClaseConInsert" con el nombre de tu clase
            Orders order = new Orders(2, c, it, 50, LocalDateTime.now());

            dao.getById("1");

            System.out.println("Registro encontrado exitosamente.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new DAOException(ex);
                }
            }

        }*//*

        try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");


            OrderDAO dao = new MysqlOrderDAO(connection); // Reemplaza "TuClaseConInsert" con el nombre de tu clase
            Orders order = new Orders(2, c, it, 50, LocalDateTime.now());

            dao.getAll();

            System.out.println("Registro encontrado exitosamente.");

            List<Orders> ordersList = dao.getAll();
            for (Orders orders : ordersList) {
                System.out.println(orders);
            }
            //System.out.println(ordersList.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new DAOException(ex);
                }
            }

        }
    }

}
*/
