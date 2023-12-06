package com.datamasters.DAO;

import java.sql.SQLException;


public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static  final  int ORACLE = 2;
    public static  final  int SQLSERVER = 3;
    public static  final  int POSTGRESQL = 4;

    public abstract CustomerDAO getCustomerDAO() throws SQLException;
    public abstract ItemDAO getItemDAO() throws DAOException;
    public abstract OrderDAO getOrdersDAO();

    public  static  DAOFactory getDAOFactory(int whichFactory) throws DAOException{
        switch (whichFactory) {
            case MYSQL:
                return new HibernateUtil();
            case ORACLE:
                throw new DAOException("Oracle not implemented yet");
                //return new OracleDAOFactory();
            case SQLSERVER:
                throw new DAOException("SqlServer not implemented yet");
                //return new SqlServerDAOFactory();
            case POSTGRESQL:
                throw new DAOException("PostgreSql not implemented yet");
                //return new PostgreSqlDAOFactory();
            default:
                return null;
        }
    }
}
