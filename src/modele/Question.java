package modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A question is composed of an author, a category, an interrogation and a set of choices
 */
public class Question {
	private String author;
	private Category category;
	private String interrogation;
	private Map<String, Boolean> choices;

	
	// The constructor of the class Question. It creates a new Question with the author, the category and
	// the interrogation.
	public Question(String author, Category category, String interrogation) {
		this.author = author;
		this.category = category;
		this.interrogation = interrogation;
		choices = new HashMap<String, Boolean>();
	}

	/**
	 * It adds a new choice to the question.
	 * 
	 * @param reponses The answer to the question.
	 * @param isGoodAnswer a boolean that indicates whether the answer is correct or not.
	 */
	public void addAnswer(String reponses, boolean isGoodAnswer)
	{
		if(choices.size()<3)
		{
			choices.put(reponses, isGoodAnswer);
		}
	}

	/**
	 * It returns the author of the question.
	 * 
	 * @return The author of the question.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Returns the category of the current node
	 * 
	 * @return The category of the question.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * It sets the category of the question.
	 * 
	 * @param category The category of the product.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Returns the interrogation string
	 * 
	 * @return The interrogation variable.
	 */
	public String getInterrogation() {
		return interrogation;
	}

	/**
	 * It sets the interrogation variable to the value of the interrogation parameter.
	 * 
	 * @param interrogation The name of the interrogation.
	 */
	public void setInterrogation(String interrogation) {
		this.interrogation = interrogation;
	}

	/**
	 * Returns a map of the choices made by the user
	 * 
	 * @return A map of strings to booleans.
	 */
	public Map<String, Boolean> getChoices() {
		return choices;
	}

	/**
	 * It sets the choices of the question.
	 * 
	 * @param choices A map of the choices to be displayed to the user. The key is the choice text, and
	 * the value is whether or not the choice is selected.
	 */
	public void setChoices(Map<String, Boolean> choices) {
		this.choices = choices;
	}

	/**
	 * This function returns a string representation of the question
	 * 
	 * @return A String
	 */
	@Override
	public String toString() {
		return "Question [author=" + author + ", category=" + category.getColor() + ", interrogation=" + interrogation
				+ ", choices=" + choices + "]";
	}

	/**
	 * Clones the question
	 * 
	 * @return A new Question object.
	 */
	public Question clone()
	{
		return new Question(author, category, interrogation);
	}

	/**
	 * The hashCode method returns the hash code of the category and interrogation
	 * 
	 * @return The hash code of the category and interrogation.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(category, interrogation);
	}

	/**
	 * This function returns true if the category and interrogation are equal
	 * 
	 * @param obj the object to compare to this object
	 * @return A boolean value.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return category == other.category && Objects.equals(interrogation, other.interrogation);
	}
}