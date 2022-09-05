package fileio;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Reads from and writes to the file about the user's highest scores obtained
 * from Games Module quizzes.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class HighestEarningsIO extends FileIO {
	private int numberOfEarningsToRecord;

	/**
	 * Creates a HighestEarningsIO object with the filepath to the file to modify.
	 *
	 * @param filepath A String object containing the filepath to the file that the
	 *                 object deals with.
	 */
	public HighestEarningsIO(String filepath) {
		super(filepath);
		numberOfEarningsToRecord = 5;
	}

	/**
	 * Records the total score earned for the current quiz. Note that this is only
	 * successful if the total score earned is already higher than the existing
	 * ones.
	 *
	 * @param totalScoreForQuiz An integer representing the total score earned for
	 *                          the current quiz.
	 */
	public void recordQuizEarning(int totalScoreForQuiz) {
		ArrayList<Integer> topQuizScores = getExistingTopQuizEarnings();
		topQuizScores.add(totalScoreForQuiz);
		Collections.sort(topQuizScores);
		topQuizScores.remove(0);
		Collections.reverse(topQuizScores);
		saveTopQuizEarnings(topQuizScores);
	}

	/**
	 * Returns an ArrayList of the top quiz earnings currently saved in the file.
	 *
	 * @return An ArrayList containing integers, with each representing a top score
	 *         obtained in a quiz.
	 */
	private ArrayList<Integer> getExistingTopQuizEarnings() {
		ArrayList<String> existingTotalScoreStrings = readLines();
		ArrayList<Integer> existingTopQuizScores = new ArrayList<Integer>();
		int numberOfScores = existingTotalScoreStrings.size();
		for (int i = 0; i < numberOfScores; i++) {
			String totalScoreString = existingTotalScoreStrings.get(i);
			int totalScore = Integer.valueOf(totalScoreString);
			existingTopQuizScores.add(totalScore);
		}
		return existingTopQuizScores;
	}

	/**
	 * Writes the top scores earned in Games Module quizzes to the file.
	 *
	 * @param sortedTopQuizScores An ArrayList containing integers, with each
	 *                            representing the top scores earned from the
	 *                            quizzes.
	 */
	private void saveTopQuizEarnings(ArrayList<Integer> sortedTopQuizScores) {
		clearFile();
		int numberOfScores = sortedTopQuizScores.size();
		for (int i = 0; i < numberOfScores; i++) {
			int score = sortedTopQuizScores.get(i);
			String scoreString = String.valueOf(score);
			appendLine(scoreString);
		}
	}

	/**
	 * Returns the top scores from Games Module quizzes, each as a String object.
	 *
	 * @return An ArrayList containing String objects, with each representing a top
	 *         score earned in a quiz.
	 */
	public ArrayList<String> getHighestQuizEarningsAsStrings() {
		return readLines();
	}

	/**
	 * Resets all highest earnings in quizzes to zero.
	 */
	public void resetHighestEarnings() {
		ArrayList<String> lines = new ArrayList<String>();
		for (int i = 0; i < numberOfEarningsToRecord; i++) {
			lines.add("0");
		}
		overwriteLines(lines);
	}
}
