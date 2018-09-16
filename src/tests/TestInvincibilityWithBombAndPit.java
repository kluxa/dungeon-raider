package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

class TestInvincibilityWithBombAndPit {

	@Test
	void surviveBombWithInvincibility() {
		Level level = new Level(TestMaze.LEVEL10);
		
		level.move(Direction.RIGHT);
		assert level.playerHas(new UnlitBomb()) == 1;
		
		assert level.itemIsAt(new InvincibilityPotion(), 1, 3);
		
		level.move(Direction.RIGHT);
		assert !level.itemIsAt(new InvincibilityPotion(), 1, 3);
		assert level.playerIsInvincible();
		
		assert level.playerIsAt(1, 3);
		level.dropBomb();
		assert level.entityIsAt(new LitBomb(), 1, 3);
		level.move(Direction.UP);
		level.move(Direction.UP);
		level.move(Direction.UP);
		
		assert level.playerIsAt(1, 3);
		
		// Kaboom! The player should have survived
		assert !level.entityIsAt(new LitBomb(), 1, 3);
		assert level.playerIsAt(1, 3);
		assert level.playerIsAlive();
	}
	
	@Test
	void doNotSurvivePitWithInvincibility() {
		Level level = new Level(TestMaze.LEVEL10);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		
		assert level.playerIsInvincible();
		assert level.playerIsAt(1, 4);
		assert level.playerIsAlive();
		
		// Now the player walks into a pit
		// and dies
		assert level.playerIsInvincible();
		level.move(Direction.DOWN);
		assert !level.playerIsAlive();
	}
	
	@Test
	void playerDoesNotPickUpPotions() {
		// Potions' effects are instantaneous,
		// players do not get to pick them up
		Level level = new Level(TestMaze.LEVEL10);
		level.move(Direction.RIGHT);
		
		assert level.itemIsAt(new InvincibilityPotion(), 1, 3);
		level.move(Direction.RIGHT);
		assert !level.itemIsAt(new InvincibilityPotion(), 1, 3);
		assert level.playerIsInvincible();
		assert level.playerHas(new InvincibilityPotion()) == 0;
		
		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		level.move(Direction.DOWN);
		
		assert level.itemIsAt(new HoverPotion(), 3, 1);
		level.move(Direction.DOWN);
		assert !level.itemIsAt(new HoverPotion(), 3, 1);
		assert level.playerHas(new HoverPotion()) == 0;
	}
	
}
