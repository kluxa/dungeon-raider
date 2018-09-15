package designer;

import factory.*;
import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class PlacementModePlace extends PlacementMode {
	private EntityFactory factory;
	
	public PlacementModePlace(LevelDesigner levelDesigner,
			                  EntityFactory factory) {
		super(levelDesigner);
		this.factory = factory;
	}
	
	@Override
	public void select() {
		Entity e = factory.createEntity();
		System.out.println(e.getClass().getSimpleName());
		getMaze().placeEntity(getCursorY(),
				              getCursorX(),
				              factory.createEntity());
		System.out.println("Something was placed?");
	}
	
	@Override
	public void cancel() {
		quit();
	}
	
}
