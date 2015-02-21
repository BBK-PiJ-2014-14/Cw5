package quizServer;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author Noam
 * Implementation of this interface represent a whole quiz.
 * A quiz contain questions, unique id, and a reference to the winner and his score.
 */
public interface Quiz extends Remote, Serializable {
	
	/**
	 * This method set the name and the questions of the quiz.
	 * @param name - this is the name of the quiz
	 * @param questions - this is a list of the questions of the quiz. 
	 * @return the id that set to this quiz.
	 * @throws RemoteException
	 */
	public int setQuiz(String name, List<Question> questions) throws RemoteException;
	
	/**
	 * This method return the name of the quiz.
	 * @return A string of the name of the quiz.
	 * @throws RemoteException
	 */
	public String getName() throws RemoteException;
	
	/**
	 * This method return the id of the quiz.
	 * @return the id of the quiz.
	 * @throws RemoteException
	 */
	public int getId() throws RemoteException;
	
	/**
	 * This method return a question from the quiz.
	 * @param index this is the index of the question in the list of the questions.
	 * @return the question of the quiz in the index number.
	 * @throws RemoteException
	 * @throws IndexOutOfBoundsException if there is no question in that index.
	 */
	public Question getQuestion(int index) throws RemoteException, IndexOutOfBoundsException;
	
	/**
	 * This method return the number of questions in the quiz.
	 * @return the number of questions in the quiz.
	 * @throws RemoteException
	 */
	public int getNumOfQuestions() throws RemoteException;
	
	/**
	 * This method set a winner for the quiz.
	 * @param name the name of the winner.
	 * @param Scores the score of the winner.
	 * @throws RemoteException
	 */
	public void setWinner(String name, int score) throws RemoteException;
	
	/**
	 * This method return the name of the winner.
	 * @return the name of the winner.
	 * @throws RemoteException
	 */
	public String getWinner() throws RemoteException;
	
	
	/**
	 * This method return the score of the winner.
	 * @return the score of the winner.
	 * @throws RemoteException
	 */
	public int getHighestScore() throws RemoteException;
}
