package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

class TestHunter {

	@Test
	void hunterMovesTowardsPlayer1() {
		SimpleLevel level = new SimpleLevel(TestMaze.LEVEL11);

		assert level.numEnemies() == 1;
		assert level.playerIsAt(1, 1);
		assert level.entityIsAt(new Hunter(), 4, 3);
		
		level.move(Direction.DOWN);
		assert level.playerIsAt(2, 1);
		assert level.entityIsAt(new Hunter(), 3, 3);
		
		level.move(Direction.DOWN);
		assert level.playerIsAt(3, 1);
		assert level.entityIsAt(new Hunter(), 3, 2);
		
		// The hunter is hot on the player's trail now
		level.move(Direction.UP);
		assert level.playerIsAt(2, 1);
		assert level.entityIsAt(new Hunter(), 3, 1);
		
		level.move(Direction.UP);
		assert level.playerIsAt(1, 1);
		assert level.entityIsAt(new Hunter(), 2, 1);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(1, 2);
		assert level.entityIsAt(new Hunter(), 1, 1);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(1, 3);
		assert level.entityIsAt(new Hunter(), 1, 2);
		
		level.move(Direction.RIGHT);
		assert level.playerIsAt(1, 4);
		assert level.entityIsAt(new Hunter(), 1, 3);
		
		// Player is still alive...
		assert level.playerIsAlive();
		
		// Oops! The player hit a wall and now they
		// are dead.
		level.move(Direction.RIGHT);
		assert !level.playerIsAlive();
		assert level.entityIsAt(new Hunter(), 1, 3);
		assert level.numEnemies() == 1;
	}
	
	@Test
	void hunterMovesTowardsPlayer2() {
		SimpleLevel level = new SimpleLevel(TestMaze.LEVEL12);

		assert level.numEnemies() == 1;
		assert level.playerIsAt(1, 1);
		assert level.entityIsAt(new Hunter(), 4, 4);
		
		// We'll just make the player collide into
		// walls here, we are only interested in the
		// hunter's movement
		level.move(Direction.RIGHT);
		assert level.entityIsAt(new Hunter(), 3, 4);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 3, 3);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 3, 2);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 3, 1);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 4, 1);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 5, 1);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 6, 1);

		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 6, 2);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 6, 3);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 6, 4);
		assert level.playerIsAt(1, 2);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 6, 5);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 6, 6);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 5, 6);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 4, 6);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 3, 6);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 2, 6);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 1, 6);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 1, 5);
		
		level.move(Direction.UP);
		assert level.entityIsAt(new Hunter(), 1, 4);
		
		level.move(Direction.DOWN);
		assert level.entityIsAt(new Hunter(), 1, 3);
		
		// Player is still alive...
		assert level.playerIsAlive();
		assert level.playerIsAt(1, 2);
		
		level.move(Direction.UP);
		
		// But not anymore!
		// The hunter bumped into the player, so
		// the player died.
		assert level.entityIsAt(new Hunter(), 1, 3);
		assert level.numEnemies() == 1;
		assert !level.playerIsAlive();
	}

}
