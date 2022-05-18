package modele;

import java.util.Random;

/**
 * This class is used to generate a random number between 1 and 6
 */
public class RandomNumGen {
	private int rand;

	// This is a constructor. It is used to generate a random number between 1 and 6.
	public RandomNumGen() {
		Random random = new Random();
		this.rand = random.nextInt(6)+1;
	}

	/**
	 * This function returns a random number
	 * 
	 * @return The random number generated by the method.
	 */
	public int getRand() {
		return rand;
	}

	/**
	 * This function returns a random number
	 * 
	 * @return The random number generated by the method.
	 */
	public String getRandString()
	{
		return String.valueOf(getRand());
	}
}