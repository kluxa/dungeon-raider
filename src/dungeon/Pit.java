package dungeon;

public class Pit extends Tile {
	
	public Pit(int y, int x) {
		super(y, x);
	}
	
	@Override
	public void arrive(Entity entity) {
		super.arrive(entity);
		entity.fall();
	}
	
	@Override
	public void depart(Entity entity) {
		// Do nothing
	}

	@Override
	public char toChar() {
		return 'P';
	}
}
