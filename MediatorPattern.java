public class MediatorPattern {
	interface AuctionMediator {
		void addBidder(Bidder bidder);
		void placeBid(Bidder bidder, double amount);
	}

	static class Bidder {
		private final String name;
		private final AuctionMediator mediator;

		Bidder(String name, AuctionMediator mediator) {
			this.name = name;
			this.mediator = mediator;
			mediator.addBidder(this);
		}

		void bid(double amount) {
			System.out.println(name + " bids $" + amount);
			mediator.placeBid(this, amount);
		}

		void receiveBidUpdate(String bidderName, double amount) {
			if (!name.equals(bidderName)) {
				System.out.println(name + " is notified: " + bidderName
						+ " is now the highest bidder at $" + amount);
			}
		}

		String getName() {
			return name;
		}
	}

	static class Auction implements AuctionMediator {
		private final java.util.List<Bidder> bidders = new java.util.ArrayList<>();
		private Bidder highestBidder;
		private double highestBid;

		@Override
		public void addBidder(Bidder bidder) {
			if (bidder == null) {
				throw new IllegalArgumentException("Bidder cannot be null");
			}
			bidders.add(bidder);
		}

		@Override
		public void placeBid(Bidder bidder, double amount) {
			if (!bidders.contains(bidder)) {
				throw new IllegalArgumentException("Bidder is not registered in this auction");
			}
			if (amount <= highestBid) {
				System.out.println("Bid rejected. The bid must be higher than $" + highestBid);
				return;
			}

			highestBid = amount;
			highestBidder = bidder;
			for (Bidder registeredBidder : bidders) {
				registeredBidder.receiveBidUpdate(bidder.getName(), amount);
			}
		}

		void announceWinner() {
			if (highestBidder == null) {
				System.out.println("The auction ended without any bids.");
				return;
			}
			System.out.println("Winner: " + highestBidder.getName()
					+ " with a bid of $" + highestBid);
		}
	}

	public static void main(String[] args) {
		Auction auction = new Auction();
		Bidder alice = new Bidder("Alice", auction);
		Bidder bob = new Bidder("Bob", auction);
		Bidder charlie = new Bidder("Charlie", auction);

		alice.bid(100.0);
		bob.bid(125.0);
		charlie.bid(120.0);
		charlie.bid(150.0);

		auction.announceWinner();
	}
}
