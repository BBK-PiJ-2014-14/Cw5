package quizServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QuestionImpl extends UnicastRemoteObject implements Question {

	

	public QuestionImpl() throws RemoteException {
		super();
	}

	@Override
	public void setQuestion(String q, String a1, String a2, String a3,
			String a4, int r) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getQuestion() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getAnswers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRightAns() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
