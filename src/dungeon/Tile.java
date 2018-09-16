package dungeon;

import java.util.ArrayList;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class Tile extends Entity {
	
	public void arrive(SolidEntity e) {
		// Do nothing
	}
	
	public void depart(SolidEntity e) {
		// Do nothing
	}
	
}
