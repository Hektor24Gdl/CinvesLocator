package rmi;

import java.rmi.Remote;
import common.MapResource;
import java.util.ArrayList;;
public interface DBConnector extends Remote{
	public int setUbicacion(String name, String type, int planta, int x, int y) throws java.rmi.RemoteException;
	public MapResource getUbicacion(String name) throws java.rmi.RemoteException;
	public ArrayList<MapResource> getAll() throws java.rmi.RemoteException;
	public ArrayList<MapResource> findType(String type) throws java.rmi.RemoteException;
	public ArrayList<MapResource> findName(String name) throws java.rmi.RemoteException;
}
