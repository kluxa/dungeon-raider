package designer;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class LevelDesigner implements DesignerMode {
	
	public final static int MAZE_HEIGHT = 15;
	public final static int MAZE_WIDTH  = 15;
	
	private DesignerMode selectionMode;	// Selection Mode
	private DesignerMode mode;
	private Maze maze;
	private boolean hasExited;
	
	public LevelDesigner() {
		maze = new Maze(MAZE_HEIGHT, MAZE_WIDTH);
		selectionMode = new SelectionMode(this);
		mode = selectionMode;
		hasExited = false;
	}
	
	public void quit() {
		hasExited = true;
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public boolean keepRunning() {
		return !hasExited;
	}
	
	public void setMode(DesignerMode mode) {
		this.mode = mode;
	}
	
	public DesignerMode getSelectionMode() {
		return this.selectionMode;
	}
	
	@Override
	public void moveCursorUp() {
		mode.moveCursorUp();
	}

	@Override
	public void moveCursorLeft() {
		mode.moveCursorLeft();
	}

	@Override
	public void moveCursorDown() {
		mode.moveCursorDown();
	}

	@Override
	public void moveCursorRight() {
		mode.moveCursorRight();
	}

	@Override
	public void select() {
		mode.select();
	}

	@Override
	public void cancel() {
		mode.cancel();
	}
	
	@Override
	public void delete() {
		mode.delete();
	}
	
}
