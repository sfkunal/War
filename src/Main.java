import java.util.*;

public class Main {

	public static final int NUM_PLAYERS = 2; //number of players
	public static Queue<Card>[] currentHands = new Queue[NUM_PLAYERS]; 
	//array of player's currentHands.
	public static ArrayList<Card> cardsOnTable = new ArrayList<Card>(); 
	//cards being betted on
	private static Card p0Card; //name for card used for comparison
	private static Card p1Card; //name for card used for comparison

	public static void main(String[] args) {
		assigncurrentHands();//assigns currentHands to each player
		deal(); //deals 26 cards to each player's hand
		playWar(); //playsRounds until a player wins. 
	}

	private static void assigncurrentHands() {
		for(int i = 0; i < NUM_PLAYERS; i++) {
			currentHands[i] = new Queue<Card>();
			//creates a Queue of Cards in each slot of currentHands
		}
	}

	private static void playWar() {
		System.out.println("p0:" + currentHands[0].size() + 
				" p1: " + currentHands[1].size()); 
		//outputs initial sizes
		System.out.println();
		while(true) {

			if(currentHands[0].size() == 1 && 
					currentHands[1].size() == 1 && 
					currentHands[0].peek().compareTo(currentHands[1].peek()) == 0) {
				System.out.println("THERE IS A TIE. PLAY AGAIN");
				System.exit(0);
				//the rare case that both last cards left are of equal value
			}
			if(currentHands[0].size() == 0) {
				System.out.println("GAME OVER! Player 1 WINS");
				System.exit(0);
				//p1 win case
			}
			if(currentHands[1].size() == 0) {
				System.out.println("GAME OVER! Player 0 WINS");
				System.exit(0);
				//p0 win case
			}
			playRound();
			//plays round
			System.out.println("p0:" + currentHands[0].size() + 
					" p1:" + currentHands[1].size());
			System.out.println(cardsOnTable.size());
			//outputs updated sizes after each round
			System.out.println();
			System.out.println();
			//empty lines for looks
		}
	}

	private static void playRound() {
		Card p0Card = currentHands[0].remove();
		Card p1Card = currentHands[1].remove();
		//cards selected for comparison

		if(p0Card.compareTo(p1Card) > 0) {
			currentHands[0].put(p0Card);
			currentHands[0].put(p1Card);
			System.out.println("Player 0 won! "
					+ "Their card was " + p0Card.toString() + 
					", and Player 1's card was " + 
					p1Card.toString() + ".");
			//p0 wins and earns both cards
		} else if(p0Card.compareTo(p1Card) < 0) {
			currentHands[1].put(p1Card);
			currentHands[1].put(p0Card);
			System.out.println("Player 1 won! "
					+ "Their card was " + p1Card.toString() + 
					", and Player 0's card was " + 
					p0Card.toString() + ".");
			//p1 wins and earns both cards
		} else {
			cardsOnTable.add(p0Card);
			cardsOnTable.add(p1Card);
			System.out.println("WAR!");
			war();
			/*if war, two cards are added to the 
			 * cardsOnTable list, and war is called*/

		}
	}

	private static void war() {

		if(currentHands[0].size() == 0 || currentHands[1].size() == 0) {return; }

		if(currentHands[0].size() <= 3 || currentHands[1].size() <= 3) {
			if(currentHands[0].size() <= 3) {
				for(int i = 0; i < currentHands[0].size() - 1; i++) {
					cardsOnTable.add(currentHands[0].remove());
				}
				p0Card = currentHands[0].remove();
				//check if p0Hand has less than 3 cards
			}
			if(currentHands[1].size() <= 3) {
				for(int i = 0; i < currentHands[1].size() - 1; i++) {
					cardsOnTable.add(currentHands[1].remove());
				}
				p1Card = currentHands[1].remove();
				//check if p1Hand has less than 3 cards and 
				//accounts for so in betting list
			}
		} else {
			for(int i = 0; i < 3; i++) {
				cardsOnTable.add(currentHands[0].remove());
				cardsOnTable.add(currentHands[1].remove());
				//6 cards are added to cardsOnTable for the bet
			}
			p0Card = currentHands[0].remove();
			p1Card = currentHands[1].remove();
			//another two cards are drawn for comparison
			cardsOnTable.add(p0Card);
			cardsOnTable.add(p1Card);
			//and added to cardsOnTable for the bet
		}
		if(p0Card.compareTo(p1Card) > 0) {
			//if p0 wins war
			for(int i = 0; i < cardsOnTable.size(); i++) {
				currentHands[0].put(cardsOnTable.get(i));
				//p0 gets all cards in cardsOnTable
			}
			System.out.println("Player 0 wins the WAR with " 
					+ p0Card.toString() + ". Player 1 had " + 
					p1Card.toString());
			cardsOnTable.clear();
			//cardsOnTable is cleared
		} else if(p0Card.compareTo(p1Card) < 0) {
			//if p1 wins war
			for(int i = 0; i < cardsOnTable.size(); i++) {
				currentHands[1].put(cardsOnTable.get(i));
				//p1 gets all cards in cardsOnTable
			}				
			System.out.println("Player 1 wins the WAR with " 
					+ p1Card.toString() + ". Player 0 had " + 
					p0Card.toString());
			cardsOnTable.clear();
			//cardsOnTable is cleared
		} else {
			/*this means that there is a tie yet 
			 * again, and war is called*/
			System.out.println("WAR! AGAIN!");
			war();
			/*i like this way of doing it, i don't 
			 * clear the board, so the original cards are
			 *  at stake like they are supposed to be. */
		}
	}	

	private static void deal() {
		Deck deck = new Deck();
		//deck is auto shuffled
		for(int i = 0; i < 26; i++) {
			currentHands[0].put(deck.deal());
			//p0Hand gets 26 cards
			currentHands[1].put(deck.deal());
			//p1Hand gets 26 cards
		}
	}
}
