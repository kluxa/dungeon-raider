package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class Entity {
	
	public boolean sameType(Entity e) {
		if (this == e) return true;
		if (e == null) return false;
		return (this.getClass() == e.getClass());
	}
	
}
