package statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class VocabularyStatistics {
	private HashSet<String> words;
	private HashMap<String, Integer> masteredStatistics;
	private HashMap<String, Integer> faultedStatistics;
	private HashMap<String, Integer> failedStatistics;

	public VocabularyStatistics() {
		words = new HashSet<>();
		masteredStatistics = new HashMap<>();
		faultedStatistics = new HashMap<>();
		failedStatistics = new HashMap<>();
	}

	public ArrayList<String> getWords() {
		ArrayList<String> wordList = new ArrayList<>();
		for (String word : words) {
			wordList.add(word);
		}
		return wordList;
	}

	public int getMastered(String word) {
		return getSpecificStatistic(word, masteredStatistics);
	}

	public int getFaulted(String word) {
		return getSpecificStatistic(word, faultedStatistics);
	}

	public int getFailed(String word) {
		return getSpecificStatistic(word, failedStatistics);
	}

	private int getSpecificStatistic(String word, HashMap<String, Integer> statistics) {
		if (statistics.containsKey(word)) {
			return statistics.get(word);
		} else {
			return 0;
		}
	}

	public void incrementMastered(String word) {
		incrementSpecificStatistic(word, masteredStatistics);
	}

	public void incrementFaulted(String word) {
		incrementSpecificStatistic(word, faultedStatistics);
	}

	public void incrementFailed(String word) {
		incrementSpecificStatistic(word, failedStatistics);
	}

	private void incrementSpecificStatistic(String word, HashMap<String, Integer> statistics) {
		words.add(word);
		if (statistics.containsKey(word)) {
			addToExistingStatisticsForWord(word, statistics);
		} else {
			createNewStatisticsForWord(word, statistics);
		}
	}

	private void addToExistingStatisticsForWord(String word, HashMap<String, Integer> statistics) {
		int wordStatistic = statistics.get(word);
		wordStatistic++;
		statistics.put(word, wordStatistic);
	}

	private void createNewStatisticsForWord(String word, HashMap<String, Integer> statistics) {
		statistics.put(word, 1);
	}
}
