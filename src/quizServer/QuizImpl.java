package quizServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class QuizImpl extends UnicastRemoteObject implements Quiz {

	public QuizImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int setQuiz(String name, List<Question> questions)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Question getQuestion(int index) throws RemoteException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfQuestions() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWinner(String name, int Scores) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getWinner() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHighestScore() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
