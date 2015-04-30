/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cinvestav.locator.webservice;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.cinvestav.ex.DatabaseException;
import org.cinvestav.locator.dao.Agent;
import org.cinvestav.locator.dao.DAO;
import org.cinvestav.locator.dao.Location;
import org.cinvestav.locator.dao.Type;
import org.cinvestav.locator.exceptions.LocatorException;

/**
 *
 * @author hector
 */
@WebService(serviceName = "CinvesLocator")
public class CinvesLocator {

    /**
     * This is a sample web service operation
     * @param name
     * @param device
     * @param last_login
     * @param type
     * @throws org.cinvestav.locator.exceptions.LocatorException
     */
    @WebMethod(operationName = "saveAgent")
    public void saveAgent(@WebParam(name = "name") String name, @WebParam(name = "device") String device, 
            @WebParam(name = "last_login") String last_login, @WebParam(name = "type") String type) throws LocatorException {
        try {
            DAO.saveAgent(name, device, last_login, type);
        } catch (DatabaseException ex) {
            Logger.getLogger(CinvesLocator.class.getName()).log(Level.SEVERE, null, ex);
            throw new LocatorException(ex.toString());
        }
    }
    
    @WebMethod(operationName = "getAgent")
    public Agent getAgent(@WebParam(name = "name") String name) throws LocatorException {
        Agent agent;
        try {
            agent = DAO.getAgent(name);
        } catch (DatabaseException ex) {
            Logger.getLogger(CinvesLocator.class.getName()).log(Level.SEVERE, null, ex);
            throw new LocatorException(ex.toString());
        }
        
        if(agent!= null){    
            return agent;
        }
        
        throw new LocatorException("Agent not found");
    }
    
    @WebMethod(operationName = "getTypeAgent")
    public List<Agent> getTypeAgent(@WebParam(name = "type") String type) throws LocatorException {
        List<Agent> agents;
        try {
            agents = DAO.getTypeAgent(type);
        } catch (DatabaseException ex) {
            Logger.getLogger(CinvesLocator.class.getName()).log(Level.SEVERE, null, ex);
            throw new LocatorException(ex.toString());
        }
        
        if(!agents.isEmpty()){    
            return agents;
        }
        
        throw new LocatorException("Agent not found");
    }
    
    @WebMethod(operationName = "getLastLocation")
    public Location getLastLocation(@WebParam(name = "name") String name) throws LocatorException {
        Location location;
        try {
            location = DAO.getLastLocation(name);
        } catch (DatabaseException ex) {
            Logger.getLogger(CinvesLocator.class.getName()).log(Level.SEVERE, null, ex);
            throw new LocatorException(ex.toString());
        }
        
        if(location!= null){    
            return location;
        }
        
        throw new LocatorException("Agent not found");
    }
    
    @WebMethod(operationName = "saveLocation")
    public void saveLocation(@WebParam(name = "name") String name, @WebParam(name = "timestamp") String timestamp, 
            @WebParam(name = "coordinates") String coordinates) throws LocatorException {
        try {
            DAO.saveLocation(name, timestamp, coordinates);
        } catch (DatabaseException ex) {
            Logger.getLogger(CinvesLocator.class.getName()).log(Level.SEVERE, null, ex);
            throw new LocatorException(ex.toString());
        }
    }
    
}
