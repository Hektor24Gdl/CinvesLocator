package org.cinvestav.locator.dao;

import org.cinvestav.ex.DatabaseException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Héctor Guillermo Rodríguez Fuentes <hector.rodgz@facturasi.com>
 */
public class DAO {

    private static SessionFactory sf;
    private static final String AG_NAME = "Agent.findByName";

    public static Session getSession() throws DatabaseException {
        if (sf == null) {

            try {
                sf = new Configuration().configure().buildSessionFactory();
            } catch (HibernateException e) {
                Logger logger = Logger.getLogger(DAO.class.getName());
                logger.log(Level.SEVERE, "Unexpected exception initializing DAO", e);
                throw new DatabaseException(String.format("%s\n%s", "Unexpected exception initializing DAO", e.toString()));
            }
        }
        return sf.getCurrentSession();
    }

    public static <T extends Serializable> List<T> getObject(Class<T> entityClass, String namedQuery, String parameter, String value) throws DatabaseException {

        if (namedQuery == null || value == null) {
            Logger logger = Logger.getLogger(DAO.class.getName());
            logger.log(Level.WARNING, "namedQuery and value must not be null");
            throw new DatabaseException("namedQuery and value must not be null");
        }

        List<T> resultList = null;

        Session s = getSession();
        s.beginTransaction();

        Query query = s.getNamedQuery(namedQuery);
        query.setString(parameter, value);
        resultList = query.list();

        s.getTransaction().commit();

        return resultList;
    }

    public static Agent getAgent(String value) throws DatabaseException {
        List<Agent> agents = getObject(Agent.class, AG_NAME, "name", value);
        return agents.get(0);
    }

}
