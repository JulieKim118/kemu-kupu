package fileio;

import java.util.ArrayList;

import application.Cash;

/**
 * Reads from and writes to the file about the in-game cash.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class CashIO extends FileIO {

	/**
	 * Creates a CashIO object with the filepath to the file to modify.
	 *
	 * @param filepath A String object containing the filepath to the file that the
	 *                 object deals with.
	 */
	public CashIO(String filepath) {
		super(filepath);
	}

	/**
	 * Records the amount of cash in-game to the file.
	 *
	 * @param cash The Cash object to save.
	 */
	public void saveCash(Cash cash) {
		int amount = cash.getCash();
		String amountString = String.valueOf(amount);
		ArrayList<String> lines = new ArrayList<>();
		lines.add(amountString);
		overwriteLines(lines);
	}

	/**
	 * Retrieves the amount of cash in-game frmo the file.
	 *
	 * @return A Cash object to be used in-game.
	 */
	public Cash loadCash() {
		Cash cash = new Cash();
		ArrayList<String> lines = readLines();
		String amountString = lines.get(0);
		int amount = Integer.valueOf(amountString);
		cash.setCash(amount);
		return cash;
	}

	/**
	 * Resets the cash back to zero in the file.
	 */
	public void resetCash() {
		ArrayList<String> lines = new ArrayList<>();
		lines.add("0");
		overwriteLines(lines);
	}
}
