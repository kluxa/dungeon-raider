package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Path extends Tile {
	
	@Override
	public void arrive(SolidEntity e) {
		// Do nothing
	}
	
	@Override
	public void depart(SolidEntity e) {
		// Do nothing
	}
	
	@Override
	public char toChar() {
		return ' ';
	}
	
}
