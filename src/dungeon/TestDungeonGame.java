package dungeon;

public class TestDungeonGame {
	
	// NOTE:
	// These are tests based on visual inspection,
	// NOT JUnit tests.
	public static void main(String[] args) {
		TestDungeonGame test = new TestDungeonGame();
		test.testLevel01();
		
		
		
	}
	
	
	
	
	
	
	/**
	 * Testing that the player does not go outside the
	 * bounds of the level.
	 */
	public void testLevel01() {
		Level level01 = new Level(SampleMaze.LEVEL01);
		System.out.println(level01.showLevel());
		
		Direction[] moves = {
				Direction.UP,    Direction.DOWN,  Direction.DOWN,
				Direction.DOWN,  Direction.DOWN,  Direction.DOWN,
				Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT, Direction.UP,
				Direction.UP,    Direction.UP,    Direction.UP,
				Direction.UP,    Direction.LEFT,  Direction.LEFT,
				Direction.LEFT,  Direction.LEFT,  Direction.LEFT,
				Direction.DOWN,  Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.DOWN,  Direction.DOWN,
				Direction.LEFT,  Direction.LEFT,  Direction.UP,
				Direction.UP,    Direction.RIGHT, Direction.DOWN
		};
		
		for (Direction move: moves) {
			level01.move(move);
			System.out.println(level01.showLevel());
		}
	}
}
