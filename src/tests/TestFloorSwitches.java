package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class TestFloorSwitches {

	@Test
	void testFloorSwitch() {
		SimpleLevel level = new SimpleLevel(TestMaze.LEVEL06);
		FloorSwitch fs1 = new FloorSwitch();
		FloorSwitch fs2 = new FloorSwitch();
		FloorSwitch fs3 = new FloorSwitch();

		assertTrue(level.getNumOfEntity(new Boulder()) > 0);

		level.move(Direction.RIGHT);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		fs1.arrive(new Boulder());

		level.move(Direction.LEFT);
		level.move(Direction.LEFT);
		level.move(Direction.UP);	
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		fs2.arrive(new Boulder());	

		level.move(Direction.LEFT);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		level.move(Direction.RIGHT);
		level.move(Direction.DOWN);
		level.move(Direction.RIGHT);
		fs3.arrive(new Boulder());	

	}
}