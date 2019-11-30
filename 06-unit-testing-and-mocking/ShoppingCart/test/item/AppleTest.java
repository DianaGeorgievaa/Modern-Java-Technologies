package item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppleTest {

	private Product apple;
	private Product appleWithDifferentName;

	@Before
	public void setUp() {
		apple = new Apple("Red", "Red apple", 1.2);
		appleWithDifferentName = new Apple("Redd", "Red apple", 1.2);
	}

	@Test
	public void testEqualsWithEqualsObjects() {
		assertTrue(apple.equals(apple));
	}

	@Test
	public void testEqualsWithObjectsWithDifferentNames() {
		assertFalse(apple.equals(appleWithDifferentName));
	}

}
