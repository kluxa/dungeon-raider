package designer;

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
	
	public PlacementModeDelete(LevelDesignerController controller) {
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
