package cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import item.Apple;
import item.Chocolate;
import item.Item;

public class ListShoppingCartTest {

	private static final double DELTA = 0.000001;

	private ShoppingCart shoppingCart;
	private Item apple;
	private Item chocolate;

	@Before
	public void setUp() {
		double applePrice = 0.80;
		double chocolatePrice = 2.50;
		shoppingCart = new ListShoppingCart();
		apple = new Apple("apple", "green", applePrice);
		chocolate = new Chocolate("Lindt", "Black", chocolatePrice);
	}

	@Test
	public void testGetTotalWithEmptyShoppingCart() {
		assertEquals(0, shoppingCart.getTotal(), DELTA);
	}

	@Test
	public void testGetTotalWithNotEmptyShoppingCart() {
		shoppingCart.addItem(apple);

		assertEquals(apple.getPrice(), shoppingCart.getTotal(), DELTA);
	}

	@Test
	public void testGetTotalWithShoppingCartWithMoreThanOneItem() {
		shoppingCart.addItem(apple);
		shoppingCart.addItem(chocolate);
		double expectedTotalSum = apple.getPrice() + chocolate.getPrice();

		assertEquals(expectedTotalSum, shoppingCart.getTotal(), DELTA);
	}

	@Test
	public void testAddItemWithNullItem() {
		shoppingCart.addItem(null);

		assertTrue(shoppingCart.getUniqueItems().isEmpty());
		assertTrue(shoppingCart.getSortedItems().isEmpty());
		assertEquals(0, shoppingCart.getTotal(), DELTA);
	}

	@Test
	public void testRemoveItemWithExistingItem() throws ItemNotFoundException {
		shoppingCart.addItem(apple);
		shoppingCart.removeItem(apple);

		assertEquals(0, shoppingCart.getSortedItems().size());
	}

	@Test(expected = ItemNotFoundException.class)
	public void testRemoveItemWithNotExistingItemShouldThrowException() throws ItemNotFoundException {
		shoppingCart.removeItem(chocolate);
	}

	@Test
	public void testGetTotalShouldReturnCorrectTotalSum() {
		shoppingCart.addItem(apple);
		shoppingCart.addItem(chocolate);
		double expectedTotalSum = apple.getPrice() + chocolate.getPrice();

		assertEquals(expectedTotalSum, shoppingCart.getTotal(), DELTA);
	}

}