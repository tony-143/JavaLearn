public class MementoPattern {
	static class EditorMemento {
		private final String content;
		private final String fileName;
		private final int cursorPosition;

		EditorMemento(String content, String fileName, int cursorPosition) {
			this.content = content;
			this.fileName = fileName;
			this.cursorPosition = cursorPosition;
		}

		String getContent() {
			return content;
		}

		String getFileName() {
			return fileName;
		}

		int getCursorPosition() {
			return cursorPosition;
		}
	}

	static class TextEditor {
		private String content;
		private String fileName;
		private int cursorPosition;

		TextEditor(String fileName) {
			this.fileName = fileName;
			this.content = "";
			this.cursorPosition = 0;
		}

		EditorMemento save() {
			return new EditorMemento(content, fileName, cursorPosition);
		}

		void restore(EditorMemento memento) {
			if (memento == null) {
				throw new IllegalArgumentException("Memento cannot be null");
			}
			this.content = memento.getContent();
			this.fileName = memento.getFileName();
			this.cursorPosition = memento.getCursorPosition();
		}

		void type(String text) {
			content += text;
			cursorPosition = content.length();
			System.out.println("Typed: " + text);
		}

		void setCursorPosition(int position) {
			this.cursorPosition = Math.max(0, Math.min(position, content.length()));
		}

		void showDocument() {
			System.out.println("File: " + fileName);
			System.out.println("Cursor: " + cursorPosition);
			System.out.println("Content: " + content);
			System.out.println();
		}
	}

	static class BackupHistory {
		private final java.util.ArrayDeque<EditorMemento> history = new java.util.ArrayDeque<>();

		void push(EditorMemento memento) {
			if (memento != null) {
				history.push(memento);
			}
		}

		EditorMemento undo() {
			return history.pollFirst();
		}
	}

	public static void main(String[] args) {
		TextEditor editor = new TextEditor("notes.txt");
		BackupHistory history = new BackupHistory();

		editor.type("Hello ");
		history.push(editor.save());

		editor.type("world!");
		history.push(editor.save());

		editor.type(" This is my note.");
		editor.showDocument();

		editor.restore(history.undo());
		System.out.println("After undo:");
		editor.showDocument();

		editor.restore(history.undo());
		System.out.println("After second undo:");
		editor.showDocument();
	}
}
