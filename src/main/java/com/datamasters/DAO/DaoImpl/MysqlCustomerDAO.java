/*
package com.datamasters.DAO.DaoImpl;

import com.datamasters.DAO.CustomerDAO;
import com.datamasters.DAO.DAOException;
import com.datamasters.modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCustomerDAO implements CustomerDAO {
    public final String INSERT = "INSERT INTO Customer (name,address,id,email,customerType,membershipFee,shippingDiscount) VALUES(?,?,?,?,?,?,?)";
    public final String UPDATE = "UPDATE Customer SET name = ?, address = ?, id = ?, email = ?,customerType =?, memberShipFee = ?,shippingDiscount = ?  WHERE id=?";
    public final String DELETE = "DELETE FROM Customer WHERE id= ?";
    public final String GETALL= "SELECT * FROM Customer";
    public final String GETBYID= "SELECT * FROM Customer WHERE id = ?;";

    private Connection connection;

    public MysqlCustomerDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Customer c) throws DAOException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, c.getName());
            statement.setString(2, c.getAddress());
            statement.setString(3, c.getId());
            statement.setString(4, c.getEmail());
            statement.setString(5, c.getCustomerType().toString());
            statement.setDouble(6, c.getMembershipFee());
            statement.setDouble(7, c.getShippingDiscount());


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

    @Override
    public void update(Customer c) throws DAOException{
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, c.getName());
            statement.setString(2, c.getAddress());
            statement.setString(3, c.getId());
            statement.setString(4, c.getEmail());
            statement.setString(5, c.getCustomerType().toString());
            statement.setDouble(6, c.getMembershipFee());
            statement.setDouble(7, c.getShippingDiscount());
            statement.setString(8, c.getId());

            if (statement.executeUpdate() == 0) {
                throw new DAOException("Could not be updated");
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

    @Override
    public void remove(Customer c) throws DAOException{
        PreparedStatement statement = null;
        try{
            if(c != null) {
                statement = connection.prepareStatement(DELETE);
                statement.setString(1, c.getId());
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
    public Customer convertir(ResultSet rs) throws DAOException{
        Customer c = null;
        try {

            String name = rs.getString("name");
            String address = rs.getString("address");
            String id = rs.getString("id");
            String email = rs.getString("email");
            String customerType = rs.getString("customerType");
            double membershipFee= rs.getDouble("membershipFee");
            double shippingDiscount= rs.getDouble("shippingDiscount");

            if ("PREMIUM".equals(customerType)){
                c = new PremiumCustomer(name,address,id,email,membershipFee,shippingDiscount);
            }else{
                c = new StandardCustomer(name,address,id,email);
            }

            return c;

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Can't converted");
        }

    }
    @Override
    public List<Customer> getAll() throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();

        try {
            statement = connection.prepareStatement(GETALL);
            rs = statement.executeQuery();
            while (rs.next()) {
                customerList.add(convertir(rs));
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
        return customerList;
    }

        @Override
        public Customer getById (String id) throws DAOException{
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            Customer c = null;

            try {
                statement = connection.prepareStatement(GETBYID);
                statement.setString(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    c = convertir(resultSet);
                    System.out.println(c.toString());
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
            return c;
        }

    public static void main(String[] args) throws DAOException {
        Connection connection = null;
        Customer c = new StandardCustomer("Hector", "Barcelona", "", "sdggs");

       */
/* try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");

            CustomerDAO dao = new MysqlCustomerDAO(connection);
            dao.insert(c);

            System.out.println("Registro insertado exitosamente.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
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

            CustomerDAO dao = new MysqlCustomerDAO(connection);
            c = new PremiumCustomer("Jordi", "Barcelona", "1", "sdggs", 5.05, 3.50);

            dao.update(c);

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

        }*//*

         */
/*try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");

            CustomerDAO dao = new MysqlCustomerDAO(connection);
            c = new PremiumCustomer("Robert", "Barcelona", "", "sdggs", 0, 0);

            dao.remove(c);

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

        }
        *//*

        */
/*
        try {
            connection = DriverManager.getConnection("jdbc:DaoImpl://localhost/onlinestore", "root", "1234");


            CustomerDAO dao = new MysqlCustomerDAO(connection); // Reemplaza "TuClaseConInsert" con el nombre de tu clase

            dao.getAll();

            System.out.println("Registro encontrado exitosamente.");

            List<Customer> customerList = dao.getAll();
            for (Customer customers : customerList) {
                System.out.println(customers);
            }
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


            CustomerDAO dao = new MysqlCustomerDAO(connection);

            dao.getById("2");

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

        }
    }

}

*/
