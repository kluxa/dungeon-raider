package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class SwordFactory extends LimitedCollectibleFactory {

	@Override
	public Entity createEntity() {
		return new Sword();
	}

}
