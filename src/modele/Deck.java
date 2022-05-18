package modele;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	private List<Card> cards;

	// Creating a new deck of cards.
	public Deck() {
		cards=new ArrayList<>();
	}

	
	/**
	 * Adds a card to the deck
	 * 
	 * @param c The card to be added to the deck.
	 * @return boolean.
	 */
	public boolean addCard(Card c)
	{
		// Checking if the card is already in the deck. If it is not, it adds it.
		if(!cards.contains(c) && c!= null)
		{
			return cards.add(c);
		}
		return false;
	}

	/**
	 * Remove the card at the given index from the deck
	 * 
	 * @param i The index of the card to remove.
	 * @return boolean.
	 */
	public boolean removeCard(int i)
	{
		if(i>=cards.size()) {
			return false;
		}
		return cards.remove(cards.get(i));
	}

	/**
	 * Remove the card from the deck
	 * 
	 * @param c The card to be removed.
	 * @return A boolean value.
	 */
	public boolean removeCard(Card c)
	{
			return cards.remove(c);
	}

	/**
	 * Replace the card at position x with card c2.
	 * 
	 * @param x the index of the card you want to replace
	 * @param c2 The card you want to replace the card at index x with.
	 * @return The card that was replaced.
	 */
	public Card replaceCard(int x, Card c2)
	{
		return cards.set(x, c2);
	}

	/**
	 * Returns a list of cards
	 * 
	 * @return A list of cards.
	 */
	public List<Card> getListCards() {
		return cards;
	}

	/**
	 * The toString method is a method that returns a string representation of the object.
	 * 
	 * @return The toString method is overridden to return a String representation of the Deck object.
	 */
	@Override
	public String toString() {
		return "Deck [cards=" + cards + "]";
	}
}