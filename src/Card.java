public class Card implements Comparable<Card> {
	private String mySymbol;
	private int myRank;
	private char mySuit;

	public Card(char rank, char suit) {
		myRank = rank;
		mySuit = suit;
		mySymbol = "" + rank + suit;
	}

	public int getRank() {
		return myRank;
	}

	public char getSuit() {
		return mySuit;
	}

	public String getSymbol() {
		return mySymbol;
	}

	private int convertRank() {
		return 14 - "AKQJT98765432".indexOf(myRank);
	}

	public int compareTo(Card card) {
		return (convertRank() - card.convertRank());
	}

	public boolean equals(Card card) {
		return (mySymbol.equals(card.getSymbol()));
	}

	public String toString() {
		return mySymbol;
	}
}
