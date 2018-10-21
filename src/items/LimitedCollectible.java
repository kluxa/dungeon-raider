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
		System.out.println("Hello");
		if (player.numItemsOfType(this) >= carryLimit) {
			System.out.println("Dropping");
			Item i = player.getItemOfType(this);
			player.dropItem(i);
		}
		player.pickUp(this);
	}
	
}
