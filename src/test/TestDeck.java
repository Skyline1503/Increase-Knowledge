package test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Card;
import modele.Category;
import modele.Deck;
import modele.Question;

public class TestDeck {
	private static Deck d;
    private static Card c1, c2, c3;
    private static Question q1, q2, q3;

    @BeforeAll
    static void initAll()
    {
        q1 = new Question("Flavian", Category.COMPUTER, "Computer");
        q2 = new Question("Flavian", Category.HISTORY, "History");
        q3 = new Question("Flavian", Category.IDEAS, "Ideas");
        d = new Deck();
        c1 = new Card();
        c1.addQuestion(q1);
        c1.addQuestion(q2);
        c1.addQuestion(q3);
        c2 = c1.clone();
        c3 = new Card();
        
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testAddCard()
    {
        assertTrue( () -> d.addCard(c1), "failure - the card was not added" );
        assertTrue( () -> d.addCard(c3), "failure - the card was not added" );
    }

    @Test
    public void testAddDoubles()
    {
        assertFalse( () -> d.addCard( c2 ), "failure - the card was added" );
    }

    @Test
    public void testAddNull()
    {
        assertFalse( () -> d.addCard( null ), "failure - the card was added" );
    }

    @Test
    public void testRemoveCard()
    {
        assertTrue( () -> d.removeCard( c3 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveCardInt()
    {
        assertTrue( () -> d.removeCard( 0 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveNonExistantCard()
    {
        assertFalse( () -> d.removeCard( c2 ), "failure - the card was removed" );
    }

    @Test
    public void testRemoveNullCard()
    {
        assertFalse( () -> d.removeCard( null ), "failure - the card was removed" );
    }

    @Test
    public void testRemoveNonExistantCardInt()
    {
        assertFalse( () -> d.removeCard( 0 ), "failure - the card was removed" );
    }

    @Test
    public void testRemoveNonExistantCardIntBiggerThanDeck()
    {
        assertFalse( () -> d.removeCard( 10 ), "failure - the card was removed" );
    }


    @AfterEach
    void tearDown()
    {
    }

    @AfterAll
    static void tearDownAll()
    {
        d = null;
        c1 = null;
        c2 = null;
    }
}

