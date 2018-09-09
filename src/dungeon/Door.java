package dungeon;

public class Door extends NonLivingEntity {
	private boolean isOpen;
	
	public Door(Tile tile) {
		super(tile);
		this.isOpen = false;
		
	}
	
	@Override
	public void collide(Entity entity) {
		// TODO:
		// If the door is closed, collide
		// should do nothing
		// If the door is open, the entity
		// should be able to stand there
	}
	
	@Override
	public void getBlownUp() {
		// Do nothing
	}

	@Override
	public char toChar() {
		return 'D';
	}
}
