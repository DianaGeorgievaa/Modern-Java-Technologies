package secondweek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VirtualWalletTest {
	private static final double DELTA = 1e-15;
	
	private static final String STANDARD_CARD = "standard";
	private static final String GOLDEN_CARD = "golden";
	private static final String UNKNOWN_CARD = "unknown";
	
	private static final double LARGE_FEED_AMOUNT = 100;
	private static final double NEGATIVE_FEED_AMOUNT = -100;
	
	private static PaymentInfo negativeCostInfo;
	private static StandartCard unknownCard;
	
	private VirtualWalletAPI wallet;
	private Card standardCard;
	private Card goldenCard;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		negativeCostInfo = new PaymentInfo("Happy Bar & Grill", "Sofia", -50);
		unknownCard = new StandartCard(UNKNOWN_CARD);
	}
	
	@Before
	public void setUp() {
		wallet = new VirtualWallet();
		standardCard = new StandartCard(STANDARD_CARD);
		goldenCard = new GoldenCard(GOLDEN_CARD);
	}
	
	@Test
	public void testIfCardIsCreatedSucessfully() {
		boolean isCreateSuccessful = wallet.registerCard(standardCard);
		assertTrue(isCreateSuccessful);
		
		int expected = 1;
		int actual = wallet.getTotalNumberOfCards();
		assertEquals(expected, actual);
		
		Card card = wallet.getCardByName(STANDARD_CARD);
		assertEquals(standardCard, card);
	}
	
	@Test
	public void testIfPaymentFailsIfNegativeCost() {
		assertTrue(wallet.registerCard(goldenCard));
		assertTrue(wallet.feed(goldenCard, LARGE_FEED_AMOUNT));
		assertFalse(wallet.executePayment(goldenCard, negativeCostInfo));
		
		Card card  = wallet.getCardByName(GOLDEN_CARD);
		
		double expected = LARGE_FEED_AMOUNT;
		double actual = card.getAmount();
		assertEquals(expected, actual, DELTA);
	}
	
	@Test
	public void testIfFeedFailsIfUnknownCardIsPassed() {
		assertFalse(wallet.feed(unknownCard, LARGE_FEED_AMOUNT));
	}
	
	@Test
	public void testIfPaymentFailsIfNullPaymentIsPassed() {
		assertTrue(wallet.registerCard(standardCard));
		assertFalse(wallet.executePayment(standardCard, null));
	}
	
	@Test
	public void testIfFeedFailsIfNegativeAmount() {
		assertTrue(wallet.registerCard(standardCard));
		assertFalse(wallet.feed(standardCard, NEGATIVE_FEED_AMOUNT));
	}
}