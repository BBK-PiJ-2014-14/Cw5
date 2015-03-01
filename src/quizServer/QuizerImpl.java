package quizServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class QuizerImpl extends UnicastRemoteObject implements Quizer {
	private List<Quiz> quizzes;

	public QuizerImpl() throws RemoteException {
		super();
	    quizzes = new LinkedList<Quiz>();
	}

	@Override
	public synchronized boolean addQuiz(Quiz quiz) throws RemoteException {
		for(String s :getQuizList()) {
			if(s.equals(quiz.getName())) {
				return false;
			}
		}
		return quizzes.add(quiz);
	}

	@Override
	public String[] getQuizList() throws RemoteException {
		String[] result = new String[quizzes.size()];
		for(int i=0; i<quizzes.size(); i++) {
			result[i] = quizzes.get(i).getName();
		}
		return result;
	}

	@Override
	public boolean removeQuiz(int id) throws RemoteException, NoSuchElementException {
		try {
			return quizzes.remove(getQuiz(id));
		} catch (NullPointerException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public Quiz getQuiz(String name) throws RemoteException {
		for(Quiz a : quizzes) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		return null;	
	}

	@Override
	public Quiz getQuiz(int id) throws RemoteException {
		for(Quiz a : quizzes) {
			if (a.getId() == id) {
				return a;	
			}
		}
		return null;
	}

	@Override
	public Question getNewQuestion() throws RemoteException {
		return new QuestionImpl();
	}

	@Override
	public Quiz getNewQuiz() throws RemoteException {
		return new QuizImpl();
	}

}
