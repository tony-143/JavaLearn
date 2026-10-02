public class CompositeDesignPattern {
	interface HouseComponent {
		void turnOn();
		void turnOff();
		void displayStatus();
	}

	abstract static class SmartDevice implements HouseComponent {
		private final String name;
		private boolean on;

		SmartDevice(String name) {
			if (name == null || name.isBlank()) {
				throw new IllegalArgumentException("Device name cannot be empty");
			}
			this.name = name;
		}

		protected abstract String deviceType();

		@Override
		public void turnOn() {
			on = true;
			System.out.println(deviceType() + " '" + name + "' turned on");
		}

		@Override
		public void turnOff() {
			on = false;
			System.out.println(deviceType() + " '" + name + "' turned off");
		}

		@Override
		public void displayStatus() {
			System.out.println("  " + deviceType() + " '" + name + "': "
					+ (on ? "on" : "off"));
		}
	}

	static class SmartLight extends SmartDevice {
		SmartLight(String name) {
			super(name);
		}

		@Override
		protected String deviceType() {
			return "Light";
		}
	}

	static class SmartFan extends SmartDevice {
		SmartFan(String name) {
			super(name);
		}

		@Override
		protected String deviceType() {
			return "Fan";
		}
	}

	static class DeviceGroup implements HouseComponent {
		private final String name;
		private final java.util.List<HouseComponent> components = new java.util.ArrayList<>();

		DeviceGroup(String name) {
			if (name == null || name.isBlank()) {
				throw new IllegalArgumentException("Group name cannot be empty");
			}
			this.name = name;
		}

		void add(HouseComponent component) {
			if (component == null) {
				throw new IllegalArgumentException("Component cannot be null");
			}
			if (component == this) {
				throw new IllegalArgumentException("A group cannot contain itself");
			}
			components.add(component);
		}

		boolean remove(HouseComponent component) {
			return components.remove(component);
		}

		@Override
		public void turnOn() {
			System.out.println("Turning on " + name + "...");
			for (HouseComponent component : components) {
				component.turnOn();
			}
		}

		@Override
		public void turnOff() {
			System.out.println("Turning off " + name + "...");
			for (HouseComponent component : components) {
				component.turnOff();
			}
		}

		@Override
		public void displayStatus() {
			System.out.println(name + ":");
			for (HouseComponent component : components) {
				component.displayStatus();
			}
		}
	}

	public static void main(String[] args) {
		DeviceGroup livingRoom = new DeviceGroup("Living room");
		livingRoom.add(new SmartLight("Ceiling light"));
		livingRoom.add(new SmartFan("Ceiling fan"));

		DeviceGroup bedroom = new DeviceGroup("Bedroom");
		bedroom.add(new SmartLight("Bedside lamp"));

		DeviceGroup firstFloor = new DeviceGroup("First floor");
		firstFloor.add(livingRoom);

		DeviceGroup secondFloor = new DeviceGroup("Second floor");
		secondFloor.add(bedroom);

		DeviceGroup smartHouse = new DeviceGroup("Smart house");
		smartHouse.add(firstFloor);
		smartHouse.add(secondFloor);

		smartHouse.turnOn();
		System.out.println("\nCurrent house status:");
		smartHouse.displayStatus();

		System.out.println("\nTurning off only the bedroom:");
		bedroom.turnOff();
		System.out.println("\nUpdated house status:");
		smartHouse.displayStatus();
	}
}
