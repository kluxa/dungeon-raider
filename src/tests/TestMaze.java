package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.model.*;
import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class TestGame {

	@Test
	public void TestGame() {
		Maze maze = new Maze(5,5);

		//Test maze initialization
		maze.placeEntity(1,1, new Square(1,1));
		ArrayList<Entity> entities = maze.getEntities(1,1);
		assertTrue(entities.size() > 0);
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