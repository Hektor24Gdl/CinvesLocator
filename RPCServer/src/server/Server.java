package server;

import common.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import common.MapResource;

public class Server {
	
	public static void main(String args[]){
		ResguardoServer libreriaServer = null;
		libreriaServer = new ResguardoServer();
		try {
			boolean cont = true;
			ServerSocket socket = new ServerSocket();
			socket.bind(new InetSocketAddress("0.0.0.0", 3100));
			System.out.println("Server corriendo");
			while(cont){
				Socket client = socket.accept();
				ServerDispatcher dispatch = new ServerDispatcher(client, libreriaServer);
				dispatch.start();
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("No se pudo conectar al puerto");
			System.exit(1);
		}

	}
}
class ServerDispatcher extends Thread implements Runnable{
	Socket clientSocket;
	ResguardoServer libreriaServer ;
	public ServerDispatcher(Socket clientSocket, ResguardoServer libreriaServer){
		this.clientSocket = clientSocket;
		this.libreriaServer = libreriaServer;
	}
	@Override
	public void run() {
		byte codop;
		int msgLength;
		byte resultMessage[] = new byte[1];
		DataOutputStream dOut;
		DataInputStream dIn;
		try {
			dIn = new DataInputStream(clientSocket.getInputStream());
			msgLength = dIn.readInt();
			byte[] message = new byte[msgLength]; 
			dIn.readFully(message, 0, msgLength); // read the message
			codop = message[0];
			switch(codop){
			case Resguardo.CODOP_SET_UBICACION:
				{
					MapResource mr = new MapResource(message,5);
					MapPoint pos = mr.getPosition();
					int result = libreriaServer.setUbicacion(mr.getName(), 
							mr.getType(), 
							mr.getPlanta(), 
							pos.getX(), 
							pos.getY()
					);
					Object[] objs = {result};
					int[] lengths = {4};
					resultMessage = Empaquetador.empaquetar(objs, lengths, 4);
				}
				break;
			case Resguardo.CODOP_GET_UBICACION:
				{
					String name = Empaquetador.getString(message, 1, message.length-1);
					MapResource mr = libreriaServer.getUbicacion(name);
					byte[] msg = mr.getBytes();
					Object[] objs = {msg.length};
					int[] lengths = {4};
					
					resultMessage = Empaquetador.empaquetar(objs, lengths, 4);
					int iter = 0;
					byte[] mergeMsgs = new byte[resultMessage.length + msg.length];
					for(int i=0;i<resultMessage.length;i++){
						mergeMsgs[iter++] = resultMessage[i];
					}
					for(int i=0;i<msg.length;i++){
						mergeMsgs[iter++] = msg[i];
					}
					resultMessage = mergeMsgs;
				
				}
				break;
			case Resguardo.CODOP_GET_ALL:
				{
					ArrayList<MapResource> mrs = libreriaServer.getAll();
					Object[] objs = {mrs.size()};
					int[] lengths = {4};
					resultMessage = Empaquetador.empaquetar(objs, lengths, 4);
					for(MapResource mr : mrs){
						byte[] msg = mr.getBytes();
						//copy over.
						int iter = 0;
						byte[] mergeMsgs = new byte[resultMessage.length + msg.length];
						for(int i=0;i<resultMessage.length;i++){
							mergeMsgs[iter++] = resultMessage[i];
						}
						for(int i=0;i<msg.length;i++){
							mergeMsgs[iter++] = msg[i];
						}
						resultMessage = mergeMsgs;
					}
				}
				break;
			case Resguardo.CODOP_FIND_TYPE:
				{
					String type = Empaquetador.getString(message, 1, message.length-1);
					ArrayList<MapResource> mrs = libreriaServer.findType(type);
					Object[] objs = {mrs.size()};
					int[] lengths = {4};
					resultMessage = Empaquetador.empaquetar(objs, lengths, 4);
					for(MapResource mr : mrs){
						byte[] msg = mr.getBytes();
						//copy over.
						int iter = 0;
						byte[] mergeMsgs = new byte[resultMessage.length + msg.length];
						for(int i=0;i<resultMessage.length;i++){
							mergeMsgs[iter++] = resultMessage[i];
						}
						for(int i=0;i<msg.length;i++){
							mergeMsgs[iter++] = msg[i];
						}
						resultMessage = mergeMsgs;
					}
				}
				break;
			case Resguardo.CODOP_FIND_NAME:
				{
					String name = Empaquetador.getString(message, 1, message.length-1);
					ArrayList<MapResource> mrs = libreriaServer.findName(name);
					Object[] objs = {mrs.size()};
					int[] lengths = {4};
					resultMessage = Empaquetador.empaquetar(objs, lengths, 4);
					for(MapResource mr : mrs){
						byte[] msg = mr.getBytes();
						//copy over.
						int iter = 0;
						byte[] mergeMsgs = new byte[resultMessage.length + msg.length];
						for(int i=0;i<resultMessage.length;i++){
							mergeMsgs[iter++] = resultMessage[i];
						}
						for(int i=0;i<msg.length;i++){
							mergeMsgs[iter++] = msg[i];
						}
						resultMessage = mergeMsgs;
					}
				}
			}
			dOut = new DataOutputStream(clientSocket.getOutputStream());
			dOut.write(resultMessage);           // write the message
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se pudo leer codop");
		}

	}
	
}
