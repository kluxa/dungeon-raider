package dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestEnemy {

	@Test
	void TestEnemy() {

		//Test Strategist enemy
		Maze maze = new Maze(9,9);
		Player player = new Player(maze);
		Tile t = new Tile(1,1);
		Strategist s = new Strategist(t);

		s.collide(player);
		assertFalse(player.isAlive());

		s.getBlownUp();
		assertTrue(s.isAlive());

		s.die();
		assertFalse(s.isAlive());

		//Test Hunter enemy
		t.clearTile();
		Hunter h = new Hunter(t);

		
		assertEquals(h.toChar(), 'H');
}