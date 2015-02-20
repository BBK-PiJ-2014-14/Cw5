package quizServer;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Noam
 * Implementation of this interface represent a question in a quiz.
 * Each question should contain four possible answer and a reference to the correct answer. 
 */

public interface Question extends Remote, Serializable {
	
	/**
	 * This method set the the question and the answers.
	 * @param q this is the question itself.
	 * @param a1 this is the first answer.
	 * @param a2 this is the second answer.
	 * @param a3 this is the third answer.
	 * @param a4 this is the forth answer.
	 * @param r this is the number of correct answer.
	 * @throws RemoteException
	 */
	public void setQuestion(String q, String a1, String a2, String a3, String a4, int r) throws RemoteException;
	
	/**
	 * This method return a string of the question.
	 * @return the question
	 * @throws RemoteException
	 */
	public String getQuestion() throws RemoteException;
	
	/**
	 * This method return an array of String containing the possible answers
	 * @return array of possible answers.
	 * @throws RemoteException
	 */
	public String[] getAnswers() throws RemoteException;
	
	/**
	 * This method return a number of the correct answer.
	 * @return the number of the correct answer.
	 * @throws RemoteException
	 */
	public int getRightAns() throws RemoteException;
}
