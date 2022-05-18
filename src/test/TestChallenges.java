package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Challenges;

class TestChallenges {

	private static Challenges c;

    @BeforeAll
    static void initAll()
    {
        c = new Challenges();
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testAddChallenge()
    {
        assertTrue( () -> c.addChallenge("Challenges 1"), "failure - the Challenge was not added" );
        assertTrue( () -> c.addChallenge("Challenges 2"), "failure - the Challenge was not added" );
    }
    
    @Test
    public void testAddChallengeDoublon()
    {
        assertFalse( () -> c.addChallenge("Challenges 1"), "failure - the Challenge was added" );
    }
    
    @Test
    public void testRemoveChallengeDoublon()
    {
        assertTrue( () -> c.removeChallenge(1), "failure - the Challenge was added" );
    }


}

