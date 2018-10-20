package designer_controller;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public abstract class PlacementMode {
	protected LevelDesignerMainController controller;	
	private Maze maze;
	
	public PlacementMode(LevelDesignerMainController controller) {
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
