package fileIO;

import dungeon.Boulder;
import dungeon.Door;
import dungeon.Exit;
import dungeon.FloorSwitch;
import dungeon.Maze;
import dungeon.Path;
import dungeon.Pit;
import dungeon.SolidEntity;
import dungeon.Tile;
import dungeon.Wall;
import enemies.Coward;
import enemies.Hound;
import enemies.Hunter;
import enemies.Strategist;
import game.*;
import items.Arrow;
import items.HoverPotion;
import items.InvincibilityPotion;
import items.Item;
import items.Key;
import items.Sword;
import items.Treasure;
import items.UnlitBomb;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class MazeFileReader {
	/**
	 * Loads a Maze from a file under a strict format
	 */
	public static LinkedHashMap<String, Object> getMazeInfo(String fileLoc) {
		BufferedReader reader = null;
		LinkedHashMap<String, Object> mazeInfo = new LinkedHashMap<String, Object>();

		try {
			File file = new File(fileLoc);
			reader = new BufferedReader(new FileReader(file));

			String line;
			ArrayList<String> allLines = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {
				allLines.add(line);
			}

			if (!StringUtilsRead.isBalanced(allLines)) {
				System.out.println("File not parentheses balanced.");
				throw new IOException();
			}

			LinkedHashMap<String, ArrayList<String>> mapMap =
					StringUtilsRead.hasapafy(allLines, "map");
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> solidMap =
					StringUtilsRead.hasapafyNonUnique(allLines, "SolidEntities");
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> tileMap  =
					StringUtilsRead.hasapafyNonUnique(allLines, "TileEntities");
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> itemsMap =
					StringUtilsRead.hasapafyNonUnique(allLines, "Items");

			mazeInfo.put("Map", mapMap);
			mazeInfo.put("SolidEntities", solidMap);
			mazeInfo.put("TileEntities", tileMap);
			mazeInfo.put("Items", itemsMap);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// newMaze.toString();
		return mazeInfo;
	}
}