package quizServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class QuizImpl extends UnicastRemoteObject implements Quiz {
	private static int idProducer;
	
	private int id;
	private String name;
	private List<Question> questions;
	private String winner;
	private int highestScore;

	
	public QuizImpl() throws RemoteException {
		super();
	}

	@Override
	public int setQuiz(String name, List<Question> questions) throws RemoteException {
		this.name = name;
		this.questions = questions;
		this.id = idProducer;
		QuizImpl.idProducer++;
		return id;
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public int getId() throws RemoteException {
		return id;
	}

	@Override
	public Question getQuestion(int index) throws RemoteException, IndexOutOfBoundsException {
		return questions.get(index);
	}

	@Override
	public int getNumOfQuestions() throws RemoteException {
		return questions.size();
	}

	@Override
	public void setWinner(String name, int score) throws RemoteException {
		if(score > highestScore) {
			highestScore = score;
			winner = name;
		}
	}

	@Override
	public String getWinner() throws RemoteException {
		return winner;
	}

	@Override
	public int getHighestScore() throws RemoteException {
		return highestScore;
	}

}
