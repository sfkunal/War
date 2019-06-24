import java.util.ArrayList;

public class Deck { 
	ArrayList<Card> cards = new ArrayList<Card>();
	char[] suits = { 'S', 'H', 'C', 'D' };
	char[] ranks = { 'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4',
			'3', '2' };

	public Deck() {
		init();
	}

	private void init() {
		for (int suit = 0; suit < suits.length; suit++)
			for (int rank = 0; rank < ranks.length; rank++)
				cards.add(new Card(ranks[rank], suits[suit]));
	}

	// Note why we need to adhere to abstractions. The shuffle method
	// doesn't really shuffle at all upon inspection. However, in conjunction
	// with getCard(), the behavior of shuffling and getting cards from the
	// deck is perfectly reasonable.
	//
	// While I would not recommend writing shuffle() this way in general,
	// the point is that the Deck abstraction should be treated like a black
	// box, not an open book.

	public void shuffle() {
		cards.clear();
		init();
	}

	// deal(): return one card and remove it from the deck
	public Card deal() {
		return cards.remove((int) (Math.random() * size()));
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public int size() {
		return cards.size();
	}
}
