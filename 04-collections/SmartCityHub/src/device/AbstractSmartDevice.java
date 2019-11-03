package device;

import java.time.LocalDateTime;

import enums.DeviceType;

public abstract class AbstractSmartDevice implements SmartDevice {

	private String name;
	private double powerConsumption;
	private LocalDateTime installationDateTime;
	private DeviceType type;

	public AbstractSmartDevice(String name, double powerConsumption, LocalDateTime installationDateTime,
			DeviceType type) {
		this.name = name;
		this.powerConsumption = powerConsumption;
		this.installationDateTime = installationDateTime;
		this.type = type;
	}

	public abstract String getId();

	public abstract boolean equals(Object other);

	public abstract int hashCode();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getPowerConsumption() {
		return powerConsumption;
	}

	@Override
	public LocalDateTime getInstallationDateTime() {
		return installationDateTime;
	}

	@Override
	public DeviceType getType() {
		return type;
	}
}
