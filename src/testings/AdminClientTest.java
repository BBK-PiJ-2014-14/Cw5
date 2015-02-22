package testings;

import static org.junit.Assert.*;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.Before;
import org.junit.Test;

import quizServer.AdminClient;
import quizServer.Quizer;
import quizServer.Server;

public class AdminClientTest {
	AdminClient client;
	
	@Before
	public void creator() {
		Server.main(new String[0]);
		client = new AdminClient();
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunAdmin() {
		
	}

	@Test
	public void testQuizCreator() throws RemoteException {
		client.quizCreator();
		Quizer myQuizer = null;
		try {
			Registry reg = LocateRegistry.getRegistry(1099);
			myQuizer = (Quizer) reg.lookup("QuizerImpl");
			
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		assertEquals(myQuizer.getQuizList().length, 1);	
	}

	@Test
	public void testQuizCloser() throws AccessException, RemoteException {
		client.quizCreator();
		Quizer myQuizer = null;
		try {
			Registry reg = LocateRegistry.getRegistry(1099);
			myQuizer = (Quizer) reg.lookup("QuizerImpl");
			
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		client.quizCloser();
		assertEquals(myQuizer.getQuizList().length, 0);
	}

}
