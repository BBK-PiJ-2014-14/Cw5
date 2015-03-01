package testings;

import static org.junit.Assert.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Test;

import quizServer.Client;
import quizServer.ClientImpl;
import quizServer.Quizer;
import quizServer.Server;

/**
 * 
 * @author Noam
 * This class tests the ClientImpl class.
 * The is no test for mathod runClient since the method is empty. it should be tested in the extending classes.
 */
public class ClientImplTest {

	/**
	 * Test for getQuizer() method.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	@Test
	public void testGetQuizer() throws RemoteException, NotBoundException {
		Client c = new ClientImpl(System.in);
		Server.main(new String[0]);
		Quizer q = c.getQuizer();
		assertNotNull(q);
		assertTrue(q.getQuizList().length == 0);
	}
}
