package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class TestSwitchFloors {

	@Test
	void TestFloorSwitches() {
		Level level = new Level(TestMaze.LEVEL06);
		FloorSwitch fs1 = new FloorSwitch();
		FloorSwitch fs2 = new FloorSwitch();
		FloorSwitch fs3 = new FloorSwitch();

		assertTrue(level.getNumOfEntity(new Boulder()) > 0);

		level.move(Direction.RIGHT);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		fs1.arrive(new Boulder());

		assertTrue("After boulder arrives the floor switch is triggered",fs1.isTriggered());

		assertTrue(level.playerIsAt(6, 6));

		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		level.move(Direction.UP);	
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		fs2.arrive(new Boulder());	

		assertTrue("After boulder arrives the floor switch is triggered",fs2.isTriggered());

		level.move(Direction.LEFT);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		fs3.arrive(new Boulder());	
		level.completeLevel();

		assertTrue("After boulder arrives the floor switch is triggered",fs2.isTriggered());
		assertTrue(level.isComplete());
	}
}