package item;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ChocolateTest {

	private Product chocolate = new Chocolate("Lindt", "Black chocolate", 4.2);
	private Product chocolateWithDifferentName = new Chocolate("Milka", "Black chocolate", 4.2);

	@Before
	public void setUp() {
		chocolate = new Chocolate("Lindt", "Black chocolate", 4.2);
		chocolateWithDifferentName = new Chocolate("Milka", "Black chocolate", 4.2);
	}

	@Test
	public void testEqualsWithEqualsObjects() {
		assertTrue(chocolate.equals(chocolate));
	}

	@Test
	public void testEqualsWithObjectsWithDifferentNames() {
		assertFalse(chocolate.equals(chocolateWithDifferentName));
	}
}
