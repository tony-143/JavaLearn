public class TempletePattern {
	abstract static class Beverage {
		final void prepareRecipe() {
			System.out.println("\nMaking " + name());
			boilWater();
			brew();
			pourInCup();
			addCondiments();
		}

		private void boilWater() {
			System.out.println("Boiling water");
		}

		private void pourInCup() {
			System.out.println("Pouring into a cup");
		}

		abstract String name();
		abstract void brew();
		abstract void addCondiments();
	}

	static class Tea extends Beverage {
		@Override
		String name() {
			return "Tea";
		}

		@Override
		void brew() {
			System.out.println("Steeping the tea bag");
		}

		@Override
		void addCondiments() {
			System.out.println("Adding lemon");
		}
	}

	static class Coffee extends Beverage {
		@Override
		String name() {
			return "Coffee";
		}

		@Override
		void brew() {
			System.out.println("Brewing coffee grounds");
		}

		@Override
		void addCondiments() {
			System.out.println("Adding sugar and milk");
		}
	}

	static class Boost extends Beverage {
		@Override
		String name() {
			return "Boost";
		}

		@Override
		void brew() {
			System.out.println("Stirring Boost powder into the hot water");
		}

		@Override
		void addCondiments() {
			System.out.println("Adding milk");
		}
	}

	public static void main(String[] args) {
		Beverage[] beverages = { new Tea(), new Coffee(), new Boost() };

		for (Beverage beverage : beverages) {
			beverage.prepareRecipe();
		}
	}
}
