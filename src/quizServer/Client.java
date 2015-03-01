package quizServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 
 * @author Noam
 * This is an interface of a client. all kind of client should implement it directly or indirectly.
 * It has a method to run the client and a method to get the remote object from the server.
 */
public interface Client {
	
	/**
	 * This method run the client.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 */
	public void runClient() throws RemoteException, NotBoundException;
	
	/**
	 * This method register with the server and return the remote object to be used by the client.
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public Quizer getQuizer() throws RemoteException, NotBoundException;

}
