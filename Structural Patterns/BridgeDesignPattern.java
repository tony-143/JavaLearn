public class BridgeDesignPattern {
	interface DrawingAPI {
		void drawCircle(double x, double y, double radius);
		void drawRectangle(double x, double y, double width, double height);
	}

	static class VectorDrawingAPI implements DrawingAPI {
		@Override
		public void drawCircle(double x, double y, double radius) {
			System.out.println("Vector API: Drawing circle at (" + x + ", " + y + ") with radius " + radius);
		}

		@Override
		public void drawRectangle(double x, double y, double width, double height) {
			System.out.println("Vector API: Drawing rectangle at (" + x + ", " + y + ") with width " + width + " and height " + height);
		}
	}

	static class RasterDrawingAPI implements DrawingAPI {
		@Override
		public void drawCircle(double x, double y, double radius) {
			System.out.println("Raster API: Rendering circle at (" + x + ", " + y + ") with radius " + radius);
		}

		@Override
		public void drawRectangle(double x, double y, double width, double height) {
			System.out.println("Raster API: Rendering rectangle at (" + x + ", " + y + ") with width " + width + " and height " + height);
		}
	}

	abstract static class Shape {
		protected final DrawingAPI drawingAPI;

		Shape(DrawingAPI drawingAPI) {
			if (drawingAPI == null) {
				throw new IllegalArgumentException("Drawing API cannot be null");
			}
			this.drawingAPI = drawingAPI;
		}

		public abstract void draw();
		public abstract void resize(double factor);
	}

	static class Circle extends Shape {
		private double x;
		private double y;
		private double radius;

		Circle(double x, double y, double radius, DrawingAPI drawingAPI) {
			super(drawingAPI);
			this.x = x;
			this.y = y;
			this.radius = radius;
		}

		@Override
		public void draw() {
			drawingAPI.drawCircle(x, y, radius);
		}

		@Override
		public void resize(double factor) {
			radius *= factor;
		}
	}

	static class Rectangle extends Shape {
		private double x;
		private double y;
		private double width;
		private double height;

		Rectangle(double x, double y, double width, double height, DrawingAPI drawingAPI) {
			super(drawingAPI);
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		@Override
		public void draw() {
			drawingAPI.drawRectangle(x, y, width, height);
		}

		@Override
		public void resize(double factor) {
			width *= factor;
			height *= factor;
		}
	}

	public static void main(String[] args) {
		Shape circle = new Circle(10, 20, 5, new VectorDrawingAPI());
		Shape rectangle = new Rectangle(0, 0, 15, 8, new RasterDrawingAPI());

		circle.draw();
		rectangle.draw();

		circle.resize(2);
		rectangle.resize(1.5);

		System.out.println("After resize:");
		circle.draw();
		rectangle.draw();
	}
}
