package issues;

import enums.IssuePriority;
import enums.IssueResolution;
import enums.WorkAction;

public class Task extends Issue {

	public Task(IssuePriority priority, Component component, String description) {
		super(priority, component, description);
	}

	public void resolve(IssueResolution resolution) {
		super.setResolution(resolution);
	}

	@Override
	public void addAction(WorkAction action, String description) {
		if (action == WorkAction.DESIGN || action == WorkAction.IMPLEMENTATION || action == WorkAction.TESTS) {
			throw new RuntimeException();
		}
		super.addAction(action, description);
	}
}
