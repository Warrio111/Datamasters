package com.datamasters.DAO;
import com.datamasters.DAO.DaoImpl.CustomerDaoImpl;
import com.datamasters.DAO.DaoImpl.ItemDaoImpl;
import com.datamasters.DAO.DaoImpl.OrderDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil extends DAOFactory{
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Cargar configuración de Hibernate desde hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Construir la factoría de sesiones
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            // Manejar errores de manera adecuada
            System.err.println("Error al inicializar la Factoria de sesiones: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session abrirSession() {
        // Abrir y retornar una nueva sesión de la factoría de sesiones
        return sessionFactory.openSession();
    }

    public static void cerrarSession(Session session) {
        // Cerrar la sesión si está abierta
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public static void cerrarSessionFactory() {
        // Cerrar la factoría de sesiones al finalizar la aplicación
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
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