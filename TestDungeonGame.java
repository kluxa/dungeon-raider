package dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


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
		
		for (Direction move : moves) {
			level01.move(move);
			System.out.println(level01.showLevel());
		}
	}

	//JUNIT TESTS

	/*
	@Test
	void ConstructorTest() {
		Player player = new Player();
		assertTrue(player != null);
		Level level1 = new Level(SampleMaze.LEVEL01);

	}*/

	@Test
	void PlayerTest() {
		Player player = new Player();

		int x = player.getX();
		int y = player.getY();

		//Test setters in player
		player.setX(3);
		assertEquals(3, x);

		player.setY(3);
		assertEquals(3, y);

		//test that player moves
		player.move(SampleMaze, Direction.UP);
		assertTrue(x != player.getX());
		assertTrue(y != player.getY());

		//assert if player doesn't move
		assertFalse("player didn't move", x == player.getX());
		assertFalse("player didn't move", y == player.getY());

	}

	@Test
	void MazeTest() {
		Player player = new Player();
		Maze maze = new Maze(SampleMaze.LEVEL01, player);
		String s = " ";

		//test maze exists and is > 0
		assertTrue(maze.getLength() > 0);
		assertTrue(maze.getHeight() > 0);

		//test setX and setY in player
		player.setX(maze.getStartingX());
		player.setY(maze.getStartingY());

		assertEquals(maze.getStartingX(), player.getX());
		assertEquals(maze.getStartingY(), player.getY());

		//test that UP,DOWN,LEFT,RIGHT are valid moves
		assertTrue(maze.isLegalMove(player, Directions.UP));
		assertTrue(maze.isLegalMove(player, Direction.DOWN));
		assertTrue(maze.isLegalMove(player, Direction.LEFT));
		assertTrue(maze.isLegalMove(player, Direction.RIGHT));

		//test DIAGONAL isn't a valid move
		assertFalse(maze.isLegalMove(player, Direction.DIAGONAL));

		//Check that the maze isn't empty
		assertFalse(s.equals(maze.showMaze()));
	}

	@Test
	void LegalMoveTest() {
		Maze maze = new Maze(SampleMaze.LEVEL01);

		//test player movements
		player.move(SampleMaze, Direction.DOWN);
		assertTrue(getPlayer().move(SampleMaze.LEVEL01));

		player.move(SampleMaze, Direction.RIGHT);
		player.move(SampleMaze, Direction.DOWN);
		player.move(SampleMaze, Direction.RIGHT);

		assertTrue(x != player.getX());
		assertTrue(y != player.getY());
	}

	@Test
	void LevelTest() {
		Level level = new Level(SampleMaze.LEVEL01);

		int x = level.getPlayer().getX();
		int y = level.getPlayer().getY();

		//Check for enemy movements
		level.nextMove(Direction.DOWN);
		level.nextMove(Direction.RIGHT);

		assertTrue(x != level.getPlayer().getX());
		assertTrue(y != level.getPlayer().getY());
	}








	
}
