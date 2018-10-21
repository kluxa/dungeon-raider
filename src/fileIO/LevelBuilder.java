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
		Maze maze = createMazeFromHashMap((LinkedHashMap<String, ArrayList<String>>) mazeInfo.get("Map"));
		maze = placeSolidEntities((LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>) mazeInfo.get("SolidEntities"), maze);
		maze = placeItems((LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>) mazeInfo.get("Items"), maze);
		maze = placeTiles((LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>) mazeInfo.get("TileEntities"), maze);
		
		ArrayList<String> obj = ((LinkedHashMap<String, ArrayList<String>>) mazeInfo.get("Map")).get("completion");
		
		Level lvl = giveCompObj(maze, obj);
		
		maze.toString();
		
		return lvl;
	}

	/**
	 * Places tiles on map
	 * @param tileMap
	 * @param maze
	 * @return
	 */
	private static Maze placeTiles(LinkedHashMap<String,
			                       LinkedHashMap<String, ArrayList<String>>> tileMap,
			                       Maze maze) {
		for (String key: tileMap.keySet()) {
			ArrayList<String> locData = tileMap.get(key).get("location");
			if (locData == null) break;
			for (String loc : locData) {
				Tile t = stringToTile(key);
				Integer[] coords = StringUtilsRead.getCoords(loc);
				maze.placeEntity(coords[0], coords[1], t);
			}
		}
		return maze;
	}

	/**
	 * Places items on the map
	 * @param itemMap
	 * @param maze
	 * @return
	 */
	private static Maze placeItems(LinkedHashMap<String,
			                       LinkedHashMap<String, ArrayList<String>>> itemMap,
			                       Maze maze) {
		for (String key: itemMap.keySet()) {
			ArrayList<String> locData = itemMap.get(key).get("location");
			if (locData == null) break;
			for (String loc: locData) {
				Item e = stringToItem (key);
				Integer[] coords = StringUtilsRead.getCoords(loc);
				maze.placeEntity(coords[0], coords[1], e);
			}
		}
		return maze;
	}

	/**
	 * Places solidEntities on the map from hashmap
	 * @param entityMap
	 * @param maze
	 * @return
	 */
	private static Maze placeSolidEntities(LinkedHashMap<String,
			                               LinkedHashMap<String, ArrayList<String>>> entityMap,
			                               Maze maze) {
		for (String key: entityMap.keySet()) {
			ArrayList<String> locData = entityMap.get(key).get("location");
			if (locData == null) break;
			for (String loc: locData) {
				SolidEntity e = stringToSolidEntity(key);
				Integer[] coords = StringUtilsRead.getCoords(loc);
				maze.placeEntity(coords[0], coords[1], e);
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
		System.out.println(dims.toString());
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
		SimpleLevel.LevelBuilder builder = new SimpleLevel.LevelBuilder(maze);
		
		if (compObj != null) {
			builder = builder.collectTreasure(compObj.contains("treasure"))
					         .triggerSwitches(compObj.contains("switches"))
					         .defeatEnemies(compObj.contains("enemies"));
		}
		
		return builder.build();
	}
	
	private static SolidEntity stringToSolidEntity(String code) {
		switch (code) {
		case "Wall": return new Wall();
		case "Boulder": return new Boulder();
		case "Hunter": return new Hunter();
		case "Strategist": return new Strategist();
		case "Hound": return new Hound();
		case "Coward": return new Coward();
		case "RedDoor": return new Door("red");
		case "YellowDoor": return new Door("yellow");
		case "GreenDoor": return new Door("green");
		case "BlueDoor": return new Door("blue");
		default: return null;
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
		case "RedTorch": return new RedTorch();
		case "YellowTorch": return new YellowTorch();
		case "BlueTorch": return new BlueTorch();
		default: return null;
		}
	}
	
	private static Tile stringToTile(String code) {
		switch (code) {
		case "Path": return new Path();
		case "Pit": return new Pit();
		case "FloorSwitch": return new FloorSwitch();
		case "Exit": return new Exit();
		default: return null;
		}
	}
}
