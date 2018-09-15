package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class WallFactory extends NonLivingEntityFactory {

	@Override
	public Entity createEntity() {
		return new Wall();
	}

}
