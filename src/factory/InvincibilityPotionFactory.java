package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class InvincibilityPotionFactory extends PotionFactory {

	@Override
	public Entity createEntity() {
		return new InvincibilityPotion();
	}

}
