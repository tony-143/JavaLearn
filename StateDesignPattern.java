public class StateDesignPattern {
	interface TrafficLightState {
		String color();
		TrafficLightState nextState();
	}

	static final class RedState implements TrafficLightState {
		private static final RedState INSTANCE = new RedState();

		private RedState() {}

		@Override
		public String color() {
			return "RED";
		}

		@Override
		public TrafficLightState nextState() {
			return GreenState.INSTANCE;
		}
	}

	static final class GreenState implements TrafficLightState {
		private static final GreenState INSTANCE = new GreenState();

		private GreenState() {}

		@Override
		public String color() {
			return "GREEN";
		}

		@Override
		public TrafficLightState nextState() {
			return YellowState.INSTANCE;
		}
	}

	static final class YellowState implements TrafficLightState {
		private static final YellowState INSTANCE = new YellowState();

		private YellowState() {}

		@Override
		public String color() {
			return "YELLOW";
		}

		@Override
		public TrafficLightState nextState() {
			return RedState.INSTANCE;
		}
	}

	static class TrafficLight {
		private TrafficLightState state;

		TrafficLight() {
			this(RedState.INSTANCE);
		}

		TrafficLight(TrafficLightState initialState) {
			if (initialState == null) {
				throw new IllegalArgumentException("Initial state cannot be null");
			}
			state = initialState;
		}

		void display() {
			System.out.println("Traffic light is " + state.color());
		}

		void advance() {
			state = state.nextState();
		}
	}

	public static void main(String[] args) {
		TrafficLight trafficLight = new TrafficLight();

		for (int cycle = 0; cycle < 6; cycle++) {
			trafficLight.display();
			trafficLight.advance();
		}
	}
}
