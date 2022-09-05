package quiz;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Stores words that can be used for testing in the spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class WordStore {
	private HashSet<String> wordList;

	/**
	 * Creates a WordLists object.
	 */
	public WordStore() {
		wordList = new HashSet<String>();
	}

	/**
	 * Adds a word to the WordStore object.
	 *
	 * @param word A string containing the word to add.
	 */
	public void addWord(String word) {
		wordList.add(word);
	}

	/**
	 * Returns all words contained within the WordStore object.
	 *
	 * @return A HashSet containing Strings, where each String object contains a
	 *         word in the WordStore object.
	 */
	public HashSet<String> getAllWords() {
		return wordList;
	}

	/**
	 * Returns a particular number of words, chosen at random from the WordStore
	 * object.
	 *
	 * @param count An integer containing the number of words chosen at random to
	 *              return.
	 * @return A HashSet containing Strings, where each String object contains a
	 *         word in the WordStore object.
	 */
	public HashSet<String> getRandomWords(int count) {
		HashSet<String> selection = new HashSet<>();
		count = Integer.min(count, wordList.size());
		while (selection.size() != count) {
			selection.add(getRandomWord());
		}
		return selection;
	}

	/**
	 * Private helper method that returns one word chosen at random from the
	 * WordStore object.
	 *
	 * @return A String containing the word that has been chosen at random.
	 */
	private String getRandomWord() {
		String selection = null;
		int selectedIndex = getRandomInteger(wordList.size());
		int currentIndex = 0;

		Iterator<? extends String> iterator = wordList.iterator();
		while (iterator.hasNext()) {
			selection = iterator.next();
			if (currentIndex == selectedIndex) {
				return selection;
			}
			currentIndex++;
		}

		return selection;
	}

	/**
	 * Private helper method that returns a random integer, with the input bound
	 * being the upper limit exclusive.
	 *
	 * @param bound An integer representing the upper bound exclusive.
	 * @return An integer representing the random integer selected.
	 */
	private int getRandomInteger(int bound) {
		Random random = new Random();
		return random.nextInt(bound);
	}
}
