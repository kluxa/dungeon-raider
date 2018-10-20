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
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add(formatCoords(maze.getHeight(), maze.getWidth()));
		mazeMap.put("dims", tmp);
		tmp.remove(0);
		tmp.add(formatCoords(maze.getStartSquare().getY(), maze.getStartSquare().getX()));
		mazeMap.put("start", tmp);
		//completion objectives
		
		System.out.println(mazeMap.toString());
		
		return null;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> hasapafyEnts (LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> fileMap, Level level) {
		//Gets the entity and converts it and its location to a string
		//If the entMap already has atleast one of that type already, the coords only will be added to the hashmap
		Maze maze = level.getMaze();
		
		LinkedHashMap<String, ArrayList<String>> entMap = new LinkedHashMap<String, ArrayList<String>>();
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				Square sq = maze.getSquare(i, j);
				SolidEntity solEnt = sq.getCollidableOccupant();
				
				if (solEnt != null) {
					System.out.println(solEnt.getClass().getSimpleName());
					ArrayList<String> tmp = new ArrayList<String>();
					if (entMap.get(solEnt.getClass().getSimpleName()) == null) {
						System.out.println("Start with null");
						
						tmp.add(formatCoords(i, j));
						entMap.put(solEnt.getClass().getSimpleName(), tmp);
						System.out.println("End with null");
					} else {
						System.out.println("Start with stuff");
						tmp = entMap.get(solEnt.getClass().getSimpleName());
						tmp.add(formatCoords(i, j));
						entMap.put(solEnt.getClass().getSimpleName(), tmp);
						System.out.println("End with stuff");
					}
				}
			}
		}
		fileMap.put("SolidEntities", entMap);
		
		return fileMap;
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
	public static String formatCoords (int y, int x) {
		String coString = "(" + y + ", " + x + ")";
		return coString;
	}
	
	public static void main (String[] args) {
		Level level = LevelBuilder.makeLevel("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testDung.txt");
		hasapafyMaze (level);
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> tmp = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>();
		tmp = hasapafyEnts (tmp, level);
	}
}
