package secondweek;

import java.time.LocalDateTime;

public class Transaction implements Comparable<Transaction> {

	private String cardName;
	private PaymentInfo paymentInfo;
	private LocalDateTime time;

	public Transaction(String cardName, PaymentInfo paymentInfo) {
		this.cardName = cardName;
		this.paymentInfo = paymentInfo;
		this.time = LocalDateTime.now();
	}

	@Override
	public int compareTo(Transaction o) {
		return this.time.compareTo(o.time);
	}
}
