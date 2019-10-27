package issues;

import java.time.LocalDateTime;

import enums.IssuePriority;
import enums.IssueResolution;
import enums.IssueStatus;
import enums.WorkAction;

public abstract class Issue {
	private static final int MAX_NUMBER_ACTIONS = 20;

	private final LocalDateTime creationTime;
	private static int idCounter = 0;
	private int actionsNumber;

	private String ID;
	private String description;
	private IssuePriority priority;
	private IssueResolution resolution;
	private IssueStatus status;
	private Component component;
	private String[] actionLog;
	private LocalDateTime lastModificationTime;

	public Issue(IssuePriority priority, Component component, String description) {
		ID = component.getShortName() + "-" + idCounter;
		idCounter++;

		this.description = description;
		this.priority = priority;
		this.resolution = IssueResolution.UNRESOLVED;
		this.component = component;
		setStatus(IssueStatus.OPEN);
		creationTime = LocalDateTime.now();
		lastModificationTime = LocalDateTime.now();

		actionLog = new String[MAX_NUMBER_ACTIONS];
		actionsNumber = 0;
	}

	public String getIssueID() {
		return ID;
	}

	public String getDescription() {
		return description;
	}

	public IssuePriority getPriority() {
		return priority;
	}

	public IssueResolution getResolution() {
		return resolution;
	}

	public IssueStatus getStatus() {
		return status;
	}

	public Component getComponent() {
		return component;
	}

	public LocalDateTime getCreatedOn() {
		return creationTime;
	}

	public LocalDateTime getLastModifiedOn() {
		return lastModificationTime;
	}

	public String[] getActionLog() {
		return actionLog;
	}

	public void setStatus(IssueStatus status) {
		this.status = status;
		lastModificationTime = LocalDateTime.now();
	}

	public void setResolution(IssueResolution resolution) {
		this.resolution = resolution;
		lastModificationTime = LocalDateTime.now();
	}

	public void addAction(WorkAction action, String description) {
		if (actionsNumber <= MAX_NUMBER_ACTIONS && description != null) {
			actionLog[actionsNumber] = action.toString().toLowerCase() + ": " + description;
			lastModificationTime = LocalDateTime.now();
			actionsNumber++;
		}
		throw new RuntimeException();
	}

	public abstract void resolve(IssueResolution resolution);
}
