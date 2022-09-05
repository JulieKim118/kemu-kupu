package fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import quiz.WordStore;

public class WordFileReader {

	/**
	 * Loads the lines in the target file onto the object.
	 *
	 * @throws FileNotFoundException Exception thrown if path to target file does
	 *                               not exist, or if the file itself does not
	 *                               exist.
	 */
	public WordStore readLines(String fileName) throws FileNotFoundException {
		// Initialise variable for words
		String word = " ";
		String filePath = "wordlist/" + fileName + ".txt";
		WordStore wordStore = new WordStore();

		// find and scan a file by using it's file name that has been in put.
		Scanner sc = new Scanner(new File(filePath));

		// If a line contains a word, add word to the list.
		while (sc.hasNextLine()) {
			word = sc.nextLine();
			wordStore.addWord(word.strip());
		}
		sc.close();
		return wordStore;
	}
}
