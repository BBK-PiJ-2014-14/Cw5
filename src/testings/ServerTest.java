package testings;

import static org.junit.Assert.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.Test;

import quizServer.Question;
import quizServer.Quiz;
import quizServer.Quizer;
import quizServer.Server;

public class ServerTest {

	@Test
	public void testServerRegister() throws RemoteException {
		Server.main(new String[0]);
		Registry reg = LocateRegistry.getRegistry(1099);
		Question question = null;
		Quiz quiz = null;
		Quizer quizer = null;
		try {
			question = (Question) reg.lookup("QuestionImpl");
			quiz = (Quiz) reg.lookup("QuizImpl");
			quizer = (Quizer) reg.lookup("QuizerImpl");
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		assertNotNull(question);
		assertNotNull(quiz);
		assertNotNull(quizer);
		
	}
}
