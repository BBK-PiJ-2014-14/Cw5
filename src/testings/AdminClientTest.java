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
import quizServer.Server;

/**
 * 
 * @author Noam
 * This class tests the class AdminClient.
 * It is uses an InputStream from files instead of the user input.
 * The files contains all king of input so that the test pass only id the class can deal with illegal input.
 */
public class AdminClientTest {
	private Client c;
	
	/**
	 * This method turn on the server.
	 */
	@Before
	public void runServer(){
		Server.main(new String[0]);
	}
	
	
	/**
	 * This test input via the file test1 and test2 if the the RunClient method can deal with all input.
	 * The input contain both legal an illegal input.
	 * After reading file test1 it should produce a quiz and after test2 this quiz should be delete.
	 * The assertions check if the method do it.
	 * It should produce a test with 2 questions.
	 * @throws FileNotFoundException 
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * 
	 */
	@Test
	public void testRunClient() throws FileNotFoundException, RemoteException, NotBoundException {
		c = new AdminClient(new FileInputStream("./src/testings/test1.txt"));
		c.runClient();
		assertTrue(c.getQuizer().getQuiz("q1").getNumOfQuestions() == 2);
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(0).getQuestion().equals("q1"));
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(0).getAnswers()[0].equals("1"));
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(0).getAnswers()[1].equals("2"));
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(0).getRightAns() == 1);
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(1).getQuestion().equals("q2"));
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(1).getAnswers()[0].equals("1"));
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(1).getAnswers()[1].equals("2"));
		assertTrue(c.getQuizer().getQuiz("q1").getQuestion(1).getRightAns() == 2);
		c = new AdminClient(new FileInputStream("./src/testings/test2.txt"));
		c.runClient();
		assertTrue(c.getQuizer().getQuizList().length == 0);	
	}
}
