package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIDummy {
	public static void main(String args[]){
		DBConnector rmiObject;
		try {
			rmiObject = (DBConnector)Naming.lookup("//127.0.0.1/DBConnectorRemoto");
			rmiObject.setUbicacion("Hector", "persona", 1, 831, 932);
			rmiObject.setUbicacion("Gibran", "persona", 1, 666, 666);
			rmiObject.setUbicacion("Lea", "persona", 1, 555, 555);
			rmiObject.setUbicacion("Victor", "persona", 1, 444, 444);
			rmiObject.setUbicacion("Felix", "persona", 1, 322, 322);
			rmiObject.setUbicacion("Lab1", "laboratorio", 1, 324, 573);
			rmiObject.setUbicacion("Lab2", "laboratorio", 1, 532, 231);
			rmiObject.setUbicacion("impre-Lab3", "impresora", 1, 352, 832);
			rmiObject.setUbicacion("impre-Lab4", "impresora", 1, 355, 233);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al conectar RMI");
			System.exit(3);
		}
	}

}
