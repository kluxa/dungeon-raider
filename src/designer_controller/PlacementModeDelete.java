package designer_controller;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

/**
 * 
 * @author Kevin
 * In this mode, the user deletes entities by selecting a tile
 */
public class PlacementModeDelete extends PlacementMode {
	
	public PlacementModeDelete(LevelDesignerMainController controller) {
		super(controller);
	}
	
	@Override
	public void select() {
		delete();
	}
	
	@Override
	public void cancel() {
		quit();
	}
}
