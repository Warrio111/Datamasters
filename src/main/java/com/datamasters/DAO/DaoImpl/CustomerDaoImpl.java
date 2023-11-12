package com.datamasters.DAO.DaoImpl;

import com.datamasters.DAO.*;
import com.datamasters.modelo.Customer;
import com.datamasters.modelo.PremiumCustomer;
import com.datamasters.modelo.StandardCustomer;
import com.datamasters.modelo.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CustomerDaoImpl extends DAOFactory implements CustomerDAO {
    public final String INSERT = "INSERT INTO Customer (name,address,id,email,customerType,membershipFee,shippingDiscount) VALUES(?,?,?,?,?,?,?)";
    public final String UPDATE = "UPDATE Customer SET name = ?, address = ?, id = ?, email = ?,customerType =?, memberShipFee = ?,shippingDiscount = ?  WHERE id=?";
    public final String DELETE = "DELETE FROM Customer WHERE id= ?";
    public final String GETALL= "SELECT * FROM Customer";
    public final String GETBYID= "SELECT FROM Customer WHERE id = ?;";
    public final String GETBYCUSTOMERTYPE = "SELECT * FROM Customer WHERE customerType = ?;";

    public final String GETSHIPPINGDISCOUNTBYCOUSTOMET = "SELECT * FROM Customer WHERE id = ?;";

    public final String GETMEMBERSHIPFEEBYCUSTOMER = "SELECT * FROM Customer WHERE id = ?;";


    PreparedStatement statement = null;

    ResultSet resultSet = null;
    Customer c = null;

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void insert(Customer c) throws DAOException, SQLException {
        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(INSERT);
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

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void update(Customer c) throws DAOException {
        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(UPDATE);
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

    /**
     * @param c
     * @throws DAOException
     */
    @Override
    public void remove(Customer c) throws DAOException {
        try{
            if(c != null) {
                statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(UPDATE);
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
    public Customer convertir(ResultSet rs) throws DAOException {
        Customer c = null;
        try {

            String name = rs.getString("name");
            String address = rs.getString("address");
            String id = rs.getString("id");
            String email = rs.getString("email");
            String customerType = rs.getString("customerType");
            double membershipFee = rs.getDouble("membershipFee");
            double shippingDiscount = rs.getDouble("shippingDiscount");

            if ("PREMIUM".equals(customerType)) {
                c = new PremiumCustomer(name, address, id, email, membershipFee, shippingDiscount);
            } else {
                c = new StandardCustomer(name, address, id, email);
            }
            System.out.println("Name: "+ name + ",Address: " + address + ",ID:" + id + ", Email: " + email + ", CustomerType: " +
                    customerType + ",MembershipFee: " + membershipFee + ",ShippingDiscount: " + shippingDiscount );

            return c;

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
    public List<Customer> getAll() throws DAOException {

        List<Customer> customerList = new List<>();

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customerList.add(convertir(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir detalles del error
            throw new DAOException("Error in SQL", ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
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

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Customer getById(int id) throws DAOException {


        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETBYID);
            statement.setInt(1, id);
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

    /**
     * @param customerType
     * @return
     * @throws DAOException
     */
    @Override
    public Customer getCustomerType(String customerType) throws DAOException {

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETBYCUSTOMERTYPE);
            statement.setString(1, customerType);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                c = convertir(resultSet);
                System.out.println(c.toString());
            } else {
                throw new DAOException("Customer not found");
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch(Exception ex){
            new DAOException("Erron in SQL");
        }
        return c;
    }

    /**
     * @param customerType
     * @return
     * @throws DAOException
     */
    @Override
    public List<Customer> getCustomerByType(String customerType) throws DAOException {
        List<Customer> customerList = new List<>();

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETBYCUSTOMERTYPE);
            statement.setString(1, customerType);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customerList.add(convertir(resultSet));
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException ex) {
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
        return customerList;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    //TODO
    @Override
    public Double getSpendByCustomer(int id) throws DAOException {
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Double getShippingDiscountByCustomer(int id) throws DAOException {

        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETSHIPPINGDISCOUNTBYCOUSTOMET);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Double shippingDiscount = resultSet.getDouble("shippingDiscount");

                System.out.println("ShippingDiscount: " + shippingDiscount );
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch(Exception ex){
            new DAOException("Erron in SQL");
        }
        return null;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Double getMembershipFeeByCustomer(int id) throws DAOException {
        try {
            statement = UtilityMySqlDAOFactory.getConnection().prepareStatement(GETMEMBERSHIPFEEBYCUSTOMER);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Double membershipFee = resultSet.getDouble("membershipFee");

                System.out.println("MembershipFee: " + membershipFee);
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch(Exception ex){
            new DAOException("Erron in SQL");
        }
        return null;
    }

    /**
     * @return
     */
    //TODO
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

}
