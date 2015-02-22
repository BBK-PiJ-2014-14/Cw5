package quizServer;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * @author Noam
 *The implementation of this interface is the class that manage the quizzes.
 *It contain the quizzes.
 */
public interface Quizer extends Remote, Serializable {
	
	/**
	 * This method add a quiz to the quizer.
	 * @param quiz this is the quiz to be added.
	 * @return return true if the quiz was added, false if not.
	 * @throws RemoteException
	 */
	public boolean addQuiz(Quiz quiz) throws RemoteException;
	
	/**
	 * This method returns an array containing the names of the available quizzes in the quizer.
	 * @return an array containing the names of the available quizzes in the quizer. Should be empty if there are no.
	 * @throws RemoteException
	 */
	public String[] getQuizList() throws RemoteException;
	
	/**
	 * This method remove a quiz from the quizer.
	 * @param id the id of the quiz to be removed.
	 * @return true if the quiz removed, false if not.
	 * @throws RemoteException
	 * @throws NoSuchElementException if there is no quiz with this id.
	 */
	public boolean removeQuiz(int id) throws RemoteException, NoSuchElementException;
	
	/**
	 * This method return the quiz which has the parm name.
	 * @param name the name of the quiz to be return.
	 * @return the quiz with the parm name or null if there is such a quiz.
	 * @throws RemoteException
	 */
	public Quiz getQuiz(String name) throws RemoteException;
	
	/**
	 * This method return the quiz which has the parm id.
	 * @param id the id of the quiz to be returned.
	 * @return the quiz with the parm id or null if there is no.
	 * @throws RemoteException
	 */
	public Quiz getQuiz(int id) throws RemoteException;
	
	/**
	 * This method return a new instance of a Question object.
	 * @return an new Question Object.
	 * @throws RemoteException
	 */
	public Question getNewQuestion() throws RemoteException;
	
	/**
	 * This method return a new instance of a Quiz object.
	 * @return an new Quiz object.
	 * @throws RemoteException
	 */
	public Quiz getNewQuiz() throws RemoteException;
}
