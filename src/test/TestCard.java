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
import modele.Question;

public class TestCard {
    private static Card c;
    private static Question q1, q2, q3, q4, q5, q6, q7;

    @BeforeAll
    static void initAll()
    {
        q1 = new Question("Flavian", Category.COMPUTER, "COMPUTER");
        q2 = new Question("Flavian", Category.HISTORY, "HISTORY");
        q4 = new Question("Flavian", Category.IDEAS, "IDEAS");
        q5 = new Question("Flavian", Category.SCIENCE, "SCIENCE");
        q6 = new Question("Flavian", Category.LITERATURE, "LITERATURE");
        q7 = new Question("Flavian", Category.PLANET, "PLANET");
        q3 = q1.clone();
        c = new Card();
        c.addQuestion(q4);
        c.addQuestion(q5);
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testAddQuestion()
    {
        assertTrue( () -> c.addQuestion(q6), "failure - the question was not added" );
        assertTrue( () -> c.addQuestion(q7), "failure - the question was not added" );
    }

    @Test
    public void testAddDoubles()
    {
    	assertTrue(() -> c.addQuestion( q1 ), "failure - the question was not added" );
        assertFalse( () -> c.addQuestion( q3 ), "failure - the question was added" );
    }

    @Test
    public void testAddNull()
    {
        assertFalse( () -> c.addQuestion( null ), "failure - the Question was added" );
    }
    

    @Test
    public void testRemoveQuestion()
    {
        assertTrue( () -> c.removeQuestion( q1 ), "failure - the Question was not removed" );
    }

    @Test
    public void testRemoveQuestionInt()
    {
    	assertTrue(() -> c.addQuestion( q1 ), "failure - the question was added" );
        assertTrue( () -> c.removeQuestion( 0 ), "failure - the Question was not removed" );
        assertFalse( () -> c.removeQuestion( 18 ), "failure - the Question was not removed" );
    }

    @Test
    public void testRemoveNonExistantQuestion()
    {
        assertFalse( () -> c.removeQuestion( q2 ), "failure - the Question was removed" );
    }

    @Test
    public void testRemoveNullQuestion()
    {
        assertFalse( () -> c.removeQuestion( null ), "failure - the Question was removed" );
    }

    @Test
    public void testRemoveNonExistantQuestionInt()
    {
        assertFalse( () -> c.removeQuestion( 8 ), "failure - the Question was removed" );
    }

    @Test
    public void testRemoveNonExistantQuestionIntBiggerThanDeck()
    {
        assertFalse( () -> c.removeQuestion( 10 ), "failure - the Question was removed" );
    }



    @AfterAll
    static void tearDownAll()
    {
        c = null;
        q1 = null;
        q2 = null;
    }
}

