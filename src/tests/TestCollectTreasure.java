package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

class TestCollectTreasure {

	@Test
	void testLevel02a() {
		Level level = new Level(TestMaze.LEVEL02);
		Treasure treasure = new Treasure();

		level.move(Direction.DOWN);
		assertTrue(level.playerIsAt(2,3));

		level.move(Direction.DOWN);
		assertTrue(level.playerIsAt(2,4));
		assertTrue("There is a treasure to pick up",level.itemIsAt(treasure, 2, 4));

		level.getPlayer().pickUp(treasure);
		assertTrue(level.playerHas(treasure));

		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		level.move(Direction.UP);

		level.getPlayer().pickUp(treasure);
		assertTrue(level.playerHas(treasure) > 0);
		assertTrue(playerHas(treasure) > 1);

		level.completeLevel();
		assertTrue(level.isComplete());

	}

	@Test
	void testLevel02b() {
		Level level = new Level(TestMaze.LEVEL02);
		Treasure treasure = new Treasure();

		level.move(Direction.DOWN);
		level.move(Direction.LEFT);
		level.move(Direction.LEFT);

		level.getPlayer().pickUp(treasure);

		assertFalse(level.playerHas(treasure) == 0);
		assertFalse(level.isComplete());

		level.move(Direction.DOWN);
		level.getPlayer().pickUp(treasure);

		assertFalse(level.itemIsAt(treasure, 4, 3));
		assertTrue(level.playerHas(treasure) > 0);
		assertTrue(level.itemIsAt(treasure, 2, 4));
	}
}
