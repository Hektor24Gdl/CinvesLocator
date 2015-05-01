package mx.cinvestav.edu.cinveslocatorclient.RPC.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import mx.cinvestav.edu.cinveslocatorclient.RPC.common.*;

public class PointsProvider implements Resguardo{
	private final String HOSTNAME="192.168.15.4";
	private final int port = 3100;
	private Socket sock;
	private static PointsProvider singleton;
	public static PointsProvider getInstance(){
		if( singleton == null ){
			singleton = new PointsProvider();
		}
		return singleton;
	}
	
	private PointsProvider(){
	}
	
	@Override
	public int setUbicacion(String name, String type, int planta, int x, int y) {
		// TODO Auto-generated method stub
		MapResource towrite = new MapResource(name,type,planta,x,y);
		byte req[] = towrite.getBytes();
		byte codop[] = { Resguardo.CODOP_SET_UBICACION };
		byte request[] = new byte[req.length + codop.length];
		int iter = 0;
		for(int i=0;i<codop.length;i++){
			request[iter++] = codop[i];
		}
		for(int i=0;i<req.length;i++){
			request[iter++] = req[i];
		}
		//send request message
		DataOutputStream dOut;
		try {
			
			sock = new Socket(HOSTNAME,port);
			dOut = new DataOutputStream(sock.getOutputStream());
			dOut.writeInt(request.length); // write length of the message
			dOut.write(request);           // write the message
			
			DataInputStream dIn = new DataInputStream(sock.getInputStream());
			int ret = dIn.readInt();                    // read length of incoming message
			return ret;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	@Override
	public MapResource getUbicacion(String name) {
		// TODO Auto-generated method stub
		byte req[] = name.getBytes();
		byte codop[] = { Resguardo.CODOP_GET_UBICACION };
		byte request[] = new byte[req.length + codop.length];
		int iter = 0;
		for(int i=0;i<codop.length;i++){
			request[iter++] = codop[i];
		}
		for(int i=0;i<req.length;i++){
			request[iter++] = req[i];
		}
		//send request message
		DataOutputStream dOut;
		try {

			sock = new Socket(HOSTNAME,port);
			dOut = new DataOutputStream(sock.getOutputStream());
			dOut.writeInt(request.length); // write length of the message
			dOut.write(request);           // write the message
			
			DataInputStream dIn = new DataInputStream(sock.getInputStream());
			int ret = dIn.readInt();
			MapResource r = null;
			if(ret > 0){
			    byte[] message = new byte[ret];
			    dIn.readFully(message, 0, message.length); // read the message
			    r = new MapResource(message,4);
			}
			return r;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}

	@Override
	public ArrayList<MapResource> getAll() {
		// TODO Auto-generated method stub
		byte codop[] = { Resguardo.CODOP_GET_ALL };
		byte request[] = new byte[codop.length];
		int iter = 0;
		for(int i=0;i<codop.length;i++){
			request[iter++] = codop[i];
		}
		//send request message
		DataOutputStream dOut;
		ArrayList<MapResource> rs = new ArrayList<MapResource>();
		try {
			sock = new Socket(HOSTNAME,port);
			dOut = new DataOutputStream(sock.getOutputStream());
			dOut.writeInt(request.length); // write length of the message
			dOut.write(request);           // write the message
			
			DataInputStream dIn = new DataInputStream(sock.getInputStream());
			int ret = dIn.readInt();
			if(ret > 0){
				for(int i=0;i<ret;i++){
					int len = dIn.readInt();
				    byte[] message = new byte[len];
				    dIn.readFully(message, 0, message.length-4); // read the message
				    rs.add(new MapResource(message));
				}
			}
			return rs;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return rs;
		}
		
	}

	@Override
	public ArrayList<MapResource> findType(String type) {
		// TODO Auto-generated method stub
		byte req[] = type.getBytes();
		byte codop[] = { Resguardo.CODOP_FIND_TYPE };
		byte request[] = new byte[req.length + codop.length];
		int iter = 0;
		for(int i=0;i<codop.length;i++){
			request[iter++] = codop[i];
		}
		for(int i=0;i<req.length;i++){
			request[iter++] = req[i];
		}
		//send request message
		DataOutputStream dOut;
		ArrayList<MapResource> rs = new ArrayList<MapResource>();
		try {
			sock = new Socket(HOSTNAME,port);
			dOut = new DataOutputStream(sock.getOutputStream());
			dOut.writeInt(request.length); // write length of the message
			dOut.write(request);           // write the message
			
			DataInputStream dIn = new DataInputStream(sock.getInputStream());
			int ret = dIn.readInt();
			if(ret > 0){
				for(int i=0;i<ret;i++){
					int len = dIn.readInt();
				    byte[] message = new byte[len];
				    dIn.readFully(message, 0, message.length-4); // read the message
				    rs.add(new MapResource(message));
				}
			}
			return rs;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return rs;
		}
		
	}

	@Override
	public ArrayList<MapResource> findName(String name) {
		// TODO Auto-generated method stub
		byte req[] = name.getBytes();
		byte codop[] = { Resguardo.CODOP_FIND_NAME };
		byte request[] = new byte[req.length + codop.length];
		int iter = 0;
		for(int i=0;i<codop.length;i++){
			request[iter++] = codop[i];
		}
		for(int i=0;i<req.length;i++){
			request[iter++] = req[i];
		}
		//send request message
		DataOutputStream dOut;
		ArrayList<MapResource> rs = new ArrayList<MapResource>();
		try {
			sock = new Socket(HOSTNAME,port);
			dOut = new DataOutputStream(sock.getOutputStream());
			dOut.writeInt(request.length); // write length of the message
			dOut.write(request);           // write the message
			
			DataInputStream dIn = new DataInputStream(sock.getInputStream());
			int ret = dIn.readInt();
			if(ret > 0){
				for(int i=0;i<ret;i++){
					int len = dIn.readInt();
				    byte[] message = new byte[len];
				    dIn.readFully(message, 0, message.length-4); // read the message
				    rs.add(new MapResource(message));
				}
			}
			return rs;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return rs;
		}		
	}
	public static void main(String args[]){
		PointsProvider p = PointsProvider.getInstance();
        p.setUbicacion( "Celeste", "persona",1,50,50 );
        p.setUbicacion( "Andres","persona",1,800,800);
        p.setUbicacion( "Gibran","persona",1,100,100 );
        p.setUbicacion( "Impresora1","impresora",1,1000,1000);
        p.setUbicacion( "Impresora2", "impresora",1,450,1000);
        p.setUbicacion( "Laboratorio Compu","laboratorio",1,800,1000);
        MapResource mr = p.getUbicacion("Celeste");
        ArrayList<MapResource> all = p.getAll();
        ArrayList<MapResource> findType  = p.findType("impresora");
        ArrayList<MapResource> findName = p.findName("el");
        

	}
}
