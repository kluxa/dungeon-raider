package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class TestCollectTreasure {

	@Test
	void testLevel02a() {
		SimpleLevel level = new SimpleLevel(TestMaze.LEVEL02);
		Treasure treasure = new Treasure();

		level.move(Direction.DOWN);
		assertTrue(level.playerIsAt(2, 1));

		level.move(Direction.DOWN);
		assertTrue(level.playerIsAt(3, 1));

		level.getPlayer().pickUp(treasure);
		assertTrue(level.playerHas(treasure) == 1);

		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		level.move(Direction.UP);
		
		assertTrue(level.playerHas(treasure) > 0);
		assertTrue(level.playerHas(treasure) == 2);

	}

	@Test
	void testLevel02b() {
		SimpleLevel level = new SimpleLevel(TestMaze.LEVEL02);
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
		SimpleLevel level = new SimpleLevel(TestMaze.LEVEL03);
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
