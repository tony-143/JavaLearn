import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorPattern {
	static class Song {
		private final String title;
		private final String artist;

		Song(String title, String artist) {
			this.title = title;
			this.artist = artist;
		}

		@Override
		public String toString() {
			return title + " - " + artist;
		}
	}

	interface PlaylistIterator {
		boolean hasNext();
		Song next();
	}

	static class MusicPlaylist {
		private final List<Song> songs = new ArrayList<>();

		void addSong(Song song) {
			songs.add(song);
		}

		PlaylistIterator iterator() {
			return new SongIterator();
		}

		private class SongIterator implements PlaylistIterator {
			private int position;

			@Override
			public boolean hasNext() {
				return position < songs.size();
			}

			@Override
			public Song next() {
				if (!hasNext()) {
					throw new NoSuchElementException("No more songs in the playlist");
				}
				return songs.get(position++);
			}
		}
	}

	public static void main(String[] args) {
		MusicPlaylist playlist = new MusicPlaylist();
		playlist.addSong(new Song("Blinding Lights", "The Weeknd"));
		playlist.addSong(new Song("Levitating", "Dua Lipa"));
		playlist.addSong(new Song("As It Was", "Harry Styles"));

		PlaylistIterator iterator = playlist.iterator();
		System.out.println("Playing playlist:");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
