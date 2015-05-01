package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

import common.MapResource;

public class RMIDummy {
	public static void main(String args[]){
		DBConnector rmiObject;
		try {
			rmiObject = (DBConnector)Naming.lookup("//127.0.0.1/DBConnectorRemoto");
			rmiObject.setUbicacion("Celeste", "persona", 1, 50, 50);
			rmiObject.setUbicacion("Hector", "persona", 1, 831, 932);
			rmiObject.setUbicacion("Gibran", "persona", 1, 666, 666);
			rmiObject.setUbicacion("Lea", "persona", 1, 555, 555);
			rmiObject.setUbicacion("Victor", "persona", 1, 444, 444);
			rmiObject.setUbicacion("Felix", "persona", 1, 322, 322);
			rmiObject.setUbicacion("Lab1", "laboratorio", 1, 324, 573);
			rmiObject.setUbicacion("Lab2", "laboratorio", 1, 532, 231);
			rmiObject.setUbicacion("impre-Lab3", "impresora", 1, 352, 832);
			rmiObject.setUbicacion("impre-Lab4", "impresora", 1, 355, 233);
			String[] names = {"Hector","Gibran","Lea","Victor","Felix","Lab1","Lab2","impre-Lab3","impre-Lab4"};
			int[] direccion={1,1,1,1,1,1,1,1,1,};
			Random generator = new Random();
			while(true){
				try {
					MapResource act = null;;
					for(int i=0;i<names.length;i++){
						act = rmiObject.getUbicacion(names[i]);
						int nx,ny;
						int dirX = generator.nextInt() %3;
						int dirY = generator.nextInt() %3;
						if(dirX == 2 ) dirX = -1;
						if(dirY == 2 ) dirY = -1;
						do{
							nx = act.getPosition().getX() + generator.nextInt(20) * dirX;
							ny = act.getPosition().getX() + generator.nextInt(20) * dirY;
						}while( nx > 1200 || ny > 1200 || nx <= 0 || ny <= 0);
						rmiObject.setUbicacion(act.getName(), act.getType(), act.getPlanta(), nx,ny);
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al conectar RMI");
			System.exit(3);
		}
	}

}
