package quiz;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides for and administers the questions to be asked in the spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class QuestionManager {
	private ArrayList<String> words;
	private int maximumNumberOfQuestions;
	private int questionNumber;

	/**
	 * Creates a Quiz object containing the words to ask the user on for the
	 * spelling quiz.
	 *
	 * @param words A Collection object that contains String objects, where each
	 *              String object contains the word.
	 */
	public QuestionManager(Collection<? extends String> words) {
		this.words = new ArrayList<>();
		this.words.addAll(words);
		this.maximumNumberOfQuestions = words.size();
		questionNumber = 0;
	}

	/**
	 * Returns true when there is another question that can be asked, or false
	 * otherwise.
	 *
	 * @return A Boolean that determines if there is another question to ask.
	 */
	public Boolean hasNextQuestion() {
		return (questionNumber < maximumNumberOfQuestions);
	}

	/**
	 * Returns the next question to ask the user on.
	 *
	 * @return A Question object that contains the word to test the user on.
	 */
	public Question getNextQuestion() {
		if (hasNextQuestion()) {
			String word = words.get(questionNumber);
			Question question = new Question(word);
			questionNumber++;
			return question;
		} else {
			return null;
		}
	}

	/**
	 * Returns the position of the question currently being asked, where 1 is the
	 * position of the first question.
	 *
	 * @return An integer that indicates the position of the question.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Returns the total number of questions that can be asked from the current
	 * quiz. Also equivalent to the number of words stored in the quiz.
	 *
	 * @return An integer that indicates the total number of questions taht can be
	 *         asked.
	 */
	public int getTotalNumberOfQuestions() {
		return maximumNumberOfQuestions;
	}
}
