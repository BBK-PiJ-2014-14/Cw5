package quizServer;

import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * 
 * @author Noam
 * This class represent a general client.
 * It has a general constructor, implementation of the getQuizer() method and an empty implementation of the run method.
 * This method shoud be override by an extends clients.
 */
public class ClientImpl implements Client {
	Scanner in;
	
	/**
	 * The constructor get an InputStream to be used in the run method.
	 * This shoud be System.in in the main method or something else for the testings.
	 * @param input InputStream to use by the client.
	 */
	public ClientImpl(InputStream input) {
		in = new Scanner(input);	
	}

	/**
	 * This method stay empty since there is no common implementations for it.
	 */
	@Override
	public void runClient() throws RemoteException,  NotBoundException{
	}

	/**
	 * This method connect to the server and return the remote object QuizerImpl.
	 * @return the remote object quizerImpl
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	@Override
	public Quizer getQuizer() throws RemoteException, NotBoundException {
		Registry reg= LocateRegistry.getRegistry(1099);
		return (Quizer) reg.lookup("QuizerImpl");

	}

}
