package dungeon;

import enemies.*;
import items.*;

public class MazeReader {
	
	public Maze readMaze(SampleMaze sampleMaze) {
		int height = sampleMaze.getHeight();
		int width = sampleMaze.getWidth();
		Maze maze = new Maze(height, width);
		
		char[][] tiles = sampleMaze.getTiles();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (tiles[row][col] == 'S') {
					maze.setStart(row, col);
				} else {
					maze.setTile(row, col, charToTile(tiles[row][col],
							                          row, col));
				}
			}
		}
		
		char[][] entities = sampleMaze.getEntities();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (entities[row][col] != ' ') {
					Tile t = maze.getTile(row, col);
					maze.addEntity(charToEntity(entities[row][col], t));
				}
			}
		}
		
		char[][] items = sampleMaze.getItems();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (items[row][col] != ' ') {
					maze.addItem(row, col, charToItem(items[row][col]));
				}
			}
		}
		
		return maze;
	}
	
	private Tile charToTile(char code, int row, int col) {
		switch (code) {
		case 'P': return new Pit(row, col);
		case 'F': return new FloorSwitch(row, col);
		case 'E': return new Exit(row, col);
		default:  return new Path(row, col);
		}
	}
	
	private Entity charToEntity(char code, Tile t) {
		switch (code) {
		case 'W': return new Wall(t);
		case 'O': return new Boulder(t);
		case 'H': return new Hunter(t);
		case 'S': return new Strategist(t);
		case 'U': return new Hound(t);
		case 'C': return new Coward(t);
		case 'R': return new Door(t, "red");
		case 'Y': return new Door(t, "yellow");
		case 'G': return new Door(t, "green");
		case 'B': return new Door(t, "blue");
		}
		return null;
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
		}
		return null;
	}
	
}
