import java.util.ArrayList;
import java.util.List;

public class OberverPattern {
	interface Notifier {
		void notify(String channelName, String videoTitle);
	}

	static class EmailNotifier implements Notifier {
		private final String email;

		EmailNotifier(String email) {
			this.email = email;
		}

		@Override
		public void notify(String channelName, String videoTitle) {
			System.out.println("Email to " + email + ": " + channelName
					+ " uploaded a new video: " + videoTitle);
		}
	}

	static class YoutubeChannel {
		private final String name;
		private final List<Notifier> subscribers = new ArrayList<>();

		YoutubeChannel(String name) {
			this.name = name;
		}

		void subscribe(Notifier notifier) {
			subscribers.add(notifier);
		}

		void unsubscribe(Notifier notifier) {
			subscribers.remove(notifier);
		}

		void uploadVideo(String videoTitle) {
			System.out.println(name + " uploaded: " + videoTitle);
			for (Notifier subscriber : subscribers) {
				subscriber.notify(name, videoTitle);
			}
		}
	}

	public static void main(String[] args) {
		YoutubeChannel channel = new YoutubeChannel("Code With Me");
		Notifier alice = new EmailNotifier("alice@example.com");
		Notifier bob = new EmailNotifier("bob@example.com");

		channel.subscribe(alice);
		channel.subscribe(bob);
		channel.uploadVideo("Observer Pattern in Java");

		channel.unsubscribe(bob);
		channel.uploadVideo("Strategy Pattern in Java");
	}
}
