package factory;

import dungeon.Entity;
import items.BlueTorch;

public class BlueTorchFactory extends TorchFactory {
	
	@Override
	public Entity createEntity() {
		return new BlueTorch();
	}
	
}
