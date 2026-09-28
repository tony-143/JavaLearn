public class CommandPattern {
	interface Command {
		void execute();
	}

	static class Television {
		void turnOn() {
			System.out.println("TV is on");
		}

		void turnOff() {
			System.out.println("TV is off");
		}

		void changeChannel(int channel) {
			System.out.println("TV is showing channel " + channel);
		}
	}

	static class TurnOnCommand implements Command {
		private final Television television;

		TurnOnCommand(Television television) {
			this.television = television;
		}

		@Override
		public void execute() {
			television.turnOn();
		}
	}

	static class TurnOffCommand implements Command {
		private final Television television;

		TurnOffCommand(Television television) {
			this.television = television;
		}

		@Override
		public void execute() {
			television.turnOff();
		}
	}

	static class ChangeChannelCommand implements Command {
		private final Television television;
		private final int channel;

		ChangeChannelCommand(Television television, int channel) {
			this.television = television;
			this.channel = channel;
		}

		@Override
		public void execute() {
			television.changeChannel(channel);
		}
	}

	static class TVRemote {
		private Command button;

		void setButton(Command button) {
			this.button = button;
		}

		void pressButton() {
			if (button == null) {
				throw new IllegalStateException("No command assigned to the remote button");
			}
			button.execute();
		}
	}

	public static void main(String[] args) {
		Television television = new Television();
		TVRemote remote = new TVRemote();

		remote.setButton(new TurnOnCommand(television));
		remote.pressButton();

		remote.setButton(new ChangeChannelCommand(television, 7));
		remote.pressButton();

		remote.setButton(new TurnOffCommand(television));
		remote.pressButton();
	}
}
