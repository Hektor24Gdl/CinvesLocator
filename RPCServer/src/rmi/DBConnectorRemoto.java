package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import common.MapPoint;
import common.MapResource;

public class DBConnectorRemoto extends UnicastRemoteObject implements DBConnector{

	protected DBConnectorRemoto() throws RemoteException {
		super();
		mapResources = new HashMap<String,MapResource>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,MapResource> mapResources;

	@Override
	public int setUbicacion(String name, String type, int planta, int x, int y) {
		try{
			MapPoint curPoint = new MapPoint();
			curPoint.set(x,y);
			mapResources.put(name, new MapResource(name,type,planta,curPoint));
			return 0; //Se escribio correctamente
		}catch(Exception ex){
			return 1; //No se pudo escribir
		}

	}

	@Override
	public MapResource getUbicacion(String name) {
		return mapResources.get(name);
	}

	@Override
	public ArrayList<MapResource> getAll() {
        ArrayList<MapResource> resources = new ArrayList<MapResource>();
        for( MapResource res : mapResources.values() ){
            resources.add(res);
        }
        return resources;

	}

	@Override
	public ArrayList<MapResource> findType(String type) {
        ArrayList<MapResource> resources = new ArrayList<MapResource>();
        for( MapResource res : mapResources.values() ){
            if( res.getType().equals(type)){
                resources.add(res);
            }
        }
        return resources;
	}

	@Override
	public ArrayList<MapResource> findName(String name) {
        ArrayList<MapResource> resources = new ArrayList<MapResource>();
        for( MapResource res : mapResources.values() ){
            if(res.getName().contains(name)) {
                resources.add(res);
            }
        }
        return resources;
	}
	
	
}
