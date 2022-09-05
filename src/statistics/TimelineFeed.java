package statistics;

import java.util.ArrayList;

public class TimelineFeed extends StringIntegerStore {

	public void addData(String category, int value) {
		addValueToKey(category, value);
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> categories = new ArrayList<>();
		for (String category : store.keySet()) {
			categories.add(category);
		}
		return categories;

	}

	public int getDataOnExistingCategory(String category) {
		return store.get(category);
	}
}
