package factory;

import dungeon.Entity;
import items.YellowTorch;

public class YellowTorchFactory extends TorchFactory {
	
	@Override
	public Entity createEntity() {
		return new YellowTorch();
	}
	
}
