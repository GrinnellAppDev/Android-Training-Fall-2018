import java.io.*;
import java.util.*;
import java.lang.Character;

public class Question {

	private String question;
	private ArrayList<String> answers = new ArrayList<String>();
	private int answer_index;
	private ArrayList<String> topicsToReview = new ArrayList<String>();
	private String explanation;
	
		public  Question() {
		}

		//Setter methods
		public void setQuestion(String s) {
			this.question = s;
		}

		public void setExplanation(String s) {
			this.explanation = s;
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

		public String getExplanation() {
			return this.explanation;
		}

		public void printExplanation() {
			System.out.println("Explanation: " + this.explanation);
		}

		public void printQuestion() {
			System.out.println( this.question);
		}

		public void printAnswers() {
			int startLetter = 65;
			for(int i = 0; i < answers.size(); i++) {
				System.out.print( ((char) (startLetter + i) + ". "));
				System.out.println( this.answers.get(i));
			}
		}

		public void printAnswerOption(int x) {
			System.out.println(this.answers.get(x));
		}

		public void printRightAnswer() {
			int startLetter = 65;
			System.out.print(((char) (startLetter + this.answer_index)) + ") ");
			System.out.println(this.answers.get(answer_index));
		}

		public void printTopicsToReview() {
			for(int i = 0; i < topicsToReview.size(); i++) {
				System.out.print( this.topicsToReview.get(i) + ", ");
			}
		}
}

