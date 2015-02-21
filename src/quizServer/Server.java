package quizServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * 
 * @author Noam
 * This is the class of the server.
 * This server upload 3 remote objects: QuestionImpl, QuizImpl, and QuizerImpl.
 */
public class Server {
	
	/**
     * Main method of the server, turn on the server.
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server();
		server.serverRegister();
	}
	
	/**
	 * this method register the server and upload it's remote objects.
	 */
	public void serverRegister() {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.bind("QuizerImpl", new QuizerImpl());
			reg.bind("QuestionImpl", new QuestionImpl());
			reg.bind("QuizImpl", new QuizImpl());
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}	
	}
}
