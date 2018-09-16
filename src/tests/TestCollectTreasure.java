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
		assertTrue(level.playerHas(treasure) == 1);

		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		level.move(Direction.UP);

		level.getPlayer().pickUp(treasure);
		assertTrue(level.playerHas(treasure) > 0);
		assertTrue(level.playerHas(treasure) == 2);

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

		level.move(Direction.DOWN);
		level.getPlayer().pickUp(treasure);

		assertFalse(level.itemIsAt(treasure, 4, 3));
		assertTrue(level.playerHas(treasure) > 0);
		assertTrue(level.itemIsAt(treasure, 2, 4));
	}

	@Test 
	void testCollectKeyA() {
		Level level = new Level(TestMaze.LEVEL03);
		Key gKey = new Key("green");
		Key rKey = new Key("red");

		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		level.getPlayer().pickUp(gKey);

		assertTrue(level.playerHas(gKey) != 0);
		assertFalse(level.itemIsAt(gKey, 2, 4));

		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		assertTrue(level.itemIsAt(rKey, 4, 3));
		level.move(Direction.UP);

		level.getPlayer().pickUp(gKey);
		assertTrue(level.playerHas(rKey) > 0);
	}
}
