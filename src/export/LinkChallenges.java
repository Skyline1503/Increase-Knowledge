package export;

import java.io.IOException;

import modele.Challenges;

public class LinkChallenges {
	// Creating a new challenges object.
	private static Challenges challenges;	

	/**
	 * It checks if the challenges object is null, if it is, it creates a new challenges object, then it
	 * reads the file and catches any errors that may occur
	 */
	public static void txtCall() {
		// Checking if the challenges object is null, if it is, it creates a new challenges object.
		if (challenges == null) {
			challenges = new Challenges();
		}		
		// Reading the file and catching any errors that may occur.
		try {
			ReadChallenges.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This function returns the challenges object.
	 * 
	 * @return The challenges object.
	 */
	public static Challenges getChallenges() {
		return challenges;
	}

	/**
	 * > This function sets the challenges variable to the challenges parameter
	 * 
	 * @param challenges The Challenges object that contains all the challenges.
	 */
	public static void setChallenges(Challenges challenges) {
		LinkChallenges.challenges = challenges;
	}
}