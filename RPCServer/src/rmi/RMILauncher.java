package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMILauncher {
	public static void main(String args[]){
		if( args.length < 2 ){
			System.out.println("Debe llamar programa  <RMIObjectPath> <RMIObjectClass>");
			System.exit(1);
		}
		System.setProperty ("java.rmi.server.codebase", "file:" + args[1]);
		DBConnectorRemoto dbConnectorRemoto;
		try {
			dbConnectorRemoto = new DBConnectorRemoto();
			Naming.rebind("DBConnectorRemoto", dbConnectorRemoto);
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
