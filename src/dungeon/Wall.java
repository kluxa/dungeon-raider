package dungeon;

public class Wall extends NonLivingEntity {
	
	public Wall(Tile tile) {
		super(tile);
	}
	
	@Override
	public void collide(Entity entity) {
		// Do nothing
	}
	
	@Override
	public void getBlownUp() {
		System.out.println("Walls don't get blown up.");
	}

	@Override
	public char toChar() {
		return 'W';
	}
}
