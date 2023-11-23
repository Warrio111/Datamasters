package com.datamasters.DAO;

import com.datamasters.DAO.DaoImpl.CustomerDaoImpl;
import com.datamasters.DAO.DaoImpl.ItemDaoImpl;
import com.datamasters.DAO.DaoImpl.OrderDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
public class UtilityMySqlDAOFactory extends DAOFactory {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/onlinestore";
    public  static  final  String LOGIN = "root";
    public  static  final  String PASSWORD = "1234";
    static Connection conn = null;
    public static Connection createConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            conn = java.sql.DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            createConnection();
        }
        return conn;
    }
   public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    @Override
    public CustomerDAO getCustomerDAO() {
        return new CustomerDaoImpl();
    }

    @Override
    public ItemDAO getItemDAO() throws DAOException {
        return new ItemDaoImpl();
    }

    @Override
    public OrderDAO getOrdersDAO() {
        return new OrderDaoImpl();
    }
}
