public class FacadePattern {
	enum MediaFormat {
		MP3,
		MP4
	}

	static class FormatDetector {
		MediaFormat detect(String fileName) {
			if (fileName == null || fileName.isBlank()) {
				throw new IllegalArgumentException("File name cannot be empty");
			}

			int extensionSeparator = fileName.lastIndexOf('.');
			if (extensionSeparator < 0 || extensionSeparator == fileName.length() - 1) {
				throw new IllegalArgumentException("File must have a supported extension: " + fileName);
			}

			String extension = fileName.substring(extensionSeparator + 1)
					.toUpperCase(java.util.Locale.ROOT);
			try {
				return MediaFormat.valueOf(extension);
			} catch (IllegalArgumentException exception) {
				throw new IllegalArgumentException("Unsupported media format: " + extension, exception);
			}
		}
	}

	static class AudioDecoder {
		String decode(String fileName) {
			System.out.println("Decoding audio file: " + fileName);
			return "Audio data from " + fileName;
		}
	}

	static class VideoDecoder {
		String decode(String fileName) {
			System.out.println("Decoding video file: " + fileName);
			return "Video data from " + fileName;
		}
	}

	static class Speaker {
		void play(String audioData) {
			System.out.println("Playing through speakers: " + audioData);
		}
	}

	static class Screen {
		void display(String videoData) {
			System.out.println("Displaying on screen: " + videoData);
		}
	}

	static class MediaPlayerFacade {
		private final FormatDetector formatDetector = new FormatDetector();
		private final AudioDecoder audioDecoder = new AudioDecoder();
		private final VideoDecoder videoDecoder = new VideoDecoder();
		private final Speaker speaker = new Speaker();
		private final Screen screen = new Screen();

		void playMedia(String fileName) {
			MediaFormat format = formatDetector.detect(fileName);
			switch (format) {
				case MP3 -> speaker.play(audioDecoder.decode(fileName));
				case MP4 -> screen.display(videoDecoder.decode(fileName));
			}
		}
	}

	public static void main(String[] args) {
		MediaPlayerFacade mediaPlayer = new MediaPlayerFacade();
		mediaPlayer.playMedia("favorite-song.mp3");
		System.out.println();
		mediaPlayer.playMedia("family-video.mp4");
	}
}
