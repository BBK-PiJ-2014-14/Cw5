package testings;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizServer.AdminClient;
import quizServer.Client;
import quizServer.Server;

public class AdminClientTest {
	InputStream i;
	Client c;
	
	@Before
	public void Creator(){
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
		i = new FileInputStream("./src/testings/test1.txt");
		c = new AdminClient(i);
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
		i = new FileInputStream("./src/testings/test2.txt");
		c = new AdminClient(i);
		c.runClient();
		assertTrue(c.getQuizer().getQuizList().length == 0);
		
	}
	
	

	

}
