package tree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores how many of a particular item there is available.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class ItemStock {
	private HashMap<Item, Integer> stock;

	/**
	 * Creates an ItemStock object.
	 */
	public ItemStock() {
		stock = new HashMap<>();
	}

	/**
	 * Returns true when the item is in stock, or false otherwise.
	 *
	 * @param item An Item object used to determine whether it is in stock or not.
	 * @return A boolean representing whether the item is in stock or not.
	 */
	public boolean isInStock(Item item) {
		return stock.containsKey(item);
	}

	/**
	 * Returns a list of all items available in the store.
	 *
	 * @return An ArrayList of items, each wielding properties about the item in
	 *         question.
	 */
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<>();
		for (Item item : stock.keySet()) {
			items.add(item);
		}
		return items;
	}

	/**
	 * Counts how many of an item there are in stock.
	 *
	 * @param item An Item object used to determine its stock quantity in the store.
	 * @return An integer representing how many there is of the input item in the
	 *         store.
	 */
	public int getQuantity(Item item) {
		return stock.get(item);
	}

	/**
	 * Adds an item to the store.
	 *
	 * @param item An Item object to be added to the store.
	 */
	public void addItem(Item item) {
		addItem(item, 1);
	}

	/**
	 * Adds a particular number of an item to the store.
	 *
	 * @param item     An Item object to be added to the store.
	 * @param quantity An integer representing how many of such items are to be
	 *                 added to the store.
	 */
	public void addItem(Item item, int quantity) {
		if (stock.containsKey(item)) {
			stock.put(item, quantity + stock.get(item));
		} else {
			stock.put(item, quantity);
		}
	}

	/**
	 * Remove an item from the store.
	 *
	 * @param item An Item object to be removed from the store.
	 */
	public void removeItem(Item item) {
		removeItem(item, 1);
	}

	/**
	 * Removes a particular number of an item from the store.
	 *
	 * @param item     An Item object to be removed from the store.
	 * @param quantity An integer representing how many of such items are to be
	 *                 removed to the store.
	 */
	public void removeItem(Item item, int quantity) {
		if (stock.containsKey(item)) {
			int currentQuantity = stock.get(item);
			int newQuantity = currentQuantity - quantity;
			if (newQuantity < 0) {
				stock.remove(item);
			} else {
				stock.put(item, newQuantity);
			}
		}
	}
}
