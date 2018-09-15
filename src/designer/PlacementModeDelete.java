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
	
	public PlacementModeDelete(LevelDesigner levelDesigner) {
		super(levelDesigner);
	}

	@Override
	public void select() {
		delete();
		System.out.println("Something was deleted");
	}

	@Override
	public void cancel() {
		quit();
	}
}
