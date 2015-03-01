package quizServer;

import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Noam
 *This class is a client class. This client can create and close quizzes on the server.
 */
public class AdminClient extends ClientImpl {
	
	/**
	 * Constructor from superclass.
	 * @param input
	 */
	public AdminClient(InputStream input){
		super(input);
	}

	/**
	 * Main method, turning on the client.
	 * @param args
	 */
	public static void main(String[] args) {
		Client myClient = new AdminClient(System.in);
		try {
			myClient.runClient();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method opens a menu for the user to choose what he would like to do.
	 */
	@Override
	public void runClient() {
		int choice = 0;
		System.out.println("Welcome to the quiz administrator");
		while(choice != 3) {
			System.out.println("What would you like to do now:");
			System.out.println("1. Create a quiz\n2. Close a quiz\n3. Exit");
			try {
				choice = Integer.parseInt(in.nextLine());
				if(choice<1 || choice>3) { //check if the input is legal.
					System.out.println("Invalid input, try again\n" );
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, try again\n");
			}
			if(choice==1) {
				quizCreator();  //create a quiz.
			}
			if(choice==2) {
				quizCloser(); //close a quiz.
			}		
		}	
	}
	
	/**
	 * This method create a quiz according to the user input.
	 */
	private void quizCreator() {
		System.out.println("This is a quiz builder, you will now be asked to enter your quiz details");
		String name = createName(); 
		List<Question> quizQuestions = createQuestions(); 
		try {
			Quiz quiz = getQuizer().getNewQuiz();
			quiz.setQuiz(name, quizQuestions); //constructing the quiz.
			if (getQuizer().addQuiz(quiz)) {
				System.out.println("Thank you, your quiz has been added\nYour quiz id is: " + quiz.getId()+"\n");
			} else {
				System.out.println("Something went wrong, please try again");
			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method create a name for a new quiz.
	 * @return The name of the quiz.
	 */
	private String createName(){
		String name = "0";
		while(name.equals("0")) {
			System.out.println("Enter a name for the quiz: ");
			name = in.nextLine();
			if(name.equals("0")|| name.equals("")) {
				System.out.println("Invalid name"); 
				name ="0";
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
		return name;
	}
	
	/**
	 * This method build a list of questions for a new quiz.
	 * @return A list of question for a new quiz.
	 */
	private List<Question> createQuestions() {
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
		List<Question> result = new  ArrayList<Question>();
		Question question = null;
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
				question.setQuestion(qu, a1, a2, a3, a4, r);
				result.add(question);
			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return result;
	}
		
	/**
	 * This method close a quiz according to the input id.
	 */
	private void quizCloser() {
		Quiz toClose = null;
		Integer id = null;
		try {
			if(getQuizer().getQuizList().length == 0) { //check if there are any quizzes to close.
				System.out.println("No quizzes to close\n");
				return;
			}
			while (toClose == null){
				try {
					System.out.println("Please enter the id of the quiz you wish to close( or -1 to go back): ");
					id = Integer.parseInt(in.nextLine());
					if (id == -1) {
						return; //user chose to go back.
					}
					toClose = getQuizer().getQuiz(id); // searching for the quiz. 
					if(toClose == null) {
						System.out.println("Quiz not found, make sure you enter a correct id");
					}
				} catch (NumberFormatException e) {
					System.out.println("Id should contain numbers only");
				}
			}
			if(getQuizer().removeQuiz(id)) {
				System.out.println("Quiz " +toClose.getName()+ " closed!\nThe winner of this quiz is: "+ toClose.getWinner());
				System.out.println("His score is: "+toClose.getHighestScore()+"\n");
			} else {
				System.out.println("Quiz not found, might closed by someone else, try again");
			}
		
			
		} catch(RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}	
}
