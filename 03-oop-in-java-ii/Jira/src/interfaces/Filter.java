package interfaces;

import issues.Issue;

public interface Filter {
	public Issue find(String issueID);
}
