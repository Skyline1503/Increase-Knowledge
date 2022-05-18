package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player class is a class that contains a list of players
 */
public class Player {
	private List<Personne> lstPlayers;

	// Creating a new list of players.
	public Player() {
		lstPlayers = new ArrayList<>();
	}

	/**
	 * Adds a player to the list if there is room for him
	 * 
	 * @param p The personne to add to the list.
	 * @return A boolean value.
	 */
	public boolean addPlayer(Personne p)
	{
		if(!lstPlayers.contains(p) && lstPlayers.size()<8)
		{
			return lstPlayers.add(p.clone());
		}
		return false;
	}

	/**
	 * Remove a player from the list
	 * 
	 * @param p The personne to be removed.
	 * @return A boolean value.
	 */
	public boolean removePlayer(Personne p)
	{
		return lstPlayers.remove(p);
	}

	/**
	 * Returns the number of players in the game
	 * 
	 * @return The size of the players arraylist.
	 */
	public int getSizePlayer()
	{
		return lstPlayers.size();
	}

	/**
	 * Returns true if the given person is in the list of players
	 * 
	 * @param p The personne to check for.
	 * @return A boolean value.
	 */
	public boolean isContains(Personne p)
	{
		return lstPlayers.contains(p);
	}

	/**
	 * Remove all elements from the list
	 * 
	 * @return A boolean value.
	 */
	public boolean removeAllList() {
		return lstPlayers.removeAll(lstPlayers);
	}

	/**
	 * Returns a list of all the players in the game
	 * 
	 * @return A list of players.
	 */
	public List<Personne> getLstPlayers() {
		return lstPlayers;
	}

	/**
	 * The toString method is a method that returns a string representation of the object.
	 * 
	 * @return The string "Liste de joueur :\n"+players;
	 */
	@Override
	public String toString() {
		return "Liste de joueur :\n"+lstPlayers;
	}
}