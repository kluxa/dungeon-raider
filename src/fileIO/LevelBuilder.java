package fileIO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import dungeon.Entity;
import dungeon.Maze;
import dungeon.Square;
import game.Level;

public class LevelBuilder {

	/**
	 * Makes a fully fledged maze from a file
	 * @param fileLoc
	 * @return
	 */
	public static Maze makeMaze (String fileLoc) {
		LinkedHashMap<String, Object> mazeInfo = MazeFileReader.getMazeInfo(fileLoc);
		
		Maze maze = createMazeFromHashMap ((LinkedHashMap<String, ArrayList<String>>) mazeInfo.get("Maze"));
		ArrayList<Entity> livingEntities = getAllLivingEntities ((LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>) mazeInfo.get("LivingEntities"));
		
		return null;
		
	}

	private static ArrayList<Entity> getAllLivingEntities(LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> entityMap) {
		ArrayList<Entity> allLivingEntities = new ArrayList<Entity>();
		for (String key : entityMap.keySet()) {
			ArrayList<String> locData = entityMap.get(key).get("location");
			for (String loc : locData) {
				ArrayList<Integer> coords = StringUtils.getCoords(loc);
				Square pos = new Square (coords.get(0), coords.get(1));
				//TODO create classes using a string name and square
			}
		}
		
		return allLivingEntities;
	}

	/**
	 * Takes in a hashMap representing the maze and creates a Maze from it
	 * @param mapInfo
	 * @return
	 */
	private static Maze createMazeFromHashMap(LinkedHashMap<String, ArrayList<String>> mapInfo) {
		ArrayList<String> dims = mapInfo.get("dims");
		ArrayList<Integer> coords = StringUtils.getCoords(dims.get(0));
		
		Maze maze = new Maze (coords.get(0), coords.get(1));
		
		return maze;
	}
	
	/**
	 * Creates a level from a maze and a list of completion objectives
	 * @param maze
	 * @param compObj
	 * @return
	 */
	public static Level makeLevel (Maze maze, ArrayList<String> compObj) {
		boolean treasure = compObj.contains("treasure");
		boolean switches = compObj.contains("switches");
		boolean enemies = compObj.contains("enemies");
		
		Level lvl = new SimpleLevel.LevelBuilder(maze).collectTreasure(treasure)
				.triggerSwitches(switches).defeatEnemies(enemies).build();
		return lvl;
		
	}
}
