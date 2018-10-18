package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Path extends Tile {
	
	@Override
	public char toChar() {
		return ' ';
	}

	@Override
	public String getImageName() {
		return "path";
	}
	
}
