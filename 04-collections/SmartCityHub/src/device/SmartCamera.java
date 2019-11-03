package device;

import java.time.LocalDateTime;

import enums.DeviceType;

public class SmartCamera extends AbstractSmartDevice {

	private final String ID;
	private static int counter = 0;

	public SmartCamera(String name, double powerConsumption, LocalDateTime installationDateTime) {
		super(name, powerConsumption, installationDateTime, DeviceType.CAMERA);
		ID = DeviceType.CAMERA.getShortName() + "-" + name + "-" + counter;
		counter++;
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmartCamera other = (SmartCamera) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
}
