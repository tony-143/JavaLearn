public class FlyWeightPattern {
	static class Particle {
		private final double x;
		private final double y;
		private final ParticleType type;

		Particle(double x, double y, ParticleType type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		void render() {
			type.render(x, y);
		}
	}

	static class ParticleType {
		private final String color;
		private final String shape;
		private final double size;

		ParticleType(String color, String shape, double size) {
			this.color = color;
			this.shape = shape;
			this.size = size;
		}

		void render(double x, double y) {
			System.out.println("Rendering " + shape + " particle in " + color
					+ " at position (" + x + ", " + y + ") with size " + size);
		}
	}

	static class ParticleFactory {
		private final java.util.Map<String, ParticleType> particleTypes = new java.util.HashMap<>();

		ParticleType getParticleType(String color, String shape, double size) {
			String key = color + "-" + shape + "-" + size;
			return particleTypes.computeIfAbsent(key, k -> new ParticleType(color, shape, size));
		}
	}

	public static void main(String[] args) {
		ParticleFactory factory = new ParticleFactory();

		java.util.List<Particle> particles = new java.util.ArrayList<>();
		particles.add(new Particle(10, 15, factory.getParticleType("red", "circle", 2.5)));
		particles.add(new Particle(20, 35, factory.getParticleType("red", "circle", 2.5)));
		particles.add(new Particle(40, 60, factory.getParticleType("blue", "square", 3.0)));
		particles.add(new Particle(55, 80, factory.getParticleType("blue", "square", 3.0)));
		particles.add(new Particle(70, 90, factory.getParticleType("green", "triangle", 2.0)));

		for (Particle particle : particles) {
			particle.render();
		}
	}
}
