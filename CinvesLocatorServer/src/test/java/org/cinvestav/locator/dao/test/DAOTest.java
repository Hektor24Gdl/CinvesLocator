package org.cinvestav.locator.dao.test;

import java.util.List;
import org.cinvestav.locator.dao.Agent;
import org.cinvestav.locator.dao.DAO;
import org.cinvestav.locator.dao.Location;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Héctor Guillermo Rodríguez Fuentes <hector.rodgz@facturasi.com>
 */
public class DAOTest {
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Test
    public void getAgent() throws Exception {
        Agent response = DAO.getAgent("HECTOR");
        assertNotNull(response);
    }
    
    @Test
    public void getNotAgent() throws Exception {
        Agent response = DAO.getAgent("NOT EXISTING AGENT");
        assertNull(response);
    }
    
    @Test
    public void getAgentByType() throws Exception {
        List<Agent> response = DAO.getTypeAgent("Agent");
        assertNotNull(response);
    }
    
    @Test
    public void getNotAgentType() throws Exception {
        List<Agent> response = DAO.getTypeAgent("Unknown");
        assertTrue((response.isEmpty()));
    }
    
    @Test
    public void getAgentLocation() throws Exception {
        Location response = DAO.getLastLocation("HECTOR");
        assertNotNull(response);
    }
    
    @Test
    public void getAgentLocationNotFound() throws Exception {
        Location response = DAO.getLastLocation("Unknown");
        assertNull(response);
    }
    
     @Test
    public void saveAgent() throws Exception {
        DAO.saveAgent("AGENTE", "MOBILE", "2015-28-04 13:00:00", "Agent");
    }
    
     @Test
    public void saveAgentLocation() throws Exception {
        DAO.saveLocation("2015-28-04 13:00:00", "coordinates", "HECTOR");
    }
}
