package quiz;

import enums.AnswerStatus;

/**
 * Keeps track of the answer status of words in a spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class AnswerStatusTracker {
	private AnswerStatus[] answerStatuses;
	private int maximumNumberOfQuestions;

	/**
	 * Creates an AnswerStatusTracker object that takes in number of questions in
	 * the quiz.
	 *
	 * @param maximumNumberOfQuestions An integer that indicates the number of
	 *                                 questions in the quiz.
	 */
	public AnswerStatusTracker(int maximumNumberOfQuestions) {
		answerStatuses = new AnswerStatus[maximumNumberOfQuestions];
	}

	/**
	 * Updates the AnswerStatusTracker object with the answer status of a particular
	 * question at a specific position in the quiz.
	 *
	 * @param questionNumber An integer that indicates the position of the question
	 *                       in the quiz, where 1 is the first question.
	 * @param answerStatus   An AnswerStatus enumeration that indicates the answer
	 *                       status of the question.
	 */
	public void update(int questionNumber, AnswerStatus answerStatus) {
		answerStatuses[questionNumber - 1] = answerStatus;
	}

	/**
	 * Returns the answer status of a particular question at a specific position in
	 * the quiz.
	 *
	 * @param questionNumber An integer that indicates the position of a question in
	 *                       the quiz, where 1 is the first question.
	 * @return An AnswerStatus enumeration indicating the answer status of the
	 *         question found in the position indicated by the question number.
	 */
	public AnswerStatus getAnswerStatus(int questionNumber) {
		return answerStatuses[questionNumber - 1];
	}
}
