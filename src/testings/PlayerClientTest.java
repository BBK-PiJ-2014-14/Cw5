package testings;

import static org.junit.Assert.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import quizServer.AdminClient;
import quizServer.Client;
import quizServer.PlayerClient;
import quizServer.Server;

/**
 * 
 * @author Noam
 * This class tests the class PlayerClient.
 * It is uses an InputStream from files instead of the user input.
 * The files contains all king of input so that the test pass only id the class can deal with illegal input.
 */
public class PlayerClientTest {
	private Client c;

	/**
	 * This method turn on the server.
	 */
	@Before
	public void runServer() {
		Server.main(new String[0]);
	}
	
	/**
	 * This test input via the file test1 and test3 if the the RunClient method can deal with all inputs.
	 * The input contain both legal an illegal input.
	 * After reading file test1 it should produce a quiz and after test3 the the winner of the test should be Noam with score of 50.
	 * The assertions check if the method do it.
	 * @throws FileNotFoundException 
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * 
	 */
	@Test
	public void testRunClient() throws RemoteException, NotBoundException, FileNotFoundException {
		c = new PlayerClient(System.in);
		c.runClient(); // should terminate since there are no quizzes.
		c = new AdminClient(new FileInputStream("./src/testings/test1.txt"));
		c.runClient();
		c = new PlayerClient(new FileInputStream("./src/testings/test3.txt"));
		c.runClient();
		assertTrue(c.getQuizer().getQuiz("q1").getHighestScore() == 50);
		assertTrue(c.getQuizer().getQuiz("q1").getWinner().equals("Noam"));
	}
}
