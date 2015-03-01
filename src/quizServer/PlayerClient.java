package quizServer;

import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 
 * @author Noam
 *This is a client class. This client can only play the quizzes that are available on the server.
 */
public class PlayerClient extends ClientImpl {
	
	/**
	 * Constructor from superclass.
	 * @param input
	 */
	public PlayerClient(InputStream input) {
		super(input);	
	}

	/**
	 * Main method, turning on the client. 
	 * @param args
	 */
	public static void main(String[] args) {
		PlayerClient player = new PlayerClient(System.in);
		try {
			player.runClient();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method runs a menu that asks the user to choose a quiz from the list of available quizzes.
	 * It calls then another method that run the quiz.
	 */
	@Override
	public void runClient() throws RemoteException,  NotBoundException {
		if(getQuizer().getQuizList().length== 0) { // Check if there are any quizzes
			System.out.println("No quizzes found, try later");
		} else {
			Quiz toPlay = null;
			String quiz = "";
			while(!quiz.equals("0")) {
				System.out.println("Please choose a quiz to play from the list or enter 0 to exit:");
				for(String a: getQuizer().getQuizList()) {
					System.out.println(a); //print a list of available quizzes.
				}
				quiz = in.nextLine();
				toPlay = getQuizer().getQuiz(quiz); //Sreach for the chosen quiz.
				if (toPlay != null) {
					System.out.println("Running quiz "+quiz);
					play(toPlay); //play the quiz
				} else if (quiz.equals("0")) { //user chose to exit, goes out of the loop and terminate.
				} else {
					System.out.println("No such a quiz, try again"); // user entered a name that does not exsist.
				}
			}
		}			
	}
	
	/**
	 * This method run the quiz it gets as a parameter.
	 * @param quiz the quiz to be run.
	 * @throws RemoteException
	 */
	private void play(Quiz quiz) throws RemoteException {
		int counter = quiz.getNumOfQuestions(); // count the correct answers. decrement when an answer is wrong.
		for (int i=0; i<quiz.getNumOfQuestions(); i++){ //run each quiestion.
			System.out.println(quiz.getQuestion(i).getQuestion());
			System.out.println("1. " + quiz.getQuestion(i).getAnswers()[0]);
			System.out.println("2. " + quiz.getQuestion(i).getAnswers()[1]);
			System.out.println("3. " + quiz.getQuestion(i).getAnswers()[2]);
			System.out.println("4. " + quiz.getQuestion(i).getAnswers()[3]);
			int ans = 0;
			while(ans<1 || ans>4) { // take the user answer.
				try{
					System.out.println("Your Answer: ");
					ans = Integer.parseInt(in.nextLine());
					if(ans<1 || ans>4) { //check if the user input is legal.
						System.out.println("Answer must be a number between 1-4");
					}
				} catch (NumberFormatException e) { // thrown in case that the user input a non number input.
					System.out.println("Answer must be a number between 1-4");
				}		
			}
			if(ans == quiz.getQuestion(i).getRightAns()) { //check if the answer is correct. 
				System.out.println("Thats RIGHT!");
			} else {
				System.out.println("Nice try, but WRONG!");
				counter --;
			}
		}
		int score = (int)Math.round((double)counter/quiz.getNumOfQuestions()*100); //calculate the final score.
		System.out.println("You have finished the quiz");
		System.out.println("Your got " + counter + " From " + quiz.getNumOfQuestions() + " questions");
		System.out.println("Your score is " + score);
		if(score > quiz.getHighestScore()) { //check if it's a record break.
			System.out.println("Enter Your name:"); //takes user name if it's a record.
			String player = in.nextLine();
			quiz.setWinner(player, score); //set the record in the quiz.
			System.out.println("You got the highest score so far!");
		}
		System.out.println("Thank you for playing the quiz\n");
	}
}
