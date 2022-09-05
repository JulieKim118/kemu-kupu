package fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads from and writes to files in the operating system.
 *
 * @author Jared Daniel Recomendable
 *
 */
public abstract class FileIO {
	protected String filepath;

	/**
	 * Creates a FileIO object with the filepath to the file to modify.
	 *
	 * @param filepath A String object containing the filepath to the file that the
	 *                 object deals with.
	 */
	protected FileIO(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Returns the lines read from the file, stripped of whitespaces at the edges.
	 *
	 * @return An ArrayList object storing Strings, where each String contains one
	 *         line in the file. The Strings are in the same sequence as the lines
	 *         in the file.
	 */
	protected ArrayList<String> readLines() {
		ArrayList<String> lines = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File(filepath));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().strip();
				lines.add(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
//			System.err.println("File not found!");
//			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * Writes to lines in the file, overwriting the existing content in the file.
	 *
	 * @param lines An ArrayList object storing Strings, where each String contains
	 *              one line in the file. The Strings are in the same sequence as
	 *              the lines in the file.
	 */
	protected void overwriteLines(ArrayList<String> lines) {
		writeLines(lines, false);
	}

	/**
	 * Writes to lines in the file, writing after existing content in the file.
	 *
	 * @param lines An ArrayList object storing Strings, where each String contains
	 *              one line in the file. The Strings are in the same sequence as
	 *              the lines in the file.
	 */
	protected void appendLines(ArrayList<String> lines) {
		writeLines(lines, true);
	}

	/**
	 * Writes a single line in the file, writing after existing content in the file.
	 *
	 * @param lines A String object containing the line to add to the end of the
	 *              file.
	 */
	protected void appendLine(String line) {
		ArrayList<String> lines = new ArrayList<>();
		lines.add(line);
		writeLines(lines, true);
	}

	/**
	 * Writes to lines in the file, overwriting or appending to the content in the
	 * file depending on the specified mode.
	 *
	 * @param lines      An ArrayList object storing Strings, where each String
	 *                   contains one line in the file. The Strings are in the same
	 *                   sequence as the lines in the file.
	 * @param appendMode A boolean that is true if the user intends to append to the
	 *                   file, or false if the user intends to overwrite the file.
	 */
	private void writeLines(ArrayList<String> lines, boolean appendMode) {
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(filepath, appendMode));
			for (String line : lines) {
				String formatted = line.strip();
				printWriter.println(formatted);
			}
			printWriter.close();
		} catch (IOException e) {
//			System.err.println("Cannot write to specified filepath.");
//			System.err.println("Please contact the developers.");
//			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the String at a particular position within a line when split into
	 * Strings by spaces.
	 *
	 * @param line  A String containing the line to split.
	 * @param index The position of the String we intend to get. Begins at zero for
	 *              the first element.
	 * @return A String containing the target item to obtain.
	 */
	protected String getStringPartFromLine(String line, int index) {
		String formatted = line.strip();
		String[] splitString = formatted.split("\\s+");
		return splitString[index];
	}

	/**
	 * Causes the file to be blank.
	 */
	protected void clearFile() {
		ArrayList<String> lines = new ArrayList<>();
		overwriteLines(lines);
	}
}
