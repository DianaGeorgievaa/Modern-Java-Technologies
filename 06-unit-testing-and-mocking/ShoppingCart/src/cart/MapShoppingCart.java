package cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import item.Item;

public class MapShoppingCart implements ShoppingCart {
	
	private Map<Item, Integer> items;

	public MapShoppingCart() {
		items = new HashMap<>();
	}

	@Override
	public void addItem(Item item) {
		if (item != null) {
			Integer occurrences = items.get(item);
			if (occurrences == null) {
				items.put(item, 1);
			} else {
				items.put(item, occurrences + 1);
			}
		}
	}

	@Override
	public void removeItem(Item item) throws ItemNotFoundException {
		if (!items.containsKey(item)) {
			throw new ItemNotFoundException();
		}
		int occurrences = items.get(item);
		if (occurrences == 1) {
			items.remove(item);
		} else {
			items.put(item, occurrences - 1);
		}
	}

	@Override
	public Collection<Item> getUniqueItems() {
		return items.keySet();
	}

	@Override
	public Collection<Item> getSortedItems() {
		List<Item> itemsToBeSorted = new ArrayList<>(items.keySet());
		Collections.sort(itemsToBeSorted, new Comparator<Item>() {
			
			@Override
			public int compare(Item o1, Item o2) {
				return Integer.compare(items.get(o2), items.get(o1));
			}
		});
		
		return itemsToBeSorted;
	}

	@Override
	public double getTotal() {
		double total = 0.0;
		for (Item item : items.keySet()) {
			total += (item.getPrice() * items.get(item));
		}
		
		return total;
	}
}