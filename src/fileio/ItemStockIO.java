package fileio;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import tree.Item;
import tree.ItemStock;

/**
 * Reads from and writes to the file about the stock of items available, listing
 * their quantities.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class ItemStockIO extends FileIO {

	/**
	 * Creates an ItemStockIO object with the filepath to the file to modify.
	 *
	 * @param filepath A String object containing the filepath to the file that the
	 *                 object deals with.
	 */
	public ItemStockIO(String filepath) {
		super(filepath);
	}

	/**
	 * Saves the quantities of items in the stock to file.
	 *
	 * @param stock
	 * @param dateTime
	 */
	public void saveStockNumbers(ItemStock stock, OffsetDateTime dateTime) {
		ArrayList<Item> items = stock.getItems();
		saveDateTime(dateTime);
		for (Item item : items) {
			saveStockNumber(item, stock);
		}
	}

	/**
	 * Writes the date and time passed in to the beginning of the file, deleting
	 * previous content.
	 *
	 * @param dateTime An OffsetDateTime object containing information about the
	 *                 date and time to be saved.
	 */
	private void saveDateTime(OffsetDateTime dateTime) {
		String dateTimeString = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		ArrayList<String> lines = new ArrayList<>();
		lines.add(dateTimeString);
		overwriteLines(lines);
	}

	/**
	 * Writes information about each item and their stock qunatity to file.
	 *
	 * @param item  An Item object that will be recorded in the file.
	 * @param stock An ItemStock object which contains quantity information about
	 *              Item objects.
	 */
	private void saveStockNumber(Item item, ItemStock stock) {
		String itemName = item.getName();
		int stockNumber = stock.getQuantity(item);
		String stockNumberString = String.valueOf(stockNumber);
		String line = itemName + " " + stockNumberString;
		appendLine(line);
	}

	/**
	 * Get the date and time saved in file.
	 *
	 * @return An OffsetDateTime object based on the date and time retrieved from
	 *         the file.
	 */
	public OffsetDateTime getDateTimeSaved() {
		ArrayList<String> lines = readLines();
		String dateTimeString = lines.get(0);
		OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString);
		return dateTime;
	}

	/**
	 * Retrieves information about item names and their stock quantity.
	 *
	 * @return A Map object with the String as the key and an Integer as the value.
	 *         The key contains the name of the item. The value represents the stock
	 *         quantity of said item.
	 */
	public Map<String, Integer> loadStockNumbers() {
		Map<String, Integer> stockNumbers = new LinkedHashMap<>();
		ArrayList<String> stockRecords = readLines();
		stockRecords.remove(0);
		for (String stockRecord : stockRecords) {
			loadStockNumber(stockRecord, stockNumbers);
		}
		return stockNumbers;
	}

	/**
	 * Puts information about an item and its associated stock quantity onto the
	 * stockNumbers Map object that was passed in as input.
	 *
	 * @param stockRecord  A line in the file containing information about an item
	 *                     and its stock quantity.
	 * @param stockNumbers The Map object to write to regarding the item name and
	 *                     its stock quantity.
	 */
	private void loadStockNumber(String stockRecord, Map<String, Integer> stockNumbers) {
		String stockName = getStringPartFromLine(stockRecord, 0);
		String stockNumberString = getStringPartFromLine(stockRecord, 1);
		int stockNumber = Integer.valueOf(stockNumberString);
		stockNumbers.put(stockName, stockNumber);
	}

	/**
	 * Remove all items and the date and time information from the file.
	 */
	public void resetItemStock() {
		clearFile();
	}
}
