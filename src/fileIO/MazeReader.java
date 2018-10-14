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

public class MazeReader implements FileIOStrat {


	/**
	 * Loads a Maze from a file under a strict format
	 */
	@Override
    public Maze loadMaze(String fileLoc) {
        Maze newMaze = null;
        BufferedReader reader = null;
        
        try {
        	File file = new File(fileLoc);
            reader = new BufferedReader(new FileReader(file));
            
            String line;
            ArrayList<String> allLines = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
            	allLines.add(line);
            }
            
            int[] hw = {0, 0};
            hw = getDims(allLines.get(0));
            newMaze = new Maze(hw[0], hw[1]);
            
            //if H is 7, line 8 contains final line of W&B grid
            newMaze = setWallAndBoulderGrid (newMaze, allLines.subList(1, newMaze.getHeight()+1));
            
            LinkedHashMap<String, ArrayList<Object>> mazeInfo = getMazeInfo(allLines.subList(newMaze.getHeight()+2, allLines.size()));
            
            newMaze = addInfoToMaze (newMaze, mazeInfo);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        newMaze.toString();
        return newMaze;
    }

    private Maze addInfoToMaze(Maze newMaze, LinkedHashMap<String, ArrayList<Object>> mazeInfo) {
		
    	
    	
		return null;
	}

    
    
    
	@Override
    public void saveMaze(String location, Maze maze) {

    }
    
    /**
     * Gets the coordinates of an entity from a file based representation "<Entity> <Y1> <X1>, <Y2> <X2>..."
     * Returns a <String, ArrayList<Object>> HashMap where the Object is some info about the Entity
     * @param lines
     * @return
     */
    private LinkedHashMap<String, ArrayList<Object>> getMazeInfo (List<String> lines) {
    	
    	//Split the list of strings and store in a 2D string array
    	String[][] splitted = splitStrings (lines);
    	LinkedHashMap<String, ArrayList<Object>> mazeInfo = new LinkedHashMap<String, ArrayList<Object>>();
    	
    	for (int i = 0; i < splitted.length; i++) {
    		if (splitted[i] != null) {
    			ArrayList<Object> info = new ArrayList<Object>();
    			for (int j = 1; j < splitted[i].length; j++) {
    				info.add(splitted[i][j]);
    			}
    			mazeInfo.put(splitted[i][0], info);
    		}
    	}

    	System.out.println(mazeInfo.toString());
    	return mazeInfo;
    	
    }
    
    private String[][] splitStrings (List<String> lines) {
    	String[][] splitted = new String[lines.size()][]; //if lots of comments this will be much larger than needed
    	
    	for (int i = 0; i < lines.size(); i++) {
    		String line = lines.get(i);
    		if (line.charAt(0) == '#') {
    			break;
    		} else {
    			line.trim();
    			line.replaceAll(",", ""); //unnecessary responsibility, possible refactoring
    			splitted[i] = line.split(" ");
    		}
    	}
    	return splitted;
    }
    
    private int[] getDims (String line) {
    	String[] splitted = line.split(" ");
    	int[] hw = {0, 0};
    	hw[0] = Integer.parseInt(splitted[0]); //h
    	hw[1] = Integer.parseInt(splitted[1]); //w
    	return hw;
    }

    /**
     * Sets the walls and boulders according to the file.
     * @param maze
     * @param list
     * @return
     */
    private Maze setWallAndBoulderGrid (Maze maze, List<String> list) {
    	for (int i = 0; i < maze.getHeight(); i++) {
    		String rep = list.get(i);
    		for (int j = 0; j < maze.getWidth(); j++) {
    			maze.placeEntity(i, j, charToSolidEntity(rep.charAt(j)));
    		}
    	}
    	return maze;
    }
    
    
    private Tile charToTile(char code) {
		switch (code) {
		case 'P': return new Pit();
		case 'F': return new FloorSwitch();
		case 'E': return new Exit();
		default:  return new Path();
		}
	}
	
	private SolidEntity charToSolidEntity(char code) {
		switch (code) {
		case 'W': return new Wall();
		case 'B': return new Boulder();
		case 'H': return new Hunter();
		case 'S': return new Strategist();
		case 'U': return new Hound();
		case 'C': return new Coward();
		case 'R': return new Door("red");
		case 'Y': return new Door("yellow");
		case 'G': return new Door("green");
		case 'b': return new Door("blue");
		default:  return null;
		}
	}
	
	private Item charToItem(char code) {
		switch (code) {
		case 'H': return new HoverPotion();
		case 'I': return new InvincibilityPotion();
		case 'S': return new Sword();
		case 'T': return new Treasure();
		case 'B': return new UnlitBomb();
		case 'A': return new Arrow();
		case 'r': return new Key("red");
		case 'y': return new Key("yellow");
		case 'g': return new Key("green");
		case 'b': return new Key("blue");
		default:  return null;
		}
	}
	
	
    public static void main (String[] args) throws IOException {
    	MazeReader reader = new MazeReader();
    	Maze newMaze = reader.loadMaze("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testDung.txt");
    	
    	System.out.println(newMaze.toString());
    }
}