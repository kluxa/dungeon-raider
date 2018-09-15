package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class PathFactory extends TileFactory {

	@Override
	public Entity createEntity() {
		return new Path();
	}

}
