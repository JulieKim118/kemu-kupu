package quiz;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Stores WordStore objects that can be used for testing on the spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class WordStoreManager {
	private ArrayList<WordStore> wordStores;

	/**
	 * Creates a WordStoreManager object.
	 */
	public WordStoreManager() {
		wordStores = new ArrayList<>();
	}

	/**
	 * Adds a WordStore object to the WordStoreManager object.
	 *
	 * @param wordStore
	 */
	public void addWordList(WordStore wordStore) {
		wordStores.add(wordStore);
	}

	/**
	 * Removes a WordStore object from the WordStoreManager object.
	 *
	 * @param wordStore
	 */
	public void removeWordList(WordStore wordStore) {
		wordStores.remove(wordStore);
	}

	/**
	 * Returns a single WordStore object containing all words from the stored
	 * WordStore objects.
	 *
	 * @return
	 */
	public WordStore getCombinedWords() {
		WordStore combinedWordList = new WordStore();
		combineWordLists(combinedWordList);
		return combinedWordList;
	}

	/**
	 * Private helper method that iterates through the WordStoreManager object for
	 * WordStore objects.
	 *
	 * @param combinedWordList The WordStore object used to store all words from the
	 *                         stored WordStore objects.
	 */
	private void combineWordLists(WordStore combinedWordList) {
		for (WordStore wordStore : wordStores) {
			addWordsToCombinedWordList(wordStore, combinedWordList);
		}
	}

	/**
	 * Private helper method that iterates through a WorddList object for words.
	 *
	 * @param wordStore         The WordStore object to iterate through.
	 * @param combinedWordList The WordStore object used to store all words from the
	 *                         stored WordStore objects.
	 */
	private void addWordsToCombinedWordList(WordStore wordStore, WordStore combinedWordList) {
		HashSet<String> words = wordStore.getAllWords();
		for (String word : words) {
			combinedWordList.addWord(word);
		}
	}
}
