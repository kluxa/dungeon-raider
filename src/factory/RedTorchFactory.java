package factory;

import dungeon.Entity;
import items.RedTorch;

public class RedTorchFactory extends TorchFactory {

	@Override
	public Entity createEntity() {
		return new RedTorch();
	}

}
