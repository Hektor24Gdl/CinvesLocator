package mx.cinvestav.edu.cinveslocatorclient.RPC.common;

import java.util.ArrayList;

public interface Resguardo {
	public static final int CODOP_LEN = 1;
	public static final byte CODOP_SET_UBICACION= 1;
	public static final byte CODOP_GET_UBICACION = 2;
	public static final byte CODOP_GET_ALL = 3;
	public static final byte CODOP_FIND_TYPE = 4;
	public static final byte CODOP_FIND_NAME = 5;
	//Paquete : CODOP,name_LEN,name,type_LEN,type,i
	public int setUbicacion(String name, String type, int planta, int x, int y);
	public MapResource getUbicacion(String name);
	public ArrayList<MapResource> getAll();
	public ArrayList<MapResource> findType(String type);
	public ArrayList<MapResource> findName(String name);
}
