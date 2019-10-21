package secondweek;

public abstract class Card {

	private String name;
	private double amount;

	public Card(String name) {
		this.name = name;
		setAmount(0);
	}

	public abstract boolean executePayment(double cost);

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

	public boolean setAmount(double amount) {
		if (amount > 0) {
			this.amount = amount;
			return true;
		}
		return false;
	}

	public boolean deposit(double amount) {
		if (amount > 0) {
			this.amount += amount;
			return true;
		}
		return false;
	}
}
