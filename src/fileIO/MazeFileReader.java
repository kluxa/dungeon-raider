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
        Maze newMaze = null;
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
            
            if (!StringUtils.isBalanced(allLines)) {
            	System.out.println("File not parentheses balanced.");
            	throw new IOException();
            }
            
            LinkedHashMap<String, ArrayList<String>> mapMap 
            = StringUtils.hasapafy(allLines, "map");
            LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> nonLivingEntitiesMap 
            = StringUtils.hasapafyNonUnique(allLines, "nonlivingentities");
            LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> livingEntitiesMap 
            = StringUtils.hasapafyNonUnique(allLines, "livingentities");
            LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> itemsMap 
            = StringUtils.hasapafyNonUnique(allLines, "items");
            
            mazeInfo.put("Map", mapMap);
            mazeInfo.put("NonLivingEntities", nonLivingEntitiesMap);
            mazeInfo.put("LivingEntities", livingEntitiesMap);
            mazeInfo.put("Items", itemsMap);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //newMaze.toString();
        return mazeInfo;
    }
}