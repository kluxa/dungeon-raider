package dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class TestItems {

	/**
	*
	*/
	@Test
	void LimitedTest() {
		Maze maze = new Maze(9,9);
		Player player = new Player(maze);
		Limited limited = new Limited(2);

		limited.pickUp(player);



	}

	/**
	*JUnit tests for class Entity
	*/
	@Test 
	void TestEntity() {
		Entity e = new Entity(new Tile(1,1));

		//Test getters and setters
		assertEquals(e.getX(),1);
		assertEquals(e.getY(),1);

		e.setLocation(new Tile(0,1));
		assertEquals(e.getX(),0);
		assertEquals(e.getY(),1);

		//Test entity is collidable
		assertTrue(e.isCollidable());
	}
} 