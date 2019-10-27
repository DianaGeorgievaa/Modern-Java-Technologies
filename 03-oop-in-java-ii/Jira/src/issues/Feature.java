package issues;

import enums.IssuePriority;
import enums.IssueResolution;
import enums.WorkAction;

public class Feature extends Issue {

	private boolean isDesigned;
	private boolean isImplemented;
	private boolean isTested;

	public Feature(IssuePriority priority, Component component, String description) {
		super(priority, component, description);

		isDesigned = false;
		isImplemented = false;
		isTested = false;
	}

	@Override
	public void resolve(IssueResolution resolution) {
		if (!isImplemented) {
			throw new RuntimeException("The feature should be implemented!");
		}
		if (!isDesigned) {
			throw new RuntimeException("The feature should be designed!");
		}
		if (!isTested) {
			throw new RuntimeException("The feature should be tested!");
		}

		super.setResolution(resolution);
	}

	@Override
	public void addAction(WorkAction action, String description) {
		if (action == WorkAction.DESIGN) {
			isDesigned = true;
		} else if (action == WorkAction.IMPLEMENTATION) {
			isImplemented = true;
		} else if (action == WorkAction.TESTS) {
			isTested = true;
		}

		super.addAction(action, description);
	}
}
