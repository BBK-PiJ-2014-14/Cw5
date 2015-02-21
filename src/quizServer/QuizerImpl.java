package quizServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QuizerImpl extends UnicastRemoteObject implements Quizer {

	protected QuizerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addQuiz(Quiz quiz) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getQuizList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeQuiz(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Quiz getQuiz(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz getQuiz(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
