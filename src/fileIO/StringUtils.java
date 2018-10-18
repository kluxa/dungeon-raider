package fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;

public class StringUtils {
	
	/**
	 * Gets JSON objects out of the file and puts them in a hashmap
	 * @param allLines
	 * @return
	 */
	public static LinkedHashMap<String, ArrayList<String>> hasapafy(ArrayList<String> allLines, String entityName) {
		int index = getIndexContaining(allLines, entityName);
		//1. Read until }
		//2. when line found with [, create key, add lines to ArrayList until ], add to hashmap
		//3. Repeat from 2
		
		LinkedHashMap<String, ArrayList<String>> simpleMap = new LinkedHashMap<String, ArrayList<String>>();
		for (int i = index; !allLines.get(i).contains("}"); i++) { 	
			if (allLines.get(i).contains("[")) {
				String key = formatLine(allLines.get(i));
				ArrayList<String> keyData = new ArrayList<String>();
				for (int j = i+1; !allLines.get(j).contains("]"); j++) { //i+1 because next line is locData
					String locData = formatLine(allLines.get(j));
					keyData.add(locData);
				}
				simpleMap.put(key, keyData);
			}
		}
		
		return simpleMap;
	}
	
	/**
	 * This hashmapify's non-unique entities (everything but the player and the game)
	 */
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> hasapafyNonUnique (ArrayList<String> allLines, String entityName) {
		int index = getIndexContaining(allLines, entityName);
		
		/*walkthrough:
		* Start at nonlivingentities in testDungeon file
		* 1go down one and make key (bigKey ="Door")
		* 2go down one 
		* 3make key (smallKey = "location")
		* 3make new list of strings for 'data' arrayList
		* 4go down one and add all lines under small key until ] is met
		* 5add this keyvalue pair to the map
		* 6if next line has [, go down one, go to 3
		* else go down 2, go to 1
		* 7go to 3
		*/
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> entityMap = new LinkedHashMap <String, LinkedHashMap<String, ArrayList<String>>>();
		int i = index+1;
		while (!allLines.get(i).contains("}")) {
			String bigKey = formatLine(allLines.get(i)); //wall
			i++;
			
			LinkedHashMap<String, ArrayList<String>> smallMap = new LinkedHashMap<String, ArrayList<String>>();
			while (allLines.get(i).contains("[")) {
				String smallKey = formatLine(allLines.get(i));
				i++;
				ArrayList<String> data = new ArrayList<String>();
				while (!allLines.get(i).contains("]")) {
					data.add(formatLine(allLines.get(i)));
					i++;
				}
				smallMap.put(smallKey, data);
				if (allLines.get(i+1).contains("[")) {
					i++;
				} else {
					i += 2;
				}
			}
			entityMap.put(bigKey, smallMap);
		}
		return entityMap;
	}
	
	/**
	 * Removes ':', '[', ',', '()', '{' and whitespaces from string
	 * @param string
	 * @return
	 */
	private static String formatLine(String string) {
		string = string.trim();
		string = string.replace(":", "");
		string = string.replace("[", "");
		string = string.replace(",", "");
		string = string.replace("(", "");
		string = string.replace(")", "");
		string = string.replace("{", "");
		return string;
	}

	/**
	 * Checks any large number of lines are parentheses balanced
	 * @param input
	 * @return
	 */
	public static boolean isBalanced (ArrayList<String> input) {
		//traverse until stack is empty or incorrect brackets are found
		Stack<Character> stack = new Stack();
		for (String line : input) {
			char[] brackets = line.toCharArray();
			for (char bracket : brackets) {
			    switch (bracket) {
			      case '{':
			      case '(':
			      case '[': 
			    	  stack.push(bracket); break;
			      case '}':
	                    if (stack.isEmpty() || stack.pop() != '{')
	                        return false;
	                    break;
	                case ']':
	                    if (stack.isEmpty() || stack.pop() != '[')
	                        return false;
	                    break;
	                case ')':
	                    if (stack.isEmpty() || stack.pop() != '(')
	                        return false;
	                    break; 
			    }
			}
		}
		return stack.empty();
	}
	
	/**
	 * Goes through an ArrayList and finds the index of the String starting with toFind
	 * @param allLines
	 * @param toFind
	 * @return
	 */
	private static int getIndexContaining(ArrayList<String> allLines, String toFind) {
		for (int i = 0; i < allLines.size(); i++) {
			if (allLines.get(i).contains(toFind)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main (String[] args) throws IOException {
		ArrayList<String> input = new ArrayList<String>();
		input.add("   ] aa");
		input.add(")}");
		
		BufferedReader reader = null;
    	File file = new File("C:\\Users\\Matthew\\eclipse-workspace\\Dungeon\\testDung.txt");
        reader = new BufferedReader(new FileReader(file));
        
        String line;
        ArrayList<String> allLines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
        	allLines.add(line);
        }
    	
        LinkedHashMap<String, ArrayList<String>> player = StringUtils.hasapafy(allLines, "player");
        System.out.println(player.toString());
        
        LinkedHashMap<String, ArrayList<String>> map = StringUtils.hasapafy(allLines, "map");
        System.out.println(map.toString());
        
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> entityMap = new LinkedHashMap <String, LinkedHashMap<String, ArrayList<String>>>();
		entityMap = hasapafyNonUnique (allLines, "nonlivingentities");
		System.out.println(entityMap.toString());
		
	}
}
