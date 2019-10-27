package issues;

import enums.IssuePriority;
import enums.IssueResolution;
import enums.IssueStatus;
import enums.WorkAction;

public class Bug extends Issue {

	private boolean isFixed;
	private boolean isTested;

	public Bug(IssuePriority priority, Component component, String description) {
		super(priority, component, description);
		isFixed = false;
		isTested = false;
	}

	@Override
	public void resolve(IssueResolution resolution) {
		if (!isFixed) {
			throw new RuntimeException("The bug should be fixed!");
		}

		if (!isTested) {
			throw new RuntimeException("The bug should be tested!");
		}
		
		super.setResolution(resolution);
	}

	@Override
	public void addAction(WorkAction action, String description) {
		if (action == WorkAction.FIX) {
			isFixed = true;
		} else if (action == WorkAction.TESTS) {
			isTested = true;
		}

		super.addAction(action, description);
	}
}
