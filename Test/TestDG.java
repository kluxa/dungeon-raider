package dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class TestDG {
	
	// NOTE:
	// These are tests based on visual inspection,
	// NOT JUnit tests.
	public static void main(String[] args) {
		TestDG test = new TestDG();
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

	/**
	*Test direction enum 
	*/
	@Test
	void DirectionTest() {
		//Check direction UP exists
		Direction dir = Direction.UP;
		assertEquals(Direction.toString("up"), dir);
		assertTrue(dir.getDX() == 0 && dir.getDY() == -1);

		dir = Direction.DOWN;
		assertEquals(Direction.toString("down"), dir);
		assertTrue(dir.getDX() == 0 && dir.getDY() == 1);

		dir = Direction.LEFT;
		assertEquals(Direction.toString("left"), dir);
		assertTrue(dir.getDX() == -1 && dir.getDY() == 0);

		dir = Direction.RIGHT;
		assertEquals(Direction.toString("right"), dir);
		assertTrue(dir.getDX() == 1 && dir.getDY() == 0);
	}

	/**
	*Test functionalities in maze
	*/
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
	void MoveTest() {
		Player player = new Player();
		Maze maze = new Maze(SampleMaze.LEVEL01);

		//test player movements
		player.move(SampleMaze, Direction.DOWN);
		assertEquals(maze.getPlayer().getY(), 1);

		player.move(SampleMaze, Direction.RIGHT);
		assertEquals(maze.getPlayer().getX(), 1);

		player.move(SampleMaze, Direction.DOWN);
		assertEquals(maze.getPlayer().getY(), 2);

		player.move(SampleMaze, Direction.RIGHT);
		assertEquals(maze.getPlayer().getY(), 2);
	}

	//Test for illegal movements like crashing into a wall
	@Test
	void IllegalMoveTest() {
		Player player = new Player();
		Maze maze = new Maze(SampleMaze.LEVEL01, player);

		assertTrue(maze.isLegalMove(player, Direction.DOWN));
		assertTrue(maze.isLegalMove(player, Direction.RIGHT));
		assertTrue(maze.isLegalMove(player, Direction.LEFT));
		assertTrue(maze.isLegalMove(player, Direction.UP));
	}

	//Test Level funcionalities
	@Test
	void LevelTest() {
		Level level = new Level(SampleMaze.LEVEL01);

		//Test player inside level
		assertTrue(level.getPlayer() != null);
		//Test player is alive
		assertTrue(level.playersAlive());

		//Test player is at certain coordinates and moves accordingly
		assertTrue(level.playerIsAt(0,0));

		level.getPlayer().move(SampleMaze.LEVEL01, Direction.DOWN);
		assertTrue(level.playerIsAt(0,1));

		level.getPlayer().move(SampleMaze.LEVEL01, Direction.RIGHT);
		assertTrue(level.playerIsAt(1,1));

		level.getPlayer().move(SampleMaze.LEVEL01, Direction.RIGHT);
		assertTrue(level.playerIsAt(2,1));

		level.getPlayer().move(SampleMaze.LEVEL01, Direction.LEFT);
		assertTrue(level.playerIsAt(1,1));

		//Test bomb droped correctly
		UnlitBomb bomb = new UnlitBomb();
		level.dropBomb();
		assertFalse(level.getPlayer().hasItem(bomb));
		assertTrue(level.getMaze().getLast(bomb) instanceof LitBomb);

		//Test entity location
		FloorSwitch fs = new FloorSwitch(1,1);
		assertTrue(level.entityIsAt(fs,1,1));

		Boulder b = new Boulder(new Tile(1,1));
		assertTrue(level.entityIsAt(b,1,1));

		Door d = new Door(new Tile(1,1));
		assertTrue(level.entityIsAt(d,1,1));

		//Test level is complete
		assertFalse(level.levelIsComplete());
	}








	
}
