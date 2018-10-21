package fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import dungeon.*;
import game.*;

public class MazeToFileWriter {

	/**
	 * Writes maze to fileLoc from level
	 * @param fileLoc
	 * @param level
	 */
	public static void writeMazeToFile (String fileLoc, Level level) {
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> fileMap = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>();
		
		fileMap = StringUtilsWrite.hasapafyEnts (fileMap, level);
		fileMap = StringUtilsWrite.hasapafyItems(fileMap, level);
		fileMap = StringUtilsWrite.hasapafyTiles(fileMap, level);
		
		LinkedHashMap<String, ArrayList<String>> mazeMap = StringUtilsWrite.hasapafyMaze(level);
	    mazeMap = StringUtilsWrite.hasapafyMaze(level);
	        
	    writeMazeFile (fileMap, mazeMap, fileLoc);    
	}

	private static void writeMazeFile (LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> fileMap, LinkedHashMap<String, ArrayList<String>> mazeMap, String fileLoc) {
		ArrayList<String> fileStrings = new ArrayList<String>();
		fileStrings.add("{");
		fileStrings.addAll(formatMazeMap(mazeMap));
		fileStrings.addAll(formatNonUnique(fileMap));
		fileStrings.add("}");
		
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileLoc));
		    for (String line : fileStrings) {
		    	writer.write(line + "\n");
		    }
		    
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	/**
	 * Creates a list of strings representing a maze in our JSON format
	 * @param mazeMap
	 * @return
	 */
	private static ArrayList<String> formatMazeMap (LinkedHashMap<String, ArrayList<String>> mazeMap) {
		ArrayList<String> mazeMapRep = new ArrayList<String>();
		mazeMapRep.add("map: {");
		mazeMapRep.add("dims: [");
		mazeMapRep.add(mazeMap.get("dims").get(0));
		mazeMapRep.add("]");
		mazeMapRep.add("start: [");
		mazeMapRep.add(mazeMap.get("start").get(0));
		mazeMapRep.add("]");
		mazeMapRep.add("completion: [");
		if (mazeMap.get("completion") != null) {
			int numObjectives = mazeMap.get("completion").size();
			for (int i = 0; i < numObjectives; i++) {
				mazeMapRep.add(mazeMap.get("completion").get(i) +
						      (i == numObjectives - 1 ? "" : ","));
			}
		}
		mazeMapRep.add("]");
		mazeMapRep.add("}");
		
		return mazeMapRep;
	}
	
	/**
	 * Takes in a linked hash map and formats it according to solidents, items or tiles
	 * @return
	 */
	private static ArrayList<String> formatNonUnique (LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> nonUniqMap) {
		ArrayList<String> nonUniqRep = new ArrayList<String>();
		Set<String> bigKeys = nonUniqMap.keySet();
		
		for (String bigKey : bigKeys) {
			nonUniqRep.add(bigKey + ": {");
			LinkedHashMap<String, ArrayList<String>> specMap = nonUniqMap.get(bigKey);
			Set<String> smallKeys = specMap.keySet();
			for (String smallKey : smallKeys) {
				nonUniqRep.add(smallKey + ": {");
				nonUniqRep.add("location: [");
				nonUniqRep.addAll(specMap.get(smallKey));
				nonUniqRep.add("]");
				nonUniqRep.add("}");
			}		
			nonUniqRep.add("}");
		}
	
		return nonUniqRep;
	}
	
	/*
	public static void main (String[] args) {
		Level lvl1 = LevelBuilder.makeLevel("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testDung.txt");
		writeMazeToFile ("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\out.txt", lvl1);
		Level lvl2 = LevelBuilder.makeLevel("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\out.txt");
	}
	*/
	
}
