package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class TreasureFactory extends UnlimitedCollectibleFactory {

	@Override
	public Entity createEntity() {
		return new Treasure();
	}

}
