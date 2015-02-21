package testings;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import quizServer.Question;
import quizServer.QuestionImpl;
import quizServer.Quiz;
import quizServer.QuizImpl;

/**
 * 
 * @author Noam
 * This is a test class for class QuizImpl.
 * Each method test the method is tested by a test method with the same name.
 */
public class QuizImplTest {
	List<Question> questions;
	Question q1;
	Question q2;
	Quiz quiz;
	String name;
	
	@Before
	public void creator() throws RemoteException {
		String q = "myQuestion";
		String a1 = "a1";
		String a2 = "a2";
		String a3 = "a3";
		String a4 = "a4";
		int r = 1;
		q1 = new QuestionImpl();
		q1.setQuestion(q, a1, a2, a3, a4, r);
		q= "myQuestion#";
		a1 = "a1#";
		a2 = "a2#";
		a3 = "a3#";
		a4 = "a4#";
		r = 2;
		q2 = new QuestionImpl();
		q2.setQuestion(q, a1, a2, a3, a4, r);
		questions = new LinkedList<Question>();
		questions.add(q1);
		questions.add(q2);
		quiz = new QuizImpl();
		name = "First";
	}

	@Test
	public void testSetQuiz() throws RemoteException {
		quiz.setQuiz(name, questions);
		assertEquals(quiz.getName(), name);
		assertEquals(quiz.getQuestion(0), q1);
		assertEquals(quiz.getQuestion(1), q2);
		assertEquals(quiz.getNumOfQuestions(),2);	
	}

	@Test
	public void testGetName() throws RemoteException {
		quiz.setQuiz(name, questions);
		assertEquals(quiz.getName(), name);	
	}

	@Test
	public void testGetId() throws RemoteException {
		int id1 = quiz.setQuiz(name, questions);
		assertEquals(quiz.getId(), id1);
		int id2 = quiz.setQuiz("another Quiz", questions);
		assertEquals(quiz.getId(), id2);
		assertNotSame(id1, id2);
	}

	@Test
	public void testGetQuestion() throws RemoteException {
		quiz.setQuiz(name, questions);
		assertEquals(quiz.getQuestion(0), q1);
		assertEquals(quiz.getQuestion(1), q2);
		Question a = null;
		try {
			a = quiz.getQuestion(3);
		} catch (IndexOutOfBoundsException e) {}
		assertNull(a);	
	}

	@Test
	public void testGetNumOfQuestions() throws RemoteException {
		assertEquals(quiz.getNumOfQuestions(), 0);
		quiz.setQuiz(name, questions);
		assertEquals(quiz.getNumOfQuestions(), 2);
	}

	@Test
	public void testSetWinner() throws RemoteException {
		String n = "Noam";
		int s = 50;
		quiz.setWinner(n, s);
		assertEquals(quiz.getWinner(), n);
		assertEquals(quiz.getHighestScore(), s);
		n = "Bob";
		s = 70;
		quiz.setWinner(n, s);
		assertEquals(quiz.getWinner(), n);
		assertEquals(quiz.getHighestScore(), s);
		s = 30;
		quiz.setWinner(n, s);
		assertNotSame(quiz.getHighestScore(), s);	
	}

	@Test
	public void testGetWinner() throws RemoteException {
		String n = "Noam";
		int s = 50;
		quiz.setWinner(n, s);
		assertEquals(quiz.getWinner(), n);
		n = "Bob";
		s = 100;
		quiz.setWinner(n, s);
		assertEquals(quiz.getWinner(), n);
		n = "Noam";
		s = 60;
		quiz.setWinner(n, s);
		assertNotSame(quiz.getWinner(), n);
	}

	@Test
	public void testGetHighestScore() throws RemoteException {
		String n = "Noam";
		int s = 50;
		quiz.setWinner(n, s);
		assertEquals(quiz.getHighestScore(), s);
		n = "Bob";
		s = 100;
		quiz.setWinner(n, s);
		assertEquals(quiz.getHighestScore(), s);
		n = "Noam";
		s = 60;
		quiz.setWinner(n, s);
		assertNotSame(quiz.getHighestScore(), s);
	}

}
