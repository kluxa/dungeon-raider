package dungeon;

public class FloorSwitch extends Tile {
	private boolean isTriggered;
	
	public FloorSwitch(int y, int x) {
		super(y, x);
		this.isTriggered = false;
	}
	
	@Override
	public void arrive(Entity entity) {
		super.arrive(entity);
		// TODO Auto-generated method stub

	}

	@Override
	public void depart(Entity entity) {
		// TODO Auto-generated method stub

	}
	
	public boolean isTriggered() {
		return this.isTriggered;
	}
	
}
