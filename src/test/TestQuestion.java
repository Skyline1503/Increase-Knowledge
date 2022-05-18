package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Category;
import modele.Question;

/**
 * It tests the equality of three questions, q1, q2, and q3
 */
class TestQuestion {
	private static Question q1, q2, q3;

	/**
	 * It creates three questions, q1, q2, and q3. q1 and q3 are different objects, but they have the
	 * same content. q2 is a clone of q1.
	 */
	@BeforeAll
	static void initAll()
	{
		q1 = new Question( "Giorgio Caculli", Category.COMPUTER,"What does RAM stand for?");
		q2 = q1.clone();
		q3 = new Question( "Giorgio Caculli", Category.COMPUTER, "What does RAM stand for?");
	}

	/**
	 * > The `@BeforeEach` annotation tells JUnit to run the `init()` function before each test
	 */
	@BeforeEach
	void init()
	{
	}

	/**
	 * The function `testQuestion()` tests the equality of two strings, `q1` and `q2`, and `q1` and
	 * `q3`
	 */
	@Test
	public void testEquals()
	{
		assertEquals( q1, q2, "failure - strings are not equal" );
		assertEquals( q1, q3, "failure - strings are not equal" );
	}

	/**
	 * TestSameAuthor() tests that the author of q1 is the same as the author of q2 and q3.
	 */
	@Test
	public void testSameAuthor()
	{
		assertEquals( q1.getAuthor(), q2.getAuthor(), "failure - the authors are not the same" );
		assertEquals( q1.getAuthor(), q3.getAuthor(), "failure - the authors are not the same" );
	}

	/**
	 * This function tests the category of the questions
	 */
	@Test
	public void testCategory()
	{
		assertEquals( q1.getCategory(), q2.getCategory(), "failure - the authors are not the same" );
		assertEquals( q1.getCategory(), q3.getCategory(), "failure - the authors are not the same" );
	}

	/**
	 * The function tests the getInterrogation() method of the Question class
	 */
	@Test
	public void testInterrogation()
	{
		assertEquals( q1.getInterrogation(), q2.getInterrogation(), "failure - the authors are not the same" );
		assertEquals( q1.getInterrogation(), q3.getInterrogation(), "failure - the authors are not the same" );
	}

	/**
	 * The function tests the toString() method of the Quote class
	 */
	@Test
	public void testToString()
	{
		assertEquals( q1.toString(), q2.toString(), "failure - the authors are not the same" );
		assertEquals( q1.toString(), q3.toString(), "failure - the authors are not the same" );
	}
	
	/**
	 * 
	 */
	@Test
//	public void testChoice()
//	{
//		assertTrue( q1.addAnswer("1", false), "failure - the choices are not the same" );
//		assertTrue( q1.addAnswer("2", true), "failure - the choices are not the same" );
//		assertTrue( q1.addAnswer("3", false), "failure - the choices are not the same" );
//		assertFalse( q1.addAnswer("4", false), "failure - the choices are not the same" );
//		assertTrue( q2.addAnswer("1", false), "failure - the choices are not the same" );
//		assertTrue( q2.addAnswer("2", true), "failure - the choices are not the same" );
//		assertFalse( q2.addAnswer("3", true), "failure - the choices are not the same" );
//	}

	/**
	 * > The `tearDown()` function is called after each test case
	 */
	@AfterEach
	void tearDown()
	{
	}

	/**
	 * > The `@AfterAll` annotation is used to mark a method to be executed after all tests in the
	 * current class have been run
	 */
	@AfterAll
	static void tearDownAll()
	{
		q1 = null;
		q2 = null;
		q3 = null;
	}
}