package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

class TestHoverPotion {
	
	@Test
	void survivePitsWithHoverPotion() {
		Level level = new Level(TestMaze.LEVEL10);
		
		assert level.itemIsAt(new HoverPotion(), 3, 1);
		
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		
		// Player has now drunk the potion
		assert level.playerIsAt(3, 1);
		assert !level.itemIsAt(new HoverPotion(), 3, 1);
		
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		
		level.move(Direction.UP);
		// Now player is above the pit
		assert level.playerIsAlive();
		assert level.playerIsAt(2, 4);
	}
	
}
