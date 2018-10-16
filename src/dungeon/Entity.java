package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import javafx.scene.image.Image;
import game.*;

public abstract class Entity {
	
	public boolean sameType(Entity e) {
		if (this == e) return true;
		if (e == null) return false;
		return (this.getClass() == e.getClass());
	}
	
	public abstract char toChar();
	
	public abstract String getImageName();
}
