package testings;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import quizServer.Question;
import quizServer.QuestionImpl;
/**
 * 
 * @author Noam
 *This is a test class for class QuestionImpl.
 *Each method test the method is tested by a test method with the same name.
 */
public class QuestionImplTest {
	Question question;
	String q = "myQuestion";
	String a1;
	String a2;
	String a3;
	String a4;
	int r;	
	
	@Before
	public void createObject() {
		q = "myQuestion";
		a1 = "a1";
		a2 = "a2";
		a3 = "a3";
		a4 = "a4";
		r = 1;	
		try {
			question = new QuestionImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetQuestion() throws RemoteException {
		question.setQuestion(q, a1, a2, a3, a4, r);
		assertEquals(question.getQuestion(), q);
		assertEquals(question.getAnswers()[0], a1);
		assertEquals(question.getAnswers()[1], a2);
		assertEquals(question.getAnswers()[2], a3);
		assertEquals(question.getAnswers()[3], a4);
		assertEquals(question.getRightAns(),r);
		r = 6;
		try {
			question.setQuestion(q, a1, a2, a3, a4, r);
		} catch (IndexOutOfBoundsException e) {}
		assertNotSame(question.getRightAns(),r);
	}

	@Test
	public void testGetQuestion() throws RemoteException {
		question.setQuestion(q, a1, a2, a3, a4, r);
		assertEquals(question.getQuestion(), q);
	}

	@Test
	public void testGetAnswers() throws RemoteException {
		question.setQuestion(q, a1, a2, a3, a4, r);
		assertEquals(question.getAnswers().length, 4);
		assertEquals(question.getAnswers()[0],a1);
		assertEquals(question.getAnswers()[1],a2);
		assertEquals(question.getAnswers()[2],a3);
		assertEquals(question.getAnswers()[3],a4);
	}

	@Test
	public void testGetRightAns() throws RemoteException {
		question.setQuestion(q, a1, a2, a3, a4, r);
		assertEquals(question.getRightAns(),r);
	}

}
