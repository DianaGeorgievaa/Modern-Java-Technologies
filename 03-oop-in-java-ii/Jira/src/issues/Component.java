package issues;

public class Component {

	private String name;
	private String shortName;

	public Component(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	public String getName() {
		return this.name;
	}

	public String getShortName() {
		return this.shortName;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Component)) {
			return false;
		}

		Component c = (Component) o;
		return name.equals(c.name) && shortName.equals(c.shortName);
	}
}
