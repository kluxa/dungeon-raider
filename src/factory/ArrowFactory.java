package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class ArrowFactory extends UnlimitedCollectibleFactory {

	@Override
	public Entity createEntity() {
		return new Arrow();
	}

}
