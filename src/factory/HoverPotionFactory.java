package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class HoverPotionFactory extends PotionFactory {

	@Override
	public Entity createEntity() {
		return new HoverPotion();
	}

}
