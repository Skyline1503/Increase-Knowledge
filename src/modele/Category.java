package modele;

import java.util.ArrayList;
import java.util.List;

public enum Category {

	// This is a enum class.
	IDEAS("#44078C"),
	SCIENCE("#E88325"),
	PLANET("#008000"),
	HISTORY("#CC14CC"),
	LITERATURE("#0080FF"),
	COMPUTER("#733B10");

	// A variable that is private to the class.
	private String color;
	private static List<Category> categ;

	// This is a constructor.
	Category(String color) {
		this.color=color;
	}

	/**
	 * Returns the color of the Category
	 * 
	 * @return The color of the Category.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * It returns a list of categories
	 * 
	 * @return A list of categories.
	 */
	public static List<Category> listCateg()
	{
		categ=new ArrayList<Category>();
		categ.add(Category.IDEAS);
		categ.add(Category.SCIENCE);
		categ.add(Category.PLANET);
		categ.add(Category.HISTORY);
		categ.add(Category.LITERATURE);
		categ.add(Category.COMPUTER);
		return categ;
	}

	// It returns the category at the index i.
	public static Category getCateList(int i)
	{
		return (Category) categ.get(i);
	}
}