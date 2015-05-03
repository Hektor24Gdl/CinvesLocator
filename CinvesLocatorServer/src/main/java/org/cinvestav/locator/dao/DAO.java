package org.cinvestav.locator.dao;

import org.cinvestav.ex.DatabaseException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    private static final String AG_TYPE = "Agent.findByType";
    private static final String TY_TYPE = "Type.findByType";
    private static final String LOC_AGENT = "Location.findByAgent";
    
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

    private static <T extends Serializable> List<T> getObject(Class<T> entityClass, String namedQuery, String parameter, String value) throws DatabaseException {

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
    
    private static <T extends Serializable> void saveObject(T object) throws DatabaseException{
        Session s = getSession();
        s.beginTransaction();
                
        s.saveOrUpdate(object);
        s.getTransaction().commit();
        
    }

    public static Agent getAgent(String value) throws DatabaseException {
        List<Agent> agents = getObject(Agent.class, AG_NAME, "name", value);
        
        if(agents.size() > 0){
            return agents.get(0);
        }
        
        return null;
    }
    
    public static void saveAgent(String name, String device, String last_login, String type) throws DatabaseException{
        Agent agent = getAgent(name);
        
        if(agent == null){
            agent = new Agent();
        }
        
        agent.setName(name);
        agent.setDevice(device);
        agent.setLastLogin(last_login);
        Type t = getType(type);
        agent.setTypeIdtype(t);
        
        saveObject(agent);
    }
    
    public static Type getType(String value) throws DatabaseException {
        List<Type> types = getObject(Type.class, TY_TYPE, "type", value);
        
        if(types.size() > 0){
            return types.get(0);
        }
        
        return null;
    }
    
    public static List<Agent> getTypeAgent(String value) throws DatabaseException {
        List<Agent> agents = getObject(Agent.class, AG_TYPE, "type", value);
        return agents;
    }
    
    public static Location getLastLocation(String value) throws DatabaseException {
        List<Location> location = getObject(Location.class, LOC_AGENT, "name", value);

        if(location.size() > 0){
            return location.get(0);
        }
        
        return null;
    }
    
    public static void saveLocation(String timestamp, String coordinates, String agent) throws DatabaseException{
        
        Location location = getLastLocation(agent);
        if(location == null){
            location = new Location();
        }
        
        Agent ag = getAgent(agent);
        
        location.setAgenteIdagente(ag);
        location.setTimestamp(timestamp);
        location.setCoordinates(coordinates);
        
        saveObject(location);
    }

}
