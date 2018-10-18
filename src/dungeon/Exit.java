package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Exit extends Tile {
	
	@Override
	public void arrive(SolidEntity e) {
		if (e instanceof Player) {
			Player p = (Player)e;
			p.setOnExit();
		}
	}
	
	@Override
	public char toChar() {
		return 'E';
	}

	@Override
	public String getImageName() {
		return "exit";
	}
	
}
