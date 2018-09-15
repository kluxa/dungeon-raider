package game;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

/**
 * 
 * @author Kevin
 * Loads test mazes
 */
public class MazeLoader {
	
	public Maze readMaze(TestMaze sampleMaze) {
		int height = sampleMaze.getHeight();
		int width = sampleMaze.getWidth();
		Maze maze = new Maze(height, width);
		
		char[][] tiles = sampleMaze.getTiles();
		char[][] entities = sampleMaze.getSolidEntities();
		char[][] items = sampleMaze.getItems();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (tiles[row][col] == 'S') {
					maze.setStart(row, col);
				} else {
					maze.placeEntity(row, col, charToTile(tiles[row][col]));
					maze.placeEntity(row, col, charToSolidEntity(entities[row][col]));
					maze.placeEntity(row, col, charToItem(items[row][col]));
				}
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
		case 'O': return new Boulder();
		case 'H': return new Hunter();
		case 'S': return new Strategist();
		case 'U': return new Hound();
		case 'C': return new Coward();
		case 'R': return new Door("red");
		case 'Y': return new Door("yellow");
		case 'G': return new Door("green");
		case 'B': return new Door("blue");
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
	
}
