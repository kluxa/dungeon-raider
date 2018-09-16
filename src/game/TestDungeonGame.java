package game;

import dungeon.Direction;

public class TestDungeonGame {
	
	// NOTE:
	// These are tests based on visual inspection,
	// NOT JUnit tests.
	// But they are very helpful for visualising the game
	public static void main(String[] args) {
		TestDungeonGame test = new TestDungeonGame();
		test.testLevel19();
		
	}
	
	/**
	 * Testing that players can't use items they don't
	 * have...
	 */
	public void testLevel19() {
		Level level = new Level(TestMaze.LEVEL19);
		System.out.println(level.showLevel());
		
		moveAndShowResult(level, Direction.RIGHT);
		dropBombAndShowResult(level);
		moveAndShowResult(level, Direction.RIGHT);
		moveAndShowResult(level, Direction.RIGHT);
		dropBombAndShowResult(level);
		moveAndShowResult(level, Direction.DOWN);
		fireArrowAndShowResult(level, Direction.LEFT);
		fireArrowAndShowResult(level, Direction.LEFT);
	}
	
	/**
	 * Testing that enemies can go through open doors
	 */
	public void testLevel18() {
		Level level = new Level(TestMaze.LEVEL18);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.LEFT, Direction.LEFT,  Direction.LEFT, Direction.DOWN,
				Direction.DOWN, Direction.RIGHT, Direction.LEFT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that you can push a boulder through an
	 * open door
	 */
	public void testLevel17() {
		Level level = new Level(TestMaze.LEVEL17);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.RIGHT, Direction.DOWN,  Direction.RIGHT,
				Direction.DOWN,  Direction.LEFT,  Direction.LEFT,  Direction.UP,
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that arrows do not go through boulders or 
	 * closed doors, but do go through open doors. Also
	 * testing that you can't push a boulder into an
	 * enemy
	 */
	public void testLevel16() {
		Level level = new Level(TestMaze.LEVEL16);
		System.out.println(level.showLevel());
		
		Direction[] moves1 = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves1) {
			moveAndShowResult(level, move);
		}
		
		fireArrowAndShowResult(level, Direction.LEFT);
		fireArrowAndShowResult(level, Direction.RIGHT);
		moveAndShowResult(level, Direction.RIGHT);
		fireArrowAndShowResult(level, Direction.RIGHT);
		
		Direction[] moves2 = {
				Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT,
				Direction.LEFT, Direction.LEFT
		};
		for (Direction move: moves2) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing a player placing a bomb and firing an arrow,
	 * and destroying enemies with these
	 */
	public void testLevel15() {
		Level level = new Level(TestMaze.LEVEL15);
		System.out.println(level.showLevel());
		
		Direction[] moves1 = {
				Direction.RIGHT
		};
		for (Direction move: moves1) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		
		Direction[] moves2 = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves2) {
			moveAndShowResult(level, move);
		}
		
		level.fireArrow(Direction.LEFT);
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
	}
	
	
	/**
	 * Testing the player and the enemy running around
	 * in circles
	 */
	public void testLevel14() {
		Level level = new Level(TestMaze.LEVEL14);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.DOWN,  Direction.DOWN,  Direction.UP,    Direction.DOWN,
				Direction.LEFT,  Direction.UP,    Direction.UP,    Direction.UP,
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that a sword does not get used if the
	 * player is invincible
	 */
	public void testLevel13b() {
		Level level = new Level(TestMaze.LEVEL13);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.LEFT,  Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.DOWN,
				Direction.DOWN
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing a sword running out of uses
	 */
	public void testLevel13a() {
		Level level = new Level(TestMaze.LEVEL13);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.UP, Direction.UP,    Direction.UP,
				Direction.RIGHT, Direction.UP, Direction.RIGHT, Direction.UP,
				Direction.RIGHT, Direction.UP, Direction.RIGHT, Direction.UP,
				Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing hunter pathfinding in a spiral. Testing
	 * the player killing the hunter with a sword. Testing
	 * the player dying to the hunter even with a sword.
	 */
	public void testLevel12() {
		Level level = new Level(TestMaze.LEVEL12);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.DOWN,  Direction.DOWN,  Direction.DOWN,
				Direction.DOWN,  Direction.DOWN,  Direction.RIGHT, Direction.LEFT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Simple testing of a hunter. Checking that a hunter
	 * moves towards the player.
	 */
	public void testLevel11() {
		Level level = new Level(TestMaze.LEVEL11);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.LEFT,  Direction.DOWN,  Direction.DOWN,
				Direction.DOWN,  Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.UP,    Direction.UP,    Direction.UP,    Direction.RIGHT,
				Direction.LEFT,  Direction.LEFT,  Direction.LEFT,  Direction.DOWN,
				Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that the player survives landing on a pit
	 * with both potions. Testing that pits don't disappear
	 * when blown up.
	 */
	public void testLevel10c() {
		Level level = new Level(TestMaze.LEVEL10);
		System.out.println(level.showLevel());
		
		Direction[] moves1 = {
				Direction.RIGHT, Direction.RIGHT, Direction.LEFT,  Direction.LEFT,
				Direction.DOWN,  Direction.DOWN,  Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.UP,    Direction.UP
		};
		for (Direction move: moves1) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		
		Direction[] moves2 = {
				Direction.LEFT, Direction.LEFT, Direction.LEFT
		};
		for (Direction move: moves2) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that invincibility eventually times out.
	 * (It should time out after 15 turns.)
	 */
	public void testLevel10b() {
		Level level = new Level(TestMaze.LEVEL10);
		System.out.println(level.showLevel());
		
		Direction[] moves1 = {
				Direction.RIGHT, Direction.RIGHT, Direction.UP,   Direction.UP,
				Direction.UP,    Direction.UP,    Direction.UP,   Direction.UP,
				Direction.UP,    Direction.LEFT,  Direction.LEFT, Direction.LEFT,
				Direction.LEFT,  Direction.UP
		};
		for (Direction move: moves1) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		
		Direction[] moves2 = {
				Direction.RIGHT, Direction.UP, Direction.UP
		};
		for (Direction move: moves2) {
			moveAndShowResult(level, move);
		}
	}
	
	
	/**
	 * Testing that the player survives blasts with an invincibility
	 * potion. Testing that the player dies when landing on a pit
	 * tile, even with an invincibility potion.
	 */
	public void testLevel10a() {
		Level level = new Level(TestMaze.LEVEL10);
		System.out.println(level.showLevel());
		
		Direction[] moves1 = {
				Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves1) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		
		Direction[] moves2 = {
				Direction.LEFT,  Direction.UP,    Direction.UP,   Direction.UP,
				Direction.RIGHT, Direction.RIGHT, Direction.DOWN
		};
		for (Direction move: moves2) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Bombs - testing collecting and dropping bombs.
	 * Testing blowing up boulders. Testing a chain reaction explosion.
	 * Testing that walls and doors don't get blown up. Testing the
	 * player dying from a bomb.
	 */
	public void testLevel09() {
		Level level = new Level(TestMaze.LEVEL09);
		System.out.println(level.showLevel());
		
		Direction[] moves1 = {
				Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.UP,
				Direction.DOWN, Direction.RIGHT
		};
		for (Direction move: moves1) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		
		Direction[] moves2 = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves2) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		dropBombAndShowResult(level);
		
		Direction[] moves3 = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.UP
		};
		for (Direction move: moves3) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		
		Direction[] moves4 = {
				Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.UP,
				Direction.UP,   Direction.LEFT
		};
		for (Direction move: moves4) {
			moveAndShowResult(level, move);
		}
		
		dropBombAndShowResult(level);
		
		Direction[] moves5 = {
				Direction.LEFT, Direction.UP, Direction.UP
		};
		for (Direction move: moves5) {
			moveAndShowResult(level, move);
		}
	}

	/**
	 * Testing pushing boulders into pits while hovering and
	 * moving onto a pit tile while hovering
	 */
	public void testLevel08b() {
		Level level = new Level(TestMaze.LEVEL08);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.DOWN,  Direction.DOWN,  Direction.UP,    Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.UP,
				Direction.RIGHT, Direction.DOWN,  Direction.DOWN,  Direction.UP,
				Direction.LEFT,  Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing pushing boulders into pits and falling into pits
	 */
	public void testLevel08a() {
		Level level = new Level(TestMaze.LEVEL08);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.DOWN,  Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing boulders starting on switches
	 * If a boulder starts on a switch, the switch should already
	 * be triggered...
	 */
	public void testLevel07() {
		Level level = new Level(TestMaze.LEVEL07);
		System.out.println(level.showLevel());
	}
	
	
	/**
	 * Testing boulder pushing
	 * Testing that pushing a boulder onto a switch triggers it,
	 * and that pushing a boulder off a switch untriggers it.
	 */
	public void testLevel06() {
		Level level = new Level(TestMaze.LEVEL06);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.RIGHT, Direction.DOWN,  Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT, Direction.DOWN,  Direction.RIGHT,
				Direction.UP,    Direction.UP,    Direction.LEFT,  Direction.LEFT,
				Direction.LEFT,  Direction.DOWN,  Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing boulder pushing
	 * Testing that players can't push more than one boulder at
	 * once and that players can't push boulders into walls
	 */
	public void testLevel05() {
		Level level = new Level(TestMaze.LEVEL05);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.DOWN,  Direction.DOWN,  Direction.RIGHT, Direction.RIGHT,
				Direction.DOWN,  Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.UP,    Direction.LEFT,  Direction.LEFT,
				Direction.LEFT,  Direction.LEFT,  Direction.LEFT,  Direction.UP
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that the player can't open a door
	 * with a non-matching key, but can open a door
	 * with a matching key.
	 * Testing that the key disappears after use.
	 */
	public void testLevel04() {
		Level level = new Level(TestMaze.LEVEL04);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.DOWN,  Direction.DOWN,  Direction.RIGHT, Direction.UP,
				Direction.RIGHT, Direction.RIGHT, Direction.UP,    Direction.DOWN,
				Direction.RIGHT, Direction.UP,    Direction.DOWN,  Direction.RIGHT,
				Direction.RIGHT
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that the player picks up keys,
	 * but not when they already have a key
	 */
	public void testLevel03() {
		Level level = new Level(TestMaze.LEVEL03);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT,
				Direction.UP,   Direction.UP,   Direction.DOWN,  Direction.DOWN,
				Direction.UP
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that the player picks up treasure
	 */
	public void testLevel02() {
		Level level = new Level(TestMaze.LEVEL02);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT,
				Direction.UP
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing that the player does not walk into walls
	 */
	public void testLevel01b() {
		Level level = new Level(TestMaze.LEVEL01);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.UP,   Direction.RIGHT, Direction.UP,
				Direction.RIGHT, Direction.DOWN, Direction.RIGHT, Direction.DOWN,
				Direction.RIGHT, Direction.DOWN, Direction.LEFT,  Direction.DOWN,
				Direction.LEFT,  Direction.DOWN, Direction.LEFT,  Direction.UP,
				Direction.LEFT,  Direction.UP,   Direction.LEFT,  Direction.UP
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	/**
	 * Testing simple movement
	 */
	public void testLevel01a() {
		Level level = new Level(TestMaze.LEVEL01);
		System.out.println(level.showLevel());
		
		Direction[] moves = {
				Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN,
				Direction.LEFT,  Direction.LEFT,  Direction.UP,   Direction.UP
		};
		for (Direction move: moves) {
			moveAndShowResult(level, move);
		}
	}
	
	private void moveAndShowResult(Level l, Direction move) {
		l.move(move);
		System.out.println(l.showLevel());
		System.out.println(l.showPlayer());
	}
	
	private void dropBombAndShowResult(Level l) {
		l.dropBomb();
		System.out.println(l.showLevel());
		System.out.println(l.showPlayer());
	}
	
	private void fireArrowAndShowResult(Level l, Direction move) {
		l.fireArrow(move);
		System.out.println(l.showLevel());
		System.out.println(l.showPlayer());
	}
}
