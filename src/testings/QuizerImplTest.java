package testings;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import quizServer.Question;
import quizServer.QuestionImpl;
import quizServer.Quiz;
import quizServer.QuizImpl;
import quizServer.Quizer;
import quizServer.QuizerImpl;

public class QuizerImplTest {
	Quizer quizer;
	Quiz q1;
	Quiz q2;
	Question q;
	List<Question> questions;
	
	@Before
	public void creator() throws RemoteException {
		q = new QuestionImpl();
		q.setQuestion("q", "a1", "a2", "a3", "a4", 1);getClass();
		q1 = new QuizImpl();
		questions = new LinkedList<Question>();
		q1.setQuiz("q1", questions);
		q2.setQuiz("q2", questions);
		quizer = new QuizerImpl();
	}

	@Test
	public void testAddQuiz() throws RemoteException {
		quizer.addQuiz(q1);
		assertEquals(quizer.getQuiz("q1"), q1);
		assertEquals(quizer.getQuiz(q1.getId()),q1);
		assertEquals(quizer.getQuizList().length, 1);
		quizer.addQuiz(q2);
		assertEquals(quizer.getQuiz("q2"), q2);
		assertEquals(quizer.getQuiz(q2.getId()),q2);
		assertEquals(quizer.getQuizList().length, 2);
		assertEquals(quizer.getQuiz("q1"), q1);
		assertEquals(quizer.getQuiz(q1.getId()),q1);	
	}

	@Test
	public void testGetQuizList() throws RemoteException {
		assertEquals(quizer.getQuizList().length, 0);
		quizer.addQuiz(q1);
		assertEquals(quizer.getQuizList().length, 1);
		assertEquals(quizer.getQuizList()[0], "q1");
		quizer.addQuiz(q2);
		assertEquals(quizer.getQuizList().length, 2);
		assertEquals(quizer.getQuizList()[0], "q1");
		assertEquals(quizer.getQuizList()[1], "q2");
	}

	@Test
	public void testRemoveQuiz() throws RemoteException {
		boolean check = false;
		try {
			check = quizer.removeQuiz(66);
		} catch (NoSuchElementException e) {}
		assertFalse(check);
		quizer.addQuiz(q1);
		int id = q1.getId();
		check = quizer.removeQuiz(id);
		assertTrue(check);
		assertEquals(quizer.getQuizList().length, 0);	
	}

	@Test
	public void testGetQuizString() throws RemoteException {
		quizer.addQuiz(q1);
		assertEquals(quizer.getQuiz("q1"), q1.getName());
		quizer.addQuiz(q2);
		assertEquals(quizer.getQuiz("q2"), q2.getName());
		assertEquals(quizer.getQuiz("q1"), q1.getName());
		Quiz q = quizer.getQuiz("NoSuchQuiz");
		assertNull(q);
	}

	@Test
	public void testGetQuizInt() throws RemoteException {
		assertNull(quizer.getQuiz(55));
		quizer.addQuiz(q1);
		assertEquals(quizer.getQuiz(q1.getId()), q1.getId());
		quizer.addQuiz(q2);
		assertEquals(quizer.getQuiz(q2.getId()), q2.getId());
		assertEquals(quizer.getQuiz(q1.getId()), q1.getId());	
	}

}
