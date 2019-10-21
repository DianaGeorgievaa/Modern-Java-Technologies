package firstweek;

import java.util.stream.Stream;

public class FractionSimplifier {

	public static String simplify(String fraction) {
		int[] numbers = Stream.of(fraction.split("/")).mapToInt(Integer::parseInt).toArray();
		
		if (numbers[0] % numbers[1] == 0) {
			return Integer.toString(numbers[0] / numbers[1]);
		}
		
		int divisor = getGreatestCommonDivisor(numbers[0], numbers[1]);
		StringBuilder result = new StringBuilder();
		numbers[0] /= divisor;
		numbers[1] /= divisor;
		result.append(numbers[0]).append("/").append(numbers[1]);

		return new String(result);
	}

	private static int getGreatestCommonDivisor(int first, int second) {
		if (second == 0) {
			return first;
		}
		return getGreatestCommonDivisor(second, first % second);
	}
}
