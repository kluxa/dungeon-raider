package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class ExitFactory extends TileFactory {

	@Override
	public Entity createEntity() {
		return new Exit();
	}

}
