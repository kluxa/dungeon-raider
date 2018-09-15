package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class FloorSwitch extends Tile {
	private boolean isTriggered;
	
	public FloorSwitch() {
		this.isTriggered = false;
	}
	
	public boolean isTriggered() {
		return this.isTriggered;
	}
	
	@Override
	public void arrive(SolidEntity e) {
		if (e instanceof Boulder) {
			System.out.println("Triggered!");
			isTriggered = true;
		}
	}
	
	@Override
	public void depart(SolidEntity e) {
		if (e instanceof Boulder) {
			System.out.println("Untriggered!");
			isTriggered = false;
		}
	}
	
	@Override
	public char toChar() {
		return 'F';
	}
	
}
