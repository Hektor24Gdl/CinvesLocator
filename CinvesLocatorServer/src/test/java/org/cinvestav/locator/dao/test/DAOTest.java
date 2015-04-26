package org.cinvestav.locator.dao.test;

import java.util.List;
import org.cinvestav.locator.dao.Agent;
import org.cinvestav.locator.dao.DAO;
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
}
