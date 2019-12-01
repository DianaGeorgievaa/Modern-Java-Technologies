package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Checks adherence to Java style guidelines.
 */
public class StyleChecker {
	
	private static final int MAX_LENGTH_LINE = 100;

	/**
	 * For each line from the given {@code source} performs code style checks and
	 * writes to the {@code output} 1. a FIX-ME comment line for each style
	 * violation in the input line, if any 2. the input line itself.
	 *
	 * @param source
	 * @param output
	 */
	public void checkStyle(Reader source, Writer output) {
		try (BufferedReader bufferedReader = new BufferedReader(source);
				BufferedWriter bufferedWriter = new BufferedWriter(output)) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				List<String> violations = findAllViolations(line);
				for (String violation : violations) {
					bufferedWriter.write(violation);
					bufferedWriter.newLine();
				}
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> findAllViolations(String line) {
		List<String> violations = new ArrayList<>();
		line = line.trim();

		if (line.matches("^([^;]+[;]){2,}$")) {
			violations.add(WarningUtils.ONE_STATEMENT_PER_LINE);
		}
		if (line.matches("^import .+\\*;$")) {
			violations.add(WarningUtils.WILDCARDS_NOT_ALLOWED);
		}
		if (line.matches("^\\{.*$")) {
			violations.add(WarningUtils.OPEN_BRACKETS_ON_SAME_LINE);
		}
		if (line.matches("^package .*[A-Z_].*")) {
			violations.add(WarningUtils.INVALID_PACKAGE_NAME);
		}
		if (line.matches("^(?!import).*") && line.length() > MAX_LENGTH_LINE) {
			violations.add(WarningUtils.LINE_LENGTH_NOT_EXCEED);
		}

		return violations;
	}
}