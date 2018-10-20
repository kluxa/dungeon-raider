package fileIO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import dungeon.Entity;
import dungeon.Maze;
import dungeon.SolidEntity;
import dungeon.Square;
import game.Level;
import items.Item;

public class StringUtilsWrite {
	/**
	 * Turns a maze into a linkedhashmap
	 * @return
	 */
	public static LinkedHashMap<String, ArrayList<String>> hasapafyMaze (Level level) {
		LinkedHashMap<String, ArrayList<String>> mazeMap = new LinkedHashMap<String, ArrayList<String>>();
	
		Maze maze = level.getMaze();
		
		mazeMap.put("dims", formatCoords(maze.getHeight(), maze.getWidth()));
		mazeMap.put("start", formatCoords(maze.getStartSquare().getY(), maze.getStartSquare().getX()));
		//completion objectives
		
		
		System.out.println(mazeMap.toString());
		
		return null;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> hasapafyEnts (Level level) {
		Maze maze = level.getMaze();
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> entMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>();
		LinkedHashMap<String, ArrayList<String>> locMap = new LinkedHashMap<String, ArrayList<String>>();
		ArrayList<SolidEntity> entities = new ArrayList<>();
		for (int row = 0; row < maze.getHeight(); row++) {
			for (int col = 0; col < maze.getWidth(); col++) {
				ArrayList<String> coords = formatCoords(row, col);
				SolidEntity collOcc = maze.getSquare(row, col).getCollidableOccupant();
				if (collOcc != null) {
					String className = collOcc.getClass().getSimpleName();
					locMap.put("location", coords);
					entMap.put("className", locMap); //TODO make different linkedhashmap per time or?
				}
			}
		}
		
		return null;
	}
	
	private static String classToString(SolidEntity collOcc) {
		switch(collOcc.getClass().getName()) {
			
		
		}
		
		
		return null;
	}

	/**
	 * Turns two ints into a "[x, y]" format
	 * @param x
	 * @param y
	 * @return
	 */
	public static ArrayList<String> formatCoords (int y, int x) {
		String coString = "(" + y + ", " + x + ")";
		ArrayList<String> coords = new ArrayList<String>();
		coords.add(coString);
		return coords;
	}
	
	public static void main (String[] args) {
		Level level = LevelBuilder.makeLevel("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testDung.txt");
		hasapafyMaze (level);
		hasapafyEnts (level);
	}
}
