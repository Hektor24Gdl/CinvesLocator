package mx.cinvestav.edu.cinveslocatorclient;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Celeste on 26/04/2015.
 */

public class PointsProvider {
    private static HashMap<String, MapResource> ubicaciones = new HashMap<String,MapResource>();
    public PointsProvider(){
        ubicaciones = new HashMap<String,MapResource>();
    }
    public static void setUbicacion( String name, String type, int planta,int x,int y) {
        Point curPoint = new Point();
        curPoint.set(x,y);
        ubicaciones.put(name,new MapResource(name,type,planta,curPoint));
    }
    public static MapResource getUbicacion(String name){
        return ubicaciones.get(name);
    }
    public static ArrayList<MapResource> getAll(){
        ArrayList<MapResource> resources = new ArrayList<MapResource>();
        for( MapResource res : ubicaciones.values() ){
            resources.add(res);
        }
        return resources;
    }
    public static ArrayList<MapResource> findName(String name){
        ArrayList<MapResource> resources = new ArrayList<MapResource>();
        for( MapResource res : ubicaciones.values() ){
            if(res.getName().contains(name)) {
                resources.add(res);
            }
        }
        return resources;
    }

    public static ArrayList<MapResource> getAll(String type){
        ArrayList<MapResource> resources = new ArrayList<MapResource>();
        for( MapResource res : ubicaciones.values() ){
            if( res.getType().equals(type)){
                resources.add(res);
            }
        }
        return resources;
    }
}
class MapResource {
    private String name;
    private String type;
    private int planta;
    private Point position;
    public MapResource(String name, String type,int planta, int x, int y) {
        this.name = name;
        this.type = type;
        this.planta = planta;
        this.position = new Point();
        this.position.set(x, y);
    }
    public MapResource(String name, String type, int planta,Point position){
        this.name = name;
        this.type = type;
        this.planta = planta;
        this.position = position;
    }

    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
    public Point getPosition(){
        return this.position;
    }
}
