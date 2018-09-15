package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class UnlitBombFactory extends UnlimitedCollectibleFactory {

	@Override
	public Entity createEntity() {
		return new UnlitBomb();
	}

}
