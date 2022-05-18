package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import modele.Personne;
class TestPersonne {

	private static Personne p0, p1, p2, p3, p4;

	/**
	 * It creates 5 Personne objects, each with a different name and color
	 */
	@BeforeAll
	static void initAll()
	{
		p0 = new Personne("Flavian", Color.RED);
		p1 = new Personne("Mathias", Color.AQUA);
		p2 = new Personne("Mathias", Color.BLACK);
		p3 =  p0.clone();
		p4 = new Personne("Mathias", Color.RED);
	}

	/**
	 * It compares two Personne objects
	 */
	@Test
	public void testClone() {
		compareTwoPersonne(p0, p3);
	}

	/**
	 * > The function `testEquals()` tests the `equals()` method
	 */
	@Test
	public void testEquals() {
		boolean resultEqual = p1.equals(p2);
		Assertions.assertSame(true, resultEqual);
		resultEqual = p0.equals(p4);
		Assertions.assertSame(true, resultEqual);
	}

	/**
	 * It compares two Personne objects
	 * 
	 * @param p1 The first person to compare
	 * @param p2 The expected value.
	 */
	@Test
	public static void compareTwoPersonne(Personne p1, Personne p2) {
		Assertions.assertEquals(p1.getName(), p2.getName());
		Assertions.assertEquals(p2.getColor(), p2.getColor());
	}
	
	@Test
	public void testAddCategory()
    {
        assertTrue( () -> p0.addLstCate("Ideas"), "failure - the category was not added" );
        assertTrue( () -> p0.addLstCate("Computer"), "failure - the category was not added" );
    }
	
	@Test
	public void testAddCategoryExistant()
    {
        assertTrue( () -> p1.addLstCate("Ideas"), "failure - the category was not added" );
        assertFalse( () -> p1.addLstCate("Ideas"), "failure - the category was added" );
    }
}