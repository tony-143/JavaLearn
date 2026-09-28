public class StrategeyPattern {
	interface PaymentStrategy {
		void pay(double amount);
	}

	static class CreditCardPayment implements PaymentStrategy {
		private final String cardNumber;

		CreditCardPayment(String cardNumber) {
			this.cardNumber = cardNumber;
		}

		@Override
		public void pay(double amount) {
			System.out.println("Paid $" + amount + " using credit card ending in " + lastFourDigits());
		}

		private String lastFourDigits() {
			return cardNumber.substring(cardNumber.length() - 4);
		}
	}

	static class PayPalPayment implements PaymentStrategy {
		private final String email;

		PayPalPayment(String email) {
			this.email = email;
		}

		@Override
		public void pay(double amount) {
			System.out.println("Paid $" + amount + " using PayPal account " + email);
		}
	}

	static class UpiPayment implements PaymentStrategy {
		private final String upiId;

		UpiPayment(String upiId) {
			this.upiId = upiId;
		}

		@Override
		public void pay(double amount) {
			System.out.println("Paid $" + amount + " using UPI ID " + upiId);
		}
	}

	static class PaymentProcessor {
		private PaymentStrategy paymentStrategy;

		void setPaymentStrategy(PaymentStrategy paymentStrategy) {
			this.paymentStrategy = paymentStrategy;
		}

		void checkout(double amount) {
			if (paymentStrategy == null) {
				throw new IllegalStateException("Choose a payment method first");
			}

			paymentStrategy.pay(amount);
		}
	}

	public static void main(String[] args) {
		PaymentProcessor processor = new PaymentProcessor();

		processor.setPaymentStrategy(new CreditCardPayment("1234567812345678"));
		processor.checkout(49.99);

		processor.setPaymentStrategy(new PayPalPayment("player@example.com"));
		processor.checkout(19.99);

		processor.setPaymentStrategy(new UpiPayment("player@upi"));
		processor.checkout(9.99);
	}
}
