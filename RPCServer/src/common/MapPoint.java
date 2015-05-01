package common;

import java.io.Serializable;

public class MapPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5107287876341819542L;
	private int x;
	private int y;
	public MapPoint(int x,int y){
		this.x = x;
		this.y = y;
	}
	public MapPoint(){
		this.x = 0;
		this.y = 0;
	}
	public void set(int x,int y){
		this.x = x;
		this.y = y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}
