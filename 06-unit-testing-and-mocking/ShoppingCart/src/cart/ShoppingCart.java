package cart;

import item.Item;

import java.util.Collection;

public interface ShoppingCart {
	/**
	 * Returns the unique items in the shopping cart
	 * 
	 * @return the unique items in the shopping cart
	 */
	Collection<Item> getUniqueItems();

	/**
	 * Returns the unique items sorted from most to least added to the cart
	 * 
	 * @return the unique items sorted by count in the cart
	 */
	Collection<Item> getSortedItems();

	/**
	 * Adds an item to the shopping cart. If an item is null it is not added to the
	 * cart; If the same item has been added already, then the number of these items
	 * increases by one
	 * 
	 * @param item the item to be added
	 */
	void addItem(Item item);

	/**
	 * Removes the item from the shopping cart. If there is more than one of the
	 * same item, then their number decreases by one
	 * 
	 * @param item the item to be removed
	 */
	void removeItem(Item item) throws ItemNotFoundException;

	/**
	 * Returns the total sum to be paid at checkout
	 * 
	 * @return the total sum to be paid at checkout
	 */
	double getTotal();
}