package dungeon;

public class Pit extends Tile {
	
	public Pit(int y, int x) {
		super(y, x);
	}
	
	@Override
	public void arrive(Entity entity) {
		super.arrive(entity);
		// TODO Auto-generated method stub
		// When an entity lands on this
		// square, make them fall
	}
	
	@Override
	public void depart(Entity entity) {
		// Do nothing
	}
	
}
