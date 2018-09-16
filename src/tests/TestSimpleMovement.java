package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

class TestSimpleMovement {

	@Test
	void simpleMovement() {
		Level level = new Level(TestMaze.LEVEL01);
		assert level.playerIsAt(1, 1);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(1, 2);
		
		level.move(Direction.DOWN);
		assert level.playerIsAt(2, 2);
		
		level.move(Direction.LEFT);
		assert level.playerIsAt(2, 1);
		
		level.move(Direction.UP);
		assert level.playerIsAt(1, 1);
	}

	@Test
	void hittingWalls() {
		Level level = new Level(TestMaze.LEVEL01);
		assert level.playerIsAt(1, 1);
		
		level.move(Direction.UP);
		assert level.playerIsAt(1, 1);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(1, 2);
		
		level.move(Direction.UP);
		assert level.playerIsAt(1, 2);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(1, 3);
		
		level.move(Direction.UP);
		assert level.playerIsAt(1, 3);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(1, 3);
		
		level.move(Direction.DOWN);
		assert level.playerIsAt(2, 3);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(2, 3);
		
		level.move(Direction.DOWN);
		assert level.playerIsAt(3, 3);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(3, 3);
		
		level.move(Direction.LEFT);
		assert level.playerIsAt(3, 2);
		
		level.move(Direction.DOWN);
		assert level.playerIsAt(3, 2);
		
		level.move(Direction.LEFT);
		assert level.playerIsAt(3, 1);
		
		level.move(Direction.DOWN);
		assert level.playerIsAt(3, 1);
	}

}
