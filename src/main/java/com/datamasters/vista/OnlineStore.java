package com.datamasters.vista;

import com.datamasters.DAO.DAOException;

import java.sql.SQLException;


public class OnlineStore {
	public static void main(String[] args) throws DAOException, SQLException {

/*		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			Transaction transaction = session.beginTransaction();

			ItemEntity itemEntity = new ItemEntity();
			itemEntity.setCode(300);
			itemEntity.setDescription("Item 1");
			itemEntity.setSellingPrice(100.0);
			itemEntity.setShippingCost(10.0);
			itemEntity.setPreparationTimeMinutes(10);

			session.save(itemEntity);

			transaction.commit();
			System.out.println("ItemEntity saved successfully.....!!");
		} catch (Exception e) {
			if (session.getTransaction() != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}*/
		ManageOS manageOS = new ManageOS();
		manageOS.run();
	}
}