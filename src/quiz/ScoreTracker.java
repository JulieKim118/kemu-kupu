package quiz;

/**
 * Keeps track of the scores and the words per question in a spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class ScoreTracker {
	private int[] scores;
	private String[] words;
	private int maximumNumberOfQuestions;

	/**
	 * Creates a ScoreTracker object that takes in the number of question in the
	 * quiz.
	 *
	 * @param maximumNumberOfQuestions An integer that indicates the number of
	 *                                 questions in the quiz.
	 */
	public ScoreTracker(int maximumNumberOfQuestions) {
		this.maximumNumberOfQuestions = maximumNumberOfQuestions;
		scores = new int[maximumNumberOfQuestions];
		words = new String[maximumNumberOfQuestions];
	}

	/**
	 * Updates the ScoreTracker object with the score and the word for a particular
	 * question at a specific position in the quiz.
	 *
	 * @param questionNumber An integer that indicates the position of the question
	 *                       in the quiz, where 1 is the first question.
	 * @param score          An integer that indicates the score.
	 * @param word           An String that contains the word.
	 */
	public void update(int questionNumber, int score, String word) {
		scores[questionNumber - 1] = score;
		words[questionNumber - 1] = word;
	}

	/**
	 * Returns the score of the user for a specific position in the quiz.
	 *
	 * @param questionNumber An integer that indicates the position of a question in
	 *                       the quiz, where 1 is the first question.
	 * @return An integer containing the score of the user for a particular question
	 *         in the quiz.
	 */
	public int getScore(int questionNumber) {
		return scores[questionNumber - 1];
	}

	/**
	 * Returns the total score of the user up to a specific position in the quiz,
	 * beginning from the first question.
	 *
	 * @param questionNumber An integer that indicates the position of a question in
	 *                       the quiz, where 1 is the first question.
	 * @return An integer indicating the total score of the user up to a particular
	 *         question in the quiz.
	 */
	public int getCumulativeScore(int questionNumber) {
		int i;
		int cumulativeScore = 0;
		for (i = 1; i <= questionNumber; i++) {
			cumulativeScore += getScore(i);
		}
		return cumulativeScore;
	}

	/**
	 * Returns the word tested at a particular position in the quiz.
	 *
	 * @param questionNumber An integer that indicates the position of a question in
	 *                       the quiz, where 1 is the first question.
	 * @return A String containing the word tested at a particular position in the
	 *         quiz.
	 */
	public String getWord(int questionNumber) {
		return words[questionNumber - 1];
	}

	/**
	 * Returns the total score of the user for the entire quiz.
	 *
	 * @return An integer indicating the total score of the user for the entire
	 *         quiz.
	 */
	public int getTotalScore() {
		int totalScore = 0;
		for (int i = 0; i < scores.length; i++) {
			totalScore += scores[i];
		}
		return totalScore;
	}
}
