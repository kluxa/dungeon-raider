package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class StrategistFactory extends EnemyFactory {

	@Override
	public Entity createEntity() {
		return new Strategist();
	}

}
