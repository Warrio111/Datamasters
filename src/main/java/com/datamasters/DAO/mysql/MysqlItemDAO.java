package com.datamasters.DAO.mysql;

import com.datamasters.DAO.DAOException;
import com.datamasters.DAO.ItemDAO;
import com.datamasters.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlItemDAO implements ItemDAO {
    public final String INSERT = "INSERT INTO Item(code,description,sellingPrice,shippingCost,preparationTimeMinutes) VALUES(?,?,?,?,?)";
    public final String UPDATE = "UPDATE Item SET  code = ?, description = ?, sellingPrice = ?, shippingCost = ?,preparationTimeMinutes = ? WHERE code=?";
    public final String DELETE = "DELETE FROM Item WHERE code= ?";
    public final String GETALL= "SELECT * FROM Item";
    public final String GETBYID= "SELECT * FROM Item WHERE code = ?;";

    private Connection connection;

    public MysqlItemDAO(Connection connection){this.connection = connection;}
    @Override
    public void insert(Item c) throws DAOException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, c.getCode());
            statement.setString(2, c.getDescription());
            statement.setDouble(3, c.getSellingPrice());
            statement.setDouble(4, c.getShippingCost());
            statement.setInt(5, c.getPreparationTimeMinutes());

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
    public void update(Item c) throws DAOException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, c.getCode());
            statement.setString(2, c.getDescription());
            statement.setDouble(3, c.getSellingPrice());
            statement.setDouble(4, c.getShippingCost());
            statement.setInt(5, c.getPreparationTimeMinutes());
            statement.setString(6, c.getCode());


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
    public void remove(Item c) throws DAOException {
        PreparedStatement statement = null;
        try{
            if(c != null) {
                statement = connection.prepareStatement(DELETE);
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
    @Override
    public List<Item> getAll() throws DAOException{
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Item> itemList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(GETALL);
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

    @Override
    public Item getById(String id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Item it = null;

        try {
            statement = connection.prepareStatement(GETBYID);
            statement.setString(1, id);
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

    public static void main(String[] args) throws DAOException {
        Connection connection = null;
        /*try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlinestore", "root", "1234");

            ItemDAO dao = new MysqlItemDAO(connection);
            Item it = new Item("3", "ddsgk", 40.00, 120.00, 8);

            dao.insert(it);

            System.out.println("Registro insertado exitosamente.");

        } catch (SQLException | DAOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error in SQL",ex);
                }
            }

        }
    }*/
       /* try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlinestore", "root", "1234");

            ItemDAO dao = new MysqlItemDAO(connection);
            Item it = new Item("3", "ddsgk", 150.00, 120.00, 15);


            dao.update(it);

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
    }*/
        /*try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlinestore", "root", "1234");


            ItemDAO dao = new MysqlItemDAO(connection);
            Item it = new Item("3", "ddsgk", 40.00, 120.00, 8);

            dao.remove(it);

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
    }*/
       /* try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlinestore", "root", "1234");


            ItemDAO dao = new MysqlItemDAO(connection); // Reemplaza "TuClaseConInsert" con el nombre de tu clase

            dao.getAll();

            System.out.println("Registro encontrado exitosamente.");

            List<Item> itemList = dao.getAll();
            for (Item items : itemList) {
                System.out.println(items);
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
    }*/
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlinestore", "root", "1234");


            ItemDAO dao = new MysqlItemDAO(connection); // Reemplaza "TuClaseConInsert" con el nombre de tu clase

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

        }
    }
}
