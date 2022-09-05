package quiz;

import java.util.ArrayList;

import enums.AnswerStatus;

/**
 * Stores the word for each question in the quiz. Also checks if a user's answer
 * has the same spelling as the word stored.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class Question {
	private String word;
	private int attemptNumber;

	/**
	 * Creates a new Question object with the word to test the user's answer on.
	 *
	 * @param word A string containing the word.
	 */
	public Question(String word) {
		this.word = word;
		attemptNumber = 1;
	}

	/**
	 * Returns one of the four possible states in this application that indicates if
	 * the user's answer is correct and on which attempt it occurred.
	 *
	 * @param answer A string containing the user's answer.
	 * @return An AnswerStatus enum that indicates the state of the user's answer.
	 */
	public AnswerStatus checkAnswer(String answer) {
		String wordSanitised = word.strip();
		String answerSanitised = answer.strip();

		if (wordSanitised.equalsIgnoreCase(answerSanitised)) {
			return determineAnswerStatusIfCorrect();
		} else {
			attemptNumber++;
			return determineAnswerStatusIfIncorrect();
		}
	}

	/**
	 * Returns the word associated with the question.
	 *
	 * @return A string containing the word.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Returns the letter of the word associated with the question at the specified
	 * index .
	 *
	 * @return A char containing the letter at the specified index of the word.
	 */
	public char getLetter(int index) {
		return word.charAt(index);
	}

	/**
	 * Returns the number of characters in the word associated with the question.
	 *
	 * @return An integer indicating the number of characters in the word.
	 */
	public int getNumberOfCharacters() {
		return word.length();
	}

	/**
	 * Returns the number of letters in the word associated with the question.
	 *
	 * @return An integer indicating the number of letters in the word.
	 */
	public int getNumberOfLetters() {
		ArrayList<Integer> spaceIndices = getSpacePositions();
		int numberOfSpaces = spaceIndices.size();
		return word.length() - numberOfSpaces;
	}

	/**
	 * Returns the positions of spaces in the word associated with the question.
	 * Zero-based. If there are no spaces found, it returns an empty array list.
	 *
	 * @return An ArrayList of integers containing the positions of the spaces in
	 *         the word.
	 */
	public ArrayList<Integer> getSpacePositions() {
		ArrayList<Integer> spacePositions = new ArrayList<Integer>();
		int wordLength = word.length();
		for (int i = 0; i < wordLength; i++) {
			if (word.charAt(i) == ' ') {
				spacePositions.add(i);
			}
		}
		return spacePositions;
	}

	/**
	 * Returns true if the user is at their second attempt of the question, or false
	 * otherwise.
	 *
	 * @return A boolean that indicates whether the user is on their second attempt.
	 */
	public boolean isSecondAttempt() {
		return (attemptNumber == 2);
	}

	/**
	 * Private helper method that determines the user's answer state, assuming that
	 * their answer was correct.
	 *
	 * @return An AnswerStatus enum that indicates the state of the user's answer.
	 */
	private AnswerStatus determineAnswerStatusIfCorrect() {
		switch (attemptNumber) {
		case (2):
			return AnswerStatus.FAULTED;
		default:
			return AnswerStatus.MASTERED;
		}
	}

	/**
	 * Private helper method that determines the user's answer state, assuming that
	 * their answer was incorrect.
	 *
	 * @return An AnswerStatus enum that indicates the state of the user's answer.
	 */
	private AnswerStatus determineAnswerStatusIfIncorrect() {
		switch (attemptNumber) {
		case (3):
			return AnswerStatus.FAILED;
		default:
			return AnswerStatus.INCORRECT;
		}
	}
}
