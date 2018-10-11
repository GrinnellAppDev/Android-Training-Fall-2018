import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.Character;
import java.util.Random;

public class Test {

	private static Colors.Color Color;
	private static String getAnswer = Color.YELLOW + "Enter answer here: " + Color.YELLOW_BOLD_BRIGHT;
	private static String SPACE = "\n";
	private static int totalQuestions = 0;
    private static int totalCorrect = 0;
    private static ArrayList<Question> questions = new ArrayList<>();
	private static ArrayList<Question> failedQuestions = new ArrayList<>();
	private static boolean isQuestionsRandom = false;
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		init(args);
		loadQuestions("." + args[0] + ".txt");
		runTest();	
		printScore();
		printFailedQuestions();
		printTopicsToReview();
		finishProgram();
	}

	public static void printScore() {
		System.out.println(SPACE);
		System.out.println(Color.CYAN_BRIGHT + "TOTAL SCORE: " + totalCorrect + "/" + totalQuestions + Color.RESET);
		System.out.print(SPACE);
	}

	public static void finishProgram() {
		System.out.println();
		System.out.println("Thanks for working on the mini quiz :)!");
		System.exit(0);
	}

	public static void init(String[] args) {
		//check if user included the random flag
		if(args.length > 1) {
			 if(args[1].equals("-r") || args[1].equals("-random")) {
				isQuestionsRandom = true;
			}
		}

		System.out.println(SPACE);
		System.out.print(Color.YELLOW_BOLD_BRIGHT + "Enter A, B, C, or D to answer (case insensitive). \n");
		System.out.print("Are you ready to start the quiz? y/n " + Color.RESET);
		String response = scan.nextLine();

		if(response.equals("y") || response.equals("Y")) {
			return;
		} else {
			finishProgram();
		}
	}

	public static void printFailedQuestions() {
		System.out.println(SPACE);
		System.out.print(Color.RED_BOLD_BRIGHT);
		System.out.println("FAILED QUESTIONS: ");
		System.out.print(Color.RESET);

		for(int i = 0; i < failedQuestions.size(); i++) {
			failedQuestions.get(i).printQuestion();
			System.out.print("Correct answer: ");
			failedQuestions.get(i).printRightAnswer(); 
			failedQuestions.get(i).printExplanation(); 
			System.out.println();
		}
	}

	public static void runTest() {
	int questionNum = 1;
	Random rand = new Random();
	ArrayList<Integer> lst = new ArrayList<Integer>();

	for(int x = 0; x < questions.size(); x++) {
		lst.add(x);
	}

	 for(int i = 0; i < questions.size(); i++) {
	 	Question qsn;

	 		if(isQuestionsRandom == true) {
	 			int pickedNum = rand.nextInt(lst.size());
	 			qsn = questions.get(lst.get(pickedNum));
	 			lst.remove(pickedNum);
	 		} else {
			    qsn = questions.get(i);
			}

			 System.out.println(SPACE);
			 System.out.print(Color.RESET);
			 System.out.print(questionNum++ + ") ");
			 qsn.printQuestion();
			 qsn.printAnswers();
			 System.out.print(getAnswer);

			 if(isAnswerCorrect(qsn, scan.nextLine()) == false) {
			 	failedQuestions.add(qsn);
				} else {
					totalCorrect++;
				} 
			}
		scan.close();
	}

	public static void loadQuestions(String filename) {

	BufferedReader br = null;

		try {
			  br = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				System.out.println("File containing questions not found");
				System.exit(1);
		}

	//Load all the questions from file into arraylist
		while(true) {
			try {
			 String question = br.readLine();

			 if(question != null) {
			 String answer1 = br.readLine();
			 String answer2 = br.readLine();
			 String answer3 = br.readLine();
			 String answer4 = br.readLine();
			 int correctAnswer = Integer.parseInt(br.readLine());
			 String explanation = br.readLine();
			 String topicsToReview = br.readLine();

			 Question q = new Question();
			 q.setQuestion(question);
			 q.addAnswer(answer1);
			 q.addAnswer(answer2);
			 q.addAnswer(answer3);
			 q.addAnswer(answer4);
			 q.addAnswerIndex(correctAnswer);
			 q.setExplanation(explanation);
			 q.addTopicToReview(topicsToReview);
			 totalQuestions++;
			 questions.add(q);
			} else {
				//we have loaded all questions
				break;
				}
			} catch(IOException e) {
				System.out.println("Something went wrong when reading file");
				System.exit(1);
			}
		}

			try {
				br.close();
			} catch(IOException e) {
				System.out.println("Something went wrong closing file");
				System.exit(1);
		}
	}

	public static void printTopicsToReview() {
		System.out.println(Color.CYAN_BOLD_BRIGHT + "TOPICS TO REVIEW: " + Color.RESET);
			for(int i = 0; i < failedQuestions.size(); i++) {
				failedQuestions.get(i).printTopicsToReview();
		        System.out.print(" ");
			}
			System.out.println();
			System.out.print(SPACE);
			System.out.print(SPACE);
	}

	public static boolean isAnswerCorrect(Question q, String answer) {
		char c = Character.toLowerCase(answer.charAt(0));

		if(c < 'a' || c > 'd') {
			System.out.println("Please enter A, B, C or D (case insensitive)");
			System.out.print(getAnswer);
			return isAnswerCorrect(q, scan.nextLine());
		}

		if(Character.toLowerCase(q.getRightAnswerSymbol()) == c) {
			return true;
		} else {
			return false;
		}
	}
}