package json;

import modele.Deck;

/**
 * This class is used to call the json file and create the deck of cards
 */
public class Link {
	private static Deck deck;	

	/**
	 * Reads the file and creates a deck of cards.
	 */
	public static void jsonCall() {
		// Contains all cards to use in game
		if (deck == null) {
			deck = new Deck();
		}		
		Read.readFile();		
		// Write.writeJson(getDeck().getListCard());
	}

	/**
	 * Returns the deck
	 * 
	 * @return A deck of cards.
	 */
	public static Deck getDeck() {
		return deck;
	}
	
	/**
	 * Returns the deck
	 * 
	 * @return A deck of cards.
	 */
	public static void setDeck(Deck deck) {
		Link.deck = deck;
	}
}