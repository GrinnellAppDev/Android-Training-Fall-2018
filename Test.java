import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.Character;


public class Test {


	public static void main(String[] args) {
		String SPACE = "\n";
		String getAnswer = "Enter answer here: ";

		Scanner scan = new Scanner(System.in);
		System.out.print("Are you ready to start the quiz? If yes, press ENTER");
		scan.nextLine();

		System.out.println(SPACE);

		BufferedReader br = null;

		try {
			   br = new BufferedReader(new FileReader("questions.txt"));
			} catch (FileNotFoundException e) {
			System.out.println("File containing questions not found");
		System.exit(1);
	}


		while(true) {
	
			try {
			 String question = br.readLine();

			 if(question != null) {
			 String answer1 = br.readLine();
			 String answer2 = br.readLine();
			 String answer3 = br.readLine();
			 String answer4 = br.readLine();
			 int correctAnswer = Integer.parseInt(br.readLine());
			 String topicsToReview = br.readLine();

			 Question q = new Question();
			 q.setQuestion(question);
			 q.addAnswer(answer1);
			 q.addAnswer(answer2);
			 q.addAnswer(answer3);
			 q.addAnswer(answer4);
			 q.addAnswerIndex(correctAnswer);
			 q.addTopicToReview(topicsToReview);

			 q.printQuestion();
			 q.printAnswers();
			 System.out.print(getAnswer);

			 isAnswerCorrect(q, scan.nextLine());

			} else {
				break;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong when reading file");
			System.exit(1);
		}		
	}

			System.out.println("Thanks for working on the mini quiz :)!");
		
	}

	public static boolean isAnswerCorrect(Question q, String answer) {
		char c = answer.charAt(0);

		if(Character.toLowerCase(q.getRightAnswerSymbol()) == Character.toLowerCase(c)) {
			System.out.println(q.getRightAnswerSymbol());
			System.out.println("correctAnswer");
			return true;
		} else {
			System.out.println(q.getRightAnswerSymbol());
			System.out.print("WrongAnswer, please review these topics: ");
			System.out.println(q.topicsToReview);
			return false;
		}

	}

	public static class Question {

		private String question;
		private ArrayList<String> answers = new ArrayList<String>();
		private int answer_index;
		private ArrayList<String> topicsToReview = new ArrayList<String>();

		public  Question() {
		}

		//Setter methods
		public void setQuestion(String s) {
			this.question = s;
		}

		public void addAnswer(String s) {
			this.answers.add(s);
		}

		public void addAnswerIndex(int x) {
			this.answer_index = x;
		}

		public void addTopicToReview(String topic) {
			this.topicsToReview.add(topic);
		}

		public int getRightAnswerIndex() {
			return answer_index;
		}

		public char getRightAnswerSymbol() {
			return ((char) (65 + answer_index));
		}

		//Getter methods
		public String getQuestion() {
			return this.question;
		}

		public void printQuestion() {
			System.out.println(this.question);
		}

		public void printAnswers() {
			int startLetter = 65;
			for(int i = 0; i < answers.size(); i++) {
				System.out.print(((char) (startLetter + i) + " "));
				System.out.println(this.answers.get(i));
			}
		}

		public void printAnswerOption(int x) {
			System.out.println(this.answers.get(x));
		}

		public void printRightAnswer() {
			int startLetter = 65;
			System.out.print(((char) startLetter + answer_index) + " ");
			System.out.println(this.answers.get(answer_index));
		}

		public void printTopicsToReview() {
			for(int i = 0; i < topicsToReview.size(); i++) {
				System.out.print(this.topicsToReview.get(i) + ", ");
			}
		}
	}
}