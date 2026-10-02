public class ProxyDesignPattern {
	interface VideoService {
		void playVideo(String videoId);
		void downloadVideo(String videoId);
	}

	static class RealVideoService implements VideoService {
		@Override
		public void playVideo(String videoId) {
			System.out.println("Playing video: " + videoId);
		}

		@Override
		public void downloadVideo(String videoId) {
			System.out.println("Downloading video: " + videoId);
		}
	}

	static class SecureVideoProxy implements VideoService {
		private final RealVideoService realVideoService = new RealVideoService();
		private final java.util.Set<String> normalUserVideos = new java.util.HashSet<>();
		private final java.util.Set<String> premiumUserVideos = new java.util.HashSet<>();
		private final java.util.Map<String, String> cachedVideos = new java.util.HashMap<>();
		private final String userRole;

		SecureVideoProxy(String userRole) {
			if (userRole == null || userRole.isBlank()) {
				throw new IllegalArgumentException("User role cannot be empty");
			}
			this.userRole = userRole;
			normalUserVideos.add("movie-101");
			normalUserVideos.add("series-202");
			premiumUserVideos.add("movie-101");
			premiumUserVideos.add("series-202");
			premiumUserVideos.add("premium-999");
		}

		@Override
		public void playVideo(String videoId) {
			if (!canAccess(videoId)) {
				System.out.println("Access denied for " + userRole + " user to video " + videoId);
				return;
			}

			String cached = cachedVideos.get(videoId);
			if (cached != null) {
				System.out.println("Serving cached video: " + videoId + " to " + userRole + " user");
				return;
			}

			realVideoService.playVideo(videoId);
			cachedVideos.put(videoId, "cached");
		}

		@Override
		public void downloadVideo(String videoId) {
			if (!canAccess(videoId)) {
				System.out.println("Download denied for " + userRole + " user to video " + videoId);
				return;
			}
			realVideoService.downloadVideo(videoId);
		}

		private boolean canAccess(String videoId) {
			if ("premium".equalsIgnoreCase(userRole)) {
				return premiumUserVideos.contains(videoId);
			}
			if ("normal".equalsIgnoreCase(userRole)) {
				return normalUserVideos.contains(videoId);
			}
			return false;
		}
	}

	public static void main(String[] args) {
		VideoService normalUser = new SecureVideoProxy("normal");
		VideoService premiumUser = new SecureVideoProxy("premium");

		System.out.println("Normal user:");
		normalUser.playVideo("movie-101");
		normalUser.playVideo("premium-999");
		normalUser.downloadVideo("series-202");

		System.out.println("\nPremium user:");
		premiumUser.playVideo("premium-999");
		premiumUser.playVideo("premium-999");
		premiumUser.downloadVideo("premium-999");
	}
}
