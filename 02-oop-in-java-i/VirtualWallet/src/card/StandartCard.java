package secondweek;

public class StandartCard extends Card {

	public StandartCard(String name) {
		super(name);
	}

	@Override
	public boolean executePayment(double cost) {
		if (cost < 0) {
			return false;
		}
		double newAmount = getAmount() - cost;
		return this.setAmount(newAmount);
	}
}
