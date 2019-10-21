package firstweek;

import java.util.Arrays;

public class WordAnalyzer {

	public static String getSharedLetters(String word1, String word2) {
		int maxLength = Math.max(word1.length(), word2.length());
		StringBuilder sharedLetters = new StringBuilder();

		StringBuilder firstWord = new StringBuilder(word1);
		StringBuilder secondWord = new StringBuilder(word2);

		String s1 = firstWord.toString().toLowerCase();
		String s2 = secondWord.toString().toLowerCase();
		char currentLetter;

		for (int i = 0; i < maxLength; i++) {
			currentLetter = s1.charAt(i);
			StringBuilder b = new StringBuilder();
			b.append(currentLetter);

			if (s2.contains(b)) {
				sharedLetters.append(b.toString());
			}
		}

		String result = new String(sharedLetters);
		return sort(result);
	}

	private static String sort(String text) {
		char[] chars = text.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
