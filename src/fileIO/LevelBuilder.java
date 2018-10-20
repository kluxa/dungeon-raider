package fileIO;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import dungeon.*;
import enemies.*;
import game.Level;
import game.SimpleLevel;
import items.*;

public class LevelBuilder {

	/**
	 * Makes a fully fledged level from a file
	 * @param fileLoc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Level makeLevel (String fileLoc) {
		LinkedHashMap<String, Object> mazeInfo = MazeFileReader.getMazeInfo(fileLoc);
		Maze maze = createMazeFromHashMap ((LinkedHashMap<String, ArrayList<String>>) mazeInfo.get("Map"));
		maze = placeSolidEntities ((LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>) mazeInfo.get("SolidEntities"), maze);
		maze = placeItems ((LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>) mazeInfo.get("Items"), maze);
		maze = placeTiles ((LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>) mazeInfo.get("TileEntities"), maze);
		
		ArrayList<String> obj = ((LinkedHashMap<String, ArrayList<String>>) mazeInfo.get("Map")).get("completion");
		
		Level lvl = giveCompObj(maze, obj);
		
		maze.toString();
		
		return lvl;
	}

	private static Maze placeTiles(LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> tileMap,
			Maze maze) {
		for (String key : tileMap.keySet()) {
			ArrayList<String> locData = tileMap.get(key).get("location");
			if (locData == null) break;
			for (String loc : locData) {
				Integer[] coords = StringUtilsRead.getCoords(loc);
				Square pos = new Square (coords[0], coords[1]);
				Tile t = stringToTile (key);
				maze.placeEntity(pos.getY(), pos.getX(), t);
			}
		}
		return maze;
	}

	private static Maze placeItems(LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> itemMap,
			Maze maze) {
		for (String key : itemMap.keySet()) {
			ArrayList<String> locData = itemMap.get(key).get("location");
			if (locData == null) break;
			for (String loc : locData) {
				Integer[] coords = StringUtilsRead.getCoords(loc);
				Square pos = new Square (coords[0], coords[1]);
				Item e = stringToItem (key);
				maze.placeEntity(pos.getY(), pos.getX(), e);
			}
		}
		return maze;
	}

	private static Maze placeSolidEntities(LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> entityMap, Maze maze) {
		for (String key : entityMap.keySet()) {
			ArrayList<String> locData = entityMap.get(key).get("location");
			if (locData == null) break;
			for (String loc : locData) {
				Integer[] coords = StringUtilsRead.getCoords(loc);
				Square pos = new Square (coords[0], coords[1]);
				SolidEntity e = stringToSolidEntity (key);
				maze.placeEntity(pos.getY(), pos.getX(), e);
			}
		}
		
		return maze;
	}

	/**
	 * Takes in a hashMap representing the maze and creates a Maze from it
	 * @param 
	 * @return
	 */
	private static Maze createMazeFromHashMap(LinkedHashMap<String, ArrayList<String>> mapInfo) {
		ArrayList<String> dims = mapInfo.get("dims");
		Integer[] coords = StringUtilsRead.getCoords(dims.get(0));
		Maze maze = new Maze (coords[0], coords[1]);
		
		ArrayList<String> start = mapInfo.get("start");
		coords = StringUtilsRead.getCoords(start.get(0));
		maze.setStart(coords[0], coords[1]);
		
		return maze;
	}
	
	/**
	 * Creates a level from a maze and a list of completion objectives
	 * @param maze
	 * @param compObj
	 * @return
	 */
	public static Level giveCompObj (Maze maze, ArrayList<String> compObj) {
		boolean treasure = false;
		boolean switches = false;
		boolean enemies = false;
		
		if (compObj != null) {
			treasure = compObj.contains("treasure");
			switches = compObj.contains("switch");
			enemies = compObj.contains("enemies");
		}
		
		Level lvl = new SimpleLevel.LevelBuilder(maze).collectTreasure(treasure)
				.triggerSwitches(switches).defeatEnemies(enemies).build();
		return lvl;
	}
	
	private static SolidEntity stringToSolidEntity(String code) {
		switch (code) {
		case "Wall": return new Wall();
		case "Boulder": return new Boulder();
		case "Hunter": return new Hunter();
		case "Strategist": return new Strategist();
		case "Hound": return new Hound();
		case "Coward": return new Coward();
		case "redDoor": return new Door("red");
		case "yellowDoor": return new Door("yellow");
		case "greenDoor": return new Door("green");
		case "blueDoor": return new Door("blue");
		default:  return null;
		}
	}
	
	private static Item stringToItem(String code) {
		switch (code) {
		case "HoverPotion": return new HoverPotion();
		case "InvincibilityPotion": return new InvincibilityPotion();
		case "Sword": return new Sword();
		case "Treasure": return new Treasure();
		case "UnlitBomb": return new UnlitBomb();
		case "Arrow": return new Arrow();
		case "RedKey": return new Key("red");
		case "YellowKey": return new Key("yellow");
		case "GreenKey": return new Key("green");
		case "BlueKey": return new Key("blue");
		default: return null;
		}
	}
	
	private static Tile stringToTile(String code) {
		switch (code) {
		case "Pit": return new Pit();
		case "FloorSwitch": return new FloorSwitch();
		case "Exit": return new Exit();
		default: return new Path();
		}
	}
}
