package jira;

import enums.IssueResolution;
import enums.WorkAction;
import interfaces.Filter;
import interfaces.Repository;
import issues.Issue;

public class Jira implements Filter, Repository {
	private static final int MAX_NUMBER_ISSUES = 100;

	private Issue[] issues;
	private int numberIssues;

	public Jira() {
		issues = new Issue[MAX_NUMBER_ISSUES];
		this.numberIssues = 0;
	}

	public void addActionToIssue(Issue issue, WorkAction action, String actionDescription) {
		for (int index = 0; index < numberIssues; index++) {
			if (issues[index].equals(issue)) {
				issue.addAction(action, actionDescription);
				return;
			}
		}
	}

	public void resolveIssue(Issue issue, IssueResolution resolution) {
		for (int i = 0; i < numberIssues; i++) {
			if (issues[i].getIssueID().equals(issue.getIssueID())) {
				issue.resolve(resolution);
			}
		}
	}

	@Override
	public void addIssue(Issue issue) {
		if (numberIssues == MAX_NUMBER_ISSUES) {
			throw new RuntimeException("The limit for issues is reached!");
		}
		for (int i = 0; i < numberIssues; i++) {
			if (issues[i].getIssueID().equals(issue.getIssueID())) {
				throw new RuntimeException("The issue already exists!");
			}
		}
		issues[numberIssues] = issue;
		numberIssues++;
	}

	@Override
	public Issue find(String issueID) {
		for (int i = 0; i < numberIssues; i++) {
			if (issues[i].getIssueID().equals(issueID)) {
				return issues[i];
			}
		}
		return null;
	}

}
