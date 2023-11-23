package com.datamasters.vista;

import com.datamasters.DAO.DAOException;

import java.sql.SQLException;

public class OnlineStore {
	public static void main(String[] args) throws DAOException, SQLException {
		ManageOS manageOS = new ManageOS();
		manageOS.run();
	}
}