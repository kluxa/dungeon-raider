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
		// Do nothing
	}

	@Override
	public char toChar() {
		return 'W';
	}
}
