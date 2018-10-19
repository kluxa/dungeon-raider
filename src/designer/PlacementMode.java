package designer;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public abstract class PlacementMode {
	protected LevelDesignerController controller;	
	private Maze maze;
	
	public PlacementMode(LevelDesignerController controller) {
		this.controller = controller;
		this.maze = controller.getMaze();
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public abstract void select();
	
	public void delete() {
		maze.clearTile(controller.getCursorY(),
				       controller.getCursorX());
	}
	
	public abstract void cancel();
	
	public void quit() {
		controller.switchToSelectionMode();
	}
}
