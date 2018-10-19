package fileIO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import dungeon.Maze;
import game.Level;

public class StringUtilsWrite {
	/**
	 * Turns a maze into a linkedhashmap
	 * @return
	 */
	public static LinkedHashMap<String, ArrayList<String>> hasapafyMaze (Level level) {
		LinkedHashMap<String, ArrayList<String>> mazeMap = new LinkedHashMap<String, ArrayList<String>>();
	
		Maze maze = level.getMaze();
		
		mazeMap.put("dims", formatCoords(maze.getHeight(), maze.getWidth()));
		
		System.out.println(mazeMap.toString());
		
		return null;
	}
	
	/**
	 * Turns two ints into a "[x, y]" format
	 * @param x
	 * @param y
	 * @return
	 */
	public static ArrayList<String> formatCoords (int y, int x) {
		String coString = "[" + y + ", " + x + "]";
		ArrayList<String> coords = new ArrayList<String>();
		coords.add(coString);
		return coords;
	}
	
	public static void main (String[] args) {
		hasapafyMaze (LevelBuilder.makeLevel("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testDung.txt"));
	}
}
