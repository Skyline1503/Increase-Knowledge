package modele;

import java.util.ArrayList;
import java.util.List;

public class Challenges{
	private List<String> listChallenges;
	// Creating a new list of challenges.

	public Challenges() {
		listChallenges = new ArrayList<String>();
	}

	/**
	 *  This function returns the list of challenges
	 * 
	 * @return A list of strings
	 */
	public List<String> getListChallenges() {
		return listChallenges;
	}


	/**
	 * The function returns a string representation of the object
	 * 
	 * @return The list of challenges.
	 */
	@Override
	public String toString() {
		return "Challenges [listChallenges=" + listChallenges + "]";
	}

	/**
	 * If the list of challenges does not contain the challenge c, then add it to the list.
	 * 
	 * @param c The challenge to be added to the list of challenges.
	 * @return The method is returning a boolean value.
	 */
	public boolean addChallenge(String c)
	{
		// Checking if the list of challenges contains the challenge c. If it does not, it adds it to the
		// list.
		if(!listChallenges.contains(c))
		{
			return listChallenges.add(c);
		}
		return false;
	}

	/**
	 *  Removes the challenge at the given index from the list of challenges
	 * 
	 * @param i The index of the challenge to remove.
	 * @return A boolean value.
	 */
	public boolean removeChallenge(int i)
	{
		return listChallenges.remove(listChallenges.get(i));
	}
}