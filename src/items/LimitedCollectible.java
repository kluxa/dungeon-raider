package items;

import player.Player;

public abstract class LimitedCollectible extends Collectible {
	private int carryLimit;
	
	public LimitedCollectible(int limit) {
		super();
		carryLimit = limit;
	}
	
	@Override
	public void pickUp(Player player) {
		if (player.numItemsOfType(this) >= carryLimit) {
			Item i = player.getItemOfType(this);
			player.dropItem(i);
		}
		player.pickUp(this);
	}
	
}
