public class AdaptorPattern {
	interface Device {
		void turnOn();
		void turnOff();
	}

	static class SmartSpeaker implements Device {
		@Override
		public void turnOn() {
			System.out.println("Smart speaker is on");
		}

		@Override
		public void turnOff() {
			System.out.println("Smart speaker is off");
		}
	}

	static class LegacyProjector {
		void powerOn() {
			System.out.println("Legacy projector is on");
		}

		void powerOff() {
			System.out.println("Legacy projector is off");
		}
	}

	static class LegacyProjectorAdapter implements Device {
		private final LegacyProjector projector;

		LegacyProjectorAdapter(LegacyProjector projector) {
			if (projector == null) {
				throw new IllegalArgumentException("Projector cannot be null");
			}
			this.projector = projector;
		}

		@Override
		public void turnOn() {
			projector.powerOn();
		}

		@Override
		public void turnOff() {
			projector.powerOff();
		}
	}

	static class DeviceController {
		void start(Device device) {
			device.turnOn();
		}

		void stop(Device device) {
			device.turnOff();
		}
	}

	public static void main(String[] args) {
		DeviceController controller = new DeviceController();	
		Device speaker = new SmartSpeaker();
		Device projector = new LegacyProjectorAdapter(new LegacyProjector());

		controller.start(speaker);
		controller.stop(speaker);

		controller.start(projector);
		controller.stop(projector);
	}
}
