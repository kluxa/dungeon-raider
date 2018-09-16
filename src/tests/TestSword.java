package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

class TestSword {

	@Test
	void swordKillsEnemies() {
		// Testing that a sword will kill a hunter
		Level level = new Level(TestMaze.LEVEL12);
		
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		assert level.itemIsAt(new Sword(), 6, 6);
		assert level.playerHas(new Sword()) == 0;
		level.move(Direction.DOWN);
		assert level.playerHas(new Sword()) == 1;
		assert !level.itemIsAt(new Sword(), 6, 6);
		assert level.playerIsAlive();
		
		assert level.entityIsAt(new Hunter(), 6, 4);
		
		// First the player bumps into a wall to
		// let the hunter approach...
		level.move(Direction.DOWN);
		
		assert level.entityIsAt(new Hunter(), 6, 5);
		// Then the player strikes!
		
		assert level.numEnemies() == 1;
		level.move(Direction.LEFT);
		assert !level.entityIsAt(new Hunter(), 6, 5);
		assert level.playerHas(new Sword()) == 1;
		assert level.numEnemies() == 0;
	}
	
	@Test
	void dyingEvenWithSword() {
		// Even if you have a sword, you can die
		// if an enemy bumps into you.
		Level level = new Level(TestMaze.LEVEL12);
		
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		level.move(Direction.DOWN);
		assert level.itemIsAt(new Sword(), 6, 6);
		assert level.playerHas(new Sword()) == 0;
		level.move(Direction.DOWN);
		assert level.playerHas(new Sword()) == 1;
		assert !level.itemIsAt(new Sword(), 6, 6);
		assert level.playerIsAlive();
		
		assert level.entityIsAt(new Hunter(), 6, 4);
		
		// The player runs into the square next to
		// the hunter, and then the hunter bumps
		// into the player
		level.move(Direction.LEFT);
		
		// Hence, the player dies
		assert level.numEnemies() == 1;
		assert level.entityIsAt(new Hunter(), 6, 4);
		assert !level.playerIsAlive();
	}
	
	@Test
	void swordHasOnlyFiveUses() {
		// There are 5 enemies in this level!
		Level level = new Level(TestMaze.LEVEL13);
		assert level.numEnemies() == 5;
		
		assert level.playerHas(new Sword()) == 0;
		level.move(Direction.RIGHT);
		assert level.playerHas(new Sword()) == 1;
		level.move(Direction.RIGHT);
		level.move(Direction.DOWN);
		
		assert level.playerIsAt(1, 4);
		assert level.entityIsAt(new Hunter(), 1, 5);
		
		// Here, the player kills an enemy
		level.move(Direction.RIGHT);
		assert !level.entityIsAt(new Hunter(), 1, 5);
		assert level.playerHas(new Sword()) == 1;
		assert level.numEnemies() == 4;
		
		// Bump
		level.move(Direction.DOWN);
		assert level.playerIsAt(1, 4);
		assert level.entityIsAt(new Hunter(), 1, 5);
		
		// Kill no. 2
		level.move(Direction.RIGHT);
		assert !level.entityIsAt(new Hunter(), 1, 5);
		assert level.playerHas(new Sword()) == 1;
		assert level.numEnemies() == 3;
		
		// Bump
		level.move(Direction.DOWN);
		assert level.playerIsAt(1, 4);
		assert level.entityIsAt(new Hunter(), 1, 5);
		
		// Kill no. 3
		level.move(Direction.RIGHT);
		assert !level.entityIsAt(new Hunter(), 1, 5);
		assert level.playerHas(new Sword()) == 1;
		assert level.numEnemies() == 2;
		
		// Bump
		level.move(Direction.DOWN);
		assert level.playerIsAt(1, 4);
		assert level.entityIsAt(new Hunter(), 1, 5);
		
		
		// Kill no. 4
		level.move(Direction.RIGHT);
		assert !level.entityIsAt(new Hunter(), 1, 5);
		assert level.playerHas(new Sword()) == 1;
		assert level.numEnemies() == 1;
		
		// Bump
		level.move(Direction.DOWN);
		assert level.playerIsAt(1, 4);
		assert level.entityIsAt(new Hunter(), 1, 5);
		
		// Kill no. 5
		level.move(Direction.RIGHT);
		assert !level.entityIsAt(new Hunter(), 1, 5);
		assert level.numEnemies() == 0;

		// Notice that the sword is now gone
		assert level.playerHas(new Sword()) == 0;
		
		// The player killed all 5 enemies while on
		// the same square!
		assert level.playerIsAlive();
		assert level.playerIsAt(1, 4);
	}
}
