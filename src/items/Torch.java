package items;

import player.Player;

public abstract class Torch extends LimitedCollectible {
	private static final int CARRY_LIMIT = 1;
	
	public Torch() {
		super(CARRY_LIMIT);
	}
	
	public void pickUp(Player player) {
		applyEffect(player);
	}
	
	public abstract void applyEffect(Player player);
	
}
