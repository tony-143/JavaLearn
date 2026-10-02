public class DecoratorPattern {
	interface Coffee {
		double cost();
		String description();
	}

	static class Espresso implements Coffee {
		@Override
		public double cost() {
			return 2.50;
		}

		@Override
		public String description() {
			return "Espresso";
		}
	}

	static class Cappuccino implements Coffee {
		@Override
		public double cost() {
			return 3.25;
		}

		@Override
		public String description() {
			return "Cappuccino";
		}
	}

	static abstract class CoffeeDecorator implements Coffee {
		protected final Coffee decoratedCoffee;

		CoffeeDecorator(Coffee coffee) {
			if (coffee == null) {
				throw new IllegalArgumentException("Coffee cannot be null");
			}
			this.decoratedCoffee = coffee;
		}
	}

	static class MilkDecorator extends CoffeeDecorator {
		MilkDecorator(Coffee coffee) {
			super(coffee);
		}

		@Override
		public double cost() {
			return decoratedCoffee.cost() + 0.75;
		}

		@Override
		public String description() {
			return decoratedCoffee.description() + ", Milk";
		}
	}

	static class SugarDecorator extends CoffeeDecorator {
		SugarDecorator(Coffee coffee) {
			super(coffee);
		}

		@Override
		public double cost() {
			return decoratedCoffee.cost() + 0.20;
		}

		@Override
		public String description() {
			return decoratedCoffee.description() + ", Sugar";
		}
	}

	static class VanillaDecorator extends CoffeeDecorator {
		VanillaDecorator(Coffee coffee) {
			super(coffee);
		}

		@Override
		public double cost() {
			return decoratedCoffee.cost() + 0.90;
		}

		@Override
		public String description() {
			return decoratedCoffee.description() + ", Vanilla";
		}
	}

	static class CaramelDecorator extends CoffeeDecorator {
		CaramelDecorator(Coffee coffee) {
			super(coffee);
		}

		@Override
		public double cost() {
			return decoratedCoffee.cost() + 1.10;
		}

		@Override
		public String description() {
			return decoratedCoffee.description() + ", Caramel";
		}
	}

	static class WhippedCreamDecorator extends CoffeeDecorator {
		WhippedCreamDecorator(Coffee coffee) {
			super(coffee);
		}

		@Override
		public double cost() {
			return decoratedCoffee.cost() + 1.30;
		}

		@Override
		public String description() {
			return decoratedCoffee.description() + ", Whipped Cream";
		}
	}

	static void printOrder(Coffee coffee) {
		System.out.printf("%s -> $%.2f%n", coffee.description(), coffee.cost());
	}

	public static void main(String[] args) {
		Coffee baseCoffee = new Espresso();
		printOrder(baseCoffee);

		Coffee milkCoffee = new MilkDecorator(baseCoffee);
		printOrder(milkCoffee);

		Coffee specialCoffee = new CaramelDecorator(
				new WhippedCreamDecorator(
						new VanillaDecorator(
								new Cappuccino())));
		printOrder(specialCoffee);

		Coffee customCoffee = new SugarDecorator(
				new MilkDecorator(
						new VanillaDecorator(baseCoffee)));
		printOrder(customCoffee);
	}
}
