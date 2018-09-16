package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

class TestBomb {

	@Test
	void pickUpBombs() {
		Level level = new Level(TestMaze.LEVEL09);
		level.move(Direction.DOWN);
		
		assert (level.playerHas(new UnlitBomb()) == 1);
		
		level.move(Direction.DOWN);
		assert (level.playerHas(new UnlitBomb()) == 2);
		
		level.move(Direction.RIGHT);
		assert (level.playerHas(new UnlitBomb()) == 3);
		
		level.move(Direction.UP);
		assert (level.playerHas(new UnlitBomb()) == 4);
	}
	
	@Test
	void dropBombNextToBoulder() {
		Level level = new Level(TestMaze.LEVEL09);
		level.move(Direction.DOWN);
		
		assert (level.playerHas(new UnlitBomb()) == 1);
		
		level.move(Direction.DOWN);
		assert (level.playerHas(new UnlitBomb()) == 2);
		
		level.move(Direction.RIGHT);
		assert (level.playerHas(new UnlitBomb()) == 3);
		
		level.move(Direction.UP);
		assert (level.playerHas(new UnlitBomb()) == 4);
		
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		
		assert !level.entityIsAt(new LitBomb(), 3, 3);
		level.dropBomb();
		assert (level.playerHas(new UnlitBomb()) == 3);
		
		assert level.entityIsAt(new LitBomb(), 3, 3);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		
		assert level.entityIsAt(new LitBomb(), 3, 3);
		assert level.entityIsAt(new Boulder(), 2, 3);
		
		assert level.getNumOfEntity(new Boulder()) == 2;
		level.move(Direction.RIGHT);
		// Boom! Boulder is gone
		assert !level.entityIsAt(new Boulder(), 2, 3);
		assert level.getNumOfEntity(new Boulder()) == 1;
		
		// But the wall is still there
		assert level.entityIsAt(new Wall(), 4, 3);
		
		// And the player is still alive
		assert level.playerIsAlive();
		
		// The other boulder is still there
		assert level.entityIsAt(new Boulder(), 2, 4);
	}
	
	@Test
	void bombChainReaction() {
		Level level = new Level(TestMaze.LEVEL09);
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		level.move(Direction.UP);
		
		assert level.playerHas(new UnlitBomb()) == 4;
		level.move(Direction.UP);
		level.move(Direction.RIGHT);
		
		level.dropBomb();
		assert level.entityIsAt(new LitBomb(), 1, 3);
		level.dropBomb();
		assert level.entityIsAt(new LitBomb(), 1, 3);
		
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		
		// Kaboom!
		// If bombs only explode after three turns,
		// then there should still be a bomb on
		// (1, 3). But because of the chain
		// reaction, both bombs exploded
		assert !level.entityIsAt(new LitBomb(), 1, 3);
	}
	
	@Test
	void bombDoor() {
		Level level = new Level(TestMaze.LEVEL09);
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		assert level.playerHas(new UnlitBomb()) == 3;
		
		assert level.playerIsAt(3, 8);
		
		assert level.entityIsAt(new Door("red"), 2, 8);
		
		level.dropBomb();
		assert level.entityIsAt(new LitBomb(), 3, 8);
		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		
		// Kaboom!
		// The bomb is gone, but the door should still
		// be there
		assert !level.entityIsAt(new LitBomb(), 3, 8);
		assert level.entityIsAt(new Door("red"), 2, 8);
	}
	
	@Test
	void bombSelf() {
		Level level = new Level(TestMaze.LEVEL09);
		level.move(Direction.DOWN);
		assert level.playerHas(new UnlitBomb()) == 1;
		
		level.dropBomb();
		
		assert level.entityIsAt(new LitBomb(), 2, 1);
		
		// (Player keeps hitting a wall)
		level.move(Direction.UP);
		assert level.playerIsAt(1, 1);
		level.move(Direction.UP);
		assert level.playerIsAt(1, 1);
		level.move(Direction.UP);
		assert level.playerIsAt(1, 1);
		
		// Kaboom! The player should have died.
		assert !level.entityIsAt(new LitBomb(), 2, 1);
		assert !level.playerIsAlive();
	}
	
	@Test
	void noBombsToDrop() {
		Level level = new Level(TestMaze.LEVEL09);
		
		assert level.playerIsAt(1, 1);
		
		// The player is trying to drop a bomb without
		// having one in the first place
		assert level.playerHas(new UnlitBomb()) == 0;
		level.dropBomb();
		
		assert !level.entityIsAt(new LitBomb(), 1, 1);
	}
	
}
