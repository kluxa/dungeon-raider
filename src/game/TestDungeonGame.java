package game;

import dungeon.Direction;

public class TestDungeonGame {
	
	// NOTE:
	// These are tests based on visual inspection,
	// NOT JUnit tests.
	public static void main(String[] args) {
		TestDungeonGame test = new TestDungeonGame();
		test.testLevel10c();
		
		
		
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
		
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		
		Direction[] moves2 = {
				Direction.LEFT, Direction.LEFT, Direction.LEFT
		};
		
		for (Direction move: moves2) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
		
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		
		Direction[] moves2 = {
				Direction.RIGHT, Direction.UP, Direction.UP
		};
		
		for (Direction move: moves2) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
		
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		
		Direction[] moves2 = {
				Direction.LEFT,  Direction.UP,    Direction.UP,   Direction.UP,
				Direction.RIGHT, Direction.RIGHT, Direction.DOWN
		};
		
		for (Direction move: moves2) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
		
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		
		Direction[] moves2 = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT
		};
		
		for (Direction move: moves2) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
		
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		
		Direction[] moves3 = {
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.UP
		};
		
		for (Direction move: moves3) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
		
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		
		Direction[] moves4 = {
				Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.UP,
				Direction.UP,   Direction.LEFT
		};
		
		for (Direction move: moves4) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
		
		level.dropBomb();
		System.out.print(level.showLevel());
		System.out.println(level.showPlayer());
		
		Direction[] moves5 = {
				Direction.LEFT, Direction.UP, Direction.UP
		};
		
		for (Direction move: moves5) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
		
		Direction[] moves = {
				
		};
		
		for (Direction move: moves) {
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
		}
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.print(level.showLevel());
			System.out.println(level.showPlayer());
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
			level.move(move);
			System.out.println(level.showLevel());
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
			level.move(move);
			System.out.println(level.showLevel());
		}
	}
}
