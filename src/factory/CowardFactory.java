package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class CowardFactory extends EnemyFactory {

	@Override
	public Entity createEntity() {
		return new Coward();
	}

}
