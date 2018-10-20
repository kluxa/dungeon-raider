package fileIO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import dungeon.Entity;
import dungeon.Maze;
import dungeon.SolidEntity;
import dungeon.Square;
import dungeon.Tile;
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
		//maze obj's
		
		return mazeMap;
	}
	
	/**
	 * Makes a hashmap of all the entities in the maze (collidable) according to a strict format
	 * @param fileMap
	 * @param level
	 * @return
	 */
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
					ArrayList<String> locData = new ArrayList<String>();
					if (entMap.get(solEnt.getClass().getSimpleName()) == null) {
						//add first thing
						locData.add(formatCoords(i, j));
						entMap.put(solEnt.getClass().getSimpleName(), locData);
					} else {
						//Add any after first by getting arraylist, adding to it and putting it back in
						locData = entMap.get(solEnt.getClass().getSimpleName());
						locData.add(formatCoords(i, j));
						entMap.put(solEnt.getClass().getSimpleName(), locData);
					}
				}
			}
		}
		fileMap.put("SolidEntities", entMap);
		
		return fileMap;
	}

	/**
	 * Creates a hashmap of all the items in the maze in accordance with a strict file format
	 * @param fileMap
	 * @param level
	 * @return
	 */
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> hasapafyItems (LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> fileMap, Level level) {
		Maze maze = level.getMaze();
		
		LinkedHashMap<String, ArrayList<String>> itemMap = new LinkedHashMap<String, ArrayList<String>>();
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				Square sq = maze.getSquare(i, j);
				ArrayList<Item> itemList = sq.getItems();			
				if (itemList != null) {
					for (Item item : itemList) {
						ArrayList<String> locData = new ArrayList<String>();
						if (itemMap.get(item.getClass().getSimpleName()) == null) {
							locData.add(formatCoords(i, j));
							itemMap.put(item.getClass().getSimpleName(), locData);
						} else {
							locData = itemMap.get(item.getClass().getSimpleName());
							locData.add(formatCoords(i, j));
							itemMap.put(item.getClass().getSimpleName(), locData);
						}
					}
				}
			}
		}
		fileMap.put("Items", itemMap);
		
		return fileMap;
	}
	
	/**
	 * Makes a hashmap of all the tiles in the maze according to a strict format
	 * @param fileMap
	 * @param level
	 * @return
	 */
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> hasapafyTiles (LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> fileMap, Level level) {
		Maze maze = level.getMaze();
		
		LinkedHashMap<String, ArrayList<String>> tileMap = new LinkedHashMap<String, ArrayList<String>>();
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				Square sq = maze.getSquare(i, j);
				Tile tile = sq.getTile();
				if (!tile.getClass().getSimpleName().equals("Path")) {
					ArrayList<String> locData = new ArrayList<String>();
					if (tileMap.get(tile.getClass().getSimpleName()) == null) {
						locData.add(formatCoords(i, j));
						tileMap.put(tile.getClass().getSimpleName(), locData);
					} else {
						locData = tileMap.get(tile.getClass().getSimpleName());
						locData.add(formatCoords(i, j));
						tileMap.put(tile.getClass().getSimpleName(), locData);
					}
				}
			}
		}
		fileMap.put("TileEntities", tileMap);
		
		return fileMap;
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
		tmp = hasapafyItems(tmp, level);
		tmp = hasapafyTiles (tmp, level);
		System.out.println(tmp.get("TileEntities").toString());
	}
}
