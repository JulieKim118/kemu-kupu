package statistics;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class StringIntegerStore {
	protected Map<String, Integer> store;

	protected StringIntegerStore() {
		store = new LinkedHashMap<>();
	}

	protected void addValueToKey(String key, int value) {
		if (store.containsKey(key)) {
			addValueToExistingKey(key, value);
		} else {
			addValueToNewKey(key, value);
		}
	}

	private void addValueToExistingKey(String key, int value) {
		int currentValue = store.get(key);
		currentValue += value;
		store.put(key, currentValue);
	}

	private void addValueToNewKey(String key, int value) {
		store.put(key, value);
	}
}
