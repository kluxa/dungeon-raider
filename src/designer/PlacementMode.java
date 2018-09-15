package designer;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public abstract class PlacementMode implements DesignerMode {
	
	private LevelDesigner levelDesigner;
	private Maze maze;
	private int cursorY;
	private int cursorX;
	
	public PlacementMode(LevelDesigner levelDesigner) {
		System.out.println("Entering placement mode...");
		this.levelDesigner = levelDesigner;
		maze = levelDesigner.getMaze();
		
		cursorY = LevelDesigner.MAZE_HEIGHT / 2;
		cursorX = LevelDesigner.MAZE_WIDTH  / 2;
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public int getCursorY() {
		return cursorY;
	}
	
	public int getCursorX() {
		return cursorX;
	}
	
	@Override
	public void moveCursorUp() {
		cursorY--;
		if (cursorY < 0) {
			cursorY += LevelDesigner.MAZE_HEIGHT;
		}
	}

	@Override
	public void moveCursorLeft() {
		cursorX--;
		if (cursorX < 0) {
			cursorX += LevelDesigner.MAZE_WIDTH;
		}
	}

	@Override
	public void moveCursorDown() {
		cursorY = (cursorY + 1) % LevelDesigner.MAZE_HEIGHT;
	}

	@Override
	public void moveCursorRight() {
		cursorX = (cursorX + 1) % LevelDesigner.MAZE_WIDTH;
	}
	
	@Override
	public void delete() {
		getMaze().clearTile(getCursorY(), getCursorX());
	}
	
	public void quit() {
		levelDesigner.setMode(levelDesigner.getSelectionMode());
	}
}
