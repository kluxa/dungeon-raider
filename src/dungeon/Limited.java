package dungeon;

public abstract class Limited extends Collectible {
	private int carryLimit;
	
	public Limited(int limit) {
		super();
		carryLimit = limit;
	}

	@Override
	public void pickUp(Player player) {
		// TODO Auto-generated method stub

	}
	
}
