package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import modele.Personne;
import modele.Player;

class TestPlayer {

	private static Player pla;
	private static Personne p1, p2, p3, p4;

	@BeforeAll
	static void initAll()
	{
		pla = new Player();
		p1 = new Personne("Mathias", Color.AQUA);
		p2 = new Personne("Mathias", Color.BLACK);
		p3 =  p1.clone();
		p4 = new Personne("Mathias", Color.RED);
	}
	
	@Test
	public void testAddPlayer() {
		assertTrue( () -> pla.addPlayer(p1), "failure - the palyer was not added" );
		assertTrue( () -> pla.addPlayer(p2), "failure - the palyer was not added" );
		assertTrue( () -> pla.addPlayer(p4), "failure - the palyer was not added" );
	}

}
