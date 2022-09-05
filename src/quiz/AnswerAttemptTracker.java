package quiz;

/**
 * Keeps track of the attempted spellings of words in a spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class AnswerAttemptTracker {
	private String[] answerAttempts;
	private int maximumNumberOfQuestions;

	/**
	 * Creates an AnswerAttemptTracker object that takes in number of questions in
	 * the quiz.
	 *
	 * @param maximumNumberOfQuestions An integer that indicates the number of
	 *                                 questions in the quiz.
	 */
	public AnswerAttemptTracker(int maximumNumberOfQuestions) {
		answerAttempts = new String[maximumNumberOfQuestions];
	}

	/**
	 * Updates the AnswerAttemptTracker object with the attempt by the user of the
	 * word for a particular question at a specific position in the quiz.
	 *
	 * @param questionNumber An integer that indicates the position of the question
	 *                       in the quiz, where 1 is the first question.
	 * @param answerAttempt  A String that contains the attempted user spelling of
	 *                       the word.
	 */
	public void update(int questionNumber, String answerAttempt) {
		answerAttempts[questionNumber - 1] = answerAttempt;
	}

	/**
	 * Returns the attempted user spelling of the word for a particular question at
	 * a specific position in the quiz.
	 *
	 * @param questionNumber An integer that indicates the position of a question in
	 *                       the quiz, where 1 is the first question.
	 * @return A Sstring containing the attempted user spelling attempt of the word
	 *         of the question found in the position indicated by the question
	 *         number.
	 */
	public String getAnswerAttempt(int questionNumber) {
		return answerAttempts[questionNumber - 1];
	}
}
