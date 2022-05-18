package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {
	private List<Question> questions;

	// It creates a new Card object with an empty list of questions.
	public Card() {
		questions = new ArrayList<>();
	}
	

	/**
	 * Adds a question to the list of questions if the list is not full and the question is not already in
	 * the list
	 * 
	 * @param q The question to be added.
	 * @return A boolean value.
	 */
	public boolean addQuestion(Question q)
	{
		if(!questions.contains(q) && questions.size()<=5 && q!=null)
		{
			return questions.add(q);
		}
		return false;
	}

	/**
	 * Remove the question at index i from the list of questions
	 * 
	 * @param i The index of the question to be removed.
	 * @return A boolean value.
	 */
	public boolean removeQuestion(int i)
	{
		if(questions.size() >= i)
		{
			return questions.remove(questions.get(i));
		}
		return false;
	}

	/**
	 * Remove a question from the list of questions
	 * 
	 * @param q The question to be removed.
	 * @return A boolean value.
	 */
	public boolean removeQuestion(Question q)
	{
		if(questions.contains(q))
		{
			return questions.remove(q);
		}
		return false;
	}

	// It overrides the hashCode method of the Object class.
	@Override
	public int hashCode() {
		return Objects.hash(questions);
	}

	// This method is used to compare two objects.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(questions, other.questions);
	}

	/**
	 * Returns a copy of the current object
	 * 
	 * @return Nothing is being returned.
	 */
	public Card clone()
	{
		return new Card();
	}

	/**
	 * This function returns a list of questions
	 * 
	 * @return A list of Question objects.
	 */
	public List<Question> getListQuestion(){
		return questions;
	}

	// Overriding the toString method of the Object class.
	@Override
	public String toString() {
		return "Card [questions=" + questions + "]";
	}
}