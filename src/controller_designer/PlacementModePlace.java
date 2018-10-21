package controller_designer;

import factory.*;
import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class PlacementModePlace extends PlacementMode {
	private EntityFactory factory;
	
	public PlacementModePlace(LevelDesignerMainController controller,
			                  EntityFactory factory) {
		super(controller);
		this.factory = factory;
	}
	
	@Override
	public void select() {
		Entity e = factory.createEntity();
		getMaze().placeEntity(controller.getCursorY(),
				              controller.getCursorX(),
				              e);
	}
	
	@Override
	public void cancel() {
		quit();
	}
	
}
