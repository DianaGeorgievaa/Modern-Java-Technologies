package cart;

import item.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ListShoppingCart implements ShoppingCart {
   
	private ArrayList<Item> items;
    
    public ListShoppingCart() {
        items = new ArrayList<>();
    }
            
    @Override
    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }
    
    @Override
    public void removeItem(Item item) throws ItemNotFoundException {
        if (!items.remove(item)) {
            throw new ItemNotFoundException();
        }
    }
    
    @Override
    public Collection<Item> getUniqueItems() {
        Set<Item> uniqueItems = new HashSet<>();
        for (Item item : items) {
            uniqueItems.add(item);
        }
        
        return uniqueItems;
    }
    
    @Override
    public Collection<Item> getSortedItems() {
        HashMap<Item, Integer> occurances = getItemsOccurances();
        List<Item> uniqueItems = new ArrayList<>(occurances.keySet());
        Collections.sort(uniqueItems, new Comparator<Item>() {
           
        	@Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(occurances.get(o2), occurances.get(o1));
            }
        });
        
        return uniqueItems;
    }

    @Override
    public double getTotal() {
        double totalSum = 0.0;
        for (Item item : items) {
            totalSum += item.getPrice();
        }
        
        return totalSum;
    }

    private HashMap<Item, Integer> getItemsOccurances() {
        HashMap<Item, Integer> occurances = new HashMap<>();
        for (Item item : items) {
            if (!occurances.containsKey(item)) {
                occurances.put(item, 1);
            } else {
                occurances.put(item, occurances.get(item) + 1);
            }
        }
        
        return occurances;
    }
}