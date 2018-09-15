package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class KeyFactory extends LimitedCollectibleFactory {

	@Override
	public Entity createEntity() {
		return new Key(getColor());
	}

}
