package quizServer;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author Noam
 *This class is a client class. This client can create and close quizzes on the server.
 */
public class AdminClient {
	private Scanner in = new Scanner(System.in);
	

	/**
	 * Main method, turning on the client.
	 * @param args
	 */
	public static void main(String[] args) {
		AdminClient myClient = new AdminClient();
		myClient.runAdmin();	
	}
	
	/**
	 * This method opens a menu for the user to choose what he would like to do.
	 */
	private void runAdmin() {
		int choice = 0;
		System.out.println("Welcome to the quiz administrator");
		while(choice != 3) {
			System.out.println("What would you like to do now:");
			System.out.println("1. Create a quiz\n2. Close a quiz\n3. Exit");
			try {
				choice = Integer.parseInt(in.nextLine());
				if(choice<1 || choice>3) {
					System.out.println("Invalid input, try again\n" );
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, try again\n");
			}
			if(choice==1) {
				quizCreator();
			}
			if(choice==2) {
				quizCloser();
			}		
		}	
	}
	
	/**
	 * This method create a quiz according to the user input.
	 */
	private void quizCreator() {
		System.out.println("This is a quiz builder, you will now be asked to enter your quiz details");
		String name = "0";
		while(name.equals("0")) {
			System.out.println("Enter a name for the quiz: ");
			name = in.nextLine();
			if(name.equals("0")) {
				System.out.println("Invalid name"); 
			}
			try {
				for(String s : getQuizer().getQuizList()) {
					if(s.equals(name)) {
						System.out.println("Name already exsist");
						name = "0";
					}
				}
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
		int numOfQuestions = 0;
		while (numOfQuestions < 1) {
			try{
				System.out.println("Enter the number of questions: ");
				numOfQuestions = Integer.parseInt(in.nextLine());
				if(numOfQuestions<1) {
					System.out.println("Number of questions must be a positive number!");
				}
			} catch (NumberFormatException e) {
				System.out.println("This is not a number");
			}
		}
		List<Question> quizQuestions = new  ArrayList<Question>();
		Question question = null;
		Quiz quiz = null;
		try {
			for(int i=1; i<=numOfQuestions; i++) {
				System.out.println("Enter question "+i+":");
				String qu = in.nextLine();
				System.out.println("Enter first answer:");
				String a1 = in.nextLine();
				System.out.println("Enter second answer:");
				String a2 = in.nextLine();
				System.out.println("Enter thierd answer:");
				String a3 = in.nextLine();
				System.out.println("Enter forth answer:");
				String a4 = in.nextLine();
				int r = 0;
				while (r<1 || r>4) {
					try{
						System.out.println("Enter the right answer's number(1-4):");
						r = Integer.parseInt(in.nextLine());
						if(r<1 || r>5) {
							System.out.println("Answer must be between 1-4");
						}
					} catch (NumberFormatException e) {
						System.out.println("This is not a number");
					}
				}
				question = getQuizer().getNewQuestion();
				quiz = getQuizer().getNewQuiz();
				question.setQuestion(qu, a1, a2, a3, a4, r);
				quizQuestions.add(question);
			}
			quiz.setQuiz(name, quizQuestions);
			getQuizer().addQuiz(quiz);
			System.out.println("Thank you, your quiz has been added\nYour quiz id is: " + quiz.getId()+"\n");	
		} catch (RemoteException | IndexOutOfBoundsException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
		
	/**
	 * This method close a quiz according to the input id.
	 */
	private void quizCloser() {
		Quiz toClose = null;
		Integer id = null;
		try {
			if(getQuizer().getQuizList().length == 0) {
				System.out.println("No quizzes to close\n");
				return;
			}
			while (toClose == null){
				try {
					System.out.println("Please enter the id of the quiz you wish to close( or -1 to go back): ");
					id = Integer.parseInt(in.nextLine());
					if (id == -1) {
						return;
					}
					toClose = getQuizer().getQuiz(id); // searching for the quiz. 
					if(toClose == null) {
						System.out.println("Quiz not found, make sure you enter a correct id");
					}
				} catch (NumberFormatException e) {
					System.out.println("Id should contain numbers only");
				}
			}
			if(toClose.getUser()>0) {
				System.out.println("Quiz in use, please try later");
			} else {
				System.out.println("Quiz " +toClose.getName()+ " closed!\nThe winner of this quiz is: "+ toClose.getWinner());
				System.out.println("His score is: "+toClose.getHighestScore()+"\n");
				getQuizer().removeQuiz(id);
			}
		} catch(RemoteException | NoSuchElementException | NotBoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This method connect to the server and return the remote object QuizerImpl.
	 * @return the remote object quizerImpl
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private Quizer getQuizer() throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry(1099);
		return (Quizer) reg.lookup("QuizerImpl");
	}
}
