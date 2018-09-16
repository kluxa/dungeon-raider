package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.*;

/**
*Test Controller to run all test cases for all classes
*/
public class TestController {

	//Run JUnit tests for the different classes of the game dungeons
	public static void main(String[] args) {
		JUnitCore jUnitCore = new JUnitCore();
		
		//Dungeon game package Tester
		//Test Player, Direction, Maze, Legal and Illegal Moves, Level 
		Result result = JUnitCore.runClasses(TestDG.class);
		System.out.println("Dungeon Game Tester");
		Util.printResult(result);

		//Dungeon package Tester
		//Test Entity, Tile, Square, Non living entity, living entity, Boulder, Floor Switch, Door, Exit,
		//Lit Bomb, Wall
		result = JUnitCore.runClasses(TestDungeon.class);
		System.out.println("Dungeon Tester");
		Util.printResult(result);

		//Item tester
		//Test Arrow, Hover Potion, Invincibility Potion, Key, Sword, Treasure, Unlit Bomb
		result = JUnitCore.runClasses(TestItems.class);
		System.out.println("Items Tester");
		Util.printResult(result);

	
	}
}