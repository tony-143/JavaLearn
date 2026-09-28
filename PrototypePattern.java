public class PrototypePattern {
	private abstract static class PlayerCharacter implements Cloneable {
		private final String name;
		private int level;
		private String weapon;

		PlayerCharacter(String name, int level, String weapon) {
			this.name = name;
			this.level = level;
			this.weapon = weapon;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public void setWeapon(String weapon) {
			this.weapon = weapon;
		}

		public void showStats() {
			System.out.println("Character: " + name + " (" + getClass().getSimpleName() + ")");
			System.out.println("Level: " + level);
			System.out.println("Weapon: " + weapon);
			showAbility();
		}

		@Override
		public PlayerCharacter clone() throws CloneNotSupportedException {
			return (PlayerCharacter) super.clone();
		}

		protected abstract void showAbility();
	}

	private static class Warrior extends PlayerCharacter {
		Warrior(String name, int level, String weapon) {
			super(name, level, weapon);
		}

		@Override
		public Warrior clone() throws CloneNotSupportedException {
			return (Warrior) super.clone();
		}

		@Override
		protected void showAbility() {
			System.out.println("Ability: Shield Bash");
		}
	}

	private static class Mage extends PlayerCharacter {
		Mage(String name, int level, String weapon) {
			super(name, level, weapon);
		}

		@Override
		public Mage clone() throws CloneNotSupportedException {
			return (Mage) super.clone();
		}

		@Override
		protected void showAbility() {
			System.out.println("Ability: Fireball");
		}
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Warrior warriorPrototype = new Warrior("Knight", 10, "Iron sword");
		Warrior copiedWarrior = warriorPrototype.clone();
		copiedWarrior.setLevel(12);
		copiedWarrior.setWeapon("Dragon sword");

		System.out.println("Original warrior:");
		warriorPrototype.showStats();
		System.out.println("Copied warrior:");
		copiedWarrior.showStats();

		Mage magePrototype = new Mage("Wizard", 8, "Oak staff");
		Mage copiedMage = magePrototype.clone();
		System.out.println("Copied mage:");
		copiedMage.showStats();
	}
}
