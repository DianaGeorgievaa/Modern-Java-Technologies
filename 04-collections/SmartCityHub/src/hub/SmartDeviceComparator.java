package hub;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

import device.SmartDevice;

public class SmartDeviceComparator implements Comparator<SmartDevice> {

	@Override
	public int compare(SmartDevice o1, SmartDevice o2) {
		LocalDateTime now = LocalDateTime.now();
		double firstDeviceTotalPowerConsumtion = Duration.between(o1.getInstallationDateTime(), now).toHours()
				* o1.getPowerConsumption();
		double secondDeviceTotalPowerConsumtion = Duration.between(o2.getInstallationDateTime(), now).toHours()
				* o2.getPowerConsumption();

		return Double.compare(secondDeviceTotalPowerConsumtion, firstDeviceTotalPowerConsumtion);
	}
}
