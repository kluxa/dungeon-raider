package dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestDungeon {

	@Test
	void TestBoulder() {
		Boulder b = new Boulder(new Tile(1,1));
		Maze maze = new Maze(9,9);
		Player p = new Player(maze);

		//Test boulder toChar
		assertEquals(b.toChar(), 'O');
	}

	@Test 
	void TestTile() {
		Tile t = new Tile(1,1);
		Maze maze = new Maze(9,9);

		//Test basic functions
		assertEquals(t.getX(), 1);
		assertEquals(t.getY(), 1);

		//Test item list 
		UnlitBomb bomb = new UnlitBomb();
		t.addItem(bomb);
		assertTrue(t.getItems().size() > 0);

		//Test tile when enemy arrives and ignores items
		Strategist s = new Strategist(t);
		t.arrive(s);
		assertTrue(t.getItems().size() > 0);

		//Test tile when player arrives to it and picks up the items
		Player player = new Player(maze);
		t.arrive(player);
		assertTrue(t.getItems().size() == 0);
		assertTrue(player.numItemOfType(bomb) > 0);

		//Test item drop function
		t.drop(bomb);
		assertTrue(t.getItems().size() > 0);

		//Test tile clearance
		t.clearTile();
		assertTrue(t.getItems().size() == 0);

	}
}