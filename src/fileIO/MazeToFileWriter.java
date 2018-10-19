package fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import dungeon.*;
import game.*;

public class MazeToFileWriter {

	public static void writeMazeToFile (String fileLoc, Level level) {
		BufferedReader reader = null; 
	    try {
	    	File file = new File(fileLoc);
	        reader = new BufferedReader(new FileReader(file));
	        
	        String line;
	        ArrayList<String> allLines = new ArrayList<String>();
	        while ((line = reader.readLine()) != null) {
	        	allLines.add(line);
	        }
	        
	        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	public static void main (String[] args) {
		Level lvl = LevelBuilder.makeLevel("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testDung.txt");
		writeMazeToFile ("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testWrite.txt", lvl);
	}
}
