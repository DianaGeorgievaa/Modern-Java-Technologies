package firstweek;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FractionSimplifierTest {

	private static FractionSimplifier fractionSimplifier;

	@BeforeClass
	public static void setUp() {
		fractionSimplifier = new FractionSimplifier();
	}

	@Test
	public void testFraction_ProperFraction() {
		assertEquals(fractionSimplifier.simplify("4/6"), "2/3");
	}

	@Test
	public void testFraction_ProperSimplifiedFraction() {
		assertEquals(fractionSimplifier.simplify("10/11"), "10/11");
	}

	@Test
	public void testFraction_ProperFractionManyCommonDivisors() {
		assertEquals(fractionSimplifier.simplify("100/400"), "1/4");
	}

	@Test
	public void testFraction_ImproperFractionToInteger() {
		assertEquals(fractionSimplifier.simplify("8/4"), "2");
	}

	@Test
	public void testFraction_ImproperFractionOne() {
		assertEquals(fractionSimplifier.simplify("161/1"), "161");
	}

	@Test
	public void testFraction_ProperFractionZero() {
		assertEquals(fractionSimplifier.simplify("0/100"), "0");
	}

	@Test
	public void testFraction_ProperFractionOneOneInteger() {
		assertEquals(fractionSimplifier.simplify("1/1"), "1");
	}
}
