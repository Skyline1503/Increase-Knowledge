package modele;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.scene.paint.Color;

/**
 * A person has a name, a color, a total score, a total dice, a list of categories and a list of images
 * of categories
 */
public class Personne {
	private String name;
	private Color color;
	private int totalDice;
	private int totalScore;
	private int nbCorrect;
	private int nbIncorrect;
	private int position;
	private List<String> lstCateg;
	private Pawn pawn;

	// The constructor of the class Personne. It is used to create a new person.
	public Personne(String nom,Color color) {
		this.name = nom;
		this.color = color;
		this.totalDice = 0;
		this.position=0;
		lstCateg = new ArrayList<String>();
		this.pawn = new Pawn(getColor());
	}

	/**
	 * It returns the value of the variable name.
	 * 
	 * @return The getter method returns the value of the instance variable name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the color of the object
	 * 
	 * @return The color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * This function returns a string representation of the object
	 * 
	 * @return The id, nom and color of the object.
	 */
	@Override
	public String toString() {
		return name+ " " +color;
	}

	/**
	 * Returns the total number of dice in the game
	 * 
	 * @return The total number of dice.
	 */
	public int getTotalDice() {
		return totalDice;
	}

	/**
	 * This function returns the number of correct answers
	 * 
	 * @return The number of correct answers.
	 */
	public int getNbCorrect() {
		return nbCorrect;
	}

	/**
	 * It increments the number of correct answers
	 */
	public void augNbCorrect() {
		nbCorrect++;
	}

	/**
	 *  This function returns the number of incorrect guesses
	 * 
	 * @return The number of incorrect answers.
	 */
	public int getNbIncorrect() {
		return nbIncorrect;
	}

	/**
	 *  This function increments the number of incorrect answers by one
	 */
	public void augNbIncorrect() {
		nbIncorrect++;
	}

	/**
	 * It adds the totalDice to the totalDice variable.
	 * 
	 * @param totalDice The total number of dice to roll.
	 */
	public void setTotalDice(int totalDice) {
		this.totalDice = getTotalDice()+totalDice;
	}

	/**
	 * Create a new object of the same class as this object and return it
	 * 
	 * @return A new Personne object.
	 */
	public Personne clone()
	{
		return new Personne(name, color);
	}

	/**
	 * The hashCode method returns a hash code for the object
	 * 
	 * @return The hash code of the object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(color, name);
	}

	/**
	 * The equals method is a method that is used to determine whether two objects are equal.
	 * 
	 * @param obj the object to compare to.
	 * @return The boolean value of the equals method.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		return Objects.equals(color, other.color) || Objects.equals(name, other.name);
	}

	/**
	 * Returns the total score of the game
	 * 
	 * @return The total score of the player.
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * It adds the score to the total score.
	 * 
	 * @param score The score to be added to the total score.
	 */
	public void setTotalScore(int score) {
		this.totalScore = score+getTotalScore();
	}

	/**
	 * This function returns a list of all the categories in the database
	 * 
	 * @return A list of categories.
	 */
	public List<String> getLstCateg() {
		return lstCateg;
	}

	/**
	 * This function adds a category to the list of categories if it doesn't already exist and if it's
	 * not the default category
	 * 
	 * @param c The category to add
	 * @return A boolean value.
	 */
	public boolean addLstCate(String c)
	{
		if(!lstCateg.contains(c) && !c.equalsIgnoreCase("CHALLENGES"))
		{
			return lstCateg.add(c);
		}
		return false;
	}


	/**
	 * This function returns the pawn that is associated with this player.
	 * 
	 * @return The pawn object.
	 */
	public Pawn getPawn() {
		return pawn;
	}

	// A comparator that compares the total score of two players.
	public static Comparator<Personne> SCOORE = new Comparator<Personne>() {
		@Override
		public int compare(Personne o1, Personne o2) {
			return o1.getTotalScore() - o2.getTotalScore();
		}
	};

	/**
	 * Returns the position of the player.
	 * 
	 * @return The position of the current node.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * This function sets the position of the player to the position passed in.
	 * 
	 * @param position The position of the item in the list.
	 */
	public void setPosition(int position) {
		this.position = position+0;
	}
}