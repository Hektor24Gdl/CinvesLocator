package server;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.*;
import rmi.DBConnector;

public class ResguardoServer implements Resguardo{
	DBConnector rmiObject;
	public ResguardoServer() {
		try {
			rmiObject = (DBConnector)Naming.lookup("//127.0.0.1/DBConnectorRemoto");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al conectar RMI");
			System.exit(3);
		}
	}
	@Override
	 public synchronized int  setUbicacion(String name, String type, int planta, int x,
			int y) {
		// TODO Add Code to call RMI Object.
		try {
			return rmiObject.setUbicacion(name, type, planta, x, y);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return 1;
		}
	}
	@Override
	public MapResource getUbicacion(String name) {
		// TODO Add Code to call RMI Object.
		try {
			return rmiObject.getUbicacion(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	@Override
	public ArrayList<MapResource> getAll() {
		// TODO Add Code to call RMI Object
		try {
			return rmiObject.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return new ArrayList<MapResource>();
		}
	}
	@Override
	public ArrayList<MapResource> findType(String type) {
		// TODO Add Code to call RMI Object
		try {
			return rmiObject.findType(type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return new ArrayList<MapResource>();
		}
	}
	@Override
	public ArrayList<MapResource> findName(String name) {
		// TODO Add Code to call RMI Object
		try {
			return rmiObject.findName(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return new ArrayList<MapResource>();
		}
	}
}
