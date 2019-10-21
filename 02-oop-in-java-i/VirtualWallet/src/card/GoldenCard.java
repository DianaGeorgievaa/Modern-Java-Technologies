package secondweek;

public class GoldenCard extends Card {

	private static final double DISCOUNT_PERCENT = 0.15;

	public GoldenCard(String name) {
		super(name);
	}

	@Override
	public boolean executePayment(double cost) {
		if (cost < 0) {
			return false;
		}
		double discountSum = cost * DISCOUNT_PERCENT;
		double newAmount = (this.getAmount() - cost) + discountSum;
		return this.setAmount(newAmount);
	}
}
