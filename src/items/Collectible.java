package items;

import dungeon.*;

public abstract class Collectible extends Item {
	
	public Collectible() {
		// TODO Auto-generated constructor stub
	}
	
	public void pickUp(Player player) {
		player.pickUp(this);
	}
	
}
