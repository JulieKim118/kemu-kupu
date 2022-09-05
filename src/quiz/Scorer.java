package quiz;

/**
 * Calculates the score of for a particular word/question in the spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class Scorer {
	private String word;
	private long startTime;
	private long endTime;
	private double wordLengthMultiplier;
	private int averageWordLength;
	private double highBonusReward;
	private double lowBonusReward;
	private double noBonusReward;
	private long highBonusTimeDuration;
	private long lowBonusTimeDuration;

	/**
	 * Creates a Scorer object, taking in the word to base the score on.
	 *
	 * @param word A string containing the word.
	 */
	public Scorer(String word) {
		this.word = word;
		averageWordLength = 10;
		highBonusReward = 300;
		lowBonusReward = 100;
		noBonusReward = 50;

		setWordLengthMultiplier();
		setBonusTimes();
		setRewardScheme();
	}

	/**
	 * Private helper method that adjusts the multiplier to the score to give, based
	 * on the length of the word that this Scorer object is testing on.
	 */
	private void setWordLengthMultiplier() {
		wordLengthMultiplier = (double) word.length() / averageWordLength;
	}

	/**
	 * Private helper method that determines the duration to wait for obtaining
	 * bonuses from the user, based on the length of the word that this Scorer
	 * object is testing on. The rationale for this private method is that longer
	 * words need more time to type, and vice versa.
	 */
	private void setBonusTimes() {
		highBonusTimeDuration = Long.max((long) (10 * ((double) word.length() / averageWordLength)), 6);
		lowBonusTimeDuration = highBonusTimeDuration * 3;
	}

	/**
	 * Private helper method that adjusts the amount of score to reward based on the
	 * multiplier derived from the length of the word.
	 */
	private void setRewardScheme() {
		highBonusReward *= wordLengthMultiplier;
		lowBonusReward *= wordLengthMultiplier;
		noBonusReward *= wordLengthMultiplier;
	}

	/**
	 * Returns the amount of score that the user can obtain, assuming that they get
	 * the high bonus.
	 *
	 * @return An integer containing the amount of score with high bonus.
	 */
	public int getHighBonusReward() {
		return (int) highBonusReward;
	}

	/**
	 * Returns the amount of score that the user can obtain, assuming that they get
	 * the low bonus.
	 *
	 * @return An integer containing the amount of score with low bonus.
	 */
	public int getLowBonusReward() {
		return (int) lowBonusReward;
	}

	/**
	 * Returns the amount of score that the user can obtain, assuming that they get
	 * the no bonus.
	 *
	 * @return An integer containing the amount of score with no bonus.
	 */
	public int getNoBonusReward() {
		return (int) noBonusReward;
	}

	/**
	 * Returns the amount of time that the user has to finish in to be eligible for
	 * the high bonus.
	 *
	 * @return A long containing the number of seconds for the amount of time.
	 */
	public long getHighBonusTimeDuration() {
		return highBonusTimeDuration;
	}

	/**
	 * Returns the amount of time that the user has to finish in to be eligible for
	 * the low bonus. Note that this number includes the amount of time required to
	 * be eligible for the high bonus reward as well!
	 *
	 * @return A long containing the number of seconds for the amount of time.
	 */
	public long getLowBonusTimeDuration() {
		return lowBonusTimeDuration;
	}

	/**
	 * Tells the Scorer object to start timing how long the user takes to answer a
	 * question at this point.
	 */
	public void startTiming() {
		startTime = System.currentTimeMillis() / 1000L;
	}

	/**
	 * Tells the Scorer object to end timing how long the user takes to answer a
	 * question at this point.
	 */
	public void endTiming() {
		endTime = System.currentTimeMillis() / 1000L;
	}

	/**
	 * Returns the final score to reward to the user, based on their time taken and
	 * the length of the word being tested on the user, where the final score is the
	 * score to be obtained if the user mastered the word.
	 *
	 * @return An integer indicating the score (assuming mastered).
	 */
	public int getScore() {
		long timeTaken = endTime - startTime;
		if (timeTaken < highBonusTimeDuration) {
			return (int) highBonusReward;
		} else if (timeTaken < lowBonusTimeDuration) {
			return (int) lowBonusReward;
		} else {
			return (int) noBonusReward;
		}
	}

	/**
	 * Returns the final score to reward to the user, based on their time taken and
	 * the length of the word being tested on the user, where the final score is the
	 * score to be obtained if the user faulted the word.
	 *
	 * @return An integer indicating the score (assuming faulted).
	 */
	public int getFaultedScore() {
		return (int) (0.5 * getScore());
	}
}
