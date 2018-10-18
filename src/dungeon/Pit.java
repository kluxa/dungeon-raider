package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Pit extends Tile {
	
	@Override
	public void arrive(SolidEntity e) {
		e.fall();
	}
	
	@Override
	public char toChar() {
		return 'P';
	}

	@Override
	public String getImageName() {
		return "pit";
	}
	
}
