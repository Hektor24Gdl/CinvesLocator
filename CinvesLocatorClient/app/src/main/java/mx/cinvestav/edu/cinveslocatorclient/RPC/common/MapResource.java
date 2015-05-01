package mx.cinvestav.edu.cinveslocatorclient.RPC.common;

import java.util.ArrayList;

public class MapResource {
    private String name;
    private String type;
    private int planta;
    private MapPoint position;
    public MapResource(String name, String type,int planta, int x, int y) {
        this.name = name;
        this.type = type;
        this.planta = planta;
        this.position = new MapPoint();
        this.position.set(x, y);
    }
    public MapResource(String name, String type, int planta,MapPoint position){
        this.name = name;
        this.type = type;
        this.planta = planta;
        this.position = position;
    }
    public MapResource(byte[] message){
    	int offset = 0;
    	int bName = Empaquetador.getInt(message, offset);
    	int bType = Empaquetador.getInt(message, offset + 4 + bName);
    	this.name = Empaquetador.getString(message, offset+4, bName);
    	offset = offset+4+bName;
    	this.type = Empaquetador.getString(message, offset+4,bType);
    	offset += 4+bType;
    	this.planta = Empaquetador.getInt(message, offset);
    	offset += 4;
    	int x = Empaquetador.getInt(message,offset);
    	offset+= 4;
    	int y = Empaquetador.getInt(message,offset);
    	offset +=4;
    	this.position = new MapPoint(x,y);
    	
    	
    }
    public MapResource(byte[] message, int offset){
    	int bName = Empaquetador.getInt(message, offset);
    	int bType = Empaquetador.getInt(message, offset + 4 + bName);
    	this.name = Empaquetador.getString(message, offset+4, bName);
    	offset = offset+4+bName;
    	this.type = Empaquetador.getString(message, offset+4,bType);
    	offset += 4+bType;
    	this.planta = Empaquetador.getInt(message, offset);
    	offset += 4;
    	int x = Empaquetador.getInt(message,offset);
    	offset+= 4;
    	int y = Empaquetador.getInt(message,offset);
    	offset +=4;
    	this.position = new MapPoint(x,y);
    	
    	
    }

    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
    public MapPoint getPosition(){
        return this.position;
    }
    public int getPlanta(){
    	return this.planta;
    }
    public byte[] getBytes(){
    	ArrayList<Object> objects = new ArrayList<Object>();
    	int x = position.getX();
    	int y = position.getY();
    	byte[] bName = name.getBytes();
    	byte[] bType = type.getBytes();
    	int MAX_LENGTH;
    	int[] paramsLength = { 4, 4, bName.length, 4, bType.length, 4,4,4  };
    	MAX_LENGTH = 4 + 4 + bName.length + 4 + bType.length + 4 + 4 +4 + 1;
    	objects.add(MAX_LENGTH);
    	objects.add(bName.length);
    	objects.add(name);
    	objects.add(bType.length);
    	objects.add(type);
    	objects.add(planta);
    	objects.add(x);
    	objects.add(y);
    	return Empaquetador.empaquetar(objects.toArray(), paramsLength, MAX_LENGTH);
    }
}