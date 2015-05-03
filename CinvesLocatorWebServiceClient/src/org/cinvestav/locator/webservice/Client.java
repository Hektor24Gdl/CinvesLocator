package org.cinvestav.locator.webservice;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;
import static jdk.nashorn.internal.objects.NativeJava.type;
import org.cinvestav.locator.webservice.client.Agent;
import org.cinvestav.locator.webservice.client.CinvesLocator;
import org.cinvestav.locator.webservice.client.Location;
import org.cinvestav.locator.webservice.client.LocatorException_Exception;

/**
 *
 * @author hector
 */
public class Client {
    private static String wsHost;
    private static int wsPort;
    
    public static void main(String args[]) {
        
        configure("http://localhost", 8080);
        Agent agent;
        try {
            agent = getAgent("Celeste");
        
        System.out.println("Agent: " + agent.getName() + " Login: " + agent.getLastLogin());
        
        Location location = getLastLocation("Celeste");
        
        StringTokenizer tokens = new StringTokenizer(location.getCoordinates(), ",");
        String piso = tokens.nextToken();
        String xCor = tokens.nextToken();
        String yCor = tokens.nextToken();
        System.out.println("Location: Id: " + location.getIdlocation() + " Piso: " + piso + " X: " + xCor + " Y: " + yCor + " Timestamp: " + location.getTimestamp());
        
        saveLocation("Celeste", new Date().toLocaleString(), "1,183.0389,156.31862");//
        
        } catch (LocatorException_Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void configure(String wshost, int wsport){
        wsHost = wshost;
        wsPort = wsport;
    }
    
    
    public static Agent getAgent(java.lang.String name) throws LocatorException_Exception {
        org.cinvestav.locator.webservice.client.CinvesLocator_Service service = new org.cinvestav.locator.webservice.client.CinvesLocator_Service();
        org.cinvestav.locator.webservice.client.CinvesLocator port = service.getCinvesLocatorPort();
        configurePort(port);
        return port.getAgent(name);
    }

    public static Location getLastLocation(java.lang.String name) throws LocatorException_Exception {
        org.cinvestav.locator.webservice.client.CinvesLocator_Service service = new org.cinvestav.locator.webservice.client.CinvesLocator_Service();
        org.cinvestav.locator.webservice.client.CinvesLocator port = service.getCinvesLocatorPort();
        configurePort(port);
        return port.getLastLocation(name);
    }

    public static java.util.List<org.cinvestav.locator.webservice.client.Agent> getTypeAgent(java.lang.String type) throws LocatorException_Exception {
        org.cinvestav.locator.webservice.client.CinvesLocator_Service service = new org.cinvestav.locator.webservice.client.CinvesLocator_Service();
        org.cinvestav.locator.webservice.client.CinvesLocator port = service.getCinvesLocatorPort();
        configurePort(port);
        return port.getTypeAgent(type);
    }

    public static void saveAgent(java.lang.String name, java.lang.String device, java.lang.String lastLogin, java.lang.String type) throws LocatorException_Exception {
        org.cinvestav.locator.webservice.client.CinvesLocator_Service service = new org.cinvestav.locator.webservice.client.CinvesLocator_Service();
        org.cinvestav.locator.webservice.client.CinvesLocator port = service.getCinvesLocatorPort();
        configurePort(port);
        port.saveAgent(name, device, lastLogin, type);
    }

    public static void saveLocation(java.lang.String name, java.lang.String timestamp, java.lang.String coordinates) throws LocatorException_Exception {
        org.cinvestav.locator.webservice.client.CinvesLocator_Service service = new org.cinvestav.locator.webservice.client.CinvesLocator_Service();
        org.cinvestav.locator.webservice.client.CinvesLocator port = service.getCinvesLocatorPort();
        configurePort(port);
        port.saveLocation(name, timestamp, coordinates);
    }
    
    private static void configurePort(CinvesLocator port){
        BindingProvider bp = (BindingProvider)port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsHost + ":" + wsPort + "/CinvesLocatorServerWebService/CinvesLocator");
    }
    
}
