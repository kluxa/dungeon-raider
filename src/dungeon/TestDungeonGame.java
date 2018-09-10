package dungeon;

public class TestDungeonGame {
	
	// NOTE:
	// These are tests based on visual inspection,
	// NOT JUnit tests.
	public static void main(String[] args) {
		TestDungeonGame test = new TestDungeonGame();
		test.testLevel05();
		
		
		
	}
	
	
	/**
	 * Testing boulders starting on switches
	 * If a boulder starts on a switch, the switch should already
	 * be triggered...
	 */
	public void testLevel08() {
		Level level = new Level(SampleMaze.LEVEL08);
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
		Level level = new Level(SampleMaze.LEVEL07);
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
		Level level = new Level(SampleMaze.LEVEL06);
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
		Level level = new Level(SampleMaze.LEVEL05);
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
		Level level = new Level(SampleMaze.LEVEL04);
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
		Level level = new Level(SampleMaze.LEVEL03);
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
		Level level = new Level(SampleMaze.LEVEL02);
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
		Level level = new Level(SampleMaze.LEVEL01);
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
		Level level = new Level(SampleMaze.LEVEL01);
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
