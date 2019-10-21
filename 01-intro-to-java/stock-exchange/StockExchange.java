package firstweek;

public class StockExchange {

	public static int maxProfit(int[] prices) {
		int profit = 0;
		int currentProfit;

		for (int i = 1; i < prices.length; i++) {
			currentProfit = prices[i] - prices[i - 1];
			if (currentProfit > 0) {
				profit += currentProfit;
			}
		}
		return profit;
	}
}
