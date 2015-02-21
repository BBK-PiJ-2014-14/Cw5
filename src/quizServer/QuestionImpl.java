package quizServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QuestionImpl extends UnicastRemoteObject implements Question {
	private String question;
	private String[] answers;
	private int rightAnsNum;

	
	public QuestionImpl() throws RemoteException {
		super();
		answers = new String[4];
	}

	@Override
	public void setQuestion(String q, String a1, String a2, String a3, String a4, int r) throws RemoteException, IndexOutOfBoundsException {
		this.question = q;
		String[] ans = {a1, a2, a3, a4};
		this.answers = ans;
		if( r<5 && r>0) {
			this.rightAnsNum = r;
		} else {
			throw new IndexOutOfBoundsException("Answer has to be between 1-4");
		}

	}

	@Override
	public String getQuestion() throws RemoteException {
		return question;
	}

	@Override
	public String[] getAnswers() throws RemoteException {
		return answers;
	}

	@Override
	public int getRightAns() throws RemoteException {
		return rightAnsNum;
	}
}
