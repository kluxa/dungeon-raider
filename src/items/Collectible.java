package items;

import player.Player;

public abstract class Collectible extends Item {
	
	public Collectible() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pickUp(Player player) {
		player.pickUp(this);
	}
	
}
