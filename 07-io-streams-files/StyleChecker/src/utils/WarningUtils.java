package utils;

public class WarningUtils {

	public static final String WILDCARDS_NOT_ALLOWED = "// FIXME Wildcards are not allowed in import statements";
	public static final String ONE_STATEMENT_PER_LINE = "// FIXME Only one statement per line is allowed";
	public static final String LINE_LENGTH_NOT_EXCEED = "// FIXME Length of line should not exceed 100 characters";
	public static final String OPEN_BRACKETS_ON_SAME_LINE = "// FIXME Opening brackets should be placed "
			+ "on the same line as the declaration";
	public static final String INVALID_PACKAGE_NAME = "// FIXME Package name should not contain "
			+ "upper-case letters or underscores";

	private WarningUtils() {

	}

}
