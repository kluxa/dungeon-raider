package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.dungeon.*;
import src.player.*;


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

		//test maze isn't null
		assertTrue(player.getMaze() != null);

		//Test collision mode
		Tile tile = new Tile(1,1);
		Boulder b = new Boulder(tile);
		Enemy e = new Enemy(tile);

		//if player collides with boulder isAlive = true
		player.collide(b);
		assertTrue(player.isAlive());

		//if player collides with enemy isAlive = false
		player.collide(e);
		assertFalse(player.isAlive());

		//Test player blows up
		player.getBlownUp();
		assertFalse(player.isAlive());

		//Test player dies after falling
		player.fall();
		assertFalse(player.isAlive());

		//Test player doesn't die if player can fly
		player.setFlying();
		player.fall();
		assertTrue(player.isAlive());

		//Test player pick item
		Sword sword = new Sword();
		player.pickUp();
		assertTrue(player.numItemOfType(sword) > 0);

		Arrow arrow = new Arrow();
		player.pickUp();
		assertTrue(player.numItemOfType(arrow) > 0);

		Bomb bomb = new Bomb();
		player.pickUp();
		assertTrue(player.numItemOfType(bomb) > 0);

		//Test player item drop and consume
		player.dropItem(arrow);
		assertTrue(player.numItemOfType(arrow) == 0);	

		//Test player fight with sword
		player.fight(e);
		assertTrue(player.isAlive());

		//Test player fight without sword
		player.fight(e);
		assertFalse(player.isAlive());

		//Test state updating and invincibility
		InvincibilityPotion ip = new InvincibilityPotion(player);
		player.consumeItem(ip);
		ip.applyEffect(player);
		assertEquals(player.getState(), ip);

		//Test fly potion 
		HoverPotion hp = new HoverPotion();
		player.consumeItem(hp);
		hp.applyEffect(player);
		assertTrue(player.isFlying());
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
	
}
